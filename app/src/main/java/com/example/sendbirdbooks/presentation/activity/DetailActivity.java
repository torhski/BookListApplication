package com.example.sendbirdbooks.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.sendbirdbooks.R;
import com.example.sendbirdbooks.data.realm.Const;
import com.example.sendbirdbooks.domain.model.BookData;
import com.example.sendbirdbooks.presentation.adapter.MemoAdapter;
import com.example.sendbirdbooks.presentation.base.BaseActivity;
import com.example.sendbirdbooks.presentation.dialog.MemoDialog;
import com.example.sendbirdbooks.presentation.view.BookDetailInfo;
import com.example.sendbirdbooks.presentation.view.BookSummary;
import com.example.sendbirdbooks.presentation.vm.BookViewModel;
import com.example.sendbirdbooks.presentation.vm.MemoViewmodel;
import com.example.sendbirdbooks.util.LogUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class DetailActivity extends BaseActivity {

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(BookViewModel.class);
        mMemoViewModel = new ViewModelProvider(this).get(MemoViewmodel.class);
        mIsbn13 = getIntent().getStringExtra(Const.FIELD_IBSN_13);


        fetchData();
        observeData();
        setAdapter();

        ivBookmark.setOnClickListener(v -> mViewModel.toggleBookmark());

        fbMemo.setOnClickListener(v -> {
            MemoDialog dialog = new MemoDialog(this);
            dialog.setOnOkListener(str -> mMemoViewModel.saveMemo((String) str));
            dialog.show();
        });
    }

    private void fetchData() {
        mViewModel.fetchData(mIsbn13);
        mMemoViewModel.fetchData(mIsbn13);
    }

    private void observeData() {
        mViewModel.getBook().observe(this, data -> {
            mViewModel.saveHistory();

            render(data);
        });

        mViewModel.isBookmark().observe(this, check -> {
            ivBookmark.setSelected(check);
//            String msg = check ? "북마크 저장완료" : "북마크 제거완료";
//            ToastUtil.show(getContext(), msg);
        });

        mMemoViewModel.getList().observe(this, list -> {
            LogUtil.d("count: " + list.size());
            mMemoAdapter.setItem(list);
        });
    }

    private void setAdapter() {
        mMemoAdapter = new MemoAdapter();
        rvMemoList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvMemoList.setAdapter(mMemoAdapter);
    }

    private void render(BookData data) {
        Glide.with(this).load(data.image).into(ivThumb);
        cvSummary.setData(data);
        cvDetail.setData(data);
    }

    @Override
    public void finish() {
        Intent i  = new Intent();
        i.putExtra(Const.FIELD_IBSN_13, mIsbn13);
        i.putExtra(Const.BOOKMARK_STATUS, mViewModel.isBookmark().getValue());
        setResult(RESULT_OK, i);

        super.finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.detail_book_fragment;
    }
}
