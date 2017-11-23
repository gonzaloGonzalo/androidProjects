package com.example.admin.firstproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int MAX = 20;
    int contador = 0;
    Button boton;
    Chronometer focus;
    TextView cajaDeTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boton = findViewById(R.id.boton);
        cajaDeTexto = findViewById(R.id.cajaDeTexto);
        focus = findViewById(R.id.chronometer1);
        Log.d(TAG, "Llamando on create");
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                focus.start();
                contador ++;
                cajaDeTexto.setText(Integer.valueOf(contador).toString());
                if(contador == MAX){
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("contador", cajaDeTexto.getText());
                    intent.putExtra("tiempo",   focus.getText().toString());
                    focus.stop();
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("botonNombre", boton.getText().toString());
        outState.putString("cajaDeTextoNombre", cajaDeTexto.getText().toString());
    }

}
