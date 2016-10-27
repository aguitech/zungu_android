package init_code.hyperion.zunguveterinarios;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;

import init_code.hyperion.zunguveterinarios.adapters.ClienteAdapter;
import init_code.hyperion.zunguveterinarios.adapters.MascotasAdapter;

public class Clientes extends AppCompatActivity {

    ListView lv;
    EditText buscador;
    Context context;
    private String _url;

    public static ArrayList<String> listaImgMascotas = new ArrayList<String>();
    public static ArrayList<String> listaNombreCliente = new ArrayList<String>();
    public static ArrayList<String> listaNombreMascota = new ArrayList<String>();
    public static ArrayList<String> listaIDS = new ArrayList<String>();
    public Clientes mActivity = this;
    public ClienteAdapter _mascotasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        lv = (ListView)findViewById(R.id.lvClientes);
        buscador = (EditText)findViewById(R.id.txtBuscador);

        _mascotasAdapter = new ClienteAdapter(mActivity, listaNombreMascota, listaNombreCliente, listaImgMascotas, listaIDS);
        lv.setAdapter(_mascotasAdapter);

        buscador.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    //Log.d("click", String.valueOf(event.getRawX()));
                    //Log.d("izq", String.valueOf(buscador.getTotalPaddingLeft()));
                    //Log.d("der", String.valueOf(buscador.getTotalPaddingRight()));
                    //Log.d("total", String.valueOf(buscador.getWidth()));
                    if(event.getRawX() >= ((buscador.getWidth() - buscador.getTotalPaddingRight()))) {
                        String param = buscador.getText().toString();

                        try {
                            _url = "http://hyperion.init-code.com/zungu/app/vt_get_clientes.php?search=" + URLEncoder.encode(param, "UTF-8");

                            lv.setAdapter(null);
                            new Clientes.RetrieveFeedTask().execute();
                            return true;
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                    }
                }
                return false;
            }
        });

        _url = "http://hyperion.init-code.com/zungu/app/vt_get_clientes.php";
        new Clientes.RetrieveFeedTask().execute();
    }

    public void showMascotas(View v){
        _url = "http://hyperion.init-code.com/zungu/app/vt_get_clientes.php";
        lv.setAdapter(null);
        new Clientes.RetrieveFeedTask().execute();
    }

    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
        }

        protected String doInBackground(Void... urls) {
            try {
                Log.i("INFO url: ", _url);
                URL url = new URL(_url);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if(response == null) {
                response = "THERE WAS AN ERROR";
            } else {
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;

                try {
                    JSONTokener tokener = new JSONTokener(response);
                    JSONArray arr = new JSONArray(tokener);

                    listaNombreMascota.clear();
                    listaNombreCliente.clear();
                    listaImgMascotas.clear();
                    listaIDS.clear();

                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject jsonobject = arr.getJSONObject(i);
                        Log.d("nombre",jsonobject.getString("nombre"));
                        Log.d("foto",jsonobject.getString("foto"));

                        listaNombreMascota.add(jsonobject.getString("fecha_registro"));
                        listaNombreCliente.add(jsonobject.getString("nombre"));
                        listaImgMascotas.add(jsonobject.getString("foto"));
                        listaIDS.add(jsonobject.getString("id_usuario"));
                    }

                    _mascotasAdapter = new ClienteAdapter(mActivity, listaNombreMascota, listaNombreCliente, listaImgMascotas, listaIDS);
                    //_mascotasAdapter.notifyDataSetChanged();
                    lv.setAdapter(_mascotasAdapter);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Log.i("INFO", response);
        }
    }
}
