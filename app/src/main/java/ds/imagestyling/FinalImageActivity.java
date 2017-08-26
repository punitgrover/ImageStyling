package ds.imagestyling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import ds.imagestyling.utils.App;

public class FinalImageActivity extends AppCompatActivity {
ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_image);
        img=(ImageView)findViewById(R.id.img);
        img.setImageBitmap(App.getApp().getImageBit());
    }
}
