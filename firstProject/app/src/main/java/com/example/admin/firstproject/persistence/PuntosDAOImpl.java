package com.example.admin.firstproject.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.Time;
import android.util.Log;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 14/11/2017.
 */

public class PuntosDAOImpl implements PuntosDAO {

    private static final String TAG = PuntosDAOImpl.class.getSimpleName();
    MiConexionHelper miConexion;
    SQLiteDatabase db;

    public PuntosDAOImpl(Context context){
        miConexion = new MiConexionHelper(context);
        db = miConexion.getWritableDatabase();
    }

    @Override
    public synchronized Long agregarPuntaje(String tiempo) {
        ContentValues values = new ContentValues();
        Time now = new Time();
        now.setToNow();
        values.put(PuntosContract.PuntosEntry.COLUMN_NAME_FECHA, now.toString());
        values.put(PuntosContract.PuntosEntry.COLUMN_NAME_TIEMPO, tiempo);
        long newRowId = db.insert(PuntosContract.PuntosEntry.TABLE_NAME, null, values);
        Log.d(TAG, "ID row: "+newRowId);
        return newRowId;
    }

    @Override
    public synchronized List<Puntos> getPuntaje() {
        Cursor cursor = db.rawQuery("SELECT * FROM "+ PuntosContract.PuntosEntry.TABLE_NAME, null);
        Puntos puntos;
        List<Puntos> listaPuntos = new ArrayList<>();
        if (cursor.moveToFirst()){
            agregarPuntosALista(cursor, listaPuntos);
            while(cursor.moveToNext()){
                agregarPuntosALista(cursor, listaPuntos);
            }
        }
        cursor.close();
        return listaPuntos;
    }

    private void agregarPuntosALista(Cursor cursor, List<Puntos> listaPuntos) {
        Puntos puntos = new Puntos();
        String fechaIn = cursor.getString(cursor.getColumnIndex(PuntosContract.PuntosEntry.COLUMN_NAME_FECHA));
        String tiempoIn = cursor.getString(cursor.getColumnIndex(PuntosContract.PuntosEntry.COLUMN_NAME_TIEMPO));
        Date date = stringToDate(fechaIn, "dd-MM-yyyy");
        puntos.setFecha(fechaIn);
        puntos.setTiempo(tiempoIn);
        Log.d(TAG, "ID row: "+fechaIn);
        Log.d(TAG, "ID row: "+tiempoIn);
        listaPuntos.add(puntos);
    }

    private Date stringToDate(String aDate,String aFormat) {

        if(aDate==null) return null;
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpledateformat = new SimpleDateFormat(aFormat);
        Date stringDate = simpledateformat.parse(aDate, pos);
        return stringDate;

    }
}
