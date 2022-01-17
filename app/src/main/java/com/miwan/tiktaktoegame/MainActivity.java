package com.miwan.tiktaktoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button multiPlayerbutton,singlePLayerButton,button,btn4x4,btn3x3,easyBtn,imposBtn,abmebtn;
    private TextView aboutme,logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo=findViewById(R.id.logo);
        abmebtn=findViewById(R.id.abme);
        abmebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutme.setVisibility(View.VISIBLE);
                logo.setVisibility(View.INVISIBLE);
            }
        });
        aboutme=findViewById(R.id.aboutme);
        aboutme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutme.setVisibility(View.INVISIBLE);
                logo.setVisibility(View.VISIBLE);
            }
        });
        easyBtn=findViewById(R.id.easybtn);
        easyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity5();
            }
        });
        imposBtn=findViewById(R.id.impossiblebtn);
        imposBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity4();
            }
        });
        button=findViewById(R.id.singleBtn);
        btn4x4=findViewById(R.id.button4x4);
        btn3x3=findViewById(R.id.button3x3);
        singlePLayerButton=findViewById(R.id.singleBtn);
        singlePLayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multiPlayerbutton.setVisibility(View.INVISIBLE);
                easyBtn.setVisibility(View.VISIBLE);
                imposBtn.setVisibility(View.VISIBLE);
                singlePLayerButton.setVisibility(View.INVISIBLE);
                abmebtn.setVisibility(View.INVISIBLE);
            }
        });
        multiPlayerbutton=findViewById(R.id.button2);
        multiPlayerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multiPlayerbutton.setVisibility(View.INVISIBLE);
                button.setVisibility(View.INVISIBLE);
                abmebtn.setVisibility(View.INVISIBLE);
                btn3x3.setVisibility(View.VISIBLE);
                btn4x4.setVisibility(View.VISIBLE);
                btn4x4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        openActivity3();
                    }
                });
                btn3x3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openActivity2();
                    }
                });
            }
        });
    }
    public void openActivity2(){
        Intent intent=new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
    public void openActivity3(){
        Intent intent=new Intent(this,MainActivity3.class);
        startActivity(intent);
    }
    public void openActivity4(){
        Intent intent=new Intent(this,MainActivity4.class);
        startActivity(intent);
    }
    public void openActivity5(){
        Intent intent=new Intent(this,MainActivity5.class);
        startActivity(intent);
    }
}