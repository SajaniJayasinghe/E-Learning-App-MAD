package com.example.mynew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class R_allretrive extends AppCompatActivity {

    RecyclerView recyclerView;
    R_MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rallretrive);

        recyclerView = (RecyclerView) findViewById(R.id.Rrv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<R_MainModel> options =
                new FirebaseRecyclerOptions.Builder<R_MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Subjects"), R_MainModel.class)
                        .build();

        mainAdapter = new R_MainAdapter(options);
        recyclerView.setAdapter(mainAdapter);

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
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

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
                                orderByChild("subject_title").startAt(str).endAt(str + "~"), R_MainModel.class)
                        .build();

        mainAdapter = new R_MainAdapter(options);
        mainAdapter.startListening();
        recyclerView.setAdapter(mainAdapter);

    }
}