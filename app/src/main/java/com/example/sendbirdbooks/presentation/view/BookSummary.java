package com.example.sendbirdbooks.presentation.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sendbirdbooks.R;
import com.example.sendbirdbooks.domain.model.BookData;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BookSummary extends LinearLayout {

    @BindView(R.id.title)
    TextView tvTitle;
    @BindView(R.id.subtitle)
    TextView tvSubTitle;
    @BindView(R.id.url)
    TextView tvUrl;
    @BindView(R.id.price)
    TextView tvPrice;
    @BindView(R.id.isbn13)
    TextView tvIsbn13;

    private BookData mData;

    public BookSummary(@NonNull Context context) {
        super(context);
        init(context);
    }

    public BookSummary(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BookSummary(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BookSummary(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_book_summary, this);
        ButterKnife.bind(this);
    }

    public void setData(BookData data) {
        mData = data;
        render();
    }

    private void render() {
        if (mData != null) {
            tvTitle.setText(mData.title);
            tvPrice.setText(mData.price);
            tvUrl.setText(mData.url);
            tvIsbn13.setText(mData.isbn13);

            if (mData.subtitle.isEmpty()) {
                tvSubTitle.setVisibility(GONE);
            } else {
                tvSubTitle.setVisibility(VISIBLE);
                tvSubTitle.setText(mData.subtitle);
            }
        }
    }
}
