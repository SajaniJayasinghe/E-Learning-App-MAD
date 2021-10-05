package com.example.mynew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class R_back_Calculator extends AppCompatActivity {

    //declaration
    EditText number1, number2;
    TextView result;
    Button add,subtract,multiply,divide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        number1 = (EditText)findViewById(R.id.Ran1);
        number2 = (EditText)findViewById(R.id.Ran2);

        result = (TextView)findViewById(R.id.Rantext);

        add = (Button)findViewById(R.id.Ranbutton);
        subtract = (Button)findViewById(R.id.Ranbutton1);
        multiply = (Button)findViewById(R.id.Ranbutton2);
        divide = (Button)findViewById(R.id.Ranbutton3);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(number1.getText().length()!=0 && number2.getText().length() !=0){
                    double n1 = Double.parseDouble(number1.getText().toString());
                    double n2 = Double.parseDouble(number2.getText().toString());

                    double res = n1 + n2;

                    result.setText(String.valueOf(res));
                }
                else
                {
                    Toast.makeText(R_back_Calculator.this, "Please Enter the numbers properly", Toast.LENGTH_SHORT).show();
                }

            }
        });
        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(number1.getText().length()!=0 && number2.getText().length() !=0){
                    double n1 = Double.parseDouble(number1.getText().toString());
                    double n2 = Double.parseDouble(number2.getText().toString());

                    double res = n1 - n2;

                    result.setText(String.valueOf(res));
                }
                else
                {
                    Toast.makeText(R_back_Calculator.this, "Please Enter the numbers properly", Toast.LENGTH_SHORT).show();
                }

            }
        });
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(number1.getText().length()!=0 && number2.getText().length() !=0){
                    double n1 = Double.parseDouble(number1.getText().toString());
                    double n2 = Double.parseDouble(number2.getText().toString());

                    double res = n1 * n2;

                    result.setText(String.valueOf(res));
                }
                else
                {
                    Toast.makeText(R_back_Calculator.this, "Please Enter the numbers properly", Toast.LENGTH_SHORT).show();
                }

            }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(number1.getText().length()!=0 && number2.getText().length() !=0){
                    double n1 = Double.parseDouble(number1.getText().toString());
                    double n2 = Double.parseDouble(number2.getText().toString());

                    double res = n1 / n2;

                    result.setText(String.valueOf(res));
                }
                else
                {
                    Toast.makeText(R_back_Calculator.this, "Please Enter the numbers properly", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
