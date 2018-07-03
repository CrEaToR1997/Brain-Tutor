package com.example.acer.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timer, sum, score, go, result, tit1, tit2;
    Button playAgain;
    GridLayout g;

    int r, ques = 0, correct = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = findViewById(R.id.timeView);
        sum = findViewById(R.id.equn);
        score = findViewById(R.id.score);
        result = findViewById(R.id.display);
        tit1 = findViewById(R.id.dis1);
        tit2 = findViewById(R.id.dis2);

        g = findViewById(R.id.gridLayout);

        playAgain = findViewById(R.id.playAgain);

        int x = RandomGenerator();
        int y = RandomGenerator();
        r = SetSum(x, y);
        SetGrid(r);

    }

    void SetGrid(int x){
        Random random = new Random();
        int ansPos = random.nextInt(4);
        for(int y = 0; y<g.getChildCount(); y++){
            TextView tv = (TextView)g.getChildAt(y);
            if(y == ansPos){
                tv.setText(Integer.toString(x));
            }else {
                int n = random.nextInt(60);
                if(n!=0){
                    tv.setText(Integer.toString(n));
                }else{
                    n = random.nextInt(60);
                    tv.setText(Integer.toString(n));
                }
            }
        }
    }

    int RandomGenerator(){
        Random ran = new Random();
        int ret = ran.nextInt(30);
        return ret;
    }

    int SetSum(int x, int y){
        String equation = Integer.toString(x)+" + "+Integer.toString(y);
        int summation = x+y;
        sum.setText(equation);
        return summation;
    }

   public void CheckAnswer(View view){
        ques ++;
        TextView t = (TextView)view;
        String s = t.getText().toString();
        result.setVisibility(View.VISIBLE);
        if(Integer.parseInt(s) == r){

            result.setText("Correct :-)");
            correct++;
        }else{
            result.setText("Wrong :-(");
        }
       score.setText(Integer.toString(correct)+"/"+Integer.toString(ques));
       int x = RandomGenerator();
       int y = RandomGenerator();
       r = SetSum(x, y);
       SetGrid(r);

    }

    public void StartGame(View view){
       tit1.setVisibility(View.INVISIBLE);
       tit2.setVisibility(View.INVISIBLE);
        view.setVisibility(View.INVISIBLE);
        timer.setVisibility(View.VISIBLE);
        sum.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        g.setVisibility(View.VISIBLE);

        StartTimer();

    }

    public void PlayAgain(View view){
        StartTimer();
        playAgain.setVisibility(View.INVISIBLE);
        score.setText("0/0");

        int x = RandomGenerator();
        int y = RandomGenerator();
        r = SetSum(x, y);
        SetGrid(r);

    }

    void UpdateTimer(int x){
        timer.setText(Integer.toString(x));
        x -= 1;
    }

    void StartTimer(){
        CountDownTimer countDownTimer =  new CountDownTimer(61000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                UpdateTimer((int)millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                g.setEnabled(false);
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();
    }
}
