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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class sign_up extends AppCompatActivity {

    private Button sign_up1;
    private DatabaseReference mdatabase;
    private EditText name_signup;
    private EditText enrollment_no_signup;
    private EditText contact_no_signup;
    private EditText password_signup;
    private RadioButton student;
    private RadioButton worker;
    private FirebaseFirestore mFirebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        FirebaseApp.initializeApp(this);

        sign_up1 = (Button)findViewById(R.id.sign_up1);
        name_signup = (EditText)findViewById(R.id.name2);
        enrollment_no_signup = (EditText)findViewById(R.id.enrollment_no2);
        contact_no_signup = (EditText)findViewById(R.id.contact_no2);
        password_signup = (EditText)findViewById(R.id.password2);
        student = (RadioButton) findViewById(R.id.student);
        worker = (RadioButton) findViewById(R.id.worker);
        mFirebaseFirestore = FirebaseFirestore.getInstance();

        OnClickListener3();
    }

    public void OnClickListener3(){

        sign_up1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String name = name_signup.getText().toString().trim();
                        String enrollment_no = enrollment_no_signup.getText().toString().trim();
                        String contact_no = contact_no_signup.getText().toString().trim();
                        String password = password_signup.getText().toString().trim();
                        final boolean checked = student.isChecked();

                        Map<String, String> datamap = new HashMap<String, String>();

                        datamap.put("Name", name);
                        datamap.put("Enrollment No.", enrollment_no);
                        datamap.put("Contact No.", contact_no);
                        datamap.put("Password", password);

                        if (checked) {
                            datamap.put("Login Type", "student");
                        }
                        else {
                            datamap.put("Login Type", "worker");
                        }


                        mFirebaseFirestore.collection(enrollment_no).add(datamap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(sign_up.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                String error = e.getMessage();
                                Toast.makeText(sign_up.this, "Error : " + error, Toast.LENGTH_SHORT).show();
                            }
                        });
                        Intent intent5 = new Intent("com.example.pc.complainform.Log_In_Screen");
                        startActivity(intent5);
                    }

                }
        );
    }
}
