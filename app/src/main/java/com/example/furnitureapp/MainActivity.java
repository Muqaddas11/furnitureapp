package com.example.furnitureapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.furnitureapp.adapter.AllCategoryAdapter;
import com.example.furnitureapp.adapter.CategoryAdapter;
import com.example.furnitureapp.adapter.DiscountedProductAdapter;
import com.example.furnitureapp.adapter.RecentlyViewedAdapter;
import com.example.furnitureapp.model.Category;
import com.example.furnitureapp.model.DiscountedProducts;
import com.example.furnitureapp.model.RecentlyViewed;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    
ImageView setting;

    RecyclerView discountRecyclerView, categoryRecyclerView,recentlyViewedRecycler;
    DiscountedProductAdapter discountedProductAdapter;
    List<DiscountedProducts> discountedProductsList;

    CategoryAdapter categoryAdapter;
    List<Category> categoryList;

    RecentlyViewedAdapter recentlyViewedAdapter;
    List<RecentlyViewed> recentlyViewedList;

    ImageView allCategory;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        discountRecyclerView = findViewById(R.id.discountedRecycler);
        categoryRecyclerView = findViewById(R.id.categoryRecycler);
        allCategory=findViewById(R.id.allCategoryImage);
        recentlyViewedRecycler = findViewById(R.id.recently_item);
        setting=findViewById(R.id.setting);

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,settingActivity.class);
                startActivity(i);
            }
        });

        allCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,AllCategory.class);
                startActivity(i);
            }
        });



        discountedProductsList = new ArrayList<>();
        discountedProductsList.add(new DiscountedProducts(1, R.drawable.discountedmirror));
        discountedProductsList.add(new DiscountedProducts(2, R.drawable.discountedsofa));
        discountedProductsList.add(new DiscountedProducts(3, R.drawable.discountedlamp));
        discountedProductsList.add(new DiscountedProducts(4, R.drawable.discountedbed));
        discountedProductsList.add(new DiscountedProducts(5, R.drawable.discountedsidetable));
        discountedProductsList.add(new DiscountedProducts(6, R.drawable.discountedchair));

        categoryList = new ArrayList<>();
        categoryList.add(new Category(1, R.drawable.sofas));
        categoryList.add(new Category(2, R.drawable.cupboard));
        categoryList.add(new Category(3, R.drawable.tables));
        categoryList.add(new Category(4, R.drawable.coffeetables));
        categoryList.add(new Category(5, R.drawable.beds));
        categoryList.add(new Category(6, R.drawable.dinningtables));
        categoryList.add(new Category(7, R.drawable.dressingtables));
        categoryList.add(new Category(8, R.drawable.bedsidetables));
        categoryList.add(new Category(9, R.drawable.chairs));
        categoryList.add(new Category(10, R.drawable.lamps));

        recentlyViewedList = new ArrayList<>();
        recentlyViewedList.add(new RecentlyViewed("Bed",
                "Bed is a high quality and size of bed is large and wood of bed is high quality",
                "Price:10,000",R.drawable.recentbed,R.drawable.recentbed));
        recentlyViewedList.add(new RecentlyViewed("Sofa",
                "Sofa is a high quality and it is five seattar sofa and wood of wood is high quality",
                "Price:8,000",R.drawable.sofa,R.drawable.sofa));
        recentlyViewedList.add(new RecentlyViewed("Chairs",
                "Chair is a high quality and it is two chair set and quality of chair is best",
                "Price:5,000",R.drawable.recentchair,R.drawable.recentchair));
        recentlyViewedList.add(new RecentlyViewed("DinningTable",
                "Dinning Table is a high quality and it is a set of four chairs and one table and quality of table is best",
                "Price:6,000",R.drawable.recentdinningtable,R.drawable.recentdinningtable));


        setDiscountedRecycler(discountedProductsList);
setCategoryRecycler(categoryList);
setRecentlyViewedRecycler(recentlyViewedList);
    }




    private void setDiscountedRecycler(List<DiscountedProducts> dataList) {

        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        discountRecyclerView.setLayoutManager(layoutManager);
        discountedProductAdapter = new DiscountedProductAdapter(this, dataList);
        discountRecyclerView.setAdapter(discountedProductAdapter);



    }
    private void setCategoryRecycler(List<Category> categoryDataList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(this, categoryDataList);
        categoryRecyclerView.setAdapter(categoryAdapter);

    }

    private void setRecentlyViewedRecycler(List<RecentlyViewed> recentlyViewedDataList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recentlyViewedRecycler.setLayoutManager(layoutManager);
        recentlyViewedAdapter = new RecentlyViewedAdapter(this, recentlyViewedDataList);
        recentlyViewedRecycler.setAdapter(recentlyViewedAdapter);

    }
}