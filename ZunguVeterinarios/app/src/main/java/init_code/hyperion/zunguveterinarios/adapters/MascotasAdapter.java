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

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import init_code.hyperion.zunguveterinarios.Mascotas;
import init_code.hyperion.zunguveterinarios.R;

/**
 * Created by mac on 26/10/16.
 */

public class MascotasAdapter extends BaseAdapter {
    ArrayList<String> _listaNombreMascota;
    ArrayList<String> _listaNombreCliente;
    ArrayList<String> _listaImgMascotas;
    Context context;

    private static LayoutInflater inflater=null;
    public MascotasAdapter(Mascotas mainActivity, ArrayList<String> listaNombreMascota, ArrayList<String> listaNombreCliente, ArrayList<String> listaImgMascotas) {
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
        ImageView imgMascota;
        ImageView imgEliminar;
        ImageView imgDetalle;
    }

    public void reset(){
        Log.d("clickn","entro");
    }

    public void confirm(Context mContext, Integer position, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setCancelable(true);

        final Integer _pos = position;

        builder.setMessage(message);
        builder.setPositiveButton("Eliminar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("clickdos", _pos.toString());

                        _listaNombreCliente.remove(_pos);
                        _listaNombreMascota.remove(_pos);
                        _listaImgMascotas.remove(_pos);
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Holder holder = new Holder();
        String _url;
        final View rowView;
        rowView = inflater.inflate(R.layout.list_mascotas, null);

        holder.txtNombreMascota = (TextView)rowView.findViewById(R.id.txtNombreMascota);
        holder.txtNombreCliente = (TextView)rowView.findViewById(R.id.txtNombreCliente);
        holder.imgMascota = (ImageView)rowView.findViewById(R.id.imgMascota);
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

        holder.txtNombreCliente.setText("Pertenece a: " + _listaNombreCliente.get(position));
        holder.txtNombreMascota.setText(_listaNombreMascota.get(position));
        _url = "http://hyperion.init-code.com/zungu/mascotas_subidas/" + _listaImgMascotas.get(position);
        Log.d("url", _url);
        Log.d("entro", "sii");
        //Glide.with(holder.imgMascota.getContext()).load(_url).into(holder.imgMascota);
        //Picasso.with(holder.imgMascota.getContext()).load(_url).fit().centerCrop().into(holder.imgMascota);

        Picasso.with(holder.imgMascota.getContext()).load(_url)
                .into(holder.imgMascota, new Callback() {
                    @Override
                    public void onSuccess() {
                        Bitmap imageBitmap = ((BitmapDrawable) holder.imgMascota.getDrawable()).getBitmap();
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), imageBitmap);
                        circularBitmapDrawable.setCircular(true);
                        holder.imgMascota.setImageDrawable(circularBitmapDrawable);
                    }
                    @Override
                    public void onError() {
                        //myImageView.setImageResource(R.drawable.default_image);
                    }
                });

        /*
        Glide.with(context).load(_url).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.imgMascota) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);

                holder.imgMascota.setImageDrawable(circularBitmapDrawable);
            }
        });
        */
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