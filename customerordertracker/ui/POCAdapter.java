package com.example.customerordertracker.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.customerordertracker.Activities.PoCInfo;
import com.example.customerordertracker.Entities.POC;
import com.example.customerordertracker.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class POCAdapter extends RecyclerView.Adapter<POCAdapter.POCViewHolder> {

    class POCViewHolder extends RecyclerView.ViewHolder {
        private final TextView POCNameView;

        private POCViewHolder(View itemView) {
            super(itemView);
            POCNameView = itemView.findViewById(R.id.pocItem);
            itemView.setOnClickListener(view -> {

                int position = getAdapterPosition();
                final POC current = mPOCs.get(position);

                Intent intent = new Intent(context, PoCInfo.class);
                intent.putExtra("pocId", current.getPocid());
                intent.putExtra("pocFName", current.getFName());
                intent.putExtra("pocLName", current.getLName());
                intent.putExtra("pocPhone", current.getPhone());
                intent.putExtra("pocEmail", current.getEmail());
                intent.putExtra("pocClientId", current.getClientID());
                intent.putExtra("position", position);
                context.startActivity(intent);
            });
        }
    }

    private List<POC> mPOCs;
    private final LayoutInflater mInflater;
    private final Context context;

    public POCAdapter(List<POC> mPOCs, Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mPOCs = mPOCs;
    }

    @Override
    public POCViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.poc_list_item, parent, false);
        return new POCViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(POCViewHolder holder, int position) {
        if(mPOCs != null){
            POC current = mPOCs.get(position);
            holder.POCNameView.setText(current.getFName() + " " + current.getLName());
        }
        else{
            holder.POCNameView.setText("No Points of Contact");
        }
    }

    public void setPOCs(List<POC> POCs) {
        mPOCs = POCs;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mPOCs != null)
            return mPOCs.size();
        else return 0;
    }
}
