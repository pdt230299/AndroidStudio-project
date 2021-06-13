package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailPlanet extends AppCompatActivity {

    private TextView txtName,txtDes;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_planet);

       txtName = findViewById(R.id.detail_name_planet);
        txtDes = findViewById(R.id.detail_des_planet);
        imageView = findViewById(R.id.detail_img_planet);
        Planet planet = (Planet) getIntent().getSerializableExtra("planet");
        txtName.setText(planet.getName());
        txtDes.setText(planet.getMota());
        String url = planet.getUrl();
        Glide.with(DetailPlanet.this).load(url).into(imageView);

    }
}