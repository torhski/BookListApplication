package com.example.sendbirdbooks.presentation.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;

import com.example.sendbirdbooks.R;
import com.example.sendbirdbooks.data.realm.Const;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SortSpinner extends androidx.appcompat.widget.AppCompatSpinner {

    private String[] mSortList = {"최신순", "제목순", "가격순"};

    public SortSpinner(@NonNull Context context) {
        super(context);
        init();
    }

    public SortSpinner(@NonNull Context context, int mode) {
        super(context, mode);
        init();
    }

    public SortSpinner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SortSpinner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public SortSpinner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int mode) {
        super(context, attrs, defStyleAttr, mode);
        init();
    }

    public SortSpinner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int mode, Resources.Theme popupTheme) {
        super(context, attrs, defStyleAttr, mode, popupTheme);
        init();
    }

    private void init() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, mSortList);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        setAdapter(adapter);
        setSelection(0);
    }

    public String getSortType(int position) {
        switch (position) {
            case 0:
                return Const.FIELD_UPDATED_AT;
            case 1:
                return Const.FIELD_TITLE;
            case 2:
            default:
                return Const.FIELD_PRICE;
        }
    }
}
