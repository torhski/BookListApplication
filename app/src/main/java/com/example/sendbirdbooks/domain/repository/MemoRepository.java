package com.example.sendbirdbooks.domain.repository;

import com.example.sendbirdbooks.data.datasource.MemoLocalSource;
import com.example.sendbirdbooks.domain.model.Memo;

import java.util.List;

public class MemoRepository {

    private MemoLocalSource localSource;

    public MemoRepository(MemoLocalSource localSource) {
        this.localSource = localSource;
    }

    public void saveMemo(Memo memo) {
        localSource.saveMemo(memo);
    }

    public List<Memo> loadMemo(String isbn13) {
        return localSource.loadMemoList(isbn13);
    }
}
