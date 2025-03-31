package com.example.pms_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        String name = getIntent().getStringExtra("name");
        String employeeId = getIntent().getStringExtra("employeeId");
        String employeeType = getIntent().getStringExtra("employeeType");
        String id = getIntent().getStringExtra("id");

        EditText etID = findViewById(R.id.etID);
        EditText etName = findViewById(R.id.etName);
        Spinner spinnerEmpType = findViewById(R.id.spinnerEmpType);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnDelete = findViewById(R.id.btnDelete);
        Button btnPayroll = findViewById(R.id.btnPayroll);
        Button btnCancel = findViewById(R.id.btnCancel);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.employment_type,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEmpType.setAdapter(adapter);

        etID.setText(employeeId);
        etName.setText(name);
        if (employeeType.equals("Full-Time")){
            spinnerEmpType.setSelection(0);
        } else {
            spinnerEmpType.setSelection(1);
        }


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<>();
                map.put("name", etName.getText().toString());
                map.put("employeeID", etID.getText().toString());
                map.put("employeeType", spinnerEmpType.getSelectedItem().toString());

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("database").child("employees");
                //MainModel model = new MainModel(etID.getText().toString(), etName.getText().toString(), spinnerEmpType.getSelectedItem().toString());
                ref.child(id).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        etID.setText("");
                        etName.setText("");
                        Toast.makeText(getApplicationContext(), "Registration Successful!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnPayroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}