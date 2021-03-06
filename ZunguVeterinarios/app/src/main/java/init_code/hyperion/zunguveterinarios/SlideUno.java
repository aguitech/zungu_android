package init_code.hyperion.zunguveterinarios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SlideUno extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_uno);

        RelativeLayout padre = (RelativeLayout) findViewById(R.id.padre);
        padre.setOnTouchListener(new OnSwipeTouchListener(SlideUno.this) {

            public void onSwipeLeft() {

                Intent i = new Intent(SlideUno.this, SlideDos.class);
                startActivity(i);

                finish();
            }

        });
    }

    public void goLogin(View v){
        Intent i = new Intent(SlideUno.this, Login.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed(){

    }
}
