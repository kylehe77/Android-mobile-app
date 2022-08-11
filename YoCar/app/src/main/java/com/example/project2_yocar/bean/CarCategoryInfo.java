package com.example.project2_yocar.bean;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


//Information of car categories
@Entity
public class CarCategoryInfo implements Parcelable {
    //id of category
    @NonNull
    @PrimaryKey
    public String uid;

    //name of category
    @ColumnInfo(name = "carCategoryName")
    public String carCategoryName;

    //url of car photo
    @ColumnInfo(name = "carUrl")
    public String carUrl;

    public CarCategoryInfo(){

    }

    protected CarCategoryInfo(Parcel in) {
        carCategoryName = in.readString();
        carUrl = in.readString();
        uid = in.readString();
    }

    public static final Creator<CarCategoryInfo> CREATOR = new Creator<CarCategoryInfo>() {
        @Override
        public CarCategoryInfo createFromParcel(Parcel in) {
            return new CarCategoryInfo(in);
        }

        @Override
        public CarCategoryInfo[] newArray(int size) {
            return new CarCategoryInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(carCategoryName);
        dest.writeString(carUrl);
        dest.writeString(uid);
    }

    public void setCarUrl(String carUrl) {
        this.carUrl = carUrl;
    }

    public String getCarUrl() {
        return carUrl;
    }

    public String getCarCategoryName() {
        return carCategoryName;
    }

    public void setCarCategoryName(String carCategoryName) {
        this.carCategoryName = carCategoryName;
    }

    @NonNull
    public String getUid() {
        return uid;
    }

    public void setUid(@NonNull String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "CarCategoryInfo{" +
                "uid='" + uid + '\'' +
                ", carCategoryName='" + carCategoryName + '\'' +
                ", carUrl='" + carUrl + '\'' +
                '}';
    }//return json string
}
