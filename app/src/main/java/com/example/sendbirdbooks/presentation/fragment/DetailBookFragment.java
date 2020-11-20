package com.example.sendbirdbooks.presentation.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.sendbirdbooks.R;
import com.example.sendbirdbooks.domain.model.BookData;
import com.example.sendbirdbooks.presentation.activity.MainActivity;
import com.example.sendbirdbooks.presentation.adapter.MemoAdapter;
import com.example.sendbirdbooks.presentation.base.BaseFragment;
import com.example.sendbirdbooks.presentation.dialog.MemoDialog;
import com.example.sendbirdbooks.presentation.view.BookDetailInfo;
import com.example.sendbirdbooks.presentation.view.BookSummary;
import com.example.sendbirdbooks.presentation.vm.BookViewModel;
import com.example.sendbirdbooks.presentation.vm.MemoViewmodel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class DetailBookFragment extends BaseFragment {

    @BindView(R.id.thumb)
    ImageView ivThumb;

    @BindView(R.id.bookmark)
    ImageView ivBookmark;

    @BindView(R.id.book_summary)
    BookSummary cvSummary;
    @BindView(R.id.book_detail)
    BookDetailInfo cvDetail;

    @BindView(R.id.btn_memo)
    FloatingActionButton fbMemo;
    @BindView(R.id.memo_list)
    RecyclerView rvMemoList;

    private BookViewModel mViewModel;
    private MemoViewmodel mMemoViewModel;
    private String mIsbn13;
    private MemoAdapter mMemoAdapter;

    public static void openFragement(String id, Context context) {
        DetailBookFragment fragment = new DetailBookFragment();
        Bundle args = fragment.getArguments();
        if (args == null) {
            args = new Bundle();
        }
        args.putString("id", id);
        fragment.setArguments(args);
        fragment.openPage((FragmentActivity) context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hideNav();
    }

    private void hideNav() {
        ((MainActivity) getActivity()).hideBottomNav();
    }

    private void showNav() {
        ((MainActivity) getActivity()).showBottomNav();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(BookViewModel.class);
        mMemoViewModel = new ViewModelProvider(this).get(MemoViewmodel.class);
        mIsbn13 = getArguments().getString("id", "");
        View root = super.onCreateView(inflater, container, savedInstanceState);

        setAdapter();

        ivBookmark.setOnClickListener(v -> {
            mViewModel.toggleBookmark();
        });

        fbMemo.setOnClickListener(v -> {
            MemoDialog dialog = new MemoDialog(getContext());
            dialog.setOnOkListener(str -> mMemoViewModel.saveMemo((String) str));
            dialog.show();
        });

        return root;
    }

    @Override
    public void fetchData() {
        mViewModel.fetchData(mIsbn13);
        mMemoViewModel.fetchData(mIsbn13);
    }

    @Override
    public void observeData() {
        mViewModel.getBook().observe(getViewLifecycleOwner(), data -> {
            mViewModel.saveHistory();

            render(data);
        });

        mViewModel.isBookmark().observe(getViewLifecycleOwner(), check -> {
            ivBookmark.setSelected(check);
//            String msg = check ? "북마크 저장완료" : "북마크 제거완료";
//            ToastUtil.show(getContext(), msg);
        });

        mMemoViewModel.getList().observe(getViewLifecycleOwner(), list -> {
            mMemoAdapter.setItem(list);
        });
    }

    private void render(BookData data) {
        Glide.with(getContext()).load(data.image).into(ivThumb);
        cvSummary.setData(data);
        cvDetail.setData(data);
    }

    private void setAdapter() {
        mMemoAdapter = new MemoAdapter();
        rvMemoList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvMemoList.setAdapter(mMemoAdapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        showNav();
    }

    @Override
    public int getLayout() {
        return R.layout.detail_book_fragment;
    }

    @Override
    public String getName() {
        return "DetailBookFragment";
    }
}