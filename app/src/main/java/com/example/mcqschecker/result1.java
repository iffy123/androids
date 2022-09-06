package com.example.mcqschecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class result1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result1);

        String Rq = getIntent().getStringExtra("True");
        String Wq = getIntent().getStringExtra("False");
        TextView totalq= (TextView) findViewById(R.id.answers);
        TextView rr= (TextView) findViewById(R.id.right);
        TextView ww= (TextView) findViewById(R.id.wrong);
        rr.setText(Rq);
        ww.setText(Wq);

        Button comap=findViewById(R.id.comp);
        comap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(result1.this,ActOne.class);
                startActivity(intent);
            }
        });

    }
}