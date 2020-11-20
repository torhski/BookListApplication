package com.example.sendbirdbooks.data.model;

import com.example.sendbirdbooks.domain.model.Pdf;
import com.google.gson.annotations.SerializedName;

public class EbookEntity {
    @SerializedName("Free eBook")
    public String ebook;

    public EbookEntity(String ebook) {
        this.ebook = ebook;
    }

    public Pdf mapToData() {
        Pdf pdf  = new Pdf();
        pdf.pdf = ebook;
        return pdf;
    }
}
