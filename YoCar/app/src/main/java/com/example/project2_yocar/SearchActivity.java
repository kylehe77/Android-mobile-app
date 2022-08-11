package com.example.project2_yocar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.project2_yocar.adapter.CarListAdapter;
import com.example.project2_yocar.bean.CarInfo;
import com.example.project2_yocar.database.DataProviderHelper;
import com.example.project2_yocar.databinding.ActivitySearchBinding;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private String query;
    private ActivitySearchBinding binding;
    private CarListAdapter carListAdapter;
    private static final String TAG = "SearchActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        initSearchRV();

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initData();
    }

    /**
     * search car information from data provider by typing key words
     */
    private void initData() {
        query = getIntent().getStringExtra("query");
        //set title
        binding.tvResultMsg.setText("Search results for  \"" + query + "\"");
        new Thread(() -> {
            //search from dataprovider
            List<CarInfo> carInfos = DataProviderHelper.getDB().carInfoDao().searchByName(query);
            runOnUiThread(() -> {
                //adapter set info
                carListAdapter.setNewInstance(carInfos);
            });
        }).start();


    }

    /**
     * initialise carinfo recyclerview
     */
    private void initSearchRV() {
        binding.rvSearchResult.setLayoutManager(new LinearLayoutManager(this));
        carListAdapter = new CarListAdapter();
        binding.rvSearchResult.setAdapter(carListAdapter);
        carListAdapter.setOnItemClickListener((adapter, view, position) -> {
                    Intent intent = new Intent(SearchActivity.this, CarDetailsActivity.class);
                    CarInfo carInfo = (CarInfo) adapter.getData().get(position);
                    intent.putExtra("carInfo", carInfo);
                    startActivity(intent);
                }
        );
    }

}
