package com.example.sendbirdbooks.presentation.vm;

import com.example.sendbirdbooks.util.LogUtil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class CheckableViewModel extends BaseViewModel {

    private MutableLiveData<Boolean> checkable = new MutableLiveData<>(false);
    public LiveData<Boolean> getCheckable() { return checkable; }

    public void toggle() {

        boolean val = !checkable.getValue();
        LogUtil.d("ViewModel: " + val);
        checkable.postValue(val);
    }
}
