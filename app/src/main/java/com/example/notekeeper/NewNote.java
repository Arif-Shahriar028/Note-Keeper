package com.example.notekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewNote extends AppCompatActivity {

    MyDataBaseHelper myDataBaseHelper;
    private EditText title, desc;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        setTitle("New Entry");

        title = findViewById(R.id.titleId);
        desc = findViewById(R.id.descId);
        saveButton = findViewById(R.id.buttonId);

        myDataBaseHelper = new MyDataBaseHelper(this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String titleTxt = title.getText().toString();
                String descTxt = desc.getText().toString();
                long rowId = myDataBaseHelper.insertData(titleTxt, descTxt);

                if(rowId == -1)
                {
                    Toast.makeText(getApplicationContext(), "Unsuccessful", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Saved successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(NewNote.this, MainActivity.class);
                    startActivity(intent);

                }
            }
        });
    }
    public void onBackPressed(){
        Intent intent = new Intent(NewNote.this, MainActivity.class);
        startActivity(intent);
    }
}