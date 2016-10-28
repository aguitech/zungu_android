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

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Cambiar_contrasena extends AppCompatActivity {

    private String _url;
    public static final String idu = "idu";
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_contrasena);

        /*
        //
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        //String valueNombre = sharedpreferences.getString("nombre", " ");
        int value = sharedpreferences.getInt("idu", 0);
        //showMsg(valueNombre);
        showMsg("id usuario");
        showMsg(value);
*/

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        int valueID = sharedpreferences.getInt("idu", 0);
        //showMsg(String.valueOf(valueID));
        //showMsg("id usuario");

    }
    public void irAnterior(View view) {
        Intent i = new Intent(Cambiar_contrasena.this, Cuenta.class);
        startActivity(i);
    }
    public void cancelarAccion(View view) {
        Intent i = new Intent(Cambiar_contrasena.this, Cuenta.class);
        startActivity(i);
    }

    public void goMenu(View v){
        Intent i = new Intent(Cambiar_contrasena.this, Menu.class);
        startActivity(i);
    }

    public void goBack(View v){
        finish();
    }

    public void cambiarContrasenia(View view) {
        /*
        EditText txtEmail = (EditText)findViewById(R.id.txtEmail);
        EditText txtPass = (EditText)findViewById(R.id.txtPassword);
*/


        EditText txtContraseniaActual = (EditText)findViewById(R.id.txtContraseniaActual);
        EditText txtNuevaContrasenia = (EditText)findViewById(R.id.txtNuevaContrasenia);
        EditText txtRepetirContrasenia = (EditText)findViewById(R.id.txtRepetirContrasenia);


        //if(txtPass.getText().toString().length() < 1 || txtEmail.getText().toString().length() < 1){
        if(txtContraseniaActual.getText().toString().length() < 3 || txtNuevaContrasenia.getText().toString().length() < 3 || txtRepetirContrasenia.getText().toString().length() < 3){
            showMsg("Introduce las contraseñas.");
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
            //_url = "http://hyperion.init-code.com/zungu/app/vt_cambiar_contrasenia.php?nombre="+ txtNombreServicio.getText().toString() + "&costo=" + txtCostoServicio.getText().toString() + "&duracion=" + txtDuracionServicio.getText().toString() + "&descripcion=" + txtDescripcionServicio.getText().toString() + "&capacidad=" + txtCapacidadServicio.getText().toString() + "&id_veterinario=1";
            //_url = "http://hyperion.init-code.com/zungu/app/vt_cambiar_contrasenia.php?nombre="+ txtNombreServicio.getText().toString() + "&costo=" + txtCostoServicio.getText().toString() + "&duracion=" + txtDuracionServicio.getText().toString() + "&descripcion=" + txtDescripcionServicio.getText().toString() + "&capacidad=" + txtCapacidadServicio.getText().toString() + "&id_veterinario=1";
            //if(txtNuevaContrasenia.getText().toString() == txtRepetirContrasenia.getText().toString()){
            //_url = "http://hyperion.init-code.com/zungu/app/vt_cambiar_contrasenia.php?contrasenia="+ txtContrasenia.getText().toString() + "&id_veterinario=1";

            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            int valueID = sharedpreferences.getInt("idu", 0);
            //showMsg(String.valueOf(valueID));
            //showMsg("id usuario");

            if(txtNuevaContrasenia.getText().toString().equals(txtRepetirContrasenia.getText().toString())){

                _url = "http://hyperion.init-code.com/zungu/app/vt_cambiar_contrasenia.php?contrasenia_actual="+ txtContraseniaActual.getText().toString() + "&nueva_contrasenia=" + txtNuevaContrasenia.getText().toString() + "&id_veterinario=" + String.valueOf(valueID);
                new Cambiar_contrasena.RetrieveFeedTask().execute();
                //showMsg(txtNuevaContrasenia.getText().toString());
                //showMsg(txtRepetirContrasenia.getText().toString());
                //showMsg("Las contraseñas coinciden.");
                txtContraseniaActual.setText("");
                txtNuevaContrasenia.setText("");
                txtRepetirContrasenia.setText("");

            }else{
                //showMsg(txtNuevaContrasenia.getText().toString());
                //showMsg(txtRepetirContrasenia.getText().toString());
                showMsg("Las contraseñas no coinciden.");
            }





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


                try {

                    JSONObject object = (JSONObject) new JSONTokener(response).nextValue();

                    showMsg("Se ha actualizado la contraseña.");

                    //showMsg(response);
/*

                    EditText txtNombre = (EditText) findViewById(R.id.txtNombre);
                    EditText txtDireccion = (EditText) findViewById(R.id.txtDireccion);

                    txtNombre.setText(object.getString("nombre"));
                    txtDireccion.setText(object.getString("direccion"));
                    */
                    /*
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
                    */

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            Log.i("INFO", response);
        }
    }

}

