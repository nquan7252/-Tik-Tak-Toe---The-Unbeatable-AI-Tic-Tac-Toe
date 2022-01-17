package com.miwan.tiktaktoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {
    TextView[][]textViews=new TextView[4][4];
    Button resetBtn,returnBtn;
    private boolean turn=false;
    private TextView winnertext;
    private int round=0;
    private int xrowcount=0;
    private int orowcount=0;
    private boolean havewinner=false;
    private int winNote=0;
    private int winNote2=0;
    private int[][][] winningPos= {
            {{0,0},{0,1},{0,2},{0,3}},
            {{1,0},{1,1},{1,2},{1,3}},
            {{2,0},{2,1},{2,2},{2,3}},
            {{3,0},{3,1},{3,2},{3,3}},

            {{0,0},{1,0},{2,0},{3,0}},
            {{0,1},{1,1},{2,1},{3,1}},
            {{0,2},{1,2},{2,2},{3,2}},
            {{0,3},{1,3},{2,3},{3,3}},

            {{0,0},{1,1},{2,2},{3,3}},
            {{3,0},{2,1},{1,2},{0,3}}
    };
    private int[][][] extraPtsPos={
            {{0,0},{0,1},{0,2}},
            {{0,1},{0,2},{0,3}},
            {{1,0},{1,1},{1,2}},
            {{1,1},{1,2},{1,3}},
            {{2,0},{2,1},{2,2}},
            {{2,1},{2,2},{2,3}},
            {{3,0},{3,1},{3,2}},
            {{3,1},{3,2},{3,3}},

            {{0,0},{1,0},{2,0}},
            {{1,0},{2,0},{3,0}},
            {{0,1},{1,1},{2,1}},
            {{1,1},{2,1},{3,1}},
            {{0,2},{1,2},{2,2}},
            {{1,2},{2,2},{3,2}},
            {{0,3},{1,3},{2,3}},
            {{1,3},{2,3},{3,3}},

            {{2,0},{1,1},{0,2}},
            {{3,1},{2,2},{1,3}},
            {{3,0},{2,1},{1,2}},
            {{2,1},{1,2},{0,3}},

            {{0,1},{1,2},{2,3}},
            {{1,0},{2,1},{3,2}},
            {{0,0},{1,1},{2,2}},
            {{1,1},{2,2},{3,3}}
    };
    private String board[][]=new String[4][4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        openDialog();
        winnertext=findViewById(R.id.winnertext);
        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++){
                String stringId="textView"+i+j;
                textViews[i][j]=findViewById(getResources().getIdentifier(stringId, "id", getPackageName()));
                textViews[i][j].setOnClickListener(this);
                board[i][j]="";
            }
        }
        resetBtn=findViewById(R.id.resetBtn);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<4;i++){
                    for(int j=0;j<4;j++){
                        board[i][j]="";
                        textViews[i][j].setText("");
                        textViews[i][j].setClickable(true);
                    }
                }
                round=0;
                winnertext.setText("");
            xrowcount=0;
            orowcount=0;
            havewinner=false;
            }
        });
        returnBtn=findViewById(R.id.returnBtn);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });
    }
    public void openMenu(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
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
        ((TextView)v).setClickable(false);
        round++;
        checkWinner();
        turn=!turn;
    }
    public void register(String x,View view){
        if (x.equals("X")){
            board[Character.getNumericValue((getResources().getResourceEntryName(view.getId())).charAt(8))][Character.getNumericValue((getResources().getResourceEntryName(view.getId())).charAt(9))]="x";
        }
        else{
            board[Character.getNumericValue((getResources().getResourceEntryName(view.getId())).charAt(8))][Character.getNumericValue((getResources().getResourceEntryName(view.getId())).charAt(9))]="o";
        }
    }
    public void checkWinner(){
        for (int i=0;i<=9;i++) {
            if (board[winningPos[i][0][0]][winningPos[i][0][1]].equals("x") && board[winningPos[i][1][0]][winningPos[i][1][1]].equals("x") && board[winningPos[i][2][0]][winningPos[i][2][1]].equals("x") && board[winningPos[i][3][0]][winningPos[i][3][1]].equals("x")) {
                winnertext.setVisibility(View.VISIBLE);
                winnertext.setText("X won absolutely");
                havewinner=true;
                for (int k = 0; k < 4; k++) {
                    for (int j = 0; j < 4; j++) {
                        textViews[k][j].setClickable(false);
                        textViews[k][j].setTextColor(Color.parseColor("#615f58"));
                        }
                    }
                textViews[winningPos[i][0][0]][winningPos[i][0][1]].setTextColor(Color.parseColor("#479e61"));
                textViews[winningPos[i][1][0]][winningPos[i][1][1]].setTextColor(Color.parseColor("#479e61"));
                textViews[winningPos[i][2][0]][winningPos[i][2][1]].setTextColor(Color.parseColor("#479e61"));
                textViews[winningPos[i][3][0]][winningPos[i][3][1]].setTextColor(Color.parseColor("#479e61"));
                }

            if (board[winningPos[i][0][0]][winningPos[i][0][1]].equals("o") && board[winningPos[i][1][0]][winningPos[i][1][1]].equals("o") && board[winningPos[i][2][0]][winningPos[i][2][1]].equals("o") && board[winningPos[i][3][0]][winningPos[i][3][1]].equals("o")) {
                winnertext.setVisibility(View.VISIBLE);
                winnertext.setText("O won absolutely");
                havewinner=true;
                for (int k = 0;k < 4; k++) {
                    for (int j = 0; j < 4; j++) {
                        textViews[k][j].setClickable(false);
                        textViews[k][j].setTextColor(Color.parseColor("#615f58"));
                    }
                }
                textViews[winningPos[i][0][0]][winningPos[i][0][1]].setTextColor(Color.parseColor("#acb32e"));
                textViews[winningPos[i][1][0]][winningPos[i][1][1]].setTextColor(Color.parseColor("#acb32e"));
                textViews[winningPos[i][2][0]][winningPos[i][2][1]].setTextColor(Color.parseColor("#acb32e"));
                textViews[winningPos[i][3][0]][winningPos[i][3][1]].setTextColor(Color.parseColor("#acb32e"));
            }
        }
        if (round==16&&!havewinner) {
            for (int j=0;j<4;j++){
                for (int k=0;k<4;k++){
                    textViews[k][j].setTextColor(Color.parseColor("#615f58"));
                }
            }
            for (int i = 0; i <= 23; i++) {
                if (board[extraPtsPos[i][0][0]][extraPtsPos[i][0][1]].equals("x") && board[extraPtsPos[i][1][0]][extraPtsPos[i][1][1]].equals("x") && board[extraPtsPos[i][2][0]][extraPtsPos[i][2][1]].equals("x")) {
                    xrowcount++;
                    textViews[extraPtsPos[i][0][0]][extraPtsPos[i][0][1]].setTextColor(Color.parseColor("#479e61"));
                    textViews[extraPtsPos[i][1][0]][extraPtsPos[i][1][1]].setTextColor(Color.parseColor("#479e61"));
                    textViews[extraPtsPos[i][2][0]][extraPtsPos[i][2][1]].setTextColor(Color.parseColor("#479e61"));

                }
                if (board[extraPtsPos[i][0][0]][extraPtsPos[i][0][1]].equals("o") && board[extraPtsPos[i][1][0]][extraPtsPos[i][1][1]].equals("o") && board[extraPtsPos[i][2][0]][extraPtsPos[i][2][1]].equals("o")) {
                    orowcount++;
                    textViews[extraPtsPos[i][0][0]][extraPtsPos[i][0][1]].setTextColor(Color.parseColor("#acb32e"));
                    textViews[extraPtsPos[i][1][0]][extraPtsPos[i][1][1]].setTextColor(Color.parseColor("#acb32e"));
                    textViews[extraPtsPos[i][2][0]][extraPtsPos[i][2][1]].setTextColor(Color.parseColor("#acb32e"));
                }
            }
            if (xrowcount>orowcount){
                winnertext.setVisibility(View.VISIBLE);
                winnertext.setText("X won with "+xrowcount+" rows");
            }
            else if (orowcount>xrowcount){
                winnertext.setVisibility(View.VISIBLE);
                winnertext.setText("O won with "+orowcount+" rows");
            }
            else{
                winnertext.setVisibility(View.VISIBLE);
                winnertext.setText("Draw");
            }
        }
    }
    public void openDialog (){
        ExampleDialog dialog=new ExampleDialog();
        dialog.show(getSupportFragmentManager(),"abcd");
    }
}

