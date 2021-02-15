package com.example.sdadproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Note extends AppCompatActivity {

    EditText editText;
    Button btAdd, btReset;
    RecyclerView recyclerView;

    List<MainData> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    MainAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);


        editText = findViewById(R.id.edit_text);
        btAdd = findViewById(R.id.bt_add);
        btReset = findViewById(R.id.bt_reset);
        recyclerView = findViewById(R.id.recycler_view);


        //int  database
        database = RoomDB.getInstance(this);
        //store value in db
        dataList = (List<MainData>) database.mainDao().getAll();
        //linear layout man
        linearLayoutManager = new LinearLayoutManager(this);
        //set layout man
        recyclerView.setLayoutManager(linearLayoutManager);
        //int adapter
        adapter = new MainAdapter(Note.this,dataList);
        //set adapter
        recyclerView.setAdapter(adapter);
        btAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //get string from edit text
                String sText = editText.getText().toString().trim();
                //check condition
                if(!sText.equals("")){
                    MainData data = new MainData();
                    data.setText(sText);
                    database.mainDao().insert(data);
                    editText.setText("");
                    dataList.clear();
                    dataList.addAll(database.mainDao().getAll());
                    adapter.notifyDataSetChanged();
                }
            }
        });

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete all data
                database.mainDao().reset(dataList);
                //notif on delete
                dataList.clear();
                dataList.addAll(database.mainDao().getAll());
                adapter.notifyDataSetChanged();
            }
        });
    }
}