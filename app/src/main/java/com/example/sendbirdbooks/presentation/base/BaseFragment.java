package com.example.sendbirdbooks.presentation.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sendbirdbooks.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;
    private Context mContext;

    public void openPage(FragmentActivity activity) {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.nav_host_fragment, this, getName())
                .addToBackStack(getName())
                .commit();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(getLayout(), container, false);
        unbinder = ButterKnife.bind(this, root);

        fetchData();
        observeData();

        return root;
    }

    abstract public void fetchData();

    abstract public void observeData();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onDetach() {
        mContext = null;
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    abstract public int getLayout();

    public String getName() {
        return "";
    }
    public Context getContext() {
        return mContext;
    }
}
