package com.example.mynew;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;

public class I_MathTable extends AppCompatActivity {
    
    //variable decclaration
    EditText ed_table;
    Button btn_submit;
    TextView tv_table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_table);
        
        //variable initialization
        ed_table = findViewById(R.id.ed_table);
        btn_submit = findViewById((R.id.btn_submit));
        tv_table = findViewById(R.id.tv_table);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }catch(Exception e) {
                }
                if(ed_table.getText().toString().isEmpty()) {
                    ed_table.setError("Enter Number");
                }else{
                    int n=Integer.parseInt(ed_table.getText().toString());
                    int table;
                    ArrayList<Integer>arrayList =new ArrayList<>();

                    for(int i=0;i<11;i++) {
                        table = n * i;
                        arrayList.add(table);
                    }
                    if(arrayList.size()>0){
                        tv_table.setText(
                                ed_table.getText().toString()+" *1 ="+arrayList.get(1)+System.getProperty("line.separator")+
                                        ed_table.getText().toString()+" *2 ="+arrayList.get(2)+System.getProperty("line.separator")+
                                        ed_table.getText().toString()+" *3 ="+arrayList.get(3) +System.getProperty("line.separator")+
                                        ed_table.getText().toString()+" *4 ="+arrayList.get(4)+System.getProperty("line.separator")+
                                        ed_table.getText().toString()+" *5 ="+arrayList.get(5)+System.getProperty("line.separator")+
                                        ed_table.getText().toString()+" *6 ="+arrayList.get(6)+System.getProperty("line.separator")+
                                        ed_table.getText().toString()+" *7 ="+arrayList.get(7)+System.getProperty("line.separator")+
                                        ed_table.getText().toString()+" *8 ="+arrayList.get(8)+System.getProperty("line.separator")+
                                        ed_table.getText().toString()+" *9 ="+arrayList.get(9)+System.getProperty("line.separator")+
                                        ed_table.getText().toString()+" *10 ="+arrayList.get(10)+System.getProperty("line.separator")

                        );
                    }
                }
            }
        });
    }
}
