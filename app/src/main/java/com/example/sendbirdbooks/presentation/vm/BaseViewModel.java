package com.example.sendbirdbooks.presentation.vm;

import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseViewModel extends ViewModel {

    private CompositeDisposable disposable = new CompositeDisposable();

    public void addDisposable(Disposable disposable) {
        this.disposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        disposable.clear();
        super.onCleared();
    }
}
