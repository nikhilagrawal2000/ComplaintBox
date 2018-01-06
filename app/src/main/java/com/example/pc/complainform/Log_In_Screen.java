package com.example.pc.complainform;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Log_In_Screen extends AppCompatActivity {

    private static final String TAG = "FireLog";
    private DrawerLayout mdrawerlayout;
    private ActionBarDrawerToggle mtoggle;
    private Button new_complaint;
    NavigationView navigationView;
    private RecyclerView mcomplain;
    private FirebaseFirestore mFirebaseFirestore;
    private List<Complaints> complaintsList;
    private ComplaintsAdapter complaintsAdapter;
    private EditText enrollment_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log__in__screen);

        enrollment_no = (EditText)findViewById(R.id.enrollment_no);
        mcomplain = (RecyclerView)findViewById(R.id.mcomplains);
        mcomplain.setHasFixedSize(true);
        mcomplain.setLayoutManager(new LinearLayoutManager(this));
        mcomplain.setAdapter(complaintsAdapter);
        mFirebaseFirestore = FirebaseFirestore.getInstance();
        complaintsList = new ArrayList<>();
        complaintsAdapter = new ComplaintsAdapter(complaintsList);
        OnClickListener4();

        final String enrollment_no1 = enrollment_no.getText().toString();

    mdrawerlayout = (DrawerLayout)findViewById(R.id.drawerLayout);
    mtoggle = new ActionBarDrawerToggle(this, mdrawerlayout,R.string.open, R.string.close);
    navigationView = (NavigationView)findViewById(R.id.navigation_view);
    mdrawerlayout.addDrawerListener(mtoggle);
    mtoggle.syncState();
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    mFirebaseFirestore.collection("complaints").addSnapshotListener(new EventListener<QuerySnapshot>() {
        @Override
        public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
            if (e != null) {
                Log.d(TAG, "Error :" + e.getMessage());
            }
            for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                if (doc.getType() == DocumentChange.Type.ADDED) {

                    Complaints complaint = doc.getDocument().toObject(Complaints.class);
                    complaintsList.add(complaint);

                }
            }
        }
    });
    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem menuitem) {
            int id = menuitem.getItemId();
            switch(id){
                case R.id.account:
                    Intent i1 = new Intent(Log_In_Screen.this, Log_In_Screen.class);
                    startActivity(i1);
                    break;
                case R.id.solved_complaints:
                    //Do some thing here
                    // add navigation drawer item onclick method here
                    break;
                case R.id.unsolved_complaints:
                    //Do some thing here
                    // add navigation drawer item onclick method here
                    break;
                case R.id.logout:
                    Intent i4 = new Intent(Log_In_Screen.this, sign_in.class);
                    startActivity(i4);
                    break;
            }
            return false;
        }
    });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mtoggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void OnClickListener4(){

        new_complaint = (Button)findViewById(R.id.new_complaint);
        new_complaint.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent4 = new Intent("com.example.pc.complainform.complain_form");
                        startActivity(intent4);
                    }
                }
        );
    }
}
