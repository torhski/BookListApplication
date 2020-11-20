package com.example.sendbirdbooks.presentation.fragment;

import com.example.sendbirdbooks.presentation.base.BaseListFragment;

public class HomeFragment extends BaseListFragment {
    @Override
    public void fetchData() {
        mViewModel.fetchNewData();
    }

    @Override
    public String getName() {
        return "NewFragment";
    }
}