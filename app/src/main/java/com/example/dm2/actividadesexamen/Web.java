package com.example.dm2.actividadesexamen;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class Web {

    private String nombre;
    private String enlace;
    private Drawable icono;
    private int id;

    public Web(String nom, String link, Drawable foto, int id){

        nombre = nom;
        enlace = link;
        icono = foto;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEnlace() {
        return enlace;
    }

    public Drawable getIcono() {
        return icono;
    }

    public int getId() {
        return id;
    }
}
