package com.example.mynew;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class A_back_BiologyActivity extends AppCompatActivity {
    //declaring variables
    RecyclerView recyclerView;
    A_back_BiologyAdapter biologyAdapter;

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biology);

        recyclerView = (RecyclerView)findViewById(R.id.rv3);
        recyclerView.setLayoutManager(new LinearLayoutManager( this));

        FirebaseRecyclerOptions<A_BiologyModel> options =
                new FirebaseRecyclerOptions.Builder<A_BiologyModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Biology"), A_BiologyModel.class)
                        .build();

        biologyAdapter = new A_back_BiologyAdapter(options);
        recyclerView.setAdapter(biologyAdapter);

        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), A_back_AddBioActivity.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        biologyAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        biologyAdapter.stopListening();
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

    private void txtSearch(String str)
    {
        FirebaseRecyclerOptions<A_BiologyModel> options =
                new FirebaseRecyclerOptions.Builder<A_BiologyModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Biology").orderByChild("question").startAt(str).endAt(str+"~"), A_BiologyModel.class)
                        .build();

        biologyAdapter = new A_back_BiologyAdapter(options);
        biologyAdapter.startListening();
        recyclerView.setAdapter(biologyAdapter);
    }
}
