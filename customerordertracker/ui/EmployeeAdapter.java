package com.example.customerordertracker.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.customerordertracker.Activities.Admin;
import com.example.customerordertracker.Activities.EmployeeInfo;
import com.example.customerordertracker.Entities.Employee;
import com.example.customerordertracker.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    class EmployeeViewHolder extends RecyclerView.ViewHolder {
        private final TextView employeeNameView;

        private EmployeeViewHolder(View itemView) {
            super(itemView);
            employeeNameView = itemView.findViewById(R.id.employeeName);
            itemView.setOnClickListener(view -> {

                int position = getAdapterPosition();
                final Employee current = mEmployees.get(position);
                Intent intent = new Intent(context, EmployeeInfo.class);
                intent.putExtra("employeeId", current.getEmpID());
                intent.putExtra("employeeFName", current.getFName());
                intent.putExtra("employeeLName", current.getLName());
                intent.putExtra("employeeEmail", current.getEmail());
                intent.putExtra("employeePhone", current.getPhone());
                intent.putExtra("employeeDepartment", current.getDepartmentId());
                intent.putExtra("employeeAdmin", current.getAdmin());
                intent.putExtra("employeeUsername", current.getUsername());
                intent.putExtra("employeePassword", current.getPassword());
                intent.putExtra("position", position);
                context.startActivity(intent);
            });
        }
    }

    private List<Employee> mEmployees;
    private final LayoutInflater mInflator;
    private final Context context;

    public EmployeeAdapter(List<Employee> mEmployees, Context context) {
        mInflator = LayoutInflater.from(context);
        this.context = context;
        this.mEmployees = mEmployees;
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflator.inflate(R.layout.employee_list_item, parent, false);
        return new EmployeeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, int position) {
        if(mEmployees != null) {
            Employee current = mEmployees.get(position);
            holder.employeeNameView.setText(current.getFName() + " " + current.getLName());
        }
        else{
            holder.employeeNameView.setText("No Employees");
        }
    }

    public void setEmployees(List<Employee> employees) {
        mEmployees = employees;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mEmployees != null)
            return mEmployees.size();
        else return 0;
    }
}
