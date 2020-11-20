package com.example.sendbirdbooks.presentation.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sendbirdbooks.util.ToastUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ConfirmDialog extends BaseDialog {

    private String msg;

    public ConfirmDialog(@NonNull Context context) {
        super(context);
    }

    public ConfirmDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ConfirmDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void show() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setMessage(msg)
                .setPositiveButton("확인", (dialog, which) -> {
                    getOkListener().onNext(null);
                    dismiss();
                })
                .setNegativeButton("취소", ((dialog, which) -> dismiss()));

        builder.show();
    }
}
