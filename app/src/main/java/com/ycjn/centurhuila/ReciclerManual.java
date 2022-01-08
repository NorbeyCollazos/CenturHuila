package com.ycjn.centurhuila;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ycjn.centurhuila.model.MyAdapterManual;

import java.util.ArrayList;

public class ReciclerManual extends AppCompatActivity {

    DatabaseReference ref;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recicler_manual);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.azul_oscuro)); //Define color blanco.
        }

        ImageButton atras = (ImageButton) findViewById(R.id.flecha);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

       /*CardView pantallas = (CardView)findViewById(R.id.cardonbornig);
        pantallas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent panta = new Intent(ReciclerManual.this,ManualUsuario.class);
                startActivity(panta);
                finish();
            }
        });
        */


        ref = FirebaseDatabase.getInstance().getReference().child("CenturHuila/ManualUsuario");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String fileName = dataSnapshot.getKey();
                String url = dataSnapshot.getValue(String.class);

                ((MyAdapterManual)recyclerView.getAdapter()).update(fileName,url);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        recyclerView = findViewById(R.id.reciclermanual);

        recyclerView.setLayoutManager(new LinearLayoutManager(ReciclerManual.this));
        MyAdapterManual myAdapterManual = new MyAdapterManual(recyclerView, ReciclerManual.this, new ArrayList<String>(), new ArrayList<String>());
        recyclerView.setAdapter(myAdapterManual);
    }
}
