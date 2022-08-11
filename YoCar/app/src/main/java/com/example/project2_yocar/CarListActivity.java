package com.example.project2_yocar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.project2_yocar.adapter.CarListAdapter;
import com.example.project2_yocar.bean.CarCategoryInfo;
import com.example.project2_yocar.bean.CarInfo;
import com.example.project2_yocar.database.DataProviderHelper;
import com.example.project2_yocar.databinding.ActivityCarListBinding;

import java.util.List;

public class CarListActivity extends AppCompatActivity {
    private ActivityCarListBinding binding;
    private CarListAdapter carListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_car_list);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   finish();
            }
        });

        initCategoryRV();
        initData();
    }

    //Initialize Data and set the title
    private void initData() {
        CarCategoryInfo carcategory = getIntent().getParcelableExtra("carcategory");
        if (carcategory != null) {
            binding.toolbar.setTitle(carcategory.getCarCategoryName());
            new Thread(() -> {
                List<CarInfo> byCarCategoryList = DataProviderHelper.getDB().carInfoDao().findByCarCategoryId(carcategory.getUid());
                runOnUiThread(() -> {
                    carListAdapter.setNewInstance(byCarCategoryList);
                });

            }).start();
        }

    }

    //Initialize categories, using recycler view
    private void initCategoryRV() {
        binding.rvSearchResult.setLayoutManager(new LinearLayoutManager(this));
        carListAdapter = new CarListAdapter();
        binding.rvSearchResult.setAdapter(carListAdapter);
        carListAdapter.setOnItemClickListener((adapter, view, position) -> {
                    Intent intent = new Intent(CarListActivity.this, CarDetailsActivity.class);
                    CarInfo carInfo = (CarInfo) adapter.getData().get(position);
                    intent.putExtra("carInfo", carInfo);
                    startActivity(intent);
                }
        );
    }
}
