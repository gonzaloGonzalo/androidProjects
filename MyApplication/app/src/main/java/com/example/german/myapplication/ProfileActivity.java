package com.example.german.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView mWelcomeUserTextView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Aquí seteo los miembros de mi activity e inflo el layout
        setContentView(R.layout.activity_profile);

        mWelcomeUserTextView = (TextView) findViewById(R.id.welcometextView);
        String userName = getIntent().getStringExtra(Intent.EXTRA_USER);
        mWelcomeUserTextView.setText(mWelcomeUserTextView.getText().toString() + " " + userName);

    }
}
