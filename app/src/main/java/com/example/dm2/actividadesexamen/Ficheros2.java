package com.example.dm2.actividadesexamen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ficheros2 extends AppCompatActivity {

    private Spinner spinner;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficheros2);

        try {
            spinner = findViewById(R.id.spinner);

            // INSTANCIAMOS UN ARRAYLIST Y LO VAMOS A CARGAR CON LOS DATOS DEL RECURSO
            ArrayList<String> lista = new ArrayList<String>();
            // LEEEMOS EL RECURSO
            InputStream is = getResources().openRawResource(R.raw.provincias);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String linea = null;
            linea = br.readLine();

            while (linea!= null){
                lista.add(linea);
                linea = br.readLine();
            }
            ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,lista);
            spinner.setAdapter(adaptador);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
