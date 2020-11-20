package com.example.sendbirdbooks.presentation.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.example.sendbirdbooks.data.realm.Const;
import com.example.sendbirdbooks.presentation.activity.CodeConst;
import com.example.sendbirdbooks.presentation.base.BaseEditableListFragment;
import com.example.sendbirdbooks.util.ToastUtil;

import androidx.annotation.Nullable;

import static android.app.Activity.RESULT_OK;

// TODO load more
public class HistoryFragment extends BaseEditableListFragment {

    @Override
    public void fetchData() {
        // start 1
        mViewModel.loadHistory(1, null);
    }

    @Override
    public void setSpinner() {
        vSpinner.post(() -> vSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mViewModel.loadHistory(1, vSpinner.getSortType(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }));
    }

    @Override
    protected String getDeleteMsg() {
        return "선택하신 히스토리를 삭제하시겠습니까?";
    }

    @Override
    protected void deleteLocalData() {
        mViewModel.deleteBooks(Const.TYPE_HISTORY, s -> ToastUtil.show(getContext(), "삭제되었습니다."));
    }

    @Override
    public String getName() {
        return "HistoryFragment";
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CodeConst.DETAIL_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            mViewModel.updateLiveDataBooks(data.getStringExtra(Const.FIELD_IBSN_13));
        }
    }
}
