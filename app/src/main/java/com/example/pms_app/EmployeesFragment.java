package com.example.pms_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EmployeesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmployeesFragment extends Fragment {

    RecyclerView rvEmployees;
    MainAdapter mainAdapter;

    public EmployeesFragment() {
        // Required empty public constructor
    }

    public static EmployeesFragment newInstance(String param1, String param2) {
        EmployeesFragment fragment = new EmployeesFragment();
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
        return inflater.inflate(R.layout.fragment_employees, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialization
        rvEmployees = view.findViewById(R.id.rvEmployees);
        // Layout Manager
        rvEmployees.setLayoutManager(new LinearLayoutManager(getContext()));
        // Default Query By Employee Name
        Query query = FirebaseDatabase.getInstance().getReference().child("database").child("employees").orderByChild("name");
        // Firebase Adapter
        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(query, MainModel.class)
                        .build();
        // Setting Adapter
        mainAdapter = new MainAdapter(options);
        rvEmployees.setAdapter(mainAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }
}