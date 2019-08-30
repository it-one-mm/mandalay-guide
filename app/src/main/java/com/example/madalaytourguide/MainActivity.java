package com.example.madalaytourguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         lst=findViewById(R.id.places);
        final DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        ArrayList<PlaceModel> placeModels=databaseAccess.getAllPlaces() ;

        PlaceAdapter adapter=new PlaceAdapter(placeModels);
        lst.setAdapter(adapter);
        List<String> placelist=new ArrayList<String>();
        placelist.add("Pagoda");
        placelist.add("Restaurent");
        placelist.add("All");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,placelist);
        Spinner spinner=findViewById(R.id.spinner);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) {
                    PlaceAdapter pagoda = new PlaceAdapter(databaseAccess.getAllPagoda());
                    lst.setAdapter(pagoda);
                }
                if(position==1)
                {
                    ArrayList<PlaceModel> placeModels=databaseAccess.getAllRestaurent() ;

                    PlaceAdapter adapter=new PlaceAdapter(placeModels);
                    lst.setAdapter(adapter);
                }
                if(position==2)
                {
                    ArrayList<PlaceModel> placeModels=databaseAccess.getAllPlaces() ;

                    PlaceAdapter adapter=new PlaceAdapter(placeModels);
                    lst.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private  class PlaceAdapter extends BaseAdapter
    {
        ArrayList<PlaceModel> placeModels=new ArrayList<PlaceModel>();

        public PlaceAdapter(ArrayList<PlaceModel> placeModels) {
            this.placeModels = placeModels;
        }

        @Override
        public int getCount() {
            return placeModels.size();
        }

        @Override
        public Object getItem(int position) {
            return placeModels.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater=getLayoutInflater();
            View view=inflater.inflate(R.layout.placeitem,null);
            final PlaceModel placeModel=placeModels.get(position);
            ImageView imageView=view.findViewById(R.id.placeimg);



            Glide.with(getApplicationContext())
                    .load(placeModel.Img1)
                    .override(250,250)
                    .into(imageView);

            TextView placename=view.findViewById(R.id.placename);
            placename.setText(placeModel.Name);
            LinearLayout linearLayout=view.findViewById(R.id.placeitem);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PlaceDet.placeModel=placeModel;
                    Intent intent=new Intent(getApplicationContext(),PlaceDet.class);
                    startActivity(intent);
                }
            });
            return view;

        }
    }


}
