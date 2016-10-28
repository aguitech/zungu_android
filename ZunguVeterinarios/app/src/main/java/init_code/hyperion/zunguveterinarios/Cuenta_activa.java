package init_code.hyperion.zunguveterinarios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Cuenta_activa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta_activa);
    }

    public void cancelar(View view) {
        finish();
    }

    public void guardar(View view) {
        finish();
    }

    public void goMenu(View v){
        Intent i = new Intent(Cuenta_activa.this, Menu.class);
        startActivity(i);
    }

    public void goBack(View v){
        finish();
    }
}
