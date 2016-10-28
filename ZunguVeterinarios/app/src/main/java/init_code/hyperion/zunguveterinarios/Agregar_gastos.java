package init_code.hyperion.zunguveterinarios;

import android.content.Context;
import android.content.Intent;
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
import java.net.URLEncoder;

public class Agregar_gastos extends AppCompatActivity {

    private String _url;
    public static final String idu = "idu";
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_gastos);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }

    public void goMenu(View v){
        Intent i = new Intent(Agregar_gastos.this, Menu.class);
        startActivity(i);
    }

    public void goGastos(View v){
        Intent i = new Intent(Agregar_gastos.this, Gastos.class);
        startActivity(i);
    }

    public void goAgregar(View v){
        Intent i = new Intent(Agregar_gastos.this, Gastos.class);
        startActivity(i);
    }

    public void goCancelar(View v){
        Intent i = new Intent(Agregar_gastos.this, Gastos.class);
        startActivity(i);
    }

    public void goBack(View v){
        finish();
    }

    public void agregarGasto(View view) {
        /*
        EditText txtEmail = (EditText)findViewById(R.id.txtEmail);
        EditText txtPass = (EditText)findViewById(R.id.txtPassword);
*/


        EditText txtConcepto = (EditText) findViewById(R.id.txtConcepto);
        EditText txtCantidad = (EditText) findViewById(R.id.txtCantidad);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        int valueID = sharedpreferences.getInt("idu", 0);


        //if(txtPass.getText().toString().length() < 1 || txtEmail.getText().toString().length() < 1){
        //if(txtNombreServicio.getText().toString().length() < 1 || txtCostoServicio.getText().toString().length() < 1){
        if (txtConcepto.getText().toString().length() < 1 || txtCantidad.getText().toString().length() < 1) {
            showMsg("Todos los campos son necesarios.");
        } else {
            //_url = "http://hyperion.init-code.com/zungu/app/loginApp.php?email="+ txtEmail.getText().toString() + "&password=" + txtPass.getText().toString();

            //_url = "http://hyperion.init-code.com/zungu/app/loginApp.php?email="+ txtEmail.getText().toString() + "&password=" + txtPass.getText().toString();
            //_url = "http://hyperion.init-code.com/zungu/app/vt_agregar_producto.php?nombre=" + txtNombreProducto.getText().toString() + "&numero_unidades=" + txtNumeroUnidades.getText().toString() + "&precio_compra=" + txtPrecioCompra.getText().toString() + "&precio_venta=" + txtPrecioVenta.getText().toString() + "&descripcion=" + txtDescripcionProducto.getText().toString() + "&id_veterinario=1";
            //_url = "http://hyperion.init-code.com/zungu/app/vt_agregar_producto.php?nombre=" + txtNombreProducto.getText().toString() + "&numero_unidades=" + txtNumeroUnidades.getText().toString() + "&precio_compra=" + txtPrecioCompra.getText().toString() + "&precio_venta=" + txtPrecioVenta.getText().toString() + "&descripcion=" + txtDescripcionProducto.getText().toString() + "&id_veterinario=" + String.valueOf(valueID);
            //_url = "http://hyperion.init-code.com/zungu/app/vt_agregar_producto.php?nombre=" + txtNombreProducto.getText() + "&numero_unidades=" + txtNumeroUnidades.getText() + "&precio_compra=" + txtPrecioCompra.getText() + "&precio_venta=" + txtPrecioVenta.getText() + "&descripcion=" + txtDescripcionProducto.getText() + "&id_veterinario=" + String.valueOf(valueID);
            //_url = "http://hyperion.init-code.com/zungu/app/vt_agregar_producto.php?nombre=" + txtNombreProducto.getText().toString() + "&numero_unidades=" + txtNumeroUnidades.getText().toString() + "&precio_compra=" + txtPrecioCompra.getText().toString() + "&precio_venta=" + txtPrecioVenta.getText().toString() + "&descripcion=" + txtDescripcionProducto.getText().toString() + "&id_veterinario=" + String.valueOf(valueID);
            _url = "http://hyperion.init-code.com/zungu/app/vt_agregar_gasto.php?concepto=" + URLEncoder.encode(txtConcepto.getText().toString()) + "&cantidad=" + URLEncoder.encode(txtCantidad.getText().toString()) + "&id_veterinario=" + String.valueOf(valueID);
            //
            new Agregar_gastos.RetrieveFeedTask().execute();
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

                showMsg("Gasto agregado con éxito.");
                Intent i = new Intent(Agregar_gastos.this, Gastos.class);
                startActivity(i);


            }
            Log.i("INFO", response);
        }
    }


}
