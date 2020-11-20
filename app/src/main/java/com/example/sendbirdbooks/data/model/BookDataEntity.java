package com.example.sendbirdbooks.data.model;

/**
 * "error": "0",
 * "title": "The Basics of User Experience Design",
 * "subtitle": "",
 * "authors": "Mads Soegaard",
 * "publisher": "Self-publishing",
 * "language": "English",
 * "isbn10": "1601301731",
 * "isbn13": "1001601301730",
 * "pages": "73",
 * "year": "2018",
 * "rating": "0",
 * "desc": "If you're looking to gain an introduction into the world of user experience (UX) design - or maybe even freshen up your knowledge of the field - then this UX design book is the ideal place to start.You'll cover a wide range of topics over nine highly readable chapters, with each one acting as a mini...",
 * "price": "$0.00",
 * "image": "https://itbook.store/img/books/1001601301730.png",
 * "url": "https://itbook.store/books/1001601301730",
 */
public class BookDataEntity {
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
    public EbookEntity pdf;

    public long updatedAt;
    public long createdAt;

    @Override
    public String toString() {
        return "BookDataEntity{" +
                "error='" + error + '\'' +
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
                ", pdf=" + pdf +
                '}';
    }
}


