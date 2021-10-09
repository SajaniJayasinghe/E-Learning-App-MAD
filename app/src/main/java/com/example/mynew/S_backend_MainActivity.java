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

public class S_backend_MainActivity extends AppCompatActivity {

    //variable declaration
    RecyclerView recyclerView;
    S_backend_MainBookAdapter mainBookAdapter;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sbackend_main);

        //variable initialization
        recyclerView = (RecyclerView)findViewById(R.id.sv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

  //data fetch from the database
        FirebaseRecyclerOptions<S_MainBookModel>options =
                new FirebaseRecyclerOptions.Builder<S_MainBookModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Book"), S_MainBookModel.class)
                        .build();

        mainBookAdapter = new S_backend_MainBookAdapter(options);
        recyclerView.setAdapter(mainBookAdapter);

  //Add button (floatingActionButton)
        floatingActionButton = findViewById(R.id.book_addButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), S_backend_AddNewBook.class));
            }
        });
    }
        @Override
        protected void onStart() {
          super.onStart();
          mainBookAdapter.startListening();
    }
        @Override
        protected void onStop() {
          super.onStop();
          mainBookAdapter.stopListening();
    }

    //search the book using first character is Capital letter
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.search_book,menu);
            MenuItem item = menu.findItem(R.id.search_book);
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

    //Text search the book Name
         private void txtSearch(String str){
             FirebaseRecyclerOptions<S_MainBookModel>options =
                new FirebaseRecyclerOptions.Builder<S_MainBookModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Book").orderByChild("bookName").startAt(str).endAt(str+"~"), S_MainBookModel.class)
                        .build();
        mainBookAdapter = new S_backend_MainBookAdapter(options);
        mainBookAdapter.startListening();
        recyclerView.setAdapter(mainBookAdapter);
    }
}