package com.example.mynew;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class S_backend_AddNewBook extends AppCompatActivity {

    EditText author,bookName,imgurl,stream,description;
    Button btnAdd,btnBack;
    private String saveCurrentDate,saveCurrentTime,bookRandomKey;
    private DatabaseReference Bookref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sbackend_add_new_book);

        bookName = (EditText)findViewById(R.id.bookname);
        author =(EditText)findViewById(R.id.book_authorname);
        stream = (EditText)findViewById(R.id.book_stream);
        description =(EditText)findViewById(R.id.book_description);
        imgurl = (EditText)findViewById(R.id.book_image);
        btnAdd = (Button)findViewById(R.id.book_addButton);
        btnBack = (Button)findViewById(R.id.book_cancelButton);

        Bookref = FirebaseDatabase.getInstance().getReference().child("Book");

  //Add the data via add button
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                clearAll();
            }
        });
  //Back button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

  //insert data and store the fire base
    private  void insertData(){
        storeBookDetails();
        Map<String,Object> map = new HashMap<>();
        map.put("pid", bookRandomKey);
        map.put("bookName",bookName.getText().toString());
        map.put("author",author.getText().toString());
        map.put("stream",stream.getText().toString());
        map.put("description",description.getText().toString());
        map.put("imgurl",imgurl.getText().toString());

        Bookref.child(bookRandomKey).updateChildren(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(S_backend_AddNewBook.this,"Book added Successfully!!",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Toast.makeText(S_backend_AddNewBook.this,"Error while Insertion. ",Toast.LENGTH_SHORT).show();
            }
        });
    }
  //store the book details create the id using date and time
    private void storeBookDetails() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd,yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());
        bookRandomKey = saveCurrentDate+saveCurrentTime;
    }

  //Clear add details
    private void clearAll(){
        bookName.setText("");
        author.setText("");
        stream.setText("");
        description.setText("");
        imgurl.setText("");
    }
}