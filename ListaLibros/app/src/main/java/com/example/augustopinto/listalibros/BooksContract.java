package com.example.augustopinto.listalibros;

import android.provider.BaseColumns;

/**
 * Este es el contrato, donde definimos un constructor privado y una clase interna por cada tabla
 * que vayamos a tener en la base de datos.
 * Dentro de cada clase anonima, debemos crear constantes que coincidan con el esquema de datos que
 * tenga la tabla. Debemos especificar cual va a ser el nombre de la tabla y sus columnas.
 * Created by augustopinto on 11/9/17.
 */

public class BooksContract {

    private BooksContract() {

    }

    public static final class BookList implements BaseColumns {

        public static final String TABLE_NAME = "booklist";

        public static final String COLUMN_BOOK_NAME = "bookName";

        public static final String COLUMN_AUTHOR_NAME = "authorName";

        public static final String COLUMN_ISBN_NUMBER = "isbnNumber";
    }

}
