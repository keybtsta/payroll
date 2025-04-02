package com.example.pms_app;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel, MainAdapter.myViewHolder> {
    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull MainAdapter.myViewHolder holder, int position, @NonNull MainModel model) {
        holder.name.setText(model.getName());
        holder.employeeID.setText(model.getEmployeeID());
        holder.employeeType.setText(model.getEmployeeType());

        holder.btnManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditActivity.class);

                intent.putExtra("name", model.getName());
                intent.putExtra("employeeId", model.getEmployeeID());
                intent.putExtra("employeeType", model.getEmployeeType());
                intent.putExtra("id", getRef(holder.getBindingAdapterPosition()).getKey());

                v.getContext().startActivity(intent);
            }
        });
    }
    @NonNull
    @Override
    public MainAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_item, parent, false);
        return new myViewHolder(view);
    }
    public class myViewHolder extends RecyclerView.ViewHolder {
        // Declaration
        TextView name, employeeID, employeeType;
        Button btnManage;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            btnManage = itemView.findViewById(R.id.btnManage);
            name = itemView.findViewById(R.id.textName);
            employeeID = itemView.findViewById(R.id.textEmployeeID);
            employeeType = itemView.findViewById(R.id.textEmployeeType);
        }
    }
}