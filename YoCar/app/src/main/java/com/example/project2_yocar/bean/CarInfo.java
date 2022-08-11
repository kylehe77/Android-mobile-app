package com.example.project2_yocar.bean;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Information of all cars
//Parcelable is going to convert object to byte stream and pass the data between two activities.
@Entity
public class CarInfo implements Parcelable ,Comparable<CarInfo> {


    //CarID
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "carId")
    public String carId;
    //carCategoryId
    @ColumnInfo(name = "carCategoryId")
    public String carCategoryId;
    //CarCategoryname
    @ColumnInfo(name = "carCategoryName")
    public String carCategoryName;
    //carname
    @ColumnInfo(name = "carName")
    public String carName;
    //carprice
    @ColumnInfo(name = "carPrice")
    public String carPrice;
    //car description
    @ColumnInfo(name = "carDetail")
    public String carDetail;
    //car photo 1
    @ColumnInfo(name = "carUrl1")
    public String carUrl1;
    //car photo 2
    @ColumnInfo(name = "carUrl2")
    public String carUrl2;
    //car photo 3
    @ColumnInfo(name = "carUrl3")
    public String carUrl3;
    //click times
    @ColumnInfo(name = "clickNum")
    public int clickNum;
    public CarInfo() {
    }


    protected CarInfo(Parcel in) {

        carId = in.readString();
        carCategoryId = in.readString();
        carCategoryName = in.readString();
        carName = in.readString();
        carPrice = in.readString();
        carDetail = in.readString();
        carUrl1 = in.readString();
        carUrl2 = in.readString();
        carUrl3 = in.readString();
        clickNum = in.readInt();
    }

    public static final Creator<CarInfo> CREATOR = new Creator<CarInfo>() {
        @Override
        public CarInfo createFromParcel(Parcel in) {
            return new CarInfo(in);
        }

        @Override
        public CarInfo[] newArray(int size) {
            return new CarInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(carId);
        dest.writeString(carCategoryId);
        dest.writeString(carCategoryName);
        dest.writeString(carName);
        dest.writeString(carPrice);
        dest.writeString(carDetail);
        dest.writeString(carUrl1);
        dest.writeString(carUrl2);
        dest.writeString(carUrl3);
        dest.writeInt(clickNum);

    }



    @NonNull
    public String getCarId() {
        return carId;
    }

    public void setCarId(@NonNull String carId) {
        this.carId = carId;
    }

    public String getCarCategoryId() {
        return carCategoryId;
    }

    public void setCarCategoryId(String carCategoryId) {
        this.carCategoryId = carCategoryId;
    }

    public String getCarCategoryName() {
        return carCategoryName;
    }

    public void setCarCategoryName(String carCategoryName) {
        this.carCategoryName = carCategoryName;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(String carPrice) {
        this.carPrice = carPrice;
    }

    public String getCarDetail() {
        return carDetail;
    }

    public void setCarDetail(String carDetail) {
        this.carDetail = carDetail;
    }

    public String getCarUrl1() {
        return carUrl1;
    }

    public void setCarUrl1(String carUrl1) {
        this.carUrl1 = carUrl1;
    }

    public String getCarUrl2() {
        return carUrl2;
    }

    public void setCarUrl2(String carUrl2) {
        this.carUrl2 = carUrl2;
    }

    public String getCarUrl3() {
        return carUrl3;
    }

    public void setCarUrl3(String carUrl3) {
        this.carUrl3 = carUrl3;
    }

    public int getClickNum() {
        return clickNum;
    }

    public void setClickNum(int clickNum) {
        this.clickNum = clickNum;
    }

    @Override
    public String toString() {
        return "CarInfo{" +
                "carId='" + carId + '\'' +
                ", carCategoryId='" + carCategoryId + '\'' +
                ", carCategoryName='" + carCategoryName + '\'' +
                ", carName='" + carName + '\'' +
                ", carPrice='" + carPrice + '\'' +
                ", carDetail='" + carDetail + '\'' +
                ", carUrl1='" + carUrl1 + '\'' +
                ", carUrl2='" + carUrl2 + '\'' +
                ", carUrl3='" + carUrl3 + '\'' +
                ", clickNum=" + clickNum +
                '}';
    }

    //For sorting, use compareTo to sort according to the numbers of user click the item
    @Override
    public int compareTo(CarInfo carInfo) {
        return carInfo.getClickNum() - this.getClickNum();
    }
}




