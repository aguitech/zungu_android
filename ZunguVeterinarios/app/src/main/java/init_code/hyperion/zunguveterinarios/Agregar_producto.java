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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Agregar_producto extends AppCompatActivity {

    private String _url;
    public static final String idu = "idu";
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        //btnAgregar.setOnClickListener();

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

    }

    public void agregarProducto(View view) {
        /*
        EditText txtEmail = (EditText)findViewById(R.id.txtEmail);
        EditText txtPass = (EditText)findViewById(R.id.txtPassword);
*/


        EditText txtNombreProducto = (EditText) findViewById(R.id.txtNombreProducto);
        EditText txtNumeroUnidades = (EditText) findViewById(R.id.txtNumeroUnidades);
        EditText txtPrecioCompra = (EditText) findViewById(R.id.txtPrecioCompra);
        EditText txtPrecioVenta = (EditText) findViewById(R.id.txtPrecioVenta);
        EditText txtDescripcionProducto = (EditText) findViewById(R.id.txtDescripcionProducto);



        //if(txtPass.getText().toString().length() < 1 || txtEmail.getText().toString().length() < 1){
        //if(txtNombreServicio.getText().toString().length() < 1 || txtCostoServicio.getText().toString().length() < 1){
        if (txtNombreProducto.getText().toString().length() < 1 || txtNumeroUnidades.getText().toString().length() < 1) {
            showMsg("Usuario o password no válido.");
        } else {
            //_url = "http://hyperion.init-code.com/zungu/app/loginApp.php?email="+ txtEmail.getText().toString() + "&password=" + txtPass.getText().toString();

            //_url = "http://hyperion.init-code.com/zungu/app/loginApp.php?email="+ txtEmail.getText().toString() + "&password=" + txtPass.getText().toString();
            _url = "http://hyperion.init-code.com/zungu/app/vt_agregar_producto.php?nombre=" + txtNombreProducto.getText().toString() + "&numero_unidades=" + txtNumeroUnidades.getText().toString() + "&precio_compra=" + txtPrecioCompra.getText().toString() + "&precio_venta=" + txtPrecioVenta.getText().toString() + "&descripcion=" + txtDescripcionProducto.getText().toString() + "&id_veterinario=1";
            new Agregar_producto.RetrieveFeedTask().execute();
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