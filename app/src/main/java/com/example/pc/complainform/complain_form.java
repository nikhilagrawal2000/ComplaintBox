package com.example.pc.complainform;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class complain_form extends AppCompatActivity {

    private Button save_complaint;
    private EditText date;
    private EditText time;
    private EditText room_no;
    private EditText contact_no;
    private EditText complaint;
    private RadioButton electric;
    private RadioButton general;
    private RadioButton furniture;
    private FirebaseFirestore mFirebaseFirestore1;
    private EditText enrollment_no;
    private EditText bhawan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_form);

        save_complaint = (Button)findViewById(R.id.save_complaint);
        date = (EditText)findViewById(R.id.date);
        time = (EditText)findViewById(R.id.time);
        room_no = (EditText)findViewById(R.id.room_no);
        contact_no = (EditText)findViewById(R.id.mobile_no);
        complaint = (EditText)findViewById(R.id.complaint);
        electric = (RadioButton)findViewById(R.id.electric);
        furniture = (RadioButton)findViewById(R.id.furniture);
        general = (RadioButton)findViewById(R.id.general);
        mFirebaseFirestore1 = FirebaseFirestore.getInstance();
        bhawan = (EditText)findViewById(R.id.bhawan);
        enrollment_no = (EditText)findViewById(R.id.enrollment_no);
        FirebaseApp.initializeApp(this);
        OnClickListener5();
    }

    public void OnClickListener5(){
        save_complaint.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final boolean checked1 = electric.isChecked();
                        final boolean checked2 = furniture.isChecked();
                        final boolean checked3 = general.isChecked();
                        String date1 = date.getText().toString().trim();
                        String time1 = time.getText().toString().trim();
                        String room_no1 = room_no.getText().toString().trim();
                        String mobile_no1 = contact_no.getText().toString().trim();
                        String complaint1 = complaint.getText().toString().trim();
                        String enrollment_no1 = enrollment_no.getText().toString().trim();
                        String bhawan1 = bhawan.getText().toString().trim();

                        Map<String, String> datamap1 = new HashMap<String, String>();
                        datamap1.put("date", date1);
                        datamap1.put("time", time1);
                        datamap1.put("room no.", room_no1);
                        datamap1.put("contact no.", mobile_no1);
                        datamap1.put("complaint", complaint1);
                        datamap1.put("bhawan", bhawan1);

                        if (checked1){
                            datamap1.put("complaint type", "electric");
                        }

                        else if (checked2){
                            datamap1.put("complaint type", "furniture");
                        }
                        else if (checked3) {
                            datamap1.put("complaint type", "general");
                        }
                        mFirebaseFirestore1.collection("Complaints").document(enrollment_no1).set(datamap1);
                        Intent intent6 = new Intent("com.example.pc.complainform.Log_In_Screen");
                        startActivity(intent6);
                    }
                }
        );
    }
}
