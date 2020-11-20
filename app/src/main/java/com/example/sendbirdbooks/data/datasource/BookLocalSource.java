package com.example.sendbirdbooks.data.datasource;

import com.example.sendbirdbooks.data.model.BookDataEntity;
import com.example.sendbirdbooks.data.realm.Const;
import com.example.sendbirdbooks.data.realm.RealmManager;
import com.example.sendbirdbooks.presentation.common.INextListener;
import com.example.sendbirdbooks.util.StringUtil;

import java.util.ArrayList;

public class BookLocalSource {

    private BookDataEntity mLastData;

    private static class BookLocalSourceHolder {
        private static BookLocalSource instance = new BookLocalSource();
    }

    public static BookLocalSource getInstance() {
        return BookLocalSourceHolder.instance;
    }

    public BookDataEntity loadData(String isbn13) {
        if (mLastData != null && StringUtil.equals(isbn13, mLastData.isbn13)) {
            return mLastData;
        }

        BookDataEntity data = RealmManager.loadHistorybyId(isbn13);
        mLastData = data;

        return data;
    }

    public void saveData(BookDataEntity entity, String type) {
        RealmManager.saveBook(entity, type);
    }

    // toggle (save / delete) bookmark
    public void toggleBookmark(BookDataEntity entity) {
        RealmManager.toggleBookmark(entity);
    }

    // check bookmark
    public boolean checkBookmark(String id) {
        return RealmManager.checkBookmark(id);
    }

    // load list
    public ArrayList<BookDataEntity> loadBookList(String type, int page, String sortField) {
        if (StringUtil.equals(type, Const.TYPE_HISTORY)) {
            return loadHistories(page, sortField);
        } else {
            return loadBookmarks(page, sortField);
        }
    }

    private ArrayList<BookDataEntity> loadBookmarks(int page, String sortField) {
        return (ArrayList<BookDataEntity>) RealmManager.loadBookmarkList(page, sortField);
    }

    private ArrayList<BookDataEntity> loadHistories(int page, String sortField) {
        return (ArrayList<BookDataEntity>) RealmManager.loadHistoryList(page, sortField);
    }

    public void deleteBooks(ArrayList<String> ids, String type, INextListener listener) {
        if (StringUtil.equals(type, Const.TYPE_HISTORY)) {
            deleteHistories(ids, listener);
        } else {
            deleteBookmarks(ids, listener);
        }
    }

    // delete
    private void deleteBookmarks(ArrayList<String> ids, INextListener listener) {
        RealmManager.deleteBookmarks(ids, listener);
    }

    private void deleteHistories(ArrayList<String> ids, INextListener listener) {
        RealmManager.deleteHistories(ids, listener);
    }
}
