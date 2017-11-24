package com.example.german.bookstoreapp.db;

import android.provider.BaseColumns;

/**
 * Created by leozaragoza on 11/12/17.
 */

public class BooksContract {

    private BooksContract() {

    }

    public class BookEntry implements BaseColumns {
        public static final String TABLE_NAME = "BOOKS";

        public static final String BOOK_NAME_COLUMN = "NAME";
        public static final String BOOK_AUTHOR_COLUMN = "AUTHOR";
        public static final String BOOK_STOCK_COLUMN = "STOCK";
    }
}
