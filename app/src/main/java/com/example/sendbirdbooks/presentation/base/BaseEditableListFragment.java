package com.example.sendbirdbooks.presentation.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sendbirdbooks.R;
import com.example.sendbirdbooks.presentation.dialog.ConfirmDialog;
import com.example.sendbirdbooks.presentation.view.SortSpinner;
import com.example.sendbirdbooks.presentation.vm.CheckableViewModel;
import com.example.sendbirdbooks.util.ToastUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindView;

public class BaseEditableListFragment extends BaseListFragment {

    @BindView(R.id.wrap_toolbar)
    public ConstraintLayout vToolbar;
    @BindView(R.id.btn_edit)
    Button btnEdit;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.dropdown_menu)
    public SortSpinner vSpinner;

    public CheckableViewModel mCheckableViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mCheckableViewModel = new ViewModelProvider(this).get(CheckableViewModel.class);

        View root = super.onCreateView(inflater, container, savedInstanceState);
        setSpinner();
        render();

        return root;
    }

    @Override
    public void observeData() {
        super.observeData();
        mCheckableViewModel.getCheckable().observe(getViewLifecycleOwner(), check -> {
            mBookAdapter.setEditMode(check);
            renderEditBtn(check);
        });
    }

    private void render() {
        renderEditBtn(false);
        renderCancelBtn();
    }

    private void renderEditBtn(boolean editMode) {
        btnEdit.setText(editMode ? "삭제" : "편집");
        btnEdit.setOnClickListener(v -> {
            if (editMode) {
                // 이 로직이 여기 들어가도 되나?
                if (mViewModel.isExistRemovable()) {

                    ConfirmDialog dialog = new ConfirmDialog(getContext());
                    dialog.setMsg(getDeleteMsg());
                    dialog.setOnOkListener(res -> {
                        deleteLocalData();
                        mCheckableViewModel.toggle();
                        btnCancel.setVisibility(View.GONE);
                    });
                    dialog.show();
                } else {
                    ToastUtil.show(getContext(), "삭제할 항목을 선택해주세요");
                }
            } else {
                // 편집모드 전환
                mCheckableViewModel.toggle();
                btnCancel.setVisibility(View.VISIBLE);
            }
        });
    }

    private void renderCancelBtn() {
        btnCancel.setOnClickListener(v -> {
            btnCancel.setVisibility(View.GONE);
            mCheckableViewModel.toggle();
        });
    }

    protected String getDeleteMsg() {
        return "";
    }

    protected void deleteLocalData() {

    }

    protected void setSpinner() {

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_edit_list;
    }

}
