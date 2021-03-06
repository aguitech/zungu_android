package init_code.hyperion.zunguveterinarios;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Estadisticas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);
    }

    public void enviar(View v){
        showMsg("No hay historial por enviar.");
    }

    private void showMsg(CharSequence text){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void goMenu(View v){
        Intent i = new Intent(Estadisticas.this, Menu.class);
        startActivity(i);
    }

    public void goIngresos(View v){
        Intent i = new Intent(Estadisticas.this, Ingresos_egresos.class);
        startActivity(i);
    }

    public void goBack(View v){
        finish();
    }
}
