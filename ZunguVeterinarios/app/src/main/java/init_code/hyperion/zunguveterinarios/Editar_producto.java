package init_code.hyperion.zunguveterinarios;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Editar_producto extends AppCompatActivity {

    private String _url;
    private String _urlEditar;
    public static final String idu = "idu";
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_producto);

        _urlEditar = "http://hyperion.init-code.com/zungu/app/vt_editar_producto.php?id_editar=1";
        new Editar_producto.RetrieveFeedTaskEdit().execute();

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

    }

    public void editarProducto(View view) {
        /*
        EditText txtEmail = (EditText)findViewById(R.id.txtEmail);
        EditText txtPass = (EditText)findViewById(R.id.txtPassword);
*/


        EditText txtNombreProducto = (EditText) findViewById(R.id.txtNombreProducto);
        EditText txtNumeroUnidades = (EditText) findViewById(R.id.txtNumeroUnidades);
        EditText txtPrecioCompra = (EditText) findViewById(R.id.txtPrecioCompra);
        EditText txtPrecioVenta = (EditText) findViewById(R.id.txtPrecioVenta);
        EditText txtDescripcionProducto = (EditText) findViewById(R.id.txtDescripcionProducto);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        int valueID = sharedpreferences.getInt("idu", 0);

        //if(txtPass.getText().toString().length() < 1 || txtEmail.getText().toString().length() < 1){
        //if(txtNombreServicio.getText().toString().length() < 1 || txtCostoServicio.getText().toString().length() < 1){
        if (txtNombreProducto.getText().toString().length() < 1 || txtNumeroUnidades.getText().toString().length() < 1) {
            showMsg("Usuario o password no válido.");
        } else {
            //_url = "http://hyperion.init-code.com/zungu/app/loginApp.php?email="+ txtEmail.getText().toString() + "&password=" + txtPass.getText().toString();

            //_url = "http://hyperion.init-code.com/zungu/app/loginApp.php?email="+ txtEmail.getText().toString() + "&password=" + txtPass.getText().toString();
            _url = "http://hyperion.init-code.com/zungu/app/vt_editar_producto.php?id_editar=1&nombre=" + URLEncoder.encode(txtNombreProducto.getText().toString()) + "&numero_unidades=" + URLEncoder.encode(txtNumeroUnidades.getText().toString()) + "&precio_compra=" + URLEncoder.encode(txtPrecioCompra.getText().toString()) + "&precio_venta=" + URLEncoder.encode(txtPrecioVenta.getText().toString()) + "&descripcion=" + URLEncoder.encode(txtDescripcionProducto.getText().toString()) + "&id_veterinario=" + String.valueOf(valueID);
            new Editar_producto.RetrieveFeedTask().execute();
        }

    }
    private void showMsg(CharSequence text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
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
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if (response == null) {
                response = "THERE WAS AN ERROR";
            } else {
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;

                showMsg("Se ha actualizado la información");
                /*
                try {

                    JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
                    int ID = object.getInt("id");
                    CharSequence text;

                    if(ID == 0){
                        text = "Usuario o password no válido.";
                    } else {
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putInt(idu, ID);
                        editor.commit();
                        int value = sharedpreferences.getInt("idu", 0);
                        Log.i("IDU", Integer.toString(value));

                        text = "Bienvenido a Zungu veterinarios";
                    }

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                */
            }
            Log.i("INFO", response);
        }
    }
    class RetrieveFeedTaskEdit extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
        }

        protected String doInBackground(Void... urls) {
            try {
                Log.i("INFO url: ", _urlEditar);
                URL url = new URL(_urlEditar);
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
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if (response == null) {
                response = "THERE WAS AN ERROR";
            } else {
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;


                EditText txtNombreProducto = (EditText) findViewById(R.id.txtNombreProducto);
                EditText txtNumeroUnidades = (EditText) findViewById(R.id.txtNumeroUnidades);
                EditText txtPrecioCompra = (EditText) findViewById(R.id.txtPrecioCompra);
                EditText txtPrecioVenta = (EditText) findViewById(R.id.txtPrecioVenta);
                EditText txtDescripcionProducto = (EditText) findViewById(R.id.txtDescripcionProducto);



                try {

                    JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
                    //int ID = object.getInt("id_producto");
                    //CharSequence text;


                    //showMsg(response);
                    //showMsg(object.getString("descripcion"));

                    txtNombreProducto.setText(object.getString("nombre"));
                    txtNumeroUnidades.setText(object.getString("numero_unidades"));
                    txtPrecioCompra.setText(object.getString("precio_compra"));
                    txtPrecioVenta.setText(object.getString("precio_venta"));
                    txtDescripcionProducto.setText(object.getString("descripcion"));




                    //Toast toast = Toast.makeText(context, text, duration);
                    //toast.show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }






                //showMsg(response);

                //showMsg("Respuesta");

                //showMsg(response);

                //showMsg(response.getString("descripcion"));
                //showMsg(response.getString("descripcion"));


                //showMsg(response);
                /*
                try {

                    JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
                    int ID = object.getInt("id");
                    CharSequence text;

                    if(ID == 0){
                        text = "Usuario o password no válido.";
                    } else {
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putInt(idu, ID);
                        editor.commit();
                        int value = sharedpreferences.getInt("idu", 0);
                        Log.i("IDU", Integer.toString(value));

                        text = "Bienvenido a Zungu veterinarios";
                    }

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                */
            }
            Log.i("INFO", response);
        }
    }
}
