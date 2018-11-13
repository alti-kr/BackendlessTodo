package com.gmail.sergii_tymofieiev.backendlesstodo.gui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gmail.sergii_tymofieiev.backendlesstodo.R;
import com.gmail.sergii_tymofieiev.backendlesstodo.common.DateUtils;
import com.gmail.sergii_tymofieiev.backendlesstodo.data.IDataItem;

import java.util.ArrayList;

/*
 * @author Sergii Tymofieiev on 13.11.2018
 */
public class TodoItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<IDataItem> itemsList;
    private ItemsAdapterListener itemsAdapterListener;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_list_item, parent, false);
        return new ItemViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        int currentPosition = position;
        IDataItem item = itemsList.get(position);
        if(item != null){
            ItemViewHolder holder =  (ItemViewHolder) viewHolder;
            holder.data = item;
            holder.checkBoxView.setChecked(item.isDone());
            holder.hText0.setText(item.getNotes());
            holder.hText1.setText(DateUtils.getFormattedDateAsString(DateUtils.DateFormatCustom.ddMMyyyy_HHmm, item.getTimestamp()));
            holder.hRightTextView.setText(R.string.btn_delete);
        }
    }

    @Override
    public int getItemCount() {
        return itemsList==null?0:itemsList.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItems(ArrayList<IDataItem> itemsList) {
        this.itemsList = itemsList;
    }
    public void setItemsAdapterListener(ItemsAdapterListener itemsAdapterListener){
        this.itemsAdapterListener = itemsAdapterListener;
    }
    public void removeItem(int position) {
        if (position > -1 && position < itemsList.size()) {
            itemsList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public class ItemViewHolder extends RecyclerItemTouchHelper.TouchHelperViewHolder{

        TextView hText0, hText1;
        TextView hRightTextView;
        CheckBox checkBoxView;


        public ItemViewHolder(View itemView) {
            super(itemView);
            isSwipeable = true;
            LinearLayout viewContainer = (LinearLayout) itemView.findViewById(R.id.item_container);

            hRightTextView = itemView.findViewById(R.id.item_right_text);
            checkBoxView = itemView.findViewById(R.id.item_done);
            hText0 = itemView.findViewById(R.id.text_item_0);
            hText1 = itemView.findViewById(R.id.text_item_1);

            viewContainer.setTag(this);
            foregroundView = itemView.findViewById(R.id.item_container);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemsAdapterListener!=null){
                        itemsAdapterListener.onItemClick(data, getAdapterPosition());
                    }
                }
            });
        }

        @Override
        public boolean isSwipeable() {
            return true;
        }


    }

    public interface ItemsAdapterListener<T extends Object>{
        void onItemClick(T itemData, int position);
    }
}
