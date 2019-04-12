package com.asriputridga.diary2.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.asriputridga.diary2.Model.Diary;

import java.util.ArrayList;
import java.util.List;

public class DiaryHelper {
    private static SQLiteDatabase getWritableAndOpen(DatabaseHelper databaseHelper){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        return sqLiteDatabase;
    }

    private static SQLiteDatabase getReadableAndOpen(DatabaseHelper databaseHelper){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        sqLiteDatabase.beginTransaction();
        return sqLiteDatabase;
    }

    private static void closeTransaction(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();
    }

    public static List<Diary> getDiaries(Context context){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase sqLiteDatabase = getReadableAndOpen(databaseHelper);
        Cursor cursor = sqLiteDatabase.query(Diary.TABLE_NAME, null, null, null, null, null, Diary.DATE + " DESC");
        closeTransaction(sqLiteDatabase);
        List<Diary> list = convertCursorToList(cursor);
        cursor.close();
        return list;
    }

    public static Diary getDiary(Context context, int id){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase sqLiteDatabase = getReadableAndOpen(databaseHelper);
        Cursor cursor = sqLiteDatabase.query(Diary.TABLE_NAME, null, Diary.ID + "=" + id, null, null, null, null);
        closeTransaction(sqLiteDatabase);
        cursor.moveToFirst();
        Diary diary = convertFromCursor(cursor);
        cursor.close();
        return diary;
    }

    public static void addDiary(Context context, Diary diary){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase sqLiteDatabase = getWritableAndOpen(databaseHelper);
        databaseHelper.insert(diary, sqLiteDatabase);
        closeTransaction(sqLiteDatabase);
    }

    public static void updateDiary(Context context, Diary diary){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase sqLiteDatabase = getWritableAndOpen(databaseHelper);
        databaseHelper.update(diary, sqLiteDatabase);
        closeTransaction(sqLiteDatabase);
    }

    public static void deleteDiary(Context context, int id){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase sqLiteDatabase = getWritableAndOpen(databaseHelper);
        databaseHelper.delete(id, sqLiteDatabase);
        closeTransaction(sqLiteDatabase);
    }

    public static void deleteAllDiary(Context context){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase sqLiteDatabase = getWritableAndOpen(databaseHelper);
        databaseHelper.deleteAll(sqLiteDatabase);
        closeTransaction(sqLiteDatabase);
    }

    private static List<Diary> convertCursorToList(Cursor cursor){
        List<Diary> list = new ArrayList<>();
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            do{
                list.add(convertFromCursor(cursor));
            }while(cursor.moveToNext());
            return list;
        }else{
            return list;
        }


    }

    private static Diary convertFromCursor(Cursor cursor){
        Diary diary = new Diary();

        final int id = cursor.getColumnIndex(Diary.ID);
        final int title = cursor.getColumnIndex(Diary.TITLE);
        final int content = cursor.getColumnIndex(Diary.CONTENT);
        final int location = cursor.getColumnIndex(Diary.LOCATION);
        final int date = cursor.getColumnIndex(Diary.DATE);
        diary.setId(cursor.getInt(id));
        diary.setTitle(cursor.getString(title));
        diary.setContent(cursor.getString(content));
        diary.setLocation(cursor.getString(location));
        diary.setDate(cursor.getString(date));
        return diary;
    }

}
