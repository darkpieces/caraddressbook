package com.gaox.android.tongxunlundemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: gaox
 * @date: 2018/05/26 12:08
 */
public class PaixuActivity extends AppCompatActivity {


    private List<String> titlList = Arrays.asList(
            "乔治巴顿", "日产", "马自达", "迈凯伦",
            "玛莎拉蒂", "铃木", "拉大", "劳斯莱斯", "雪佛兰",
            "奥迪", "宝马", "大众", "长安", "东风", "本田");

    private List<CarGroupBean2> carlist = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_paixu);
        for (int i = 0; i < titlList.size(); i++) {
            CarGroupBean2 carGroupBean = new CarGroupBean2();
            carGroupBean.setTittle(titlList.get(i));
            carlist.add(carGroupBean);
        }

        Log.i("========carList", carlist.toString());

    }
}
