package com.example.sendbirdbooks.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sendbirdbooks.R;
import com.example.sendbirdbooks.presentation.base.BaseActivity;
import com.example.sendbirdbooks.util.LogUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.nav_view)
    BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
    }

    public void hideBottomNav() {
        navView.setVisibility(View.GONE);
    }

    public void showBottomNav() {
        navView.setVisibility(View.VISIBLE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CodeConst.DETAIL_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Fragment navHostFragment = getSupportFragmentManager().getFragments().get(0);
            if(navHostFragment != null) {
                List<Fragment> fragments = navHostFragment.getChildFragmentManager().getFragments();
                for(int i=0; i< fragments.size(); i++) {
                    Fragment fragment = fragments.get(i);
                    fragment.onActivityResult(requestCode, resultCode, data);
                }
            }
            return;
        }
    }
}