package com.example.sendbirdbooks.presentation.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sendbirdbooks.R;
import com.example.sendbirdbooks.domain.model.BookData;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BookDetailInfo extends LinearLayout {

    @BindView(R.id.isbn10)
    TextView tvIsbn10;
    @BindView(R.id.desc)
    TextView tvDesc;
    @BindView(R.id.year)
    TextView tvYear;
    @BindView(R.id.rating)
    TextView tvRating;
    @BindView(R.id.language)
    TextView tvLanguage;
    @BindView(R.id.publisher)
    TextView tvPublisheresc;
    @BindView(R.id.authors)
    TextView tvAuthors;
    @BindView(R.id.pdf)
    TextView tvPdf;
    @BindView(R.id.pages)
    TextView tvPages;

    private BookData mData;

    public BookDetailInfo(Context context) {
        super(context);
        init(context);
    }

    public BookDetailInfo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BookDetailInfo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BookDetailInfo(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_book_detail, this);
        ButterKnife.bind(this);
    }

    public void setData(BookData data) {
        mData = data;
        render();
    }

    private void render() {
        if (mData != null) {
            tvIsbn10.setText(mData.isbn10);
            tvDesc.setText(mData.desc);
            tvYear.setText(mData.year);
            tvRating.setText(mData.rating);
            tvLanguage.setText(mData.language);
            tvPublisheresc.setText(mData.publisher);
            tvAuthors.setText(mData.authors);
            tvPages.setText(mData.pages);

            if (mData.ebook != null) {
                tvPdf.setVisibility(View.VISIBLE);
                tvPdf.setText(mData.ebook.pdf);
            } else {
                tvPdf.setVisibility(View.GONE);
            }
        }
    }
}
