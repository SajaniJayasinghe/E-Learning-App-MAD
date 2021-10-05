package com.example.mynew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class S_BookDetails extends AppCompatActivity {

    ImageView imgurl;
    TextView bookName ,author,description;
    String bookID =  "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sbook_details);

        bookID = getIntent().getStringExtra("pid");
        
        imgurl = (ImageView)findViewById(R.id.book_user_view_image);
        bookName = (TextView)findViewById(R.id.book_user_view_book_name);
        author = (TextView)findViewById(R.id.book_user_view_author);
        description =(TextView)findViewById(R.id.book_user_view_description);
        
        getBookDetails(bookID);
        
    }

    private void getBookDetails(String bookID) {
        DatabaseReference bookRef = FirebaseDatabase.getInstance().getReference().child("Book");
        bookRef.child(bookID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 if(snapshot.exists()){
                     S_MainBookModel s_mainBookModel= snapshot.getValue(S_MainBookModel.class);

                     bookName.setText(s_mainBookModel.getBookName());
                     author.setText(s_mainBookModel.getAuthor());
                     description.setText(s_mainBookModel.getDescription());
                     Picasso.get().load(s_mainBookModel.getImgurl()).into(imgurl);
                 }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}