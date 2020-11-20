package com.example.sendbirdbooks.presentation.viewHolder;

import android.view.View;
import android.widget.TextView;

import com.example.sendbirdbooks.R;
import com.example.sendbirdbooks.domain.model.Memo;

import androidx.annotation.NonNull;
import butterknife.BindView;

public class MemoViewHolder extends BaseViewHolder {

    @BindView(R.id.text)
    TextView tvMemo;

    public MemoViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void onBind(Memo memo) {
        tvMemo.setText(memo.memo);
    }
}
