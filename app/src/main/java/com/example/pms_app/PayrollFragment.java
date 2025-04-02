package com.example.pms_app;

import android.app.BroadcastOptions;
import android.content.Intent;
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

    LinearLayout PartTime;
    LinearLayout FullTime;

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

        PayrollFragmentArgs args = PayrollFragmentArgs.fromBundle(getArguments());

        String name = args.getName();
        String employeeID = args.getEmployeeID();
        String employeeType = args.getEmployeeType();
        String id = args.getId();

        Log.d("PayrollFragment", "Received: " + name + ", " + employeeID + ", " + employeeType + ", " + id);
    }
}