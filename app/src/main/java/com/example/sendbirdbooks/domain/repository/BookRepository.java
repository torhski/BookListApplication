package com.example.sendbirdbooks.domain.repository;

import com.example.sendbirdbooks.data.datasource.BookLocalSource;
import com.example.sendbirdbooks.data.datasource.BookRemoteSource;
import com.example.sendbirdbooks.data.model.BookDataEntity;
import com.example.sendbirdbooks.data.realm.Const;
import com.example.sendbirdbooks.domain.callback.IDataCallback;
import com.example.sendbirdbooks.domain.model.BookData;
import com.example.sendbirdbooks.presentation.common.INextListener;
import com.example.sendbirdbooks.util.Mapper;

import java.util.ArrayList;

public class BookRepository {

    private final BookLocalSource localSource;
    private final BookRemoteSource remoteSource;

    public BookRepository(BookLocalSource localSource, BookRemoteSource remoteSource) {
        this.localSource = localSource;
        this.remoteSource = remoteSource;
    }

    public void saveHistory(BookData data) {
        localSource.saveData(Mapper.dataToEntity(data), Const.TYPE_HISTORY);
    }

    public void loadBookInfo(String isbn13, IDataCallback callback) {
        BookDataEntity entity = localSource.loadData(isbn13);

        if (entity == null) {
            // network
            remoteSource.fetchData(isbn13, callback);
        } else {
            callback.loadBookData(Mapper.entityToData(entity));
        }
    }

    public ArrayList<BookData> loadHistory(int page, String sortField) {
        return Mapper.mapToDataList(localSource.loadBookList(Const.TYPE_HISTORY, page, sortField));
    }

    public void toggleBookmark(BookData data) {
        localSource.toggleBookmark(Mapper.dataToEntity(data));
    }

    public ArrayList<BookData> loadBookmark(int page, String sortField) {
        return Mapper.mapToDataList(localSource.loadBookList(Const.TYPE_BOOKMARK, page, sortField));
    }

    // check exist
    public boolean checkBookmark(String id) {
        return localSource.checkBookmark(id);
    }

    // delete
    public void deleteBooks(ArrayList<String> ids, String type, INextListener listener) {
        localSource.deleteBooks(ids, type, listener);
    }
}
