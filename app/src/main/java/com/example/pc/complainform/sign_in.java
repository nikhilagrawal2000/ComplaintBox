package com.example.pc.complainform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class sign_in extends AppCompatActivity {

    private static final String TAG = "FireLog";
    private Button sign_up;
    private Button sign_in;
    private EditText username;
    private EditText password;
    private FirebaseFirestore mFirebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
     OnClickListener();
     OnClickListener1();

     username = (EditText)findViewById(R.id.username1);
     password = (EditText)findViewById(R.id.password3);
     mFirebaseFirestore = FirebaseFirestore.getInstance();
    }


     public void OnClickListener(){
         sign_up = (Button)findViewById(R.id.sign_up3);
         sign_up.setOnClickListener(
                 new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         Intent intent1 = new  Intent("com.example.pc.complainform.sign_up");
                         startActivity(intent1);
                     }
                 }
         );
    }

    public void OnClickListener1(){
         sign_in = (Button)findViewById(R.id.sign_in2);
         sign_in.setOnClickListener(

                 new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         String username2 = username.getText().toString();
                         final String password2 = password.getText().toString();

                         mFirebaseFirestore.collection(username2).addSnapshotListener(new EventListener<QuerySnapshot>() {
                             @Override
                             public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                                 if(e != null){
                                     Log.d(TAG, "Error : " + e.getMessage());
                                 }
                                 for (DocumentSnapshot doc: documentSnapshots ){
                                   String password3 = doc.getString("Password");
                                     if(password2.equals(password3)) {
                                         Intent intent2 = new Intent("com.example.pc.complainform.Log_In_Screen");
                                         startActivity(intent2);
                                         finish();
                                     }
                                     else {
                                         Toast.makeText(sign_in.this, "Username/Password Combination is incorrect", Toast.LENGTH_SHORT).show();

                                     }                            }
                             }
                         });
                     }
                 }
         );
    }

}
