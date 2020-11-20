package com.example.sendbirdbooks.domain.callback;

import com.example.sendbirdbooks.domain.model.BookData;

public interface IDataCallback {
    void loadBookData(BookData data);
}
