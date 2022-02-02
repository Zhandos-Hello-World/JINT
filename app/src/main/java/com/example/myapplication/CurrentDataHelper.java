package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class CurrentDataHelper extends SQLiteOpenHelper {
    public static String DB_NAME = "CURRENT";
    public static int VERSION = 1;

    public CurrentDataHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        onUpdate(db, 0, VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpdate(db, VERSION, newVersion);
    }

    private void onUpdate(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {

            db.execSQL("CREATE TABLE DATA (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "id TEXT," +
                    "numCode TEXT, " +
                    "charCode TEXT, " +
                    "nominal INTEGER," +
                    "name TEXT," +
                    "value REAL," +
                    "previous REAL);");

            db.execSQL("CREATE TABLE CHOICE_DATA (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "id TEXT);");

            db.execSQL("CREATE TABLE TIME(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "date TEXT);");
        }
    }
}
