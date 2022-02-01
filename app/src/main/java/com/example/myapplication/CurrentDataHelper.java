package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class CurrentDataHelper extends SQLiteOpenHelper {
    public static String TABLE_NAME = "CURRENT";
    public static int VERSION = 1;

    public CurrentDataHelper(Context context, String name,
                             SQLiteDatabase.CursorFactory factory,
                             int version) {
        super(context, name, factory, version);
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
                    "numcode TEXT, " +
                    "charCode TEXT, " +
                    "nominal INTEGER," +
                    "name TEXT," +
                    "value NUMERIC," +
                    "previous NUMERIC);");
        }
        else if (oldVersion < 2){
            db.execSQL("CREATE TABLE CHOICED_DATA (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "id TEXT," +
                    "numcode TEXT, " +
                    "charCode TEXT, " +
                    "nominal INTEGER," +
                    "name TEXT," +
                    "value NUMERIC," +
                    "previous NUMERIC);");
        }
    }
}
