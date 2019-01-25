package com.example.dm2.actividadesexamen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LibrosSQliteHelper extends SQLiteOpenHelper{

    public LibrosSQliteHelper(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version){

        super(contexto,nombre,factory,version);
    }

    public void onCreate(SQLiteDatabase db) {
        String crearTabla = "CREATE TABLE LIBROS (nombre TEXT, telefono NUMBER(9))";
        db.execSQL(crearTabla);
        insertar(db);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE LIBROS");
        db.execSQL("CREATE TABLE LIBROS (nombre TEXT, telefono TEXT(9))");
        insertar(db);
    }

    public void insertar(SQLiteDatabase db){
        db.execSQL( "INSERT INTO LIBROS values('Aa Ama',688806219)");
        db.execSQL( "INSERT INTO LIBROS values('YO',657712777)");
        db.execSQL( "INSERT INTO LIBROS values('Unai',627886623)");
        db.execSQL( "INSERT INTO LIBROS values('Marck',662544764)");
        db.execSQL( "INSERT INTO LIBROS values('Goio',670318901)");
    }
}
