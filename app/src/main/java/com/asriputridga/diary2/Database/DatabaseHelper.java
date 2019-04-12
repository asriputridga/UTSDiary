package com.asriputridga.diary2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.asriputridga.diary2.Model.Diary;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String sDiaryDB = "DIARY_DB";
    private static final int sVersion = 1;
    private static final String sCreateDiaryTable = "create table " + Diary.TABLE_NAME + "(" +
            Diary.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Diary.TITLE + " TEXT, " +
            Diary.CONTENT + " TEXT, " +
            Diary.LOCATION + " TEXT, " +
            Diary.DATE + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, sDiaryDB, null, sVersion);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        db.execSQL(sCreateDiaryTable);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void update(Diary diary, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Diary.TITLE, diary.getTitle());
        contentValues.put(Diary.CONTENT, diary.getContent());
        contentValues.put(Diary.LOCATION, diary.getLocation());
        contentValues.put(Diary.DATE, diary.getDate());
        db.update(Diary.TABLE_NAME, contentValues, Diary.ID + "=" + diary.getId(), null);
    }

    public void insert(Diary diary, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Diary.TITLE, diary.getTitle());
        contentValues.put(Diary.CONTENT, diary.getContent());
        contentValues.put(Diary.LOCATION, diary.getLocation());
        contentValues.put(Diary.DATE, diary.getDate());
        db.insertOrThrow(Diary.TABLE_NAME, null, contentValues);

    }

    public void delete(int id, SQLiteDatabase db) {
        db.delete(Diary.TABLE_NAME, Diary.ID + "=" + id, null);
    }

    public void deleteAll(SQLiteDatabase db){
        db.delete(Diary.TABLE_NAME, null, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
