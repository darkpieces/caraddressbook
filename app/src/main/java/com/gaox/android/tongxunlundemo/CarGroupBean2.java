package com.gaox.android.tongxunlundemo;

/**
 * @author: gaox
 * @date: 2018/05/24 16:12
 */
public class CarGroupBean2 extends CarBaseBean{
    private String tittle;

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    @Override
    public String toString() {
        return "CarGroupBean2{" +
                "tittle='" + tittle + '\'' +
                '}';
    }
}
