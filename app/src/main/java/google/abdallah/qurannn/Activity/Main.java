package google.abdallah.qurannn.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import google.abdallah.qurannn.Compass.CompassActivity;
import google.abdallah.qurannn.Constant;
import google.abdallah.qurannn.R;

public class Main extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

    }
    public  void GoCoran(View view){
        int i=view.getId();
        switch (i){
            case R.id.imageView4:
                new intentOne().start();
                break;
            case R.id.imageView5:
                Toast.makeText(this,"tasbih",Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageView8:
                Intent intent = new Intent(Main.this, CompassActivity.class);
                //  intent.putExtra(Constant.TOOLBAR_NAV_ICON, "ic_arrow_back_custom");
                intent.putExtra(Constant.COMPASS_BG_COLOR, "#FFFFFF");
                intent.putExtra(Constant.TOOLBAR_TITLE, "Qibla");
                intent.putExtra(Constant.TOOLBAR_BG_COLOR, "#4CAF50");
                intent.putExtra(Constant.TOOLBAR_TITLE_COLOR, "#FFFFFF");
               // intent.putExtra(Constant.ANGLE_TEXT_COLOR, "#4CAF50");
                // intent.putExtra(Constant.DRAWABLE_DIAL, R.drawable.dial);
                // intent.putExtra(Constant.DRAWABLE_QIBLA, R.drawable.ic_arrow_back_custom);
                // intent.putExtra(Constant.FOOTER_IMAGE_VISIBLE, View.GONE);
                intent.putExtra(Constant.LOCATION_TEXT_VISIBLE, View.VISIBLE);
                startActivity(intent);
                break;
        }

    }
    class intentOne extends Thread{
        @Override
        public void run() {
            super.run();
            Intent intent=new Intent(Main.this, MainActivity.class);
            startActivity(intent);

        }
    }

}
