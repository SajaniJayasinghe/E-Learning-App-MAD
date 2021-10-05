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

public class A_back_QuestionsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    A_back_QuestionAdapter questionAdapter;

    FloatingActionButton floatingActionButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager( this));

        FirebaseRecyclerOptions<A_QuestionModel> options =
                new FirebaseRecyclerOptions.Builder<A_QuestionModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("QUIZ"), A_QuestionModel.class)
                        .build();

        questionAdapter = new A_back_QuestionAdapter(options);
        recyclerView.setAdapter(questionAdapter);

        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), A_back_AddActivity.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        questionAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        questionAdapter.stopListening();
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
        FirebaseRecyclerOptions<A_QuestionModel> options =
                new FirebaseRecyclerOptions.Builder<A_QuestionModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("QUIZ").orderByChild("question").startAt(str).endAt(str+"~"), A_QuestionModel.class)
                        .build();

        questionAdapter = new A_back_QuestionAdapter(options);
        questionAdapter.startListening();
        recyclerView.setAdapter(questionAdapter);
    }
}