package com.example.sendbirdbooks.data.realm;

public interface IBookRealm {
    // setter
    void setTitle(String title);
    void setSubTitle(String subTitle);
    void setIsbn13(String isbn13);
    void setPrice(String price);
    void setUrl(String url);
    void setImage(String image);
    void setAuthors(String image);
    void setPublisher(String image);
    void setLanguage(String image);
    void setIsbn10(String image);
    void setPages(String image);
    void setYear(String image);
    void setRating(String rating);
    void setDesc(String desc);
    void setCreatedAt(long createdAt);
    void setUpdatedAt(long updatedAt);
    void setEbook(String ebook);

    // getter
    String getTitle();
    String getSubTitle();
    String getPrice();
    String getImage();
    String getUrl();
    String getIsbn13();
    String getAuthors();
    String getPublisher();
    String getLanguage();
    String getIsbn10();
    String getPages();
    String getYear();
    String getRating();
    String getDesc();
    long getCreatedAt();
    long getUpdatedAt();
    String getEbook();
}
