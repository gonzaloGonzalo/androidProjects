package com.example.german.bookstoreapp.service;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.german.bookstoreapp.db.BooksContract;
import com.example.german.bookstoreapp.db.BooksDBHelper;


/**
 * Created by leozaragoza on 11/12/17.
 */

public class BookService extends IntentService {


    private static final String TAG = BookService.class.getSimpleName();

    public static final String ACTION_INSERT = "ACTION_INSERT";
    public static final String ACTION_UPDATE = "ACTION_UPDATE";
    public static final String ACTION_DELETE = "ACTION_DELETE";

    public static final String EXTRA_VALUES = "EXTRA_CONTENT_VALUES";

    public static final String EXTRA_ID = "EXTRA_ID";

    private BooksDBHelper mBookDBHelper;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public BookService(String name) {
        super(name);
    }

    public BookService() {
        this(BookService.class.getName());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mBookDBHelper = new BooksDBHelper(getApplicationContext());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String intentAction = intent.getAction();
        if (intentAction != null) {
            switch (intentAction) {
                case ACTION_INSERT:
                    ContentValues cv = intent.getParcelableExtra(EXTRA_VALUES);
                    if (cv != null) {
                        insertNewBook(cv);
                    }
                    break;
                case ACTION_DELETE:
                    long id = intent.getLongExtra(EXTRA_ID, -1);
                    deleteBook(id);
                    break;
                default:
                    throw new UnsupportedOperationException(intentAction + " Not supported by " + TAG);
            }
        } else {
            Log.e(TAG, "Calling " + TAG + " without action");
        }

    }

    private void insertNewBook(ContentValues cv) {
        SQLiteDatabase db = mBookDBHelper.getWritableDatabase();
        long id = -1;
        if (cv != null) {
            id = db.insert(BooksContract.BookEntry.TABLE_NAME, null, cv);
        }
        if (id > -1) {
            Log.d(TAG, "Row successfully into " + BooksContract.BookEntry.TABLE_NAME + " with id " + id);
        } else {
            Log.e(TAG, "Failed to insert into " + BooksContract.BookEntry.TABLE_NAME);
        }
    }

    private void deleteBook(long id) {
        SQLiteDatabase db = mBookDBHelper.getWritableDatabase();
        String bookId = Long.toString(id);
        String[] whereArgs = {bookId};
        db.delete(BooksContract.BookEntry.TABLE_NAME, BooksContract.BookEntry._ID + " = ? ", whereArgs);
    }
}
