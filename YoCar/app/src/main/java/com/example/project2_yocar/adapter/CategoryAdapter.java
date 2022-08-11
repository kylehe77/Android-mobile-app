package com.example.project2_yocar.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.project2_yocar.R;
import com.example.project2_yocar.bean.CarCategoryInfo;

public class CategoryAdapter extends BaseQuickAdapter<CarCategoryInfo, BaseViewHolder> {

    public CategoryAdapter() {
        super(R.layout.item_car_catergory_layout);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, CarCategoryInfo carCategoryInfo) {
        baseViewHolder.setText(R.id.tv_category_name,carCategoryInfo.getCarCategoryName());
        ImageView iv_car_photo = baseViewHolder.findView(R.id.iv_car_photo);
        Glide.with(getContext())
                .load(carCategoryInfo.getCarUrl())
                .into(iv_car_photo);

    }
}
