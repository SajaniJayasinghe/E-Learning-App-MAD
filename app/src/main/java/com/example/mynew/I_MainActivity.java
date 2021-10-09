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

public class I_MainActivity extends AppCompatActivity {
    
    //variable decclarartion
    RecyclerView recyclerView;
    I_MainAdapter mainAdapter;

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imain);

        //variable initiliztion
        recyclerView = (RecyclerView)findViewById(R.id.addrv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        //fetch data from the database
        FirebaseRecyclerOptions<I_MainModal> options =
                new FirebaseRecyclerOptions.Builder<I_MainModal>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("advertisements"), I_MainModal.class)
                        .build();

        mainAdapter = new I_MainAdapter(options);
        recyclerView.setAdapter(mainAdapter);
        
        //floating add button
        floatingActionButton =(FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), I_AddActivity.class));
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
        mainAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView =(SearchView)item.getActionView();

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

    //search function
    private void txtSearch(String str)
    {
        //firebase reference
        FirebaseRecyclerOptions<I_MainModal> options =
                new FirebaseRecyclerOptions.Builder<I_MainModal>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("advertisements")
                                .orderByChild("aTitle").startAt(str).endAt(str+"~"), I_MainModal.class)
                        .build();

        mainAdapter = new I_MainAdapter(options);
        mainAdapter.startListening();
        recyclerView.setAdapter(mainAdapter);
    }
}
