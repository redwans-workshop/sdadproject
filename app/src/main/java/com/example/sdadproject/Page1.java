package com.example.sdadproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Page1 extends AppCompatActivity {
    private Button mapbutton;
    private Button notebutton;
    private Button wifitoggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);

        mapbutton = (Button) findViewById(R.id.mapbutton);
        mapbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openMap();
            }
        });

        notebutton = (Button) findViewById(R.id.notebutton);
        notebutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openNote();
            }

        });

        wifitoggle = (Button) findViewById(R.id.wifitoggle);
        wifitoggle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openNetm();
            }

        });


    }

    private void openNetm() {
        Intent intent= new Intent(this, NetMan.class);
        startActivity(intent);
    }


    public void openMap(){
        Intent intent= new Intent(this, Map.class);
        startActivity(intent);
    }
    public void openNote(){
        Intent intent= new Intent(this, Note.class);
        startActivity(intent);
    }
}