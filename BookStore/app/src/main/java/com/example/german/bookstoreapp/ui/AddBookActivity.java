package com.example.german.bookstoreapp.ui;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.german.bookstoreapp.R;
import com.example.german.bookstoreapp.db.BooksContract;
import com.example.german.bookstoreapp.service.BookService;

public class AddBookActivity extends AppCompatActivity {

    private Button mAddBookButton;

    private EditText mBookNameEditText;
    private EditText mBookAuthorEditText;
    private EditText mBookStockEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        mAddBookButton = (Button) findViewById(R.id.addBookButton);
        mBookNameEditText = (EditText) findViewById(R.id.nameEditText);
        mBookAuthorEditText = (EditText) findViewById(R.id.authorEditText);
        mBookStockEditText = (EditText) findViewById(R.id.stockEditText);

        mAddBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookName = mBookNameEditText.getText().toString();
                String bookAuthor = mBookAuthorEditText.getText().toString();
                String bookStock = mBookStockEditText.getText().toString();

                if (bookName.isEmpty() || bookAuthor.isEmpty() || bookStock.isEmpty()) {
                    Toast.makeText(AddBookActivity.this, "Completá todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(AddBookActivity.this, BookService.class);
                    intent.setAction(BookService.ACTION_INSERT);
                    ContentValues cv = new ContentValues();
                    cv.put(BooksContract.BookEntry.BOOK_NAME_COLUMN, bookName);
                    cv.put(BooksContract.BookEntry.BOOK_AUTHOR_COLUMN, bookAuthor);
                    cv.put(BooksContract.BookEntry.BOOK_STOCK_COLUMN, bookStock);
                    intent.putExtra(BookService.EXTRA_VALUES, cv);
                    startService(intent);
                }
            }
        });
    }
}
