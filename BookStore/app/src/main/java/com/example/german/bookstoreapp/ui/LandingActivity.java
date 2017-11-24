package com.example.german.bookstoreapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.german.bookstoreapp.R;


public class LandingActivity extends AppCompatActivity {

    private Button mAddNewBookButton;
    private Button mSeeBooksButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        mAddNewBookButton = (Button) findViewById(R.id.addNewBookButton);
        mSeeBooksButton = (Button) findViewById(R.id.seeBooksButton);

        mAddNewBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingActivity.this, AddBookActivity.class);
                startActivity(intent);
            }
        });

        mSeeBooksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingActivity.this, SeeBooksActivity.class);
                startActivity(intent);
            }
        });
    }
}
