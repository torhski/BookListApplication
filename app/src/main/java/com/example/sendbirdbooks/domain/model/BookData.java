package com.example.sendbirdbooks.domain.model;

public class BookData {
    public String error;
    public String title;
    public String subtitle;
    public String authors;
    public String publisher;
    public String language;
    public String isbn10;
    public String isbn13;
    public String pages;
    public String year;
    public String rating;
    public String desc;
    public String price;
    public String image;
    public String url;
    public Pdf ebook;

    public long createdAt;
    public long updatedAt;

    public String toString() {
        return "BookData{" +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", authors='" + authors + '\'' +
                ", publisher='" + publisher + '\'' +
                ", language='" + language + '\'' +
                ", isbn10='" + isbn10 + '\'' +
                ", isbn13='" + isbn13 + '\'' +
                ", pages='" + pages + '\'' +
                ", year='" + year + '\'' +
                ", rating='" + rating + '\'' +
                ", desc='" + desc + '\'' +
                ", price='" + price + '\'' +
                ", image='" + image + '\'' +
                ", url='" + url + '\'' +
                ", ebook=" + ebook +
                '}';
    }
}




