package com.example.pc.complainform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.awt.font.TextAttribute;

/**
 * Created by PC on 12/31/2017.
 */

public class navigation_header extends AppCompatActivity{

    private static final String TAG = "FireLog";
    private TextView welcome_text;
    private FirebaseFirestore mFirebaseFirestore2;
    private EditText username;
    private Button sign_in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_header);

        welcome_text = (TextView) findViewById(R.id.welcome_text);
        mFirebaseFirestore2 = FirebaseFirestore.getInstance();
        username = (EditText)findViewById(R.id.username1);
        sign_in = (Button)findViewById(R.id.sign_in2);
    OnclickListener();
    }

        public void OnclickListener(){
            sign_in.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               String username2 = username.getText().toString();
                                               mFirebaseFirestore2.collection(username2).addSnapshotListener(new EventListener<QuerySnapshot>() {
                                                   @Override
                                                   public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                                                       if (e != null) {
                                                           Log.d(TAG, "Error : " + e.getMessage());
                                                       }
                                                       for (DocumentSnapshot doc : documentSnapshots) {
                                                           String enrollment_no = doc.getString("Enrollment No.");
                                                           welcome_text.setText(enrollment_no);
                                                       }
                                                   }
                                               });
                                           }
                                       }
            );

    }
}
