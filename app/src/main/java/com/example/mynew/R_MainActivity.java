package com.example.mynew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class R_MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    R_MainAdapter mainAdapter;

    FloatingActionButton floatingActionButton;
    FloatingActionButton floatingActionButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rmain);


        recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<R_MainModel> options =
                new FirebaseRecyclerOptions.Builder<R_MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Subjects"),R_MainModel.class)
                        .build();

        mainAdapter = new R_MainAdapter(options);
        recyclerView.setAdapter(mainAdapter);



        floatingActionButton = (FloatingActionButton)findViewById(R.id.RfloatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), R_AddActivity.class));
            }
        });
        floatingActionButton1 = (FloatingActionButton)findViewById(R.id.R1floatingActionButton);
        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), R_Calculator.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.startListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void txtSearch(String str) {

        FirebaseRecyclerOptions<R_MainModel> options =
                new FirebaseRecyclerOptions.Builder<R_MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Subjects").
                                orderByChild("subject_name").startAt(str).endAt(str+"~"), R_MainModel.class)
                        .build();

        mainAdapter = new R_MainAdapter(options);
        mainAdapter.startListening();
        recyclerView.setAdapter(mainAdapter);

    }
}