package com.example.sendbirdbooks.data.datasource;

import com.example.sendbirdbooks.data.realm.RealmManager;
import com.example.sendbirdbooks.domain.model.Memo;

import java.util.List;


public class MemoLocalSource {

    private static class MemoLocalSourceHolder {
        private static MemoLocalSource instance = new MemoLocalSource();
    }

    public static MemoLocalSource getInstance() {
        return MemoLocalSourceHolder.instance;
    }

    public static void saveMemo(Memo memo) {
        RealmManager.saveMemo(memo);
    }

    public static List<Memo> loadMemoList(String isbn13) {
        return RealmManager.loadMemo(isbn13);
    }
}
