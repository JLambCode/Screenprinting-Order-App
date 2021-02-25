package com.example.customerordertracker.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.customerordertracker.Activities.DepartmentEdit;
import com.example.customerordertracker.Activities.DepartmentList;
import com.example.customerordertracker.Entities.Department;
import com.example.customerordertracker.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.DepartmentViewHolder> {

    class DepartmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView departmentNameView;

        private DepartmentViewHolder(View itemView) {
            super(itemView);
            departmentNameView = itemView.findViewById(R.id.departmentName);
            itemView.setOnClickListener(view -> {

                int position = getAdapterPosition();
                final Department current = mDepartments.get(position);
                Intent intent = new Intent(context, DepartmentEdit.class);
                intent.putExtra("departmentId", current.getDeptID());
                intent.putExtra("departmentName", current.getName());
                intent.putExtra("position", position);
                context.startActivity(intent);
            });
        }
    }

    private List<Department> mDepartments;
    private final LayoutInflater mInflator;
    private final Context context;

    public DepartmentAdapter(List<Department> mDepartments, Context context) {
        mInflator = LayoutInflater.from(context);
        this.context = context;
        this.mDepartments = mDepartments;
    }

    @Override
    public DepartmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflator.inflate(R.layout.department_list_item, parent, false);
        return new DepartmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DepartmentAdapter.DepartmentViewHolder holder, int position) {
        if(mDepartments != null) {
            Department current = mDepartments.get(position);
            holder.departmentNameView.setText(current.getName());
        }
        else{
            holder.departmentNameView.setText("No Departments");
        }
    }

    public void setDepartments(List<Department> departments) {
        mDepartments = departments;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mDepartments != null)
            return mDepartments.size();
        else return 0;
    }

}
