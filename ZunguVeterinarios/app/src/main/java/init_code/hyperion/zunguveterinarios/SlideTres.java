package init_code.hyperion.zunguveterinarios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class SlideTres extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_tres);

        RelativeLayout padre = (RelativeLayout) findViewById(R.id.padre);
        padre.setOnTouchListener(new OnSwipeTouchListener(SlideTres.this) {

            public void onSwipeLeft() {

                Intent i = new Intent(SlideTres.this, SlideCuatro.class);
                startActivity(i);

                finish();
            }

        });
    }

    public void goLogin(View v){
        Intent i = new Intent(SlideTres.this, Login.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed(){

    }
}
