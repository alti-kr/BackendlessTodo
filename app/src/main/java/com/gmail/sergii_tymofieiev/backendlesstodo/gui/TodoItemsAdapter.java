package com.gmail.sergii_tymofieiev.backendlesstodo.gui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.gmail.sergii_tymofieiev.backendlesstodo.data.IDataItem;

import java.util.ArrayList;

/*
 * @author Sergii Tymofieiev on 13.11.2018
 */
public class TodoItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<IDataItem> itemsList;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void addItems(ArrayList<IDataItem> itemsList) {
        this.itemsList = itemsList;
    }
}
