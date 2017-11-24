package com.example.augustopinto.listalibros;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Arrays;

/**
 * Esta clase es el helper, que haciendo uso del contrato (BookContract) va a trabajar con el motor
 * de bases de datos de Android de forma homogenea y va a realizar las operaciones que nuestra
 * app necesite.
 * Created by augustopinto on 11/9/17.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "listalibros.db";

    private static final int DATABASE_VERSION = 2;

    private SQLiteDatabase mDatabase;
    private Context mContext;
    private Toast mToast;

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mDatabase = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // este metodo se va a ejecutar cuando se crea la base por primera vez.
        final String SQL_CREATE_BOOK_LIST_TABLE = "CREATE TABLE " + BooksContract.BookList.TABLE_NAME +
                " ( " + BooksContract.BookList._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                BooksContract.BookList.COLUMN_ISBN_NUMBER + " NUMBER NOT NULL, " +
                BooksContract.BookList.COLUMN_BOOK_NAME + " TEXT NOT NULL, " +
                BooksContract.BookList.COLUMN_AUTHOR_NAME + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_BOOK_LIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // este metodo se va a ejecutar cuando se actualice la base de datos
        final String SQL_DROP_BOOKLIST_TABLE = "DROP TABLE IF EXISTS " + BooksContract.BookList.TABLE_NAME;
        db.execSQL(SQL_DROP_BOOKLIST_TABLE);
        onCreate(db);
    }

    /**
     * Metodo que agrega un libro a la base de datos.
     * @param isbnNumber numero unico que identifica cada libro
     * @param bookName el nombre del libro
     * @param authorName el nombre del autor
     */
    public synchronized void addBook(int isbnNumber, String bookName, String authorName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BooksContract.BookList.COLUMN_ISBN_NUMBER, isbnNumber);
        contentValues.put(BooksContract.BookList.COLUMN_BOOK_NAME, bookName);
        contentValues.put(BooksContract.BookList.COLUMN_AUTHOR_NAME, authorName);

        new BookInsertion().execute(contentValues);
    }

    public class BookInsertion extends AsyncTask<ContentValues, Void, Long> {

        @Override
        protected Long doInBackground(ContentValues... contentValues) {
            long id;
            if (contentValues.length == 0) {
                id = -1L;
            } else {
                // Suponemos que solo se carga un libro a la vez.
                id = mDatabase.insert(BooksContract.BookList.TABLE_NAME, null, contentValues[0]);
            }
            return id;
        }

        @Override
        protected void onPostExecute(Long result) {
            if (mToast != null) {
                mToast.cancel();
            }
            String msg;
            // Validamos si hay algun error.
            if (result == -1) {
                msg = "Error al cargar datos";
            } else {
                msg = "Libro agregado exitosamente. Id numero " + result;
            }
            mToast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
            mToast.show();
        }
    }
}
