package com.example.mcqschecker;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.Arrays;

import java.security.spec.DSAPrivateKeySpec;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText numofQ = findViewById(R.id.inputMCQN);
        Button next=findViewById(R.id.MainNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String num=numofQ.getText().toString();
                int f=Integer.parseInt(num);
                Intent intent=new Intent(MainActivity.this,ActOne.class);
                intent.putExtra("Mcqnum",String.valueOf(f));
                startActivity(intent);



            }
        });





    }

}
