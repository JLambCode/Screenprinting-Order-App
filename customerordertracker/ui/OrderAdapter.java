package com.example.customerordertracker.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.customerordertracker.Activities.OrderInfo;
import com.example.customerordertracker.Entities.Order;
import com.example.customerordertracker.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    class OrderViewHolder extends RecyclerView.ViewHolder {
        private final TextView orderItemView;

        private OrderViewHolder(View itemView) {
            super(itemView);
            orderItemView = itemView.findViewById(R.id.orderName);
            itemView.setOnClickListener(view -> {

                int position = getAdapterPosition();
                final Order current = mOrders.get(position);

                Calendar dateDue = Calendar.getInstance();
                dateDue.setTime(current.getDateDue());
                int dateDueYear = dateDue.get(Calendar.YEAR);
                int dateDueMonth = dateDue.get(Calendar.MONTH);
                int dateDueDay = dateDue.get(Calendar.DAY_OF_MONTH);

                String dateDueString = dateDueMonth+1 + "/" + dateDueDay + "/" + dateDueYear;

                Calendar dateOrdered = Calendar.getInstance();
                dateOrdered.setTime(current.getDateDue());
                int dateOrderedYear = dateOrdered.get(Calendar.YEAR);
                int dateOrderedMonth = dateOrdered.get(Calendar.MONTH);
                int dateOrderedDay = dateOrdered.get(Calendar.DAY_OF_MONTH);

                String dateOrderedString = dateOrderedMonth+1 + "/" + dateOrderedDay + "/" + dateOrderedYear;

                Intent intent = new Intent(context, OrderInfo.class);
                intent.putExtra("orderId", current.getOrderID());
                intent.putExtra("orderName", current.getName());
                intent.putExtra("orderDateOrdered", dateOrderedString);
                intent.putExtra("orderDateDue", dateDueString);
                intent.putExtra("orderDeliveryType", current.getDeliveryType());
                intent.putExtra("orderReferenceArt", current.getRefArtLoc());
                intent.putExtra("orderClientId", current.getClientID());
                intent.putExtra("orderDescription", current.getDescription());
                intent.putExtra("orderAddress", current.getAddress());
                intent.putExtra("orderCity", current.getCity());
                intent.putExtra("orderState", current.getState());
                intent.putExtra("orderZip", current.getZip());
                intent.putExtra("position", position);
                context.startActivity(intent);
            });
        }
    }

    private List<Order> mOrders;
    private final LayoutInflater mInflater;
    private final Context context;

    public OrderAdapter(List<Order> mOrders, Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mOrders = mOrders;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.order_list_item, parent, false);
        return new OrderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        if(mOrders != null) {
            Order current = mOrders.get(position);
            holder.orderItemView.setText(current.getName());
        }
        else{
            holder.orderItemView.setText("No Orders");
        }
    }

    public void setOrders(List<Order> orders) {
        mOrders = orders;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mOrders != null)
            return mOrders.size();
        else return 0;
    }
}
