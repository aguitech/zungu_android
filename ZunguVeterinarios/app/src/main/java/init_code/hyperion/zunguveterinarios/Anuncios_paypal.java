package init_code.hyperion.zunguveterinarios;

import android.content.Context;
import android.content.Intent;
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
import java.net.URLEncoder;

public class Anuncios_paypal extends AppCompatActivity {

    private String _url;
    public static final String idu = "idu";
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncios_paypal);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }

    public void cancelarPago(View view) {
        finish();
    }

    public void goMenu(View v){
        Intent i = new Intent(Anuncios_paypal.this, Menu.class);
        startActivity(i);
    }

    public void goBack(View v){
        finish();
    }

    public void agregarPagoPaypal(View view) {
        /*
        EditText txtEmail = (EditText)findViewById(R.id.txtEmail);
        EditText txtPass = (EditText)findViewById(R.id.txtPassword);
*/


        EditText txtNombre = (EditText)findViewById(R.id.txtNombre);
        EditText txtApellido = (EditText)findViewById(R.id.txtApellido);

        EditText txtPais = (EditText)findViewById(R.id.txtPais);
        EditText txtEstado = (EditText)findViewById(R.id.txtEstado);
        EditText txtCodigoPostal = (EditText)findViewById(R.id.txtCodigoPostal);

        EditText txtCantidad = (EditText)findViewById(R.id.txtCantidad);
        EditText txtEmail = (EditText)findViewById(R.id.txtEmail);


        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        int valueID = sharedpreferences.getInt("idu", 0);

        //if(txtPass.getText().toString().length() < 1 || txtEmail.getText().toString().length() < 1){
        if(txtPais.getText().toString().length() < 1 || txtEstado.getText().toString().length() < 1){
            showMsg("Todos los datos son necesarios.");
        } else {
            //_url = "http://hyperion.init-code.com/zungu/app/loginApp.php?email="+ txtEmail.getText().toString() + "&password=" + txtPass.getText().toString();
            /**
             private EditText txtNombreServicio;
             private EditText txtCostoServicio;
             private EditText txtDuracionServicio;
             private EditText txtDescripcionServicio;
             private EditText txtCapacidadServicio;
             * */
            //_url = "http://hyperion.init-code.com/zungu/app/loginApp.php?email="+ txtEmail.getText().toString() + "&password=" + txtPass.getText().toString();
            //_url = "http://hyperion.init-code.com/zungu/app/vt_agregar_servicio.php?nombre="+ txtNombreServicio.getText().toString() + "&costo=" + txtCostoServicio.getText().toString() + "&duracion=" + txtDuracionServicio.getText().toString() + "&descripcion=" + txtDescripcionServicio.getText().toString() + "&capacidad=" + txtCapacidadServicio.getText().toString() + "&id_veterinario=1";
            //_url = "http://hyperion.init-code.com/zungu/app/vt_agregar_pago_paypal.php?pais="+ txtPais.getText().toString() + "&estado=" + txtEstado.getText().toString() + "&codigo_postal=" + txtCodigoPostal.getText().toString() + "&numero_spei=" + txtNumeroSpei.getText().toString() + "&id_veterinario=1";
            //_url = "http://hyperion.init-code.com/zungu/app/vt_agregar_pago_paypal.php?pais="+ txtPais.getText().toString() + "&estado=" + txtEstado.getText().toString() + "&codigo_postal=" + txtCodigoPostal.getText().toString() + "&numero_spei=" + txtNumeroSpei.getText().toString() + "&id_veterinario=1";
            _url = "http://hyperion.init-code.com/zungu/app/vt_agregar_pago_paypal.php?nombre=" + URLEncoder.encode(txtNombre.getText().toString()) + "&apellido=" + URLEncoder.encode(txtApellido.getText().toString()) + "&email=" + URLEncoder.encode(txtEmail.getText().toString()) + "&pais=" + URLEncoder.encode(txtPais.getText().toString()) + "&estado=" + URLEncoder.encode(txtEstado.getText().toString()) + "&codigo_postal=" + URLEncoder.encode(txtCodigoPostal.getText().toString()) + "&cantidad=" + URLEncoder.encode(txtCantidad.getText().toString()) + "&id_veterinario=" + String.valueOf(valueID);
            //new Agregar_servicio.RetrieveFeedTask().execute();
            new Anuncios_paypal.RetrieveFeedTask().execute();
        }
    }
    private void showMsg(CharSequence text){
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

                showMsg("Se ha agregado PayPal.");

                EditText txtNombre = (EditText)findViewById(R.id.txtNombre);
                EditText txtApellido = (EditText)findViewById(R.id.txtApellido);

                EditText txtPais = (EditText)findViewById(R.id.txtPais);
                EditText txtEstado = (EditText)findViewById(R.id.txtEstado);
                EditText txtCodigoPostal = (EditText)findViewById(R.id.txtCodigoPostal);

                EditText txtCantidad = (EditText)findViewById(R.id.txtCantidad);
                EditText txtEmail = (EditText)findViewById(R.id.txtEmail);
                //EditText txtNumeroSpei = (EditText)findViewById(R.id.txtNumeroSpei);

                txtNombre.setText("");
                txtApellido.setText("");
                txtCantidad.setText("");
                txtEmail.setText("");

                txtPais.setText("");
                txtEstado.setText("");
                txtCodigoPostal.setText("");
                //txtNumeroSpei.setText("");

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
