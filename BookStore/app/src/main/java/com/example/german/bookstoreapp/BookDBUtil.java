package com.example.german.bookstoreapp;

import android.database.Cursor;

import com.example.german.bookstoreapp.db.BooksContract;


/**
 * Created by leozaragoza on 11/13/17.
 */

public class BookDBUtil {

    public static final Long getBookId(Cursor cursor) {
        return cursor.getLong(cursor.getColumnIndex(BooksContract.BookEntry._ID));
    }

    public static final String getBookName(Cursor cursor) {
        return  cursor.getString(cursor.getColumnIndex(BooksContract.BookEntry.BOOK_NAME_COLUMN));
    }

    public static final String getBookAuthor(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(BooksContract.BookEntry.BOOK_AUTHOR_COLUMN));
    }

    public static final int getBookStock(Cursor cursor) {
        return cursor.getInt(cursor.getColumnIndex(BooksContract.BookEntry.BOOK_STOCK_COLUMN));
    }
}
