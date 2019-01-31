package com.example.dm2.actividadesexamen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdaptadorWebs extends ArrayAdapter<Web>{

    private Web[] datos;

    public AdaptadorWebs(Context context,Web[] datos){
        super(context,R.layout.listitem_web,datos);
        this.datos = datos;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.listitem_web, null);

        TextView nombre = (TextView) item.findViewById(R.id.nombreWeb);
        nombre.setText(datos[position].getNombre());

        TextView link = (TextView) item.findViewById(R.id.enlace);
        link.setText(datos[position].getEnlace());

        ImageView imagen = (ImageView) item.findViewById(R.id.icono);
        imagen.setImageDrawable(datos[position].getIcono());

        TextView id = (TextView) item.findViewById(R.id.identificador);
        id.setText(datos[position].getId()+"");

        return (item);
    }
}
