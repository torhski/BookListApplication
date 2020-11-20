package com.example.sendbirdbooks.presentation.vm;

import com.example.sendbirdbooks.data.datasource.BookLocalSource;
import com.example.sendbirdbooks.data.datasource.BookRemoteSource;
import com.example.sendbirdbooks.domain.model.BookData;
import com.example.sendbirdbooks.domain.repository.BookRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class BookViewModel extends BaseViewModel {
    private MutableLiveData<BookData> mBook = new MutableLiveData<>();
    private MutableLiveData<Boolean> isBookmark = new MutableLiveData<>();


    public LiveData<BookData> getBook() {
        return mBook;
    }
    public LiveData<Boolean> isBookmark() { return isBookmark;}

    private BookRepository repository = new BookRepository(BookLocalSource.getInstance(), BookRemoteSource.getInstance());

    public void fetchData(String isbn13) {
        repository.loadBookInfo(isbn13, data -> {
            if (data != null) {
                mBook.postValue(data);
            }
        });

        isBookmark.postValue(repository.checkBookmark(isbn13));
    }

    public void saveHistory() {
        if (mBook.getValue().isbn13 != null) {
            repository.saveHistory(mBook.getValue());
        }
    }

    public void toggleBookmark() {
        if (mBook.getValue().isbn13 != null) {
            isBookmark.postValue(!isBookmark.getValue());
            repository.toggleBookmark(mBook.getValue());
        }
    }
}
