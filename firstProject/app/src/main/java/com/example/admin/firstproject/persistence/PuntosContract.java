package com.example.admin.firstproject.persistence;

import android.provider.BaseColumns;

/**
 * Created by admin on 9/11/2017.
 */

public final class PuntosContract {

    private PuntosContract(){
    }

    public static  class PuntosEntry implements BaseColumns {
        public static final String TABLE_NAME = "puntos";
        public static final String COLUMN_NAME_FECHA = "fecha";
        public static final String COLUMN_NAME_TIEMPO = "tiempo";
    }
}
