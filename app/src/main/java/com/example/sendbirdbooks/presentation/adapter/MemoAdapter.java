package com.example.sendbirdbooks.presentation.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.sendbirdbooks.R;
import com.example.sendbirdbooks.domain.model.Memo;
import com.example.sendbirdbooks.presentation.viewHolder.MemoViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MemoAdapter extends RecyclerView.Adapter {

    private ArrayList<Memo> mList = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MemoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_memo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MemoViewHolder) holder).onBind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setItem(List<Memo> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }
}
