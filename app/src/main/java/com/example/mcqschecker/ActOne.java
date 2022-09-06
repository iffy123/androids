package com.example.mcqschecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.Arrays;



public class ActOne extends AppCompatActivity {
    String deepEquals;
    String tempPre;
    String tempnext;
    static int right=0;
    static int wrong=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_one);
        Button btn1=findViewById(R.id.Scanner);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(ActOne.this,Scanner.class);
                startActivity(i);


            }
        });






        String input = getIntent().getStringExtra("Mcqnum");
        int totalQuestion=Integer.parseInt(input);
        RadioButton[][] PreKey = creatButton(totalQuestion);


        Button btn = (Button) findViewById(R.id.button);
        LinearLayout mainl = (LinearLayout) findViewById(R.id.linerone);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mainl.removeAllViews();
                RadioButton[][] checkkey;
                checkkey = creatButton(totalQuestion);
                Button nextbt = new Button(ActOne.this);
                nextbt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                nextbt.setText("Next");
                mainl.addView(nextbt);
                btn.setText("Put the result of student");
                btn.setBackgroundColor(Color.TRANSPARENT);
                nextbt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int result = 0;
                        for (int i = 0; i < PreKey.length; i++)
                        {
                            Boolean[] teampre = new Boolean[4];
                            Boolean[] teamnext = new Boolean[4];

                            teampre[0] = PreKey[i][0].isChecked();
                            teampre[1] = PreKey[i][1].isChecked();
                            teampre[2] = PreKey[i][2].isChecked();
                            teampre[3] = PreKey[i][3].isChecked();


                            teamnext[0] = checkkey[i][0].isChecked();
                            teamnext[1] = checkkey[i][1].isChecked();
                            teamnext[2] = checkkey[i][2].isChecked();
                            teamnext[3] = checkkey[i][3].isChecked();
                            if (Arrays.deepEquals(teampre,teamnext)){
                                result = result +1;
                            }




                        }
                        Intent intent=new Intent(ActOne.this,result1.class);
                        intent.putExtra("True",String.valueOf(result));
                        intent.putExtra("False",String.valueOf(totalQuestion-result));
                        startActivity(intent);
//                        System.out.println("true:" + result + "\nfalse:" + (totalQuestion-result));


                    }

                });
            }
        });

    }




    private RadioButton[][] creatButton(int totalQuestions) {

        LinearLayout mainl = (LinearLayout) findViewById(R.id.linerone);
        RadioButton[][] radioButtons = new RadioButton[totalQuestions][4];


        for (int i = 0; i < radioButtons.length; i++) {


            System.out.println(i);
            RadioGroup rgroup = new RadioGroup(this);
            rgroup.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            rgroup.setOrientation(LinearLayout.HORIZONTAL);


            TextView label = new TextView(this);
            label.setText((CharSequence) String.valueOf(i + 1));
            label.setTextSize(30);
            label.setTextColor(Color.WHITE);
            label.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            label.setPadding(20 ,0,0,0);

            RadioButton rb1 = new RadioButton(this);
            rb1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            rb1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            rb1.setText((CharSequence) "A");
            rb1.setTextSize(20);
            rb1.setTextColor(Color.WHITE);


            RadioButton rb2 = new RadioButton(this);
            rb2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            rb2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            rb2.setText((CharSequence) "B");
            rb2.setTextSize(20);
            rb2.setTextColor(Color.WHITE);

            RadioButton rb3 = new RadioButton(this);
            rb3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            rb3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            rb3.setText((CharSequence) "C");
            rb3.setTextSize(20);
            rb3.setTextColor(Color.WHITE);
            RadioButton rb4 = new RadioButton(this);
            rb4.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            rb4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            rb4.setText((CharSequence) "D");
            rb4.setTextSize(20);

            rb4.setTextColor(Color.WHITE);
            rgroup.addView(label);
            rgroup.addView(rb1);
            rgroup.addView(rb2);
            rgroup.addView(rb3);
            rgroup.addView(rb4);

            radioButtons[i][0] = rb1;
            radioButtons[i][1] = rb2;
            radioButtons[i][2] = rb3;
            radioButtons[i][3] = rb4;


            mainl.addView(rgroup);

        }
        return radioButtons;


    }
}