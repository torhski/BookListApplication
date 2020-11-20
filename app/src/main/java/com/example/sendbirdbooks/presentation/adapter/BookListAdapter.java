package com.example.sendbirdbooks.presentation.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sendbirdbooks.R;
import com.example.sendbirdbooks.data.realm.Const;
import com.example.sendbirdbooks.domain.model.BookData;
import com.example.sendbirdbooks.presentation.activity.CodeConst;
import com.example.sendbirdbooks.presentation.activity.DetailActivity;
import com.example.sendbirdbooks.presentation.activity.MainActivity;
import com.example.sendbirdbooks.presentation.viewHolder.BookViewHolder;
import com.example.sendbirdbooks.presentation.vm.BookListViewModel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class BookListAdapter extends RecyclerView.Adapter {

    private boolean editMode = false;
    private BookListViewModel model;

    public BookListAdapter(BookListViewModel model) {
        this.model = model;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_book, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        setOnCheckable(holder);

        ((BookViewHolder) holder).onBind(model.getData().getValue().get(position));

        setOnClickListener(holder, position);
        setCheckedItem(holder, position);
    }

    private void setOnClickListener(@NonNull RecyclerView.ViewHolder holder, int position) {
        View.OnClickListener listener;
        BookData data = model.getData().getValue().get(position);

        if (editMode) {
            listener = v -> toggleItem(data.isbn13);
        } else {
//            listener = v -> DetailBookFragment.openFragement(data.isbn13, v.getContext());
            listener = v -> {
                AppCompatActivity activity = ((MainActivity) v.getContext());
                Intent i = new Intent(activity, DetailActivity.class);
                i.putExtra(Const.FIELD_IBSN_13, data.isbn13);
                activity.startActivityForResult(i, CodeConst.DETAIL_REQUEST_CODE);
            };
        }
        ((BookViewHolder) holder).setOnClickListener(listener);
    }

    private void setOnCheckable(@NonNull RecyclerView.ViewHolder holder) {
        ((BookViewHolder) holder).setCheckable(editMode);
    }

    private void setCheckedItem(@NonNull RecyclerView.ViewHolder holder, int position) {
        BookData data = model.getData().getValue().get(position);
        ((BookViewHolder) holder).setCheckedItem(model.isExistRemoveList(data.isbn13));
    }

    @Override
    public int getItemCount() {
        return model.getData().getValue().size();
    }

    public void setEditMode(boolean val) {
        editMode = val;
        if (!val) {
            model.clearRemoveSet();
        }
        notifyDataSetChanged();
    }

    private void toggleItem(String isbn13) {
        model.toggleRemoveItem(isbn13);
        notifyDataSetChanged();
    }
}
