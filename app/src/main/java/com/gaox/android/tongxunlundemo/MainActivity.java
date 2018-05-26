package com.gaox.android.tongxunlundemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.promeg.pinyinhelper.Pinyin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private CarBrandAdapter mAdapter;

    private List<String> titlList = Arrays.asList(
            "乔治巴顿", "日产", "马自达", "迈凯伦",
            "玛莎拉蒂", "铃木", "拉大", "劳斯莱斯", "雪佛兰",
            "奥迪", "宝马", "大众", "长安", "东风", "本田");
//    private List<String> titleList2 = new ArrayList<>();
//    private final static Comparator<Object> CHINA_COMPARE = Collator.getInstance(java.util.Locale.CHINA);
    List<CarBaseBean> list = new ArrayList<>();
    private RelativeLayout tittle_top;
    private TextView tv_main_tittle;

    private int mSuspensionHeight;
    private int mCurrentPosition = 0;
    private List<CarGroupBean> grouplist;
    //    private int distance = 0;

    private TextView mTvSideBarHint;
    private IndexBar mIndexBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tittle_top = findViewById(R.id.tittle_top);
        tv_main_tittle = findViewById(R.id.tv_main_tittle);

        mTvSideBarHint = findViewById(R.id.tvSideBarHint);

        mIndexBar = findViewById(R.id.indexBar);


        mRecyclerView = findViewById(R.id.rl_tongxunlu);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);


        mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(layoutManager);//设置RecyclerView


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mSuspensionHeight = tittle_top.getHeight();
                //                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                //                    int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                //                    Log.i("====newState","idle");
                //                    if (firstVisibleItemPosition == 0) {
                //                        tittle_top.setVisibility(View.GONE);
                //                    } else {
                //                        tv_main_tittle.setText(list.get(firstVisibleItemPosition).getIndexTittle());
                //                        tittle_top.setVisibility(View.VISIBLE);
                //                    }
                //                }else if(newState ==RecyclerView.SCROLL_STATE_DRAGGING){
                //                    Log.i("====newState","dargging");
                //                }else if(newState ==RecyclerView.SCROLL_STATE_SETTLING){
                //                    Log.i("====newState","settLing");
                //                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //                distance += dy;
                Log.i("====newState", "onScrolled");
                if (mAdapter.getItemViewType(mCurrentPosition + 1) == mAdapter.TYPE_GROUP) {
                    View view = layoutManager.findViewByPosition(mCurrentPosition + 1);
                    if (view != null) {
                        if (view.getTop() <= mSuspensionHeight) {
                            tittle_top.setY(-(mSuspensionHeight - view.getTop()));
                        } else {
                            tittle_top.setY(0);
                        }
                    }
                }

                if (mCurrentPosition != layoutManager.findFirstVisibleItemPosition()) {
                    mCurrentPosition = layoutManager.findFirstVisibleItemPosition();
                    tittle_top.setY(0);
                    updateSuspensionBar(dy);
                }
            }
        });

//        tv_main_tittle.setText("A 奥迪");
        initDatas();
    }

    private void initDatas() {

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                //1.真正的数据 遍历集合 给每一个条数据加一个type
                //2.遍历集合取出type为1的每一个tittle 并且加上前缀字母 字母set进baseTag
                //3.根据集合中的元素tittle 排序
                //4.传进adapter



                //标题排序
                //                for (int i = 0; i < titlList.size(); i++) {
                //                    if ("长".equals(String.valueOf(titlList.get(i).charAt(0)))) {
                //                        titleList2.add("C " + titlList.get(i));
                //                        continue;
                //                    }
                //                    String s = Pinyin.toPinyin(titlList.get(i).charAt(0));
                //                    String substring = s.substring(0, 1);
                //                    titleList2.add(substring + " " + titlList.get(i));
                //                }
                //                Collections.sort(titleList2, CHINA_COMPARE);
                list = getParseDatas(titlList);

                Collections.sort(list, new Comparator<CarBaseBean>() {
                    @Override
                    public int compare(CarBaseBean lhs, CarBaseBean rhs) {

                            return lhs.getIndexTittle().compareTo(rhs.getIndexTittle());

                    }
                });


//                for (int j = 0; j < list.size(); j++) {
//                    if (list.get(j).getItemType() == 1) {
//                        CarGroupBean carGroupBean = (CarGroupBean) list.get(j);
//                        String indexTag = carGroupBean.getTittle().substring(0, 1);
//                        list.get(j).setIndexTag(indexTag);
//                    }
//                }

                mAdapter = new CarBrandAdapter(MainActivity.this, list);
                mRecyclerView.setAdapter(mAdapter);

                mIndexBar.setmSourceDatas(list)//设置数据
                        .invalidate();

            }
        }, 200);

    }

    //    private int groupIndex = 0;

    private void updateSuspensionBar(int dy) {
        Log.d("HHHH", "updateSuspensionBar: " + mCurrentPosition);
        //        if (distance < 100) {
        //            tv_main_tittle.setText(grouplist.get(0).getTittle());
        //            return;
        //        }

        if (mCurrentPosition == 0) {
            tittle_top.setVisibility(View.GONE);
            return;
        }


        ////        int lastIndex = -1;
        //        if (list.get(mCurrentPosition - 1).getItemType() == mAdapter.TYPE_GROUP) {
        ////            groupIndex = mCurrentPosition - 1;
        //            if (dy >= 0) {
        //                if (mCurrentPosition == 1) {
        //                    tittle_top.setVisibility(View.VISIBLE);
        //                }
        //                CarGroupBean carBaseBean = (CarGroupBean) list.get(mCurrentPosition - 1);
        //
        //                tv_main_tittle.setText(carBaseBean.getTittle());
        //            }
        //
        //        } else if (dy <= 0 && list.get(mCurrentPosition - 1).getItemType() == mAdapter.TYPE_CHILD) {
        //
        ////            CarGroupBean carBaseBean = (CarGroupBean) list.get(groupIndex);
        ////            for (int i = 0; i < titleList2.size(); i++) {
        ////                if (titleList2.get(i).equals(carBaseBean.getTittle())) {
        ////                    lastIndex = i;
        ////                }
        ////            }
        ////            if (lastIndex == 0) {
        ////                tv_main_tittle.setText(titleList2.get(0));
        ////            } else {
        ////                tv_main_tittle.setText(titleList2.get(lastIndex - 1));
        ////            }
        //            tv_main_tittle.setText(list.get(mCurrentPosition-1).getIndexTittle());
        //        }
        if (mCurrentPosition >= 1) {
            tittle_top.setVisibility(View.VISIBLE);
        }
        tv_main_tittle.setText(list.get(mCurrentPosition - 1).getIndexTittle());
    }

    private List<CarGroupBean> getGroupDatas(List<String> titlList) {
        grouplist = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            List<CarChildBean> childList = new ArrayList<>();
            CarGroupBean bean = new CarGroupBean();
            bean.setTittle(titlList.get(i));
            //            bean.setItemType(1);
            for (int j = 0; j < 2; j++) {
                CarChildBean bean1 = new CarChildBean();
                //                bean1.setItemType(2);
                bean1.setCarName(titlList.get(i));
                childList.add(bean1);
            }
            bean.setCarChildList(childList);
            grouplist.add(bean);
        }
        return grouplist;
    }


    public List<CarBaseBean> getParseDatas(List<String> titlList) {
        List<CarBaseBean> list = new ArrayList<>();
        list.clear();
        String indexTittle;
        for (CarGroupBean bean : getGroupDatas(titlList)) {
            String tittle = Pinyin.toPinyin(bean.getTittle().charAt(0));
            String subTittle = tittle.substring(0, 1).toUpperCase();
            if ("长".equals(String.valueOf(bean.getTittle().charAt(0)))) {
                indexTittle="C "+bean.getTittle();
            }else {
                indexTittle = subTittle + " " + bean.getTittle();
            }

            bean.setIndexTittle(indexTittle);
            //            bean.setIndexTittle(bean.getTittle());
            bean.setIndexTag(subTittle);
            bean.setItemType(1);
            list.add(bean);//group

            for (CarChildBean bean1 : bean.getCarChildList()) {
                bean1.setIndexTittle(indexTittle);
//                bean1.setIndexTittle(bean.getTittle());
                bean1.setItemType(2);
                list.add(bean1);//child
            }
        }
        //        for (int i=0;i<list.size();i++){
        //            Log.i("=======list",list.get(i).getIndexTittle());
        //        }
        return list;
    }


}
