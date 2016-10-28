package init_code.hyperion.zunguveterinarios;

import android.content.Context;
import android.content.Intent;
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

import init_code.hyperion.zunguveterinarios.adapters.MascotasAdapter;
import init_code.hyperion.zunguveterinarios.adapters.ServicioAdapter;

public class Servicio extends AppCompatActivity {

    ListView lv;
    EditText buscador;
    Context context;
    private String _url;

    public static ArrayList<String> listaImgMascotas = new ArrayList<String>();
    public static ArrayList<String> listaNombreCliente = new ArrayList<String>();
    public static ArrayList<String> listaNombreMascota = new ArrayList<String>();

    public Servicio mActivity = this;
    public ServicioAdapter _mascotasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio);
        lv = (ListView)findViewById(R.id.lista_servicios);

        _mascotasAdapter = new ServicioAdapter(mActivity, listaNombreMascota, listaNombreCliente, listaImgMascotas);
        lv.setAdapter(_mascotasAdapter);

        _url = "http://hyperion.init-code.com/zungu/app/vt_get_servicios.php";
        new Servicio.RetrieveFeedTask().execute();
    }

    public void goMenu(View v){
        Intent i = new Intent(Servicio.this, Menu.class);
        startActivity(i);
    }

    public void goAgregar(View v){
        Intent i = new Intent(Servicio.this, Agregar_servicio.class);
        startActivity(i);
    }

    public void goBack(View v){
        finish();
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

                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject jsonobject = arr.getJSONObject(i);
                        Log.d("nombre",jsonobject.getString("nombre"));

                        listaNombreMascota.add(jsonobject.getString("nombre"));
                        listaNombreCliente.add(jsonobject.getString("costo"));
                        listaImgMascotas.add(jsonobject.getString("id_servicio_veterinario"));
                    }

                    _mascotasAdapter = new ServicioAdapter(mActivity, listaNombreMascota, listaNombreCliente, listaImgMascotas);
                    lv.setAdapter(_mascotasAdapter);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Log.i("INFO", response);
        }
    }
}
