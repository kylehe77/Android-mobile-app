package com.example.project2_yocar;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.project2_yocar.bean.CarInfo;
import com.example.project2_yocar.database.DataProviderHelper;
import com.example.project2_yocar.database.DataProvider;
import com.example.project2_yocar.databinding.ActivityCarDetailsBinding;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

public class CarDetailsActivity extends AppCompatActivity {
    private ActivityCarDetailsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_car_details);
        binding.toolbar.setNavigationOnClickListener(view -> finish());
        initData();

    }


    /**
     * set ui according to the selected cars
     */
    private void initData() {
        CarInfo carInfo = getIntent().getParcelableExtra("carInfo");
        if (carInfo != null) {
            Glide.with(this).load(carInfo.getCarUrl1()).into(binding.ivCarPhotoSmall);
            binding.tvCarName.setText(carInfo.getCarName());
            binding.tvCarPrice.setText("Price : $" + carInfo.getCarPrice());
            binding.tvCarDetail.setText(carInfo.getCarDetail());
            List<String> picList = new ArrayList<>();
            picList.add(carInfo.getCarUrl1());
            picList.add(carInfo.getCarUrl2());
            picList.add(carInfo.getCarUrl3());
            // set imageadapter
            binding.ivBanner.setAdapter(new BannerImageAdapter<String>(picList) {
                        @Override
                        public void onBindView(BannerImageHolder holder, String data, int position, int size) {
                            // load images
                            Glide.with(holder.itemView)
                                    .load(data)
                                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                                    .into(holder.imageView);
                        }
                    })
                    .addBannerLifecycleObserver(this)
                    .setIndicator(new CircleIndicator(this));
            updateDatabase(carInfo);
        }
    }

        /**
         * update database(by click times)
         */
        private void updateDatabase(CarInfo carInfo) {
            carInfo.setClickNum(carInfo.getClickNum()+1);
            new Thread(() ->
                    DataProviderHelper.getDB().carInfoDao().update(carInfo)
            ).start();
        }
    }


