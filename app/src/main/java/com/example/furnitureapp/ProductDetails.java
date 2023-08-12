package com.example.furnitureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductDetails extends AppCompatActivity {
    ImageView img,backBtn;
    TextView proName,proPrice,proDesc;
    String name,price,desc;
    int image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent i = getIntent();
        name = i.getStringExtra("name");
        price= i.getStringExtra("price");
        desc = i.getStringExtra("desc");
        image = i.getIntExtra("image",R.drawable.recentsofa);
        proName =findViewById(R.id.productName);
        proPrice =findViewById(R.id.prodPrice);
        img =findViewById(R.id.big_image);
        proDesc = findViewById(R.id.prodDesc);
        backBtn=findViewById(R.id.backBtn);

        proName.setText(name);
        proPrice.setText(price);
        proDesc.setText(desc);

        img.setImageResource(image);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}