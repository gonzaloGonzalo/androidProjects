package com.example.admin.asynchtaskproject;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class ActivityHome extends AppCompatActivity {

    private static final String TAG = ActivityHome.class.getSimpleName();
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LongOperation().execute("");

            }
        });


    }

    private class LongOperation extends AsyncTask<String, Void, String> {

        Context context = getApplicationContext();
        CharSequence text = "Empezando toast!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);

        @Override
        protected String doInBackground(String... params) {
            Log.d(TAG, "Ejecutando long term");
            toast.show();
            longTermTask();
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG, "Finalizando long term");
            toast.setText("Finalizando toast");
            toast.show();

        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}


    }

    private synchronized void longTermTask(){
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException ex){
            Log.d(TAG, ex.getMessage());
        }
    }
}
