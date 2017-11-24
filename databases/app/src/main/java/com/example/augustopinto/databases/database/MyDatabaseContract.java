package com.example.augustopinto.databases.database;

import android.provider.BaseColumns;

/**
 * Created by augustopinto on 11/9/17.
 */

public class MyDatabaseContract {
    // Private para evitar que instancien el contrato
    private MyDatabaseContract() {
    }

    public static final class BookList implements BaseColumns {

        // The _ID column is provided by BaseColumns api class.

        //table name
        public static final String TABLE_NAME = "booklist";

        //Columns names

        public static final String COLUMN_ISBN_NUMBER = "isbnNumber";

        public static final String COLUMN_BOOK_NAME = "bookName";

        public static final String COLUMN_AUTHOR_NAME = "AuthorName";

    }

}
