package com.example.sendbirdbooks.presentation.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sendbirdbooks.R;
import com.example.sendbirdbooks.presentation.adapter.BookListAdapter;
import com.example.sendbirdbooks.presentation.vm.BookListViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class BaseListFragment extends BaseFragment {

    @BindView(R.id.list)
    public RecyclerView rvList;

    public BookListViewModel mViewModel;
    public BookListAdapter mBookAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(BookListViewModel.class);
        View root = super.onCreateView(inflater, container, savedInstanceState);
        setAapter();
        return root;
    }

    private void setAapter() {
        mBookAdapter = new BookListAdapter(mViewModel);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvList.setAdapter(mBookAdapter);
    }

    @Override
    public void fetchData() {

    }

    @Override
    public void observeData() {
        // move into adapter?
        mViewModel.getData().observe(getViewLifecycleOwner(), data -> mBookAdapter.notifyDataSetChanged());
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_listview;
    }
}
