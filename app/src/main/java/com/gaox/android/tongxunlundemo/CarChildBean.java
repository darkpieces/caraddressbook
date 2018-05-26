package com.gaox.android.tongxunlundemo;

/**
 * @author: gaox
 * @date: 2018/05/24 16:14
 */
public class CarChildBean extends CarBaseBean{
    private String carImage;
    private String carName;
    private String carPrice;
    private String carDetail;

    public String getCarImage() {
        return carImage;
    }

    public void setCarImage(String carImage) {
        this.carImage = carImage;
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

    @Override
    public String toString() {
        return "CarChildBean{" +
                "carImage='" + carImage + '\'' +
                ", carName='" + carName + '\'' +
                ", carPrice='" + carPrice + '\'' +
                ", carDetail='" + carDetail + '\'' +
                '}';
    }
}
