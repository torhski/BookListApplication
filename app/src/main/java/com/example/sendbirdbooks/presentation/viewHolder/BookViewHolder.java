package com.example.sendbirdbooks.presentation.viewHolder;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.sendbirdbooks.R;
import com.example.sendbirdbooks.domain.model.BookData;
import com.example.sendbirdbooks.presentation.view.BookSummary;

import androidx.annotation.NonNull;
import butterknife.BindView;

public class BookViewHolder extends BaseViewHolder {

    @BindView(R.id.checkbox)
    ImageView ivCheckbox;
    @BindView(R.id.thumb)
    ImageView ivThumb;
    @BindView(R.id.book_summary)
    BookSummary cvSummary;

    private boolean checkable;

    public BookViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void onBind(BookData data) {
        Glide.with(itemView.getContext())
                .load(data.image)
                .into(ivThumb);

        cvSummary.setData(data);
        ivCheckbox.setVisibility(checkable ? View.VISIBLE : View.GONE);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        itemView.setOnClickListener(listener);
    }

    public void setCheckable(boolean checkable) {
        this.checkable = checkable;
    }

    public void setCheckedItem(boolean val) {
        ivCheckbox.setSelected(val);
    }
}
