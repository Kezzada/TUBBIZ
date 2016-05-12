package com.example.juancarlos.tubbiz.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.juancarlos.tubbiz.Beans.BeanLibro;
import com.example.juancarlos.tubbiz.R;

import java.util.List;

public class AdapterLibro extends BaseAdapter {

    private Context context;
    private int groupid;
    private List<BeanLibro> listaLibros;
    LayoutInflater inflater;

    public AdapterLibro(Context context, List<BeanLibro> listaLibros) {
        this.context = context;
        this.listaLibros = listaLibros;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listaLibros.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {


        View itemView = inflater.inflate(R.layout.layout_item_portada, parent, false);

        ImageView portada = (ImageView) itemView.findViewById(R.id.imagenPortada);
        portada.setImageBitmap(listaLibros.get(position).getPortada());

        TextView nombre = (TextView) itemView.findViewById(R.id.tvNombre);
        nombre.setText(listaLibros.get(position).getNombre());

        TextView precio = (TextView) itemView.findViewById(R.id.tvPrecio);
        precio.setText(Double.toString(listaLibros.get(position).getPrecio()) + "€");

        return itemView;
    }
}