package com.miwan.tiktaktoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    private boolean turn=true;
    private Button returnBtn;
    private Button resetBtn;
    private TextView[]txtviews=new TextView[9];
    private String[] board=new String[9];
    private TextView winnertext;
    private int round=0;
    private int winNote=0;
    List<Integer> available=new ArrayList<>();
    int[][] winningPos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{6,4,2}};
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
        for (int i=0;i<9;i++){
            txtviews[i].setText("");
            txtviews[i].setClickable(true);
            txtviews[i].setTextColor(Color.parseColor("#ffffff"));
            board[i]="";
        }
        winnertext.setVisibility(View.INVISIBLE);
        winnertext.setText("");
        round=0;
    }

    public void returnMenu(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void register(String x,View view){
        if (x.equals("X")){
            board[Character.getNumericValue((getResources().getResourceEntryName(view.getId())).charAt(8))-1]="x";
        }
        else{
            board[Character.getNumericValue((getResources().getResourceEntryName(view.getId())).charAt(8))-1]="o";
        }
    }
    @Override
    public void onClick(View v) {
        if (turn) {
            ((TextView) v).setText("X");
            ((TextView) v).setTextColor(Color.parseColor("#479e61"));
            register("X",v);
        }
        else {
            ((TextView) v).setText("O");
            ((TextView) v).setTextColor(Color.parseColor("#acb32e"));
            register("O",v);
        }
        if(checkWinner()) {
            if (turn) {
                winnertext.setText("X won");
            }
            else
                winnertext.setText("O won");
            for (int i=0;i<9;i++){
                txtviews[i].setClickable(false);
                if (i!=winningPos[winNote][0]&&i!=winningPos[winNote][1]&&i!=winningPos[winNote][2]) {
                    txtviews[i].setTextColor(Color.parseColor("#615f58"));
                }
            }
            winnertext.setVisibility(View.VISIBLE);
        }
        else {
            round++;
            if (round==9&&winnertext.getText().toString().equals("")){
                winnertext.setText("Draw");
                winnertext.setVisibility(View.VISIBLE);
            }
            v.setClickable(false);
            v.setFocusable(false);
            turn = !turn;
        }
    }
    public boolean checkWinner(){
        for (int i = 0; i < winningPos.length; i++) {
            if ((board[winningPos[i][0]].equals("x") && board[winningPos[i][1]].equals("x") && board[winningPos[i][2]].equals("x"))||(board[winningPos[i][0]].equals("o") && board[winningPos[i][1]].equals("o") && board[winningPos[i][2]].equals("o"))) {
                winNote=i;
                return true;
            }
        }
        return false;
    }
}

