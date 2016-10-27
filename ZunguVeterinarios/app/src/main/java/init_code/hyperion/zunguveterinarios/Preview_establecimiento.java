package init_code.hyperion.zunguveterinarios;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Preview_establecimiento extends AppCompatActivity {

    private String _urlGet;
    public static final String idu = "idu";
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_establecimiento);

        _urlGet = "http://hyperion.init-code.com/zungu/app/vt_preview_establecimiento.php?id=1";
        new Preview_establecimiento.RetrieveFeedTaskGet().execute();

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }
    private void showMsg(CharSequence text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    class RetrieveFeedTaskGet extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
        }

        protected String doInBackground(Void... urls) {
            try {
                Log.i("INFO url: ", _urlGet);
                URL url = new URL(_urlGet);
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


                TextView lblNombre = (TextView) findViewById(R.id.lblNombre);
                TextView lblDireccion = (TextView) findViewById(R.id.lblDireccion);




                try {

                    JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
                    //int ID = object.getInt("id_producto");
                    //CharSequence text;


                    //showMsg(response);
                    //showMsg(object.getString("descripcion"));


                    lblNombre.setText(object.getString("nombre"));
                    lblDireccion.setText(object.getString("direccion"));



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            Log.i("INFO", response);
        }
    }
}