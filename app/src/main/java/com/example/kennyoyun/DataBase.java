package com.example.kennyoyun;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {
    public DataBase(Context context)
    {
        super(context,"Scores.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE scores(id INTEGER PRIMARY KEY,score TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS scores");
    }
    public void insertScore(String score)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("score",score);
        db.insert("scores",null,cv);
        db.close();
    }
    public boolean checkBigger(String score)
    {
        int sc = Integer.parseInt(score);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM scores",null);
        while(cursor.moveToNext())
        {
            String str = cursor.getString(1);
            int pastScore = Integer.parseInt(str);
            if(sc < pastScore)
            {
                return false;
            }
            else
            {
                continue;
            }
        }
        return true;
    }
    public int bestScore()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM scores",null);
        int max=0;
        while(cursor.moveToNext())
        {
            String str = cursor.getString(1);
            int temp = Integer.parseInt(str);
            if( temp >= max )
            {
                max = temp;
            }
        }
        return max;
    }
    public List<String> display()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM scores",null);
        List<String> list = new ArrayList<>();
        List<String> l = new ArrayList<>();
        while(cursor.moveToNext())
        {
            list.add(cursor.getString(1));
        }
        for(int i = (list.size()-1); i >= 0 ; i-- )
        {
            l.add(list.get(i));
        }
        return l;
    }
}
