package com.example.sendbirdbooks.data.realm;

import com.example.sendbirdbooks.data.model.BookDataEntity;
import com.example.sendbirdbooks.domain.model.Memo;
import com.example.sendbirdbooks.presentation.common.INextListener;
import com.example.sendbirdbooks.util.Mapper;
import com.example.sendbirdbooks.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;


/**
 * data layer에서만 호출하자
 */
public class RealmManager {
    /**
     * @param data entitiy
     * @param type HISTORY / BOOKMARK
     */
    public static void saveBook(BookDataEntity data, String type) {
        if (StringUtil.equals(type, Const.TYPE_HISTORY)) {
            saveHistory(data);
        } else {
            ;
        }
    }

    public static void saveHistory(BookDataEntity data) {
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(r -> {
            HistoryRealm realmData = r.where(HistoryRealm.class)
                    .equalTo(Const.FIELD_IBSN_13, data.isbn13)
                    .findFirst();
            if (realmData == null) {
                // insert
                HistoryRealm bookRealm = (HistoryRealm) Mapper.bookEntityToRealm(new HistoryRealm(), data);
                r.copyToRealm(bookRealm);
            } else {
                // update
                realmData.setUpdatedAt(System.currentTimeMillis());
            }
        });

        realm.close();
    }

    public static BookDataEntity loadHistorybyId(String isbn13) {
        Realm realm = Realm.getDefaultInstance();

        HistoryRealm history = realm.where(HistoryRealm.class)
                .equalTo(Const.FIELD_IBSN_13, isbn13)
                .findFirst();


        BookDataEntity entity = null;
        if (history != null) {
            entity = Mapper.realmToEntity(history);
        }

        realm.close();
        return entity;
    }

    // check bookmark
    public static boolean checkBookmark(String id) {
        Realm realm = Realm.getDefaultInstance();

        BookRealm bookmark = realm.where(BookRealm.class).equalTo(Const.FIELD_IBSN_13, id).findFirst();
        boolean result = bookmark != null;
        realm.close();

        return result;
    }

    // save / delete
    public static void toggleBookmark(BookDataEntity entity) {
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(r -> {
            BookRealm bookmark = realm.where(BookRealm.class)
                    .equalTo(Const.FIELD_IBSN_13, entity.isbn13)
                    .findFirst();

            if (bookmark == null) {
                // insert
                BookRealm bookRealm = (BookRealm) Mapper.bookEntityToRealm(new BookRealm(), entity);
                r.copyToRealm(bookRealm);
            } else {
                // update
                bookmark.deleteFromRealm();
            }
        });

        realm.close();
    }

    /**
     * @param page      page (1..n)
     * @param sortField
     * @return
     */
    public static List<BookDataEntity> loadHistoryList(int page, String sortField) {
        Realm realm = Realm.getDefaultInstance();

        Sort order = StringUtil.equals(sortField, Const.FIELD_TITLE) ? Sort.ASCENDING : Sort.DESCENDING;
        RealmResults<HistoryRealm> history =
                realm.where(HistoryRealm.class)
                        .sort(sortField != null ? sortField : Const.FIELD_UPDATED_AT, order)
                        .limit(page * 10).findAll();

        List<HistoryRealm> res = realm.copyFromRealm(history.subList(10 * (page - 1), history.size()));
        List<BookDataEntity> entities = new ArrayList<>();
        for (int i = 0; i < res.size(); i++) {
            entities.add(Mapper.realmToEntity(res.get(i)));
        }

        realm.close();
        return entities;
    }

    public static List<BookDataEntity> loadBookmarkList(int page, String sortField) {
        Realm realm = Realm.getDefaultInstance();

        Sort order = StringUtil.equals(sortField, Const.FIELD_TITLE) ? Sort.ASCENDING : Sort.DESCENDING;
        RealmResults<BookRealm> history =
                realm.where(BookRealm.class)
                        .sort(sortField != null ? sortField : Const.FIELD_UPDATED_AT, order)
                        .limit(page * 10).findAll();

        List<BookRealm> res = realm.copyFromRealm(history.subList(10 * (page - 1), history.size()));
        List<BookDataEntity> entities = new ArrayList<>();
        for (int i = 0; i < res.size(); i++) {
            entities.add(Mapper.realmToEntity(res.get(i)));
        }

        realm.close();
        return entities;
    }

    /**
     * delete book
     */
    public static void deleteBookmarks(ArrayList<String> ids, INextListener listener) {
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(r -> {
            RealmResults<BookRealm> exist = r.where(BookRealm.class)
                    .in(Const.FIELD_IBSN_13, ids.toArray(new String[ids.size()]))
                    .findAll();
            exist.deleteAllFromRealm();
            listener.onNext(null);
        });

        realm.close();
    }

    public static void deleteHistories(ArrayList<String> ids, INextListener listener) {
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(r -> {
            RealmResults<HistoryRealm> exist = r.where(HistoryRealm.class)
                    .in(Const.FIELD_IBSN_13, ids.toArray(new String[ids.size()]))
                    .findAll();
            exist.deleteAllFromRealm();
            listener.onNext(null);
        });

        realm.close();
    }

    // memo
    public static List<Memo> loadMemo(String isbn13) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<MemoRealm> list = realm.where(MemoRealm.class)
                .equalTo(Const.FIELD_IBSN_13, isbn13)
                .findAll();

        List<Memo> res = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            res.add(Mapper.realmToMemo(list.get(i)));
        }

        realm.close();
        return res;
    }

    public static void saveMemo(Memo memo) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(r -> r.copyToRealm(Mapper.memoToRealm(memo)));
        realm.close();
    }
}
