package com.example.notekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private ListView listView;
    ArrayList<String> titleList= new ArrayList<>();
    ArrayList<String> dateList = new ArrayList<>();
    ArrayList<String> idList = new ArrayList<>();
    ArrayList<String> descList = new ArrayList<>();
    MyDataBaseHelper myDataBaseHelper;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Note keeper");

        fab = findViewById(R.id.fab);
        listView = findViewById(R.id.listViewId);

        myDataBaseHelper = new MyDataBaseHelper(this);

        loadData();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewNote.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String idStr = idList.get(position);
                String title = titleList.get(position);
                String desc = descList.get(position);
                Intent intent = new Intent(MainActivity.this, ViewNote.class);

                intent.putExtra("id", idStr);
                intent.putExtra("title", title);
                intent.putExtra("desc", desc);

                startActivity(intent);
            }
        });
    }

    public void loadData()
    {

        Cursor cursor = myDataBaseHelper.displayAllData();

        if(cursor.getCount() == 0)
        {
            Toast.makeText(this, "No note found", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while(cursor.moveToNext())
            {
                idList.add(cursor.getString(0));
                titleList.add(cursor.getString(1));
                descList.add(cursor.getString(2));
                dateList.add(cursor.getString(3));
            }
        }

        Collections.reverse(titleList);
        Collections.reverse(dateList);
        Collections.reverse(descList);
        Collections.reverse(idList);

        customAdapter = new CustomAdapter(this, titleList, dateList, descList);
        listView.setAdapter(customAdapter);
    }

    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }
}