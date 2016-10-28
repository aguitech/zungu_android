package init_code.hyperion.zunguveterinarios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void goMenu(View v){
        Intent i = new Intent(Home.this, Menu.class);
        startActivity(i);
    }

    public void goPerfil(View v){
        Intent i = new Intent(Home.this, Cuenta.class);
        startActivity(i);
    }

    public void goCitas(View v){
        Intent i = new Intent(Home.this, Citas.class);
        startActivity(i);
    }
}
