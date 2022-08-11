package com.example.project2_yocar.adapter;

import android.widget.ImageView;

import androidx.lifecycle.GenericLifecycleObserver;

import com.bumptech.glide.Glide;
import com.example.project2_yocar.CarListActivity;
import com.example.project2_yocar.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.project2_yocar.bean.CarInfo;

public class CarListAdapter extends BaseQuickAdapter<CarInfo,BaseViewHolder>{
    public CarListAdapter(){
        super(R.layout.item_car_list_layout);
    }
    @Override
    protected  void convert(BaseViewHolder baseViewHolder,CarInfo carInfo){
        baseViewHolder.setText(R.id.tv_car_name,carInfo.getCarName());
        ImageView iv_car_photo = baseViewHolder.findView(R.id.iv_car_photo);
        Glide.with(getContext())
                .load(carInfo.getCarUrl1())
                .into(iv_car_photo);
    }

}
