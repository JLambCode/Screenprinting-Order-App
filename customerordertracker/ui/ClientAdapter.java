package com.example.customerordertracker.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.customerordertracker.Activities.ClientInfo;
import com.example.customerordertracker.Activities.ClientList;
import com.example.customerordertracker.Entities.Client;
import com.example.customerordertracker.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientViewHolder> {

    class ClientViewHolder extends RecyclerView.ViewHolder {
        private final TextView clientNameView;

        private ClientViewHolder(View itemView) {
            super(itemView);
            clientNameView = itemView.findViewById(R.id.clientName);
            itemView.setOnClickListener(view -> {

                int position = getAdapterPosition();
                final Client current = mClients.get(position);

                Intent intent = new Intent(context, ClientInfo.class);
                intent.putExtra("clientId", current.getClientID());
                intent.putExtra("clientName", current.getName());
                intent.putExtra("clientAddress", current.getAddress());
                intent.putExtra("clientCity", current.getCity());
                intent.putExtra("clientState", current.getState());
                intent.putExtra("clientZipCode", current.getZip());
                intent.putExtra("clientPhone", current.getPhone());
                intent.putExtra("position", position);
                context.startActivity(intent);
            });
        }
    }

    private List<Client> mClients;
    private final LayoutInflater mInflater;
    private final Context context;

    public ClientAdapter(List<Client> mClients, Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mClients = mClients;
    }

    @Override
    public ClientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.client_list_item, parent, false);
        return new ClientViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ClientViewHolder holder, int position) {
        if(mClients != null){
            Client current = mClients.get(position);
            holder.clientNameView.setText(current.getName());
        }
        else{
            holder.clientNameView.setText("No Clients");
        }
    }

    public void setClients(List<Client> clients) {
        mClients = clients;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mClients != null)
            return mClients.size();
        else return 0;

    }

}
