package com.example.german.bookstoreapp.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.german.bookstoreapp.R;
import com.example.german.bookstoreapp.adapter.BooksAdapter;
import com.example.german.bookstoreapp.db.BooksContract;
import com.example.german.bookstoreapp.db.BooksDBHelper;
import com.example.german.bookstoreapp.interfaces.BooksListener;
import com.example.german.bookstoreapp.service.BookService;


public class SeeBooksActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int BOOKS_LOADER_ID = 1234;


    private BooksDBHelper mBooksDBHelper;
    private RecyclerView mBookList;
    private BooksAdapter mBooksAdapter;
    private Loader mLoader;

    private BooksListener mBookListener = new BooksListener() {
        @Override
        public void onUpdate() {
            mLoader.onContentChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_books);
        mBooksDBHelper = new BooksDBHelper(this);
        mBooksAdapter = new BooksAdapter(null, this);
        mBooksAdapter.setBooksListener(mBookListener);
        mBookList = (RecyclerView) findViewById(R.id.book_list);
        mBookList.setLayoutManager(new LinearLayoutManager(this));
        mBookList.setAdapter(mBooksAdapter);
        mBookList.setHasFixedSize(true);
        mLoader = getSupportLoaderManager().initLoader(BOOKS_LOADER_ID, null, this);
        mLoader.forceLoad();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<Cursor>(SeeBooksActivity.this) {
            private Cursor cursor;
            @Override
            public Cursor loadInBackground() {
                SQLiteDatabase db = mBooksDBHelper.getReadableDatabase();
                cursor = db.query(BooksContract.BookEntry.TABLE_NAME, null, null,
                        null, null, null, null);
                return cursor;
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mBooksAdapter.setCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
