package com.example.project2_yocar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.project2_yocar.adapter.CategoryAdapter;
import com.example.project2_yocar.adapter.MostlyViewedAdapter;
import com.example.project2_yocar.bean.CarCategoryInfo;
import com.example.project2_yocar.bean.CarInfo;
import com.example.project2_yocar.database.DataProvider;
import com.example.project2_yocar.database.DataProviderHelper;
import com.example.project2_yocar.databinding.ActivityMainBinding;
import com.example.project2_yocar.utils.AssetsUtil;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MostlyViewedAdapter mostlyViewAdapter;
    private CategoryAdapter categoryAdapter;
    private List<CarInfo> carInfos;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Inflate view and obtain an instance of the binding class.
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        //Callbacks for changes to the query text.
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //
                if(TextUtils.isEmpty(query)){
                    Toast toast = Toast.makeText(MainActivity.this, "Search...", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                    return true;
                }
                //when item has been searched, search activity would be activated.
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                intent.putExtra("query",query);
                startActivity(intent);
                return false;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        initMostlyViewRV();//loading data of mostly view
        initCategoryRV();//loading data of car category
        initData();
    }

    //this method could read 'CarInfo' and 'CarCategoryInfo' files.
    //Show data on UI and store infos into database
    private void initData(){
       carInfos = AssetsUtil.getCarInfoList(this);
       List<CarCategoryInfo> states = AssetsUtil.getCarCategoryList(this);
       categoryAdapter.setNewInstance(states);

       //store infos into database
        new Thread(() -> {
            DataProviderHelper.getDB().carCategoryInfoDao().insertAll(states);
            DataProviderHelper.getDB().carInfoDao().insertAll(carInfos);
        }).start();
    }





    //Initialize MostlyView and use recycler view to show items.
    private void initMostlyViewRV(){
         binding.rvMostlyView.setLayoutManager(new GridLayoutManager(this,3));
         mostlyViewAdapter = new MostlyViewedAdapter();
         binding.rvMostlyView.setAdapter(mostlyViewAdapter);
         mostlyViewAdapter.setOnItemClickListener(new OnItemClickListener() {
             @Override
             //when click the item on the mostly viewed, the details of that item should pop up.
             public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                 CarInfo carInfo = (CarInfo) adapter.getData().get(position);
                 Intent intent = new Intent(MainActivity.this, CarDetailsActivity.class);
                 //Append the value to be passed to the Intent object
                 intent.putExtra("carInfo",carInfo);
                 startActivity(intent);
             }
         });



    }

    
   //Initialize car category by using recycler view to show all the category.
    private void initCategoryRV(){
        binding.rvCategoryView.setLayoutManager(new LinearLayoutManager(this));
        categoryAdapter = new CategoryAdapter();
        binding.rvCategoryView.setAdapter(categoryAdapter);
        categoryAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            //when click the item on the category, the list of that category should pop up.
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                CarCategoryInfo carCategoryInfo = (CarCategoryInfo) adapter.getData().get(position);
                Intent intent = new Intent(MainActivity.this, CarListActivity.class);
                //Append the value to be passed to the Intent object
                intent.putExtra("carcategory",carCategoryInfo);
                startActivity(intent);
            }
        });



    }

    //update mostly view after each operation on the app
    @Override
    protected void onResume() {
        super.onResume();
        //store infos into database
        new Thread(() -> {
            List<CarInfo> dataBaseCarInfos = DataProviderHelper.getDB().carInfoDao().loadAll();
            if(dataBaseCarInfos == null || dataBaseCarInfos.size()<=0){
                runOnUiThread(() -> { mostlyViewAdapter.setNewInstance(carInfos.subList(0,3)); });
            }else{
                // use the self-defined rules of comparable for sorting
                Collections.sort(dataBaseCarInfos);
                runOnUiThread(() -> { mostlyViewAdapter.setNewInstance(dataBaseCarInfos.subList(0,3)); });
            }

        }).start();

    }
   
}