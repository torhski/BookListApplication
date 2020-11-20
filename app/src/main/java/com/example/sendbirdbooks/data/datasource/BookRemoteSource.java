package com.example.sendbirdbooks.data.datasource;

import com.example.sendbirdbooks.data.Api;
import com.example.sendbirdbooks.data.model.BookDataEntity;
import com.example.sendbirdbooks.domain.callback.IDataCallback;
import com.example.sendbirdbooks.util.LogUtil;
import com.example.sendbirdbooks.util.Mapper;
import com.example.sendbirdbooks.util.StringUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BookRemoteSource {
    private static class BookRemoteSourceHolder {
        private static BookRemoteSource instance = new BookRemoteSource();
    }

    public static BookRemoteSource getInstance() {
        return BookRemoteSourceHolder.instance;
    }

    private BookDataEntity mLastEnitity;

    public void fetchData(String isbn13, IDataCallback callback) {
        if (mLastEnitity != null && StringUtil.equals(mLastEnitity.isbn13, isbn13)) {
            callback.loadBookData(Mapper.entityToData(mLastEnitity));
            return;
        }

        Api.fetchDetailData(isbn13)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        res -> {
                            mLastEnitity = res;
                            callback.loadBookData(Mapper.entityToData(res));
                        },
                        err -> LogUtil.d(err.toString())
                );
    }
}
