package com.example.admin.firstproject;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.firstproject.persistence.PuntosDAO;
import com.example.admin.firstproject.persistence.PuntosDAOImpl;


/**
 * Created by admin on 7/11/2017.
 */

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = SecondActivity.class.getSimpleName();
    Button volverPrincipal;
    TextView contadorTotal;
    PuntosDAO puntosDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        volverPrincipal = findViewById(R.id.volverPrincipal);
        contadorTotal = findViewById(R.id.contadorTotal);
        String contador = getIntent().getExtras().getString("contador");
        String tiempo = getIntent().getExtras().getString("tiempo");
        contadorTotal.setText(
                new StringBuilder(contador)
                        .append(" clicks en ")
                        .append(tiempo)
                        .append(" segundos."));

        ContentValues cv = new ContentValues();
        cv.put("tiempo", tiempo);
        new AsincTask().execute(cv);
        volverPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public class AsincTask extends android.os.AsyncTask<ContentValues, Void, String>{

        @Override
        protected String doInBackground(ContentValues... contentValues) {
            String tiempo = (String) contentValues[0].get("tiempo");
            puntosDAO = new PuntosDAOImpl(getApplicationContext());
//            long newRowId = puntosDAO.agregarPuntaje(tiempo);
//            Log.d(TAG, "ID row: "+newRowId);
            puntosDAO.getPuntaje();
            return "Success";
        }

        @Override
        protected void onPostExecute(String s) {

        }
    }
}
