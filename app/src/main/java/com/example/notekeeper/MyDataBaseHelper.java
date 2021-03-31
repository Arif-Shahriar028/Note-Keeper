package com.example.notekeeper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Calendar;

public class MyDataBaseHelper extends SQLiteOpenHelper {

    private static final String Database_Name = "notes.db";
    private static final String Table_Name = "notes_table";
    private static final String ID = "_id";
    private static final String Title = "Title";
    private static final String Desc = "Description";
    private static final String Date = "Date";
    private static final int Version = 3;
    private static final String Create_Table = "CREATE TABLE "+Table_Name+" ( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Title+" VARCHAR(100), "+Desc+" TEXT,"+ Date +" VARCHAR(15) );";
    private static final String Drop_Table = "DROP TABLE IF EXISTS "+ Table_Name;
    private static final String Select_All = " SELECT * FROM "+ Table_Name;

    private Context context;


    public MyDataBaseHelper(@Nullable Context context) {
        super(context, Database_Name, null, Version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(Create_Table);
        }catch (Exception e){
            Toast.makeText(context, "Exception : "+e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(Drop_Table);
            onCreate(db);
        }catch (Exception e){

            Toast.makeText(context, "Exception : "+e, Toast.LENGTH_SHORT).show();
        }
    }

    public long insertData(String title, String desc)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cn = new ContentValues();
        String date = getDate();
        cn.put(Title, title);
        cn.put(Desc, desc);
        cn.put(Date, date);

        long rowId = db.insert(Table_Name, null, cn);
        return rowId;
    }

    public Cursor displayAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(Select_All, null);
        return cursor;
    }

    public Boolean updateData(String title, String desc, int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cn = new ContentValues();
        cn.put(Title, title);
        cn.put(Desc, desc);
        cn.put(ID, id);

        db.update(Table_Name, cn, ID+" = ?", new String[]{String.valueOf(id)});
        return true;
    }

    public int deleteData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Table_Name, ID+" = ?", new String[]{id});
    }

    public String getDate(){
        Calendar calendarForTime = Calendar.getInstance();
        String date = String.valueOf(calendarForTime.get(Calendar.DATE));
        String month = String.valueOf(calendarForTime.get(Calendar.MONTH));
        String year = String.valueOf(calendarForTime.get(Calendar.YEAR));

        String dateStr  = date+"-"+month+"-"+year;
        return dateStr;
    }
}
