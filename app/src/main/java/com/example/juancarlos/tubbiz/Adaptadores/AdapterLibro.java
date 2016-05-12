package com.example.juancarlos.tubbiz.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.juancarlos.tubbiz.Beans.BeanLibro;
import com.example.juancarlos.tubbiz.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AdapterLibro extends ArrayAdapter<BeanLibro> {

    private Context context;
    private int groupid;
    private List<BeanLibro> listaLibros;

    public AdapterLibro(Context context, int resource) {
        super(context, resource);
    }


    public AdapterLibro(Context context, int vg, int id, ArrayList<BeanLibro>
            listaLibros) {

        super(context, vg, id, listaLibros);

        this.context = context;

        groupid = vg;

        this.listaLibros = listaLibros;


    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(groupid, parent, false);

        ImageView portada = (ImageView) itemView.findViewById(R.id.imagenPortada);
        portada.setImageBitmap(listaLibros.get(position).getPortada());

        TextView nombre = (TextView) itemView.findViewById(R.id.tvNombre);
        nombre.setText(listaLibros.get(position).getNombre());

        TextView precio = (TextView) itemView.findViewById(R.id.tvPrecio);
        precio.setText(Double.toString(listaLibros.get(position).getPrecio()));

        return itemView;
    }
}