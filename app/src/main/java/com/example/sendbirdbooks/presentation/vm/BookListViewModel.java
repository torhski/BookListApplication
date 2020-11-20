package com.example.sendbirdbooks.presentation.vm;

import android.util.Log;

import com.example.sendbirdbooks.data.Api;
import com.example.sendbirdbooks.data.datasource.BookLocalSource;
import com.example.sendbirdbooks.data.datasource.BookRemoteSource;
import com.example.sendbirdbooks.domain.model.BookData;
import com.example.sendbirdbooks.domain.repository.BookRepository;
import com.example.sendbirdbooks.presentation.common.INextListener;
import com.example.sendbirdbooks.util.LogUtil;
import com.example.sendbirdbooks.util.Mapper;
import com.example.sendbirdbooks.util.StringUtil;

import java.util.ArrayList;
import java.util.HashSet;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BookListViewModel extends BaseViewModel {

    private MutableLiveData<ArrayList<BookData>> mBookList = new MutableLiveData(new ArrayList<>());

    public LiveData<ArrayList<BookData>> getData() {
        return mBookList;
    }

    private MutableLiveData<HashSet<String>> removeSet = new MutableLiveData(new HashSet<String>());

    public LiveData<HashSet<String>> getRemoveSet() {
        return removeSet;
    }

    private BookRepository repository = new BookRepository(BookLocalSource.getInstance(), BookRemoteSource.getInstance());

    // fetch
    public void fetchNewData() {
        addDisposable(
                Api.fetchNewData()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(result -> {
                            if (result != null) {
                                mBookList.postValue(Mapper.mapToDataList(result.books));
                            }
                        }, error -> {
                            Log.d("Err: ", error.toString());
                        })

        );
    }

    public void loadHistory(int page, String sortField) {
        ArrayList<BookData> list;

        ArrayList<BookData> newList = repository.loadHistory(page, sortField);
        if (page > 1) {
            list = mBookList.getValue();
            list.addAll(newList);
        } else {
            list = newList;
        }

        mBookList.postValue(list);
    }

    public void loadBookmark(int page, String sortField) {
        ArrayList<BookData> list;

        ArrayList<BookData> newList = repository.loadBookmark(page, sortField);
        if (page > 1) {
            list = mBookList.getValue();
            list.addAll(newList);
        } else {
            list = newList;
        }

        mBookList.postValue(list);
    }

    // delete local db
    public void deleteBooks(String type, INextListener listener) {
        ArrayList<String> ids = new ArrayList<>(getRemoveSet().getValue());
        repository.deleteBooks(ids, type, s -> {
            deleteLiveDataBooks(ids);
            clearRemoveSet();
            listener.onNext(s);
        });
    }

    /**
     * only called when updated list after removed bookmark in detail activity
     * 상세페이지에서 북마크 삭제됨
     *
     * @param id
     */
    public void updateBookmarkList(String id) {
        ArrayList list = new ArrayList<String>();
        list.add(id);
        deleteLiveDataBooks(list);
    }

    /**
     * LivedData 내 id 목록 지우기
     *
     * @param ids
     */
    private void deleteLiveDataBooks(ArrayList<String> ids) {
        Observable.fromArray(mBookList.getValue())
                .flatMap(Observable::fromIterable)
                .filter(data -> !ids.contains(data.isbn13))
                .toList()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(res -> {
                    mBookList.postValue((ArrayList<BookData>) res);
                }, err -> {
                    LogUtil.d("err: " + err);
                });
    }

    /**
     * 해당 isbn13 data를 앞으로 보내기.. 히스토리용
     *
     * @param id
     */
    public void updateLiveDataBooks(String id) {
        ArrayList<BookData> list = mBookList.getValue();
        Observable.fromArray(list)
                .flatMap(Observable::fromIterable)
                .filter(data -> StringUtil.equals(id, data.isbn13))
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(updated -> {
                    list.remove(updated);
                    list.add(0, updated);
                    mBookList.postValue(list);
                }, err -> {
                    LogUtil.d("err: " + err);
                });
    }

    public void toggleRemoveItem(String isbn13) {
        if (isExistRemoveList(isbn13)) {
            deleteRemoveList(isbn13);
        } else {
            addRemoveList(isbn13);
        }
    }

    public boolean isExistRemoveList(String isbn13) {
        return removeSet.getValue().contains(isbn13);
    }

    private void addRemoveList(String isbn13) {
        HashSet<String> set = removeSet.getValue();
        set.add(isbn13);
        removeSet.postValue(set);
    }

    private void deleteRemoveList(String isbn13) {
        HashSet<String> set = removeSet.getValue();
        set.remove(isbn13);
        removeSet.postValue(set);
    }

    public void clearRemoveSet() {
        HashSet<String> set = removeSet.getValue();
        set.clear();
        removeSet.postValue(set);
    }

    public boolean isExistRemovable() {
        return removeSet.getValue().size() > 0;
    }
}