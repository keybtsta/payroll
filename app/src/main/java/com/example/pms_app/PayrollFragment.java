package com.example.pms_app;

import android.app.BroadcastOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavArgs;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PayrollFragment extends Fragment {

    TextView textName;
    TextView textEmployeeID;
    TextView textEmployeeType;
    EditText etWork;
    EditText etTotalWork;
    TextView tvNetSalary;
    TextView tvSalaryRate;
    TextView tvInsurance;
    TextView tvSSS;
    TextView tvHousing;
    TextView tvTax;

    Button btnCompute;

    LinearLayout PartTime;
    LinearLayout FullTime;

    String employmentType;

    public PayrollFragment() {
        // Required empty public constructor
    }

    public static PayrollFragment newInstance(String param1, String param2) {
        PayrollFragment fragment = new PayrollFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payroll, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textName = view.findViewById(R.id.textName);
        textEmployeeID = view.findViewById(R.id.textEmployeeID);
        textEmployeeType = view.findViewById(R.id.textEmployeeType);

        btnCompute = view.findViewById(R.id.btnCompute);

        SharedPreferences sharedpreferences = requireContext().getSharedPreferences("query", Context.MODE_PRIVATE);
        String employeeID = sharedpreferences.getString("query", "none");


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child("database").child("employees");

        ref.orderByChild("employeeID").equalTo(employeeID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    textName.setText(dataSnapshot.child("name").getValue(String.class));
                    textEmployeeID.setText(dataSnapshot.child("employeeID").getValue(String.class));
                    textEmployeeType.setText(dataSnapshot.child("employeeType").getValue(String.class));

                    employmentType = dataSnapshot.child("employeeType").getValue(String.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnCompute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}