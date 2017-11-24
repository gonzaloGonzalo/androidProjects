package com.example.augustopinto.databases;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.augustopinto.databases.database.MyDatabaseContract;
import com.example.augustopinto.databases.database.MyDatabaseHelper;

public class MainActivity extends AppCompatActivity {

    // 1) Crear el miembro en la activity de tipo SQLiteDatabase
    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2) Create the helper objetc
        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(getApplicationContext());

        // 3) obtener una referencia escribible de la base de datos
        mDatabase = myDatabaseHelper.getWritableDatabase();
        Cursor cursor = getAllBooks();

        Log.d("apinto", "Cantidad de libros " + cursor.getCount());

        cursor.moveToFirst();
    }

    private Cursor getAllBooks() {
        return mDatabase.query(MyDatabaseContract.BookList.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    private void addBook(int isbnNumber, String bookName, String authorName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDatabaseContract.BookList.COLUMN_ISBN_NUMBER, isbnNumber);
        contentValues.put(MyDatabaseContract.BookList.COLUMN_BOOK_NAME, bookName);
        contentValues.put(MyDatabaseContract.BookList.COLUMN_AUTHOR_NAME, authorName);
        mDatabase.insert(MyDatabaseContract.BookList.TABLE_NAME, null, contentValues);
    }

    private boolean removeBook(long id) {
        return mDatabase.delete(MyDatabaseContract.BookList.TABLE_NAME, MyDatabaseContract.BookList._ID + " = " + id, null) > 0;
    }


}
