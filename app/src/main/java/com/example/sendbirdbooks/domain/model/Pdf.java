package com.example.sendbirdbooks.domain.model;

import com.example.sendbirdbooks.data.model.EbookEntity;

public class Pdf {
    public String pdf;

    public EbookEntity mapToEntity() {
        return new EbookEntity(pdf);
    }

    public String toString() {
        return "pdf: " + pdf;
    }
}
