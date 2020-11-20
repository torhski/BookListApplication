package com.example.sendbirdbooks.util;

import com.example.sendbirdbooks.data.model.BookDataEntity;
import com.example.sendbirdbooks.data.model.EbookEntity;
import com.example.sendbirdbooks.data.realm.IBookRealm;
import com.example.sendbirdbooks.data.realm.MemoRealm;
import com.example.sendbirdbooks.domain.model.BookData;
import com.example.sendbirdbooks.domain.model.Memo;

import java.util.ArrayList;

public class Mapper {
    public static ArrayList<BookData> mapToDataList(ArrayList<BookDataEntity> list) {
        ArrayList<BookData> books = new ArrayList<>();

        if (list == null || list.isEmpty()) {
            return books;
        }

        for (int i = 0; i < list.size(); i++) {
            books.add(entityToData(list.get(i)));
        }

        return books;
    }

    public static BookData entityToData(BookDataEntity entity) {
        BookData data = new BookData();
        data.title = entity.title;
        data.subtitle = entity.subtitle;
        data.authors = entity.authors;
        data.publisher = entity.publisher;
        data.language = entity.language;
        data.isbn10 = entity.isbn10;
        data.isbn13 = entity.isbn13;
        data.pages = entity.pages;
        data.year = entity.year;
        data.rating = entity.rating;
        data.desc = entity.desc;
        data.price = entity.price;
        data.image = entity.image;
        data.url = entity.url;
        data.updatedAt = entity.updatedAt;
        data.createdAt = entity.createdAt;
        if (entity.pdf != null) {
            data.ebook = entity.pdf.mapToData();
        }

        return data;
    }

    public static BookDataEntity dataToEntity(BookData data) {
        BookDataEntity entity = new BookDataEntity();
        entity.title = data.title;
        entity.subtitle = data.subtitle;
        entity.authors = data.authors;
        entity.publisher = data.publisher;
        entity.language = data.language;
        entity.isbn10 = data.isbn10;
        entity.isbn13 = data.isbn13;
        entity.pages = data.pages;
        entity.year = data.year;
        entity.rating = data.rating;
        entity.desc = data.desc;
        entity.price = data.price;
        entity.image = data.image;
        entity.url = data.url;
        entity.updatedAt = data.updatedAt;
        entity.createdAt = data.createdAt;

        if (data.ebook != null) {
            entity.pdf = data.ebook.mapToEntity();
        }


        return entity;
    }

    public static IBookRealm bookEntityToRealm(IBookRealm realmData, BookDataEntity data) {
        realmData.setTitle(data.title);
        realmData.setSubTitle(data.subtitle);
        realmData.setAuthors(data.authors);
        realmData.setPublisher(data.publisher);
        realmData.setLanguage(data.language);
        realmData.setIsbn10(data.isbn10);
        realmData.setIsbn13(data.isbn13);
        realmData.setPages(data.pages);
        realmData.setYear(data.year);
        realmData.setRating(data.rating);
        realmData.setDesc(data.desc);
        realmData.setPrice(data.price);
        realmData.setImage(data.image);
        realmData.setUrl(data.url);
        if (data.pdf != null) {
            realmData.setEbook(data.pdf.ebook);
        }

        realmData.setCreatedAt(System.currentTimeMillis());
        realmData.setUpdatedAt(System.currentTimeMillis());

        return realmData;
    }

    public static BookDataEntity realmToEntity(IBookRealm realmData) {
        BookDataEntity entity = new BookDataEntity();

        entity.title = realmData.getTitle();
        entity.subtitle = realmData.getSubTitle();
        entity.authors = realmData.getAuthors();
        entity.publisher = realmData.getPublisher();
        entity.language = realmData.getLanguage();
        entity.isbn10 = realmData.getIsbn10();
        entity.isbn13 = realmData.getIsbn13();
        entity.pages = realmData.getPages();
        entity.year = realmData.getYear();
        entity.rating = realmData.getRating();
        entity.desc = realmData.getDesc();
        entity.price = realmData.getPrice();
        entity.image = realmData.getImage();
        entity.url = realmData.getUrl();
        entity.pdf = new EbookEntity(realmData.getEbook());

        entity.createdAt = realmData.getCreatedAt();
        entity.updatedAt = realmData.getUpdatedAt();

        return entity;
    }

    // memo
    public static Memo realmToMemo(MemoRealm data) {
        Memo memo = new Memo();
        memo.id = data.getId();
        memo.isbn13 = data.getIsbn13();
        memo.memo = data.getText();
        return memo;
    }

    public static MemoRealm memoToRealm(Memo data) {
        MemoRealm memo = new MemoRealm();
        memo.setId(data.id);
        memo.setIsbn13(data.isbn13);
        memo.setText(data.memo);
        return memo;
    }
}
