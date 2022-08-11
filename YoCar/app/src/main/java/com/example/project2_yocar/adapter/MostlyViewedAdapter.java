package com.example.project2_yocar.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.project2_yocar.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.project2_yocar.bean.CarInfo;


public class MostlyViewedAdapter extends BaseQuickAdapter<CarInfo, BaseViewHolder>  {
    public MostlyViewedAdapter(){
        super(R.layout.item_mostly_view_layout);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, CarInfo carInfo) {
         baseViewHolder.setText(R.id.car_name,carInfo.getCarName());
         ImageView iv_car_photo = baseViewHolder.findView(R.id.iv_car_photo);
         Glide.with(getContext())
                .load(carInfo.getCarUrl1())
                .into(iv_car_photo);//access the url to get car photo and insert it
                                     //into mostly view.
    }
}
