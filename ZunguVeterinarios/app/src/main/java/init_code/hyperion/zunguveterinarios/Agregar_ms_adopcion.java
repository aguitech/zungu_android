package init_code.hyperion.zunguveterinarios;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Agregar_ms_adopcion extends AppCompatActivity {

    private String _url;
    public static final String idu = "idu";
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_ms_adopcion);
    }
    public void agregarAdopcion(View view) {
        /*
        EditText txtEmail = (EditText)findViewById(R.id.txtEmail);
        EditText txtPass = (EditText)findViewById(R.id.txtPassword);
*/


        EditText txtNombre = (EditText)findViewById(R.id.txtNombre);
        EditText txtEdad = (EditText)findViewById(R.id.txtEdad);
        EditText txtDescripcion = (EditText)findViewById(R.id.txtDescripcion);
        EditText txtNombreUsuario = (EditText)findViewById(R.id.txtNombreUsuario);
        EditText txtNumeroUsuario = (EditText)findViewById(R.id.txtNumeroUsuario);
        EditText txtCorreoUsuario = (EditText)findViewById(R.id.txtCorreoUsuario);


        //if(txtPass.getText().toString().length() < 1 || txtEmail.getText().toString().length() < 1){
        if(txtNombre.getText().toString().length() < 1 || txtEdad.getText().toString().length() < 1){
            showMsg("Necesitas completar el formulario.");
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
            //_url = "http://hyperion.init-code.com/zungu/app/vt_agregar_adocion.php?nombre="+ txtNombre.getText().toString() + "&edad=" + txtEdad.getText().toString() + "&descripcion=" + txtDescripcion.getText().toString() + "&nombre_usuario=" + txtNombreUsuario.getText().toString() + "&correo_usuario=" + txtCorreoUsuario.getText().toString() + "&numero_usuario=" + txtNumeroUsuario.getText().toString() + "&id_veterinario=1";
            _url = "http://hyperion.init-code.com/zungu/app/vt_agregar_mascota_adopcion.php?nombre="+ txtNombre.getText().toString() + "&edad=" + txtEdad.getText().toString() + "&descripcion=" + txtDescripcion.getText().toString() + "&nombre_usuario=" + txtNombreUsuario.getText().toString() + "&correo_usuario=" + txtCorreoUsuario.getText().toString() + "&numero_usuario=" + txtNumeroUsuario.getText().toString() + "&id_veterinario=1";
            //new Agregar_servicio.RetrieveFeedTask().execute();
            new Agregar_ms_adopcion.RetrieveFeedTask().execute();
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

                showMsg("Se ha registrado mascota para adopción.");

                EditText txtNombre = (EditText)findViewById(R.id.txtNombre);
                EditText txtEdad = (EditText)findViewById(R.id.txtEdad);
                EditText txtDescripcion = (EditText)findViewById(R.id.txtDescripcion);
                EditText txtNombreUsuario = (EditText)findViewById(R.id.txtNombreUsuario);
                EditText txtNumeroUsuario = (EditText)findViewById(R.id.txtNumeroUsuario);
                EditText txtCorreoUsuario = (EditText)findViewById(R.id.txtCorreoUsuario);

                txtNombre.setText("");
                txtEdad.setText("");
                txtDescripcion.setText("");
                txtNombreUsuario.setText("");
                txtNumeroUsuario.setText("");
                txtCorreoUsuario.setText("");

            }
            Log.i("INFO", response);
        }
    }
}