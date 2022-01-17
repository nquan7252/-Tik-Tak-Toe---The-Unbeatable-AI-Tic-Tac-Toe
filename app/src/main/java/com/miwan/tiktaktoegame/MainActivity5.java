package com.miwan.tiktaktoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity5 extends AppCompatActivity implements View.OnClickListener {
    private boolean turn=true;
    private boolean tie=false;
    private Button returnBtn;
    private Button resetBtn;
    private TextView[]txtviews=new TextView[9];
    private TextView winnertext;
    private int round=0;
    private int winNote=0;
    private String board[]=new String[9];
    List<Integer> available=new ArrayList<>();
    int[][] winningPos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{6,4,2}};

    Random random;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        winnertext=findViewById(R.id.winnertext);
        resetBtn=findViewById(R.id.resetBtn);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        for (int i=0;i<9;i++){
            available.add(i);
            board[i]="";
        }
        for (int i = 1; i <= 9; i++) {
            String txtId = "textView" + i;
            txtviews[i - 1] = (TextView) findViewById(getResources().getIdentifier(txtId, "id", getPackageName()));
            txtviews[i - 1].setOnClickListener(this);

        }

        returnBtn = findViewById(R.id.returnBtn);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnMenu();
            }
        });
    }

    public void reset(){
        available.clear();
        for (int i=0;i<9;i++){
            txtviews[i].setText("");
            txtviews[i].setClickable(true);
            txtviews[i].setTextColor(Color.parseColor("#ffffff"));
            board[i]="";
            available.add(i);
        }
        winnertext.setVisibility(View.INVISIBLE);
        winnertext.setText("");
        round=0;
        turn=true;
        tie=false;
    }

    public void returnMenu(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void register(String x,View view){
        if (x.equals("X")){
            available.remove(new Integer(Character.getNumericValue((getResources().getResourceEntryName(view.getId())).charAt(8))-1));
            board[Character.getNumericValue((getResources().getResourceEntryName(view.getId())).charAt(8))-1]="x";
        }
        else{
            available.remove(new Integer(Character.getNumericValue((getResources().getResourceEntryName(view.getId())).charAt(8))-1));
            board[Character.getNumericValue((getResources().getResourceEntryName(view.getId())).charAt(8))-1]="o";

        }
    }
    @Override
    public void onClick(View v) {
        if (turn) {
            ((TextView) v).setText("X");
            ((TextView) v).setTextColor(Color.parseColor("#479e61"));
            register("X", v);
            random = new Random();
            if (checkWinner()) {
                winnertext.setText("X won");
                winnertext.setVisibility(View.VISIBLE);
                for (int i = 0; i < 9; i++) {
                    txtviews[i].setClickable(false);
                    if (i!=winningPos[winNote][0]&&i!=winningPos[winNote][1]&&i!=winningPos[winNote][2]) {
                        txtviews[i].setTextColor(Color.parseColor("#615f58"));
                    }
                }
            }
            else {
                if (available.isEmpty()){
                    winnertext.setText("Draw");
                    winnertext.setVisibility(View.VISIBLE);
                }
                else{
                    new CountDownTimer(1000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            for (int i=0;i<9;i++){
                                txtviews[i].setClickable(false);
                            }
                        }

                        public void onFinish() {
                            for (int i=0;i<available.size();i++){
                                txtviews[available.get(i)].setClickable(true);
                            }
                            round++;
                            turn = !turn;
                            txtviews[available.get(random.nextInt(available.size()))].callOnClick();
                        }
                    }.start();
                }
            }
        }
        else{
            ((TextView) v).setText("O");
            ((TextView) v).setTextColor(Color.parseColor("#acb32e"));
            register("O", v);
            if (checkWinner()) {
                winnertext.setText("O won");
                winnertext.setVisibility(View.VISIBLE);
                for (int i = 0; i < 9; i++) {
                    txtviews[i].setClickable(false);
                    if (i!=winningPos[winNote][0]&&i!=winningPos[winNote][1]&&i!=winningPos[winNote][2]) {
                        txtviews[i].setTextColor(Color.parseColor("#615f58"));
                    }
                }
            } else {
                turn = !turn;
                round++;
                v.setClickable(false);
                v.setFocusable(false);
            }
        }
        v.setClickable(false);
    }

    public boolean checkWinner () {
        for (int i = 0; i < winningPos.length; i++) {
            if ((board[winningPos[i][0]].equals("x") && board[winningPos[i][1]].equals("x") && board[winningPos[i][2]].equals("x"))) {
                winNote=i;
                return true;
            }
            if ((board[winningPos[i][0]].equals("o") && board[winningPos[i][1]].equals("o") && board[winningPos[i][2]].equals("o"))) {
                winNote=i;
                return true;
            }
        }
        return false;
    }
    }


