package com.example.mynew;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

public class R_back_AddActivity extends AppCompatActivity {

    EditText name, title, desciption, siurl;
    Button btnAdd, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name = (EditText) findViewById(R.id.RsubName);
        title = (EditText) findViewById(R.id.RsubtitleName);
        desciption = (EditText) findViewById(R.id.Rsubdescription);
        siurl = (EditText) findViewById(R.id.RimageUrl);

        btnAdd = (Button) findViewById(R.id.RbtnAdd);
        btnBack = (Button) findViewById(R.id.RbtnBack);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               performAuth();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    //validation part
    private void performAuth() {
        String sname = name.getText().toString();
        String stitle = title.getText().toString();
        String sdescription = desciption.getText().toString();
        String saurl = siurl.getText().toString();

        if (sname.isEmpty() || sname.length() < 4)
        {
            showError(name, "Subject name is not valid !!");
        }
        else if (stitle.isEmpty())
        {
            showError(title,"Subject title is not valid !!");
        }
        else if (sdescription.isEmpty())
        {
            showError(desciption,"Subject Description is not valid !!");
        }
        else if (saurl.isEmpty())
        {
            showError(siurl,"Image Url is not valid !!");
        }
        else
        {
            insertData();
            clearAll();
        }
    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }


    private void insertData() {
        Map<String, Object> map = new HashMap<>();
        map.put("subject_name", name.getText().toString());
        map.put("subject_title", title.getText().toString());
        map.put("subject_description", desciption.getText().toString());
        map.put("surl", siurl.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Subjects").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(R_back_AddActivity.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(R_back_AddActivity.this, "Error While Insertion..!", Toast.LENGTH_SHORT).show();
                    }
                });

    }
    private void clearAll()
    {
        name.setText("");
        title.setText("");
        desciption.setText("");
        siurl.setText("");

    }
}