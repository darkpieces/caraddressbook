package com.gaox.android.tongxunlundemo;

import java.util.List;

/**
 * @author: gaox
 * @date: 2018/05/24 16:12
 */
public class CarGroupBean extends CarBaseBean{
    private String tittle;

    private List<CarChildBean> carChildList;

    public List<CarChildBean> getCarChildList() {
        return carChildList;
    }

    public void setCarChildList(List<CarChildBean> carChildList) {
        this.carChildList = carChildList;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    @Override
    public String toString() {
        return "CarGroupBean{" +
                "tittle='" + tittle + '\'' +
                ", carChildList=" + carChildList +
                '}';
    }
}
