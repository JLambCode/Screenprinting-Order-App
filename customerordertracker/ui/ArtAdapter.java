package com.example.customerordertracker.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.customerordertracker.Activities.ArtInfo;
import com.example.customerordertracker.Activities.ArtList;
import com.example.customerordertracker.Entities.Art;
import com.example.customerordertracker.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ArtAdapter extends RecyclerView.Adapter<ArtAdapter.ArtViewHolder> {

    class ArtViewHolder extends RecyclerView.ViewHolder {
        private final TextView artItemView;

        private ArtViewHolder(View itemView) {
            super(itemView);
            artItemView = itemView.findViewById(R.id.artName);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                final Art current = mArt.get(position);

                Intent intent = new Intent(context, ArtInfo.class);
                intent.putExtra("artId", current.getArtID());
                intent.putExtra("orderId", current.getOrderID());
                intent.putExtra("artName", current.getName());
                intent.putExtra("artPlacement", current.getPlacement());
                intent.putExtra("artNumberColors", current.getNumberOfColors());
                intent.putExtra("artLocation", current.getArtLoc());
                intent.putExtra("artLastUpdated", current.getLastUpdated());
                intent.putExtra("artLastUpdatedBy", current.getLastUpdatedBy());
                intent.putExtra("position", position);
                context.startActivity(intent);
            });
        }
    }

    private final LayoutInflater mInflator;
    private Context context;
    private List<Art> mArt;

    public ArtAdapter(List<Art> mArt, Context context) {
        mInflator = LayoutInflater.from(context);
        this.context = context;
        this.mArt = mArt;
    }

    @Override
    public ArtViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflator.inflate(R.layout.art_list_item, parent, false);
        return new ArtViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ArtViewHolder holder, int position) {
        if(mArt != null) {
            Art current = mArt.get(position);
            holder.artItemView.setText(current.getName());
        }
        else{
            holder.artItemView.setText("No Art");
        }
    }

    public void setArt(List<Art> art){
        mArt = art;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mArt != null) {
            return mArt.size();
        }
        else return 0;
    }

}
