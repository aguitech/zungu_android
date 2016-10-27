package init_code.hyperion.zunguveterinarios.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import init_code.hyperion.zunguveterinarios.Mascotas;
import init_code.hyperion.zunguveterinarios.R;
import init_code.hyperion.zunguveterinarios.Servicio;

/**
 * Created by mac on 26/10/16.
 */

public class ServicioAdapter extends BaseAdapter {
    ArrayList<String> _listaNombreMascota;
    ArrayList<String> _listaNombreCliente;
    ArrayList<String> _listaImgMascotas;
    Context context;

    private static LayoutInflater inflater=null;
    public ServicioAdapter(Servicio mainActivity, ArrayList<String> listaNombreMascota, ArrayList<String> listaNombreCliente, ArrayList<String> listaImgMascotas) {
        _listaNombreMascota = listaNombreMascota;
        _listaNombreCliente = listaNombreCliente;
        _listaImgMascotas = listaImgMascotas;
        context = mainActivity;

        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return _listaNombreCliente.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView txtNombreMascota;
        TextView txtNombreCliente;
        //ImageView imgMascota;
        ImageView imgEliminar;
        ImageView imgDetalle;
    }

    public void reset(){
        Log.d("clickn","entro");
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Holder holder = new Holder();
        String _url;
        final View rowView;
        rowView = inflater.inflate(R.layout.list_servicio, null);

        holder.txtNombreMascota = (TextView)rowView.findViewById(R.id.txtNombre);
        holder.txtNombreCliente = (TextView)rowView.findViewById(R.id.txtCosto);
        holder.imgEliminar = (ImageView)rowView.findViewById(R.id.imgEliminar);
        holder.imgDetalle = (ImageView)rowView.findViewById(R.id.imgDetalle);

        holder.imgEliminar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //confirm(holder.imgMascota.getContext(), position, "Eliminar mascota: " + _listaNombreMascota.get(position));
                _listaNombreCliente.remove(position);
                _listaNombreMascota.remove(position);
                _listaImgMascotas.remove(position);

                notifyDataSetChanged();
            }
        });

        holder.imgDetalle.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Log.d("click", "detalle");
            }
        });
        Log.d("entro carga", "entro");
        holder.txtNombreCliente.setText(_listaNombreCliente.get(position));
        holder.txtNombreMascota.setText(_listaNombreMascota.get(position));

        /*
        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //click fila
            }
        });
        */
        return rowView;
    }

}
