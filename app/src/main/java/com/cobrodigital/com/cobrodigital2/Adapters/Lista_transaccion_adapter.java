package com.cobrodigital.com.cobrodigital2.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cobrodigital.com.cobrodigital2.Model.Transaccion;
import com.cobrodigital.com.cobrodigital2.R;
import com.cobrodigital.com.cobrodigital2.Transacciones;

import java.util.List;
import java.util.Vector;
import java.util.zip.Inflater;

/**
 * Created by ariel on 15/12/16.
 */

public class Lista_transaccion_adapter extends ArrayAdapter {
    private List<Transaccion> items;

    TextView fecha;
    TextView bol;
    TextView neto;
    TextView info;
    TextView nombre;
    TextView concepto;
    private Context context;
    private int resource;
    private LayoutInflater inflater;
    public Lista_transaccion_adapter(Context context ,int resource ,Vector<Transaccion> items){
        super(context,resource,items);
        this.items=items;
        this.context=context;
        inflater=LayoutInflater.from(context);
        this.resource=resource;

    }

    public View getView(int i,View view_item, ViewGroup parent) {
        view_item = (LinearLayout) inflater.inflate( resource, null );
        Transaccion transaccion=(Transaccion) getItem(i);
        fecha= (TextView) view_item.findViewById(R.id.fecha);
        fecha.setText(transaccion.getFecha());
        bol= (TextView) view_item.findViewById(R.id.bol);
        bol.setText(transaccion.getNro_boleta());
        neto= (TextView) view_item.findViewById(R.id.neto);
        neto.setText(String.valueOf(transaccion.getNeto()));
        info= (TextView) view_item.findViewById(R.id.info);
        info.setText(transaccion.getInfo());
        nombre= (TextView) view_item.findViewById(R.id.nombre);
        nombre.setText(transaccion.getNombre());
        concepto= (TextView) view_item.findViewById(R.id.concepto);
        concepto.setText(transaccion.getConcepto());
        return view_item;
    }
}