package com.example.dm2.actividadesexamen;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ficheros3 extends AppCompatActivity {

    private ListView listaV;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficheros3);

        listaV = findViewById(R.id.lista);

        // ARRAYLIST para guardar las webs
        ArrayList<Web> datos = new ArrayList<Web>();

        // LEEMOS EL RECURSO
        InputStream is = getResources().openRawResource(R.raw.webs);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        try {
            String linea = br.readLine();
            Log.i("ewew",linea);
            int cont = 0;
            Drawable imagen = getDrawable(R.mipmap.ic_bing);
            while(linea!=null){
                String[] contenido = linea.split(";");
                Log.i("ewew","Vuelta :"+cont+", "+contenido[3]+"");
                Web nuevaWeb = new Web("prueba","prueba",getDrawable(R.mipmap.ic_bing),0);

                if ((contenido[2].toString()).equals("bing.png")){
                    nuevaWeb = new Web(contenido[0],contenido[1],getDrawable(R.mipmap.ic_bing),Integer.parseInt(contenido[3]));
                }
                if ((contenido[2].toString()).equals("yahoo.png")){
                    nuevaWeb = new Web(contenido[0],contenido[1],getDrawable(R.mipmap.ic_yahoo),Integer.parseInt(contenido[3]));
                }
                if ((contenido[2].toString()).equals("google.png")){
                    nuevaWeb = new Web(contenido[0],contenido[1],getDrawable(R.mipmap.ic_google),Integer.parseInt(contenido[3]));
                }
                if ((contenido[2].toString()).equals("youtube.png")){
                    nuevaWeb = new Web(contenido[0],contenido[1],getDrawable(R.mipmap.ic_youtube),Integer.parseInt(contenido[3]));
                }
                if ((contenido[2].toString()).equals("twitch.png")){
                    nuevaWeb = new Web(contenido[0],contenido[1],getDrawable(R.mipmap.ic_twitch),Integer.parseInt(contenido[3]));
                }
                datos.add(nuevaWeb);
                linea = br.readLine();
                cont++;
            }

            final Web[] webs = new Web[datos.size()];
            datos.toArray(webs);

            // ADAPTADOR
            AdaptadorWebs adaptador = new AdaptadorWebs(this,webs);
            listaV.setAdapter(adaptador);

            listaV.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intento = new Intent(Intent.ACTION_VIEW, Uri.parse(webs[position].getEnlace()));
                    startActivity(intento);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
