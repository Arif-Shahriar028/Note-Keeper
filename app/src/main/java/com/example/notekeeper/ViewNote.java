package com.example.notekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ViewNote extends AppCompatActivity implements View.OnClickListener{

    private String idStr, title, desc;
    private int id;
    private EditText titleView, descView;
    private Button updateButton, deleteButton;

    MyDataBaseHelper myDataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);

        setTitle("Note keeper");

        Bundle bundle = getIntent().getExtras();
        idStr = bundle.getString("id");
        title = bundle.getString("title");
        desc = bundle.getString("desc");

        myDataBaseHelper = new MyDataBaseHelper(this);
        SQLiteDatabase db = myDataBaseHelper.getWritableDatabase();

        titleView = findViewById(R.id.titleId);
        descView = findViewById(R.id.descId);
        updateButton = findViewById(R.id.updateButtonId);
        deleteButton = findViewById(R.id.deleteButtonId);

        titleView.setText(title);
        descView.setText(desc);

        updateButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.updateButtonId){
            Boolean isUpdate = myDataBaseHelper.updateData(titleView.getText().toString(), descView.getText().toString(), Integer.parseInt(idStr));
            if(isUpdate == true)
            {
                Toast.makeText(getApplicationContext(), "Note updated", Toast.LENGTH_SHORT).show();
                startIntent();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Note not updated", Toast.LENGTH_SHORT).show();
            }
        }
        if(v.getId() == R.id.deleteButtonId){
            int value = myDataBaseHelper.deleteData(idStr);
            if(value > 0){
                Toast.makeText(getApplicationContext(), "Note deleted", Toast.LENGTH_SHORT).show();
                startIntent();
            }
            else
                Toast.makeText(getApplicationContext(), "Note not deleted", Toast.LENGTH_SHORT).show();
        }
    }

    public void startIntent(){
        Intent intent = new Intent(ViewNote.this, MainActivity.class);
        startActivity(intent);
    }

    public void onBackPressed(){
        startIntent();
    }
}