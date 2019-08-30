package com.example.madalaytourguide;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         webView=findViewById(R.id.showdir);
        CheckUserPermsions();;

    }

    void CheckUserPermsions(){
        if ( Build.VERSION.SDK_INT >= 23){
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED  ){
                requestPermissions(new String[]{
                                android.Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return ;
            }
        }

        showDir();// init the contact list

    }

    private void showDir() {
        LocationManager locationManager=(LocationManager)getSystemService(LOCATION_SERVICE);
        Location location=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double lon=location.getLongitude();
        double lat=location.getLatitude();

    }

    //get acces to location permsion
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                   showDir();// init the contact list
                } else {
                    // Permission Denied
                    Toast.makeText(this, "Denail The Permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
