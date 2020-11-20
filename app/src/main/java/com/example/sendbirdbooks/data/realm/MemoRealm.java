package com.example.sendbirdbooks.data.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MemoRealm extends RealmObject {

    // TODO make unique key
    @PrimaryKey
    private String id;  // isbn13 + currentTime
    private String text;
    private String isbn13;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }
}
