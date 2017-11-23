package com.example.admin.firstproject.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by admin on 9/11/2017.
 */

public class MiConexionHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "FirstProject.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TEXT_TYPE = " TEXT ";
    private static final String DATE_TYPE = "DATE";
    private static final String COMMA_SEP = ",";


    public MiConexionHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        StringBuilder create = new StringBuilder()
                .append("CREATE TABLE ")
                .append(PuntosContract.PuntosEntry.TABLE_NAME).append("(")
                .append(PuntosContract.PuntosEntry._ID).append(" INTEGER PRIMARY KEY, ")
                .append(PuntosContract.PuntosEntry.COLUMN_NAME_FECHA).append(TEXT_TYPE).append(COMMA_SEP)
                .append(PuntosContract.PuntosEntry.COLUMN_NAME_TIEMPO).append(TEXT_TYPE).append(")");

        db.execSQL(create.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        StringBuilder delete = new StringBuilder()
                .append("DROP TABLE IF EXIST ").append(PuntosContract.PuntosEntry.TABLE_NAME);
        db.execSQL(delete.toString());
        onCreate(db);
    }
}
