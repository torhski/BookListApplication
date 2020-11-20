package com.example.sendbirdbooks.presentation.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.widget.EditText;

import com.example.sendbirdbooks.presentation.common.INextListener;
import com.example.sendbirdbooks.util.ToastUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MemoDialog extends Dialog {
    private INextListener mOkListener;

    public MemoDialog(@NonNull Context context) {
        super(context);
    }

    public MemoDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected MemoDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setOnOkListener(INextListener clickListener) {
        mOkListener = clickListener;
    }

    public void show() {
        EditText etMemo = new EditText(getContext());

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setTitle("메모를 작성해주세요")
                .setPositiveButton("확인", (dialog, which) -> {
                    String memo = etMemo.getText().toString();
                    if (memo.isEmpty()) {
                        ToastUtil.show(getContext(), "문자를 입력해주세요");
                    } else {
                        mOkListener.onNext(memo);
                        dismiss();
                    }
                })
                .setNegativeButton("취소", ((dialog, which) -> dismiss()));

        builder.setView(etMemo);
        builder.show();
    }
}
