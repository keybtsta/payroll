package com.example.pms_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class EmployeesFragment extends Fragment {

    RecyclerView rvEmployees;
    MainAdapter mainAdapter;
    SearchView etSearch;

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

        etSearch = view.findViewById(R.id.etSearch);

        etSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                FirebaseRecyclerOptions<MainModel> options =
                        new FirebaseRecyclerOptions.Builder<MainModel>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("database").child("employees").orderByChild("name").startAt(query).endAt(query + "~"), MainModel.class)
                                .build();

                mainAdapter = new MainAdapter(options);
                rvEmployees.setAdapter(mainAdapter);
                mainAdapter.startListening();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                FirebaseRecyclerOptions<MainModel> options =
                        new FirebaseRecyclerOptions.Builder<MainModel>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("database").child("employees").orderByChild("name").startAt(query).endAt(query + "~"), MainModel.class)
                                .build();

                mainAdapter = new MainAdapter(options);
                rvEmployees.setAdapter(mainAdapter);
                mainAdapter.startListening();
                return false;
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }
}