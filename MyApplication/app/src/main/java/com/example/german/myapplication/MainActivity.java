package com.example.german.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final  String TAG = MainActivity.class.getSimpleName();

    private EditText mUserNameEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUserNameEditText = (EditText) findViewById(R.id.editText);
        mPasswordEditText = (EditText) findViewById(R.id.editText3);
        mLoginButton = (Button) findViewById(R.id.login_button);

        String username = null;
        String password = null;
        if (savedInstanceState != null) {
            username = savedInstanceState.getString("Username");
            password = savedInstanceState.getString("Password");
            if (username != null) {
                mUserNameEditText.setText(username);
            }

            if (password != null) {
                mPasswordEditText.setText(password);
            }
        }

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra(Intent.EXTRA_USER, mUserNameEditText.getText().toString());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Username", mUserNameEditText.getText().toString());
        outState.putString("Password", mPasswordEditText.getText().toString());
        Log.d(TAG, "onsavedInstanceState called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Llamada a onResome");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Llamada onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Llamada onStop");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Llamada onStart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Llamada onDestroy");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_POWER) {
            // do what you want with the power button
            Log.d(TAG, "Power button pressed");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
