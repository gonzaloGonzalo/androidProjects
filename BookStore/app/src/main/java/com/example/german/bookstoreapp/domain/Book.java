package com.example.german.bookstoreapp.domain;

import android.database.Cursor;

import com.example.german.bookstoreapp.db.BooksContract;

/**
 * Created by german on 16/11/17.
 */

public class Book {

    private long bookId;
    private String bookName;
    private String bookAuthor;
    private int bookStock;

    public Book(long bookId, String bookName, String bookAuthor, int bookStock) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookStock = bookStock;
    }

    public Book(Cursor cursor) {
        bookId = cursor.getLong(cursor.getColumnIndex(BooksContract.BookEntry._ID));
        bookName = cursor.getString(cursor.getColumnIndex(BooksContract.BookEntry.BOOK_NAME_COLUMN));
        bookAuthor = cursor.getString(cursor.getColumnIndex(BooksContract.BookEntry.BOOK_AUTHOR_COLUMN));
        bookStock = cursor.getInt(cursor.getColumnIndex(BooksContract.BookEntry.BOOK_STOCK_COLUMN));
    }

    public long getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public int getBookStock() {
        return bookStock;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public void setBookStock(int bookStock) {
        this.bookStock = bookStock;
    }
}
