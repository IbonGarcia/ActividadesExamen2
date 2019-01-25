package com.example.dm2.actividadesexamen;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SQlite1 extends AppCompatActivity {

    private TextView datos;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite1);
        this.setTitle("AGENDA");

        datos = findViewById(R.id.datos);

        LibrosSQliteHelper ldb = new LibrosSQliteHelper(this,"DBLibros",null,1);

        SQLiteDatabase db = ldb.getWritableDatabase();
        String datosconsulta="";
        if (db!=null){

          Cursor info = db.rawQuery("SELECT * FROM LIBROS",null);
          if (info.moveToFirst()){
              int cont=0;
              do{
                  datosconsulta =datosconsulta+cont+" - "+info.getString(0)+", "+info.getInt(1)+"\n\n";
                  cont++;
              }while(info.moveToNext());
          }
        }
        db.close();
        datos.setText(datosconsulta);
    }
}
