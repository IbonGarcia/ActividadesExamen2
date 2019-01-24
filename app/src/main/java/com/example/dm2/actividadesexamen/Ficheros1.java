package com.example.dm2.actividadesexamen;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Ficheros1 extends AppCompatActivity {

    private final String FICHEROINT = "interno";
    private final String FICHEROEXT = "externo";
    private EditText et;
    private TextView contenido,etiqueta2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficheros1);

        // IGUALAMOS LOS COMPONENTES
        et = findViewById(R.id.editext);
        contenido = findViewById(R.id.contenidoFichero);
        etiqueta2 = findViewById(R.id.etiqueta2);
    }
    // METODO PARA ESCRIBIR EN EL FICHERO INTERNO
    public void escribirInterno(View view){

        try {
            // ABRIMOS EL FICHERO EN MODO APPEND PARA QUE EN CASO DE QUE EXISTA NO LO BORRE
            // AÑADIMOS UN SALTO DE LINEA PARA QUE CADA VEZ QUE ESCRIBAMOS TENGAMOS LINEAS DIFERENTES
            // Y NOS RESULTE MAS COMO LEER
            OutputStreamWriter osw= new OutputStreamWriter(openFileOutput(FICHEROINT,MODE_APPEND));
            osw.write(et.getText()+"\n");
            et.setText("");
            osw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // METODO PARA LEER EL FICHERO INTERNO
    public void leerInterno(View view){

        try {
            // CREMAOS UN BUFFERED READ EL CUAL NOS PERMITE LEER POR LINEAS, HACEMOS UN BUCLE PARA KE LEA
            // Y DESPUES AÑADIMOS EL CONTENIDO A UN STRING PARA PONERSELO A EL TEXTVIEW
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(FICHEROINT)));
            String cont = "";
            String linea = br.readLine();
            while (!(linea ==null)){
                cont = cont+linea+"\n";
                linea = br.readLine();
            }
            etiqueta2.setText("Contenido del fichero Interno:");
            contenido.setText(cont);
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void borrarInterno(View view){

        // BORRAMOS EL FICHERO Y VACIAMOS LA ETIQUETA
        deleteFile(FICHEROINT);
        contenido.setText("");
    }

    // METODO PARA ESCRIBIR EN EL FICHERO EXTERNO
    public void escribirExterno(View view) {

        // PRIMERO COMPROBAMOS SI PODEMOS ACCEDER Y ESCRIBIR
        // SI ENTRAMOS EN EL IF SIGNIFICA QUE TENEMOS ACCESO
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){

            // BUSCAMOS LA RUTA EXTERNA Y HACEMOS EL ARCHIVO EN ELLA
            File ruta = Environment.getExternalStorageDirectory();
            File f = new File(ruta.getAbsolutePath(),FICHEROEXT);
            try {
                OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(f,true));
                osw.write(et.getText()+"\n");
                osw.close();
                // ESTO ESCRIBE EN EL LOG, ASI PODEMOS COMPROBAR SI ESTA CORRECTAMENTE ESCRITO O NO
                // Log.i ("Ficheros", "fichero escrito correctamente");
                et.setText("");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            Toast.makeText(this.getApplicationContext(),"NO HAY MEMOERIA EXTERNA DISPONIBLE PARA ESCRIBIR "+Environment.getExternalStorageState().toString(),Toast.LENGTH_LONG).show();
        }
    }

    // METODO PARA LEER EL FICHERO EXTERNO
    public void leerExterno(View view){

        // COMPROBAMOS SI PODEMOS LEER DE LA MEMORIA EXTERNA
        if ((Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED_READ_ONLY))||(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))){

            File ruta = Environment.getExternalStorageDirectory();
            File f = new File(ruta.getAbsolutePath(),FICHEROEXT);
            try {

                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
                String linea = br.readLine();
                String cont ="";
                while (!(linea==null)){
                    cont = cont+linea+"\n";
                    linea = br.readLine();
                    Log.i("BUCLE","BUCLE BUCLE");
                }
                etiqueta2.setText("Contenido del fichero Externo:");
                contenido.setText(cont);
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            Toast.makeText(this.getApplicationContext(),"NO HAY MEMOERIA EXTERNA DISPONIBLE PARA LEER",Toast.LENGTH_LONG).show();
        }
    }

    public void borrarExterno(View view){

        // BORRAMOS EL FICHERO Y VACIAMOS LA ETIQUETA
        File ruta = Environment.getExternalStorageDirectory();
        File f = new File(ruta.getAbsolutePath(),FICHEROEXT);
        f.delete();
        contenido.setText("");
    }

    public void leerRecurso(View view){

        try {
            InputStream fraw = getResources().openRawResource(R.raw.recurso);
            BufferedReader br = new BufferedReader(new InputStreamReader(fraw));
            String linea=br.readLine();
            String cont ="";
            while(linea!=null){
                cont = cont+linea+"\n";
                linea = br.readLine();
            }
            etiqueta2.setText("Contenido del Recurso:");
            contenido.setText(cont);
            fraw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
