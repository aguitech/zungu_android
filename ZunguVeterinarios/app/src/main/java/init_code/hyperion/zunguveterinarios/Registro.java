package init_code.hyperion.zunguveterinarios;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Registro extends AppCompatActivity {

    private String _url;
    public static final String idu = "idu";
    public static final String nombre = "";
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }

    public void showPerfilList(View v){
        final CharSequence[] items = {"Servicios Veterinarios", "Servicios Pet Friendly"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                TextView txtPerfil = (TextView)findViewById(R.id.txtPerfil);
                txtPerfil.setText(items[item]);

                TextView txtCategoria = (TextView)findViewById(R.id.txtCategoria);
                txtCategoria.setText("Categoria");
            }
        });

        //AlertDialog alert = builder.create();
        AlertDialog alert = builder.create();

        ListView listView = alert.getListView();
        listView.setDivider(new ColorDrawable(Color.GRAY)); // set color
        listView.setDividerHeight(1); // set height
        //listView.setFooterDividersEnabled(false);
        listView.setOverscrollFooter(new ColorDrawable(Color.TRANSPARENT));

        alert.show();
    }

    public void showCategoriaList(View v){
        final CharSequence[] arrUno = {"Establecimiento + 6 veterinarios", "Establecimiento + 2 veterinarios", "Establecimiento", "Veterinario independiente"};
        final CharSequence[] arrDos = {"Establecimiento Pet Friendly", "Paseador", "Entrenador", "Criador", "Medicina alternativa", "Tienda"};

        TextView txtPerfil = (TextView)findViewById(R.id.txtPerfil);
        final TextView txtCategoria = (TextView)findViewById(R.id.txtCategoria);

        if(txtPerfil.getText().toString().equals("Tipo de perfil")){
            return;
        }

        final CharSequence[] items;
        if(txtPerfil.getText().toString().equals("Servicios Veterinarios")){
            items = arrUno;
        } else {
            items = arrDos;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                txtCategoria.setText(items[item]);
            }
        });

        //AlertDialog alert = builder.create();
        AlertDialog alert = builder.create();

        ListView listView = alert.getListView();
        listView.setDivider(new ColorDrawable(Color.GRAY)); // set color
        listView.setDividerHeight(1); // set height
        //listView.setFooterDividersEnabled(false);
        listView.setOverscrollFooter(new ColorDrawable(Color.TRANSPARENT));

        alert.show();
    }

    public void goLogin(View v){
        Intent i = new Intent(Registro.this, Login.class);
        startActivity(i);
    }

    public void goRegistro(View view) {
        EditText txtEmail = (EditText)findViewById(R.id.txtEmail);
        EditText txtPass = (EditText)findViewById(R.id.txtPassword);
        EditText txtPassDos = (EditText)findViewById(R.id.txtPasswordDos);
        EditText txtNombre = (EditText)findViewById(R.id.txtNombre);
        TextView txtPerfil = (TextView)findViewById(R.id.txtPerfil);
        TextView txtCategoria = (TextView)findViewById(R.id.txtCategoria);

        if(!txtPass.getText().toString().equals(txtPassDos.getText().toString())){
            showMsg("Las contraseñas no coinciden");
            return;
        }

        if(txtPass.getText().toString().length() < 1 || txtEmail.getText().toString().length() < 1 || txtNombre.getText().toString().length() < 1 || txtPerfil.getText().toString().equals("Tipo de perfil") || txtCategoria.getText().toString().equals("Categoria")){
            showMsg("Todos los datos son necesarios.");
        } else {
            _url = "http://hyperion.init-code.com/zungu/registroApp.php?email="+ txtEmail.getText().toString() + "&password=" + txtPass.getText().toString()  + "&nombre=" + URLEncoder.encode(txtNombre.getText().toString())  + "&categoria=" + URLEncoder.encode(txtCategoria.getText().toString())  + "&perfil=" + URLEncoder.encode(txtPerfil.getText().toString());
            new Registro.RetrieveFeedTask().execute();
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
                    int ID = object.getInt("id");
                    String NOMBRE = object.getString("nombre");
                    CharSequence text;

                    if(ID == 0){
                        text = "El email no es válido o ya existe.";
                    } else {
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putInt(idu, ID);
                        editor.putString(nombre, NOMBRE);
                        editor.commit();
                        int value = sharedpreferences.getInt("idu", 0);
                        Log.i("IDU", Integer.toString(value));

                        text = "Bienvenido a Zungu veterinarios";
                        Intent i = new Intent(Registro.this, Principal.class);

                        startActivity(i);
                        finish();
                    }

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Log.i("INFO", response);
        }
    }
}
