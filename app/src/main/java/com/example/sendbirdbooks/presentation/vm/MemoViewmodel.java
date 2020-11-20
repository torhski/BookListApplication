package com.example.sendbirdbooks.presentation.vm;

import com.example.sendbirdbooks.data.datasource.MemoLocalSource;
import com.example.sendbirdbooks.domain.model.Memo;
import com.example.sendbirdbooks.domain.repository.MemoRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MemoViewmodel extends BaseViewModel {

    private MutableLiveData<List<Memo>> mList = new MutableLiveData<List<Memo>>();
    private String isbn13;

    public LiveData<List<Memo>> getList() { return mList; }

    private MemoRepository repository = new MemoRepository(MemoLocalSource.getInstance());

    private void setId(String isbn13) {
        this.isbn13 = isbn13;
    }

    public void fetchData(String isbn13) {
        mList.postValue(repository.loadMemo(isbn13));
        setId(isbn13);
    }

    public void saveMemo(String text) {
        Memo memo = new Memo();
        memo.memo = text;
        memo.isbn13 = this.isbn13;
        memo.id = this.isbn13 + System.currentTimeMillis();

        repository.saveMemo(memo);
        List<Memo> list = mList.getValue();
        list.add(0, memo);
        mList.postValue(list);
    }
}
