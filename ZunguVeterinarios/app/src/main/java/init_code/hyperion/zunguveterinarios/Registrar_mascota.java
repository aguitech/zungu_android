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

public class Registrar_mascota extends AppCompatActivity {

    private String _url;
    public static final String idu = "idu";
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_mascota);
    }
    public void registrarMascota(View view) {
        /*
        EditText txtEmail = (EditText)findViewById(R.id.txtEmail);
        EditText txtPass = (EditText)findViewById(R.id.txtPassword);
*/


        EditText txtNombre = (EditText)findViewById(R.id.txtNombre);
        EditText txtFechaNacimiento = (EditText)findViewById(R.id.txtFechaNacimiento);
        EditText txtEspecie = (EditText)findViewById(R.id.txtEspecie);
        EditText txtRaza = (EditText)findViewById(R.id.txtRaza);
        EditText txtPeso = (EditText)findViewById(R.id.txtPeso);
        EditText txtColor = (EditText)findViewById(R.id.txtColor);
        EditText txtHeats = (EditText)findViewById(R.id.txtHeats);
        //EditText txtSexo = (EditText)findViewById(R.id.txtSexo);
        EditText txtSeniasParticulares = (EditText)findViewById(R.id.txtSeniasParticulares);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        int valueID = sharedpreferences.getInt("idu", 0);

        //if(txtPass.getText().toString().length() < 1 || txtEmail.getText().toString().length() < 1){
        if(txtNombre.getText().toString().length() < 1 || txtFechaNacimiento.getText().toString().length() < 1){
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
            _url = "http://hyperion.init-code.com/zungu/app/vt_registrar_mascota.php?nombre="+ txtNombre.getText().toString() + "&fecha_nacimiento=" + txtFechaNacimiento.getText().toString() + "&especie=" + txtEspecie.getText().toString() + "&raza=" + txtRaza.getText().toString() + "&peso=" + txtPeso.getText().toString() + "&color=" + txtColor.getText().toString() + "&heats=" + txtHeats.getText().toString() + "&senias_particulares=" + txtSeniasParticulares.getText().toString() + "&id_veterinario=" + String.valueOf(valueID);
            //new Agregar_servicio.RetrieveFeedTask().execute();
            new Registrar_mascota.RetrieveFeedTask().execute();
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

                showMsg("Se ha registrado mascota.");

                EditText txtNombre = (EditText)findViewById(R.id.txtNombre);
                EditText txtFechaNacimiento = (EditText)findViewById(R.id.txtFechaNacimiento);
                EditText txtEspecie = (EditText)findViewById(R.id.txtEspecie);
                EditText txtRaza = (EditText)findViewById(R.id.txtRaza);
                EditText txtPeso = (EditText)findViewById(R.id.txtPeso);
                EditText txtColor = (EditText)findViewById(R.id.txtColor);
                EditText txtHeats = (EditText)findViewById(R.id.txtHeats);
                //EditText txtSexo = (EditText)findViewById(R.id.txtSexo);
                EditText txtSeniasParticulares = (EditText)findViewById(R.id.txtSeniasParticulares);

                txtNombre.setText("");
                txtFechaNacimiento.setText("");
                txtEspecie.setText("");
                txtRaza.setText("");
                txtPeso.setText("");
                txtColor.setText("");
                txtHeats.setText("");
                txtSeniasParticulares.setText("");

            }
            Log.i("INFO", response);
        }
    }
}
