package com.example.augustopinto.listalibros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // Declaramos las variables miembro que vamos a utilizar a lo largo de la clase MainActivity
    private EditText mBookName;
    private EditText mAuthorName;
    private EditText mIsbnNumber;
    private Button mAddButton;

    private MyDatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtenemos las referencias a los objetos que vamos a utilizar
        mBookName = (EditText) findViewById(R.id.book_name);
        mAuthorName = (EditText) findViewById(R.id.author_name);
        mIsbnNumber = (EditText) findViewById(R.id.isbn);
        mAddButton = (Button) findViewById(R.id.add_button);

        // Le damos funcionalidad cuando se clickea el boton
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int isbn = Integer.parseInt(mIsbnNumber.getText().toString());
                String bookName = mBookName.getText().toString();
                String authorName = mAuthorName.getText().toString();

                if (mDatabaseHelper != null) {
                    mDatabaseHelper.addBook(isbn, bookName, authorName);
                }
            }
        });

        mDatabaseHelper = new MyDatabaseHelper(getApplicationContext());

    }
}
