package com.example.dm2.actividadesexamen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LibrosSQLiteHelper extends SQLiteOpenHelper{

    public LibrosSQLiteHelper(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version){

        super(contexto,nombre,factory,version);
    }

    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE LIBROS(isbn NUMBER(13)  PRIMARY KEY,titulo TEXT, autor TEXT)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE LIBROS");
        db.execSQL("CREATE TABLE LIBROS(isbn NUMBER(13)  PRIMARY KEY,titulo TEXT, autor TEXT)");
    }
}
