package com.example.vishal.newsapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginUser extends Activity  {
    Button button1,button2;
    EditText editText1,editText2;

    TextView tx1;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1=(Button)findViewById(R.id.button);
        editText1=(EditText)findViewById(R.id.editText);
        editText2=(EditText)findViewById(R.id.editText2);

        button2=(Button)findViewById(R.id.button2);
        tx1=(TextView)findViewById(R.id.textView3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText1.getText().toString().equals("vishal") &&

                        editText2.getText().toString().equals("vishal")) {
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong password", Toast.LENGTH_SHORT).show();

                    tx1.setVisibility(View.VISIBLE);
                    tx1.setBackgroundColor(Color.RED);
                    counter--;
                    tx1.setText(Integer.toString(counter));

                    if (counter == 0) {
                        button2.setEnabled(false);
                    }
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }


    public void methodApe(View view) {

    }
}
