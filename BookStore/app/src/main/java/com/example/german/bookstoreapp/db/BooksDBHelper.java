package com.example.german.bookstoreapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by leozaragoza on 11/12/17.
 */

public class BooksDBHelper extends SQLiteOpenHelper {

    public static final String BOOKS_DB_NAME = "books.db";
    private static final int DATABASE_VERSION = 2;

    public BooksDBHelper(Context context) {
        super(context, BOOKS_DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String DB_CREATION_QUERY = "CREATE TABLE BOOKS ( " +
                BooksContract.BookEntry._ID +  " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                BooksContract.BookEntry.BOOK_NAME_COLUMN + " TEXT NOT NULL, " +
                BooksContract.BookEntry.BOOK_AUTHOR_COLUMN + " TEXT NOT NULL, " +
                BooksContract.BookEntry.BOOK_STOCK_COLUMN + " INTEGER NOT NULL)";
        db.execSQL(DB_CREATION_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String DB_DROP_QUERY = "DROP TABLE IF EXISTS " + BooksContract.BookEntry.TABLE_NAME;
        db.execSQL(DB_DROP_QUERY);
        onCreate(db);
    }
}
