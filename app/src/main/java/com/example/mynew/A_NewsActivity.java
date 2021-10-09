package com.example.mynew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class A_NewsActivity extends AppCompatActivity {

    //declaring variables
    RecyclerView recyclerView;
    A_NewsAdapter a_newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anews);

        recyclerView = (RecyclerView)findViewById(R.id.rv1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        
        //Fetch Data from Firebase Database
        FirebaseRecyclerOptions<A_NewsModel> options =
                new FirebaseRecyclerOptions.Builder<A_NewsModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("News"), A_NewsModel.class)
                        .build();

        a_newsAdapter = new A_NewsAdapter(options);
        recyclerView.setAdapter(a_newsAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        a_newsAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        a_newsAdapter.stopListening();
    }
}
