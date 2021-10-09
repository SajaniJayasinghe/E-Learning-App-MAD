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

public class S_BookStore extends AppCompatActivity {

    RecyclerView recyclerView;
    S_MainBookUserViewAdapter mainBookUserViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_store);

        recyclerView = (RecyclerView)findViewById(R.id.bv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

  //Data fetch in the database
        FirebaseRecyclerOptions<S_MainBookModel> options =
                new FirebaseRecyclerOptions.Builder<S_MainBookModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Book"), S_MainBookModel.class)
                        .build();

        mainBookUserViewAdapter = new S_MainBookUserViewAdapter(options);
        recyclerView.setAdapter(mainBookUserViewAdapter);
    }

         @Override
           protected void onStart() {
             super.onStart();
             mainBookUserViewAdapter.startListening();
    }

         @Override
           protected void onStop() {
              super.onStop();
              mainBookUserViewAdapter.stopListening();
    }

    //search book
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
    //Search Books
    private void txtSearch(String str){
        FirebaseRecyclerOptions<S_MainBookModel>options =
                new FirebaseRecyclerOptions.Builder<S_MainBookModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Book").orderByChild("bookName").startAt(str).endAt(str+"~"), S_MainBookModel.class)
                        .build();

        mainBookUserViewAdapter = new S_MainBookUserViewAdapter(options);
        mainBookUserViewAdapter.startListening();
        recyclerView.setAdapter(mainBookUserViewAdapter);
    }

}