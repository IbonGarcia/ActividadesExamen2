package com.example.dm2.actividadesexamen;

import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ContentProvider1 extends AppCompatActivity {

    private TextView datos;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider1);

        this.setTitle("ContentProvider");
        datos = findViewById(R.id.datos);

        String[] tipo ={"","entrante","saliente","perdida"};

        Uri llamadas = Uri.parse("content://call_log/calls");
        Cursor c = getContentResolver().query(llamadas,null,null,null,null);
        if(c.moveToFirst()){
            while(c.moveToNext()){
                datos.append("\n Numero:"+c.getString(c.getColumnIndex(CallLog.Calls.NUMBER))+
                        ", "+c.getString(c.getColumnIndex(CallLog.Calls.DURATION))+
                        " - "+tipo[Integer.parseInt(c.getString(c.getColumnIndex(CallLog.Calls.TYPE)))]);
            }
        }
    }
}
