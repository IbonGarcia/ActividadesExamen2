package com.example.dm2.actividadesexamen;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SQLite2 extends AppCompatActivity {

    private EditText isbn, titulo,autor;
    private TextView contenido;
    private SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite2);

        isbn = findViewById(R.id.editISBN);
        titulo = findViewById(R.id.editTITULO);
        autor = findViewById(R.id.editAUTOR);
        contenido = findViewById(R.id.contenido);

        LibrosSQLiteHelper ldb = new LibrosSQLiteHelper(this,"DBLibros",null,1);
        db = ldb.getWritableDatabase();

        this.setTitle("Gestion de libros");
    }

    public void insertar(View view){
        String is = isbn.getText().toString();
        String tit = titulo.getText().toString();
        String aut= autor.getText().toString();
        db.execSQL("INSERT INTO LIBROS VALUES('"+is+"', '"+tit+"', '"+aut+"')");
    }

    public void buscar(View view){
        String buscado = isbn.getText().toString();
        Cursor info = db.rawQuery("SELECT * FROM LIBROS WHERE isbn= '"+buscado+"'",null);
        if (info.moveToFirst()){
            String titu = info.getString(1);
            String aut = info.getString(2);
            titulo.setText(titu);
            autor.setText(aut);
        }
    }

    public void borrar(View view){
        String is = isbn.getText().toString();
        db.execSQL("DELETE FROM LIBROS where isbn = '"+is+"'");
        limpiar(view);
        listar(view);
    }

    public void limpiar(View view){
        isbn.setText("");
        titulo.setText("");
        autor.setText("");
        contenido.setText("");
    }

    public void listar(View view){

        Cursor info = db.rawQuery("SELECT * FROM LIBROS ORDER BY isbn",null);
        String datos="Lista de libros:\n";

        if (info.moveToFirst()){
            int cont = 0;
            do {
                cont++;
                datos = datos+cont+" - "+info.getString(0)+"; "+info.getString(1)+", "+info.getString(2)+"\n";
            }while(info.moveToNext());
        }
        contenido.setText(datos);
    }

    public void modificar(View view){

        String is = isbn.getText().toString();
        String tit = titulo.getText().toString();
        String aut = autor.getText().toString();
        db.execSQL("UPDATE LIBROS set titulo ='"+tit+"', autor='"+aut+"' where isbn='"+is+"'");
        listar(view);
    }
}
