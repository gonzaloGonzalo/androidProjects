package com.example.augustopinto.databases.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by augustopinto on 11/9/17.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    // 1) Create the database file name
    private static final String DATABASE_NAME = "booklist.db";

    // 2) Create a database version.
    private static final int DATABASE_VERSION = 1;

    // 3) Create a simple constructor
    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // 5) Sobreescribir los metodos
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 6) Crete the sql query
        final String SQL_CREATE_BOOKLIST_TABLE = "CREATE TABLE " + MyDatabaseContract.BookList.TABLE_NAME +
                " (" + MyDatabaseContract.BookList._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MyDatabaseContract.BookList.COLUMN_ISBN_NUMBER + " NUMBER NOT NULL, " +
                MyDatabaseContract.BookList.COLUMN_BOOK_NAME + " TEXT NOT NULL," +
                MyDatabaseContract.BookList.COLUMN_AUTHOR_NAME + " TEXT NOT NULL);";

        // 7) execute the query
        db.execSQL(SQL_CREATE_BOOKLIST_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 8) Actualizar la tabla
        final String SQL_DROP_BOOKLIST_TABLE = "DROP TABLE IF EXIST " + MyDatabaseContract.BookList.TABLE_NAME;
        db.execSQL(SQL_DROP_BOOKLIST_TABLE);
        onCreate(db);
    }
}
