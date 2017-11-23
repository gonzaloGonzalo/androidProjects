package com.example.admin.firstproject.persistence;

import java.util.List;

/**
 * Created by admin on 14/11/2017.
 */

public interface PuntosDAO {

    Long agregarPuntaje(String tiempo);
    List<Puntos> getPuntaje();
}
