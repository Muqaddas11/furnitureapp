package com.example.furnitureapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.furnitureapp.adapter.AllCategoryAdapter;
import com.example.furnitureapp.adapter.CategoryAdapter;
import com.example.furnitureapp.model.AllCategoryModel;
import com.example.furnitureapp.model.Category;

import java.util.ArrayList;
import java.util.List;

public class AllCategory extends AppCompatActivity {

    RecyclerView AllCategoryRecycler;
    AllCategoryAdapter allCategoryAdapter;
    List<AllCategoryModel> allCategoryModelList;
    ImageView back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_category);
        AllCategoryRecycler = findViewById(R.id.allCategory);
        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        allCategoryModelList = new ArrayList<>();
        allCategoryModelList .add(new AllCategoryModel(1, R.drawable.sofas));
        allCategoryModelList .add(new AllCategoryModel(2, R.drawable.cupboard));
        allCategoryModelList .add(new AllCategoryModel(3, R.drawable.tables));
        allCategoryModelList .add(new AllCategoryModel(4, R.drawable.coffeetables));
        allCategoryModelList .add(new AllCategoryModel(5, R.drawable.beds));
        allCategoryModelList .add(new AllCategoryModel(6, R.drawable.dinningtables));
        allCategoryModelList .add(new AllCategoryModel(7, R.drawable.dressingtables));
        allCategoryModelList .add(new AllCategoryModel(8, R.drawable.bedsidetables));
        allCategoryModelList .add(new AllCategoryModel(9, R.drawable.chairs));
        allCategoryModelList .add(new AllCategoryModel(10, R.drawable.lamps));


        setCategoryRecycler( allCategoryModelList );

    }
    private void setCategoryRecycler(List<AllCategoryModel> allCategoryModelList) {

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        AllCategoryRecycler.setLayoutManager(layoutManager);
       allCategoryAdapter = new AllCategoryAdapter(this, allCategoryModelList);
        AllCategoryRecycler.setAdapter(allCategoryAdapter);

    }

}