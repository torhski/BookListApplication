package com.example.sendbirdbooks.presentation.dialog;

import android.app.Dialog;
import android.content.Context;

import com.example.sendbirdbooks.presentation.common.INextListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BaseDialog extends Dialog {
    private Context mContext;
    private INextListener mOkListener;
    private INextListener mCancelListener;

    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setOnOkListener(INextListener clickListener) {
        mOkListener = clickListener;
    }
    public void setOnCancelListener(INextListener clickListener) {
        mCancelListener = clickListener;
    }

    public INextListener getOkListener() {
        return mOkListener;
    }

    public INextListener getmCancelListener() {
        return mCancelListener;
    }

}
