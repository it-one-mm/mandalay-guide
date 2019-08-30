package com.example.madalaytourguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.bumptech.glide.Glide;

public class PlaceDet extends AppCompatActivity {


    String[] imgurls;
    ViewFlipper viewFlipper;
    ImageView imageView;
    public static PlaceModel placeModel=new PlaceModel();
    ReadMoreTextView readMoreTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_det);
        //links

        imgurls=new String[]{placeModel.Img1,placeModel.Img2,placeModel.Img3};
        //links


        readMoreTextView=findViewById(R.id.text_view);

        viewFlipper=findViewById(R.id.viewfilip);
        readMoreTextView.setText(placeModel.Desc);



        for(int i=0;i<imgurls.length;i++)
        {
            setFlipperImage(imgurls[i]);
        }
        viewFlipper.startFlipping();
    }
    private void setFlipperImage(String url) {
        Log.i("Set Filpper Called", url+"");

        ImageView imageView=new ImageView(getApplicationContext());
        Glide.with(getApplicationContext())
                .load(url)
                .override(250,250)
                .into(imageView);
        viewFlipper.addView(imageView);
    }
}
