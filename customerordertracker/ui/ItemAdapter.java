package com.example.customerordertracker.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.customerordertracker.Activities.ItemInfo;
import com.example.customerordertracker.Entities.Item;
import com.example.customerordertracker.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView itemNameView;

        private ItemViewHolder(View itemView) {
            super(itemView);
            itemNameView = itemView.findViewById(R.id.itemName);
            itemView.setOnClickListener(view -> {

                int position = getAdapterPosition();
                final Item current = mItems.get(position);
                Intent intent = new Intent(context, ItemInfo.class);
                intent.putExtra("itemId", current.getItemID());
                intent.putExtra("itemName", current.getName());
                intent.putExtra("itemDescription", current.getDescription());
                intent.putExtra("itemQuantity", current.getQuantity());
                intent.putExtra("itemAorY", current.getAdultOrYouth());
                intent.putExtra("itemSize", current.getSize());
                intent.putExtra("itemApplication", current.getApplication());
                intent.putExtra("itemColor", current.getColor());
                intent.putExtra("itemOrderId", current.getOrderID());
                intent.putExtra("itemVendorId", current.getVendorID());
                intent.putExtra("position", position);
                context.startActivity(intent);
            });
        }
    }

    private List<Item> mItems;
    private final LayoutInflater mInflater;
    private final Context context;

    public ItemAdapter(List<Item> mItems, Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mItems = mItems;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_list_item, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        if(mItems != null) {
            Item current = mItems.get(position);
            holder.itemNameView.setText(current.getName());
        }
        else{
            holder.itemNameView.setText("No Items");
        }
    }

    public void setItems(List<Item> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mItems != null)
            return mItems.size();
        else return 0;
    }
}
