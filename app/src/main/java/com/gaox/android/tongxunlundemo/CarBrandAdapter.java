package com.gaox.android.tongxunlundemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * @author: gaox
 * @date: 2018/05/22 15:34
 */
public class CarBrandAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<CarBaseBean> list;
    private Context mContext;
    private LayoutInflater inflater;

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_GROUP = 1;
    public static final int TYPE_CHILD = 2;


    public CarBrandAdapter(Context mContext, List<CarBaseBean> list) {
        this.mContext = mContext;
        this.list = list;
        inflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getItemViewType(int position) {
        if (list.size() > 0 && list != null) {
            if (position == TYPE_HEADER) {
                return TYPE_HEADER;
            }
            return list.get(position - 1).getItemType();
        }
        return -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new HeadHolder(inflater.inflate(R.layout.car_item_head, parent, false));
        } else if (viewType == TYPE_GROUP) {
            return new GroupHolder(inflater.inflate(R.layout.car_item_group, parent, false));
        } else if (viewType == TYPE_CHILD) {
            return new ChildHolder(inflater.inflate(R.layout.car_item_child, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ChildHolder) {
            CarChildBean carBaseBean = (CarChildBean) list.get(position - 1);
            ((ChildHolder) holder).tv_child_tittle.setText(carBaseBean.getCarName() + ":" + position);
            ((ChildHolder) holder).tv_child_tittle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //                    Log.d("=====position",position+"");
                }
            });
        } else if (holder instanceof GroupHolder) {
            CarGroupBean carBaseBean = (CarGroupBean) list.get(position - 1);
            ((GroupHolder) holder).tv_tittle.setText(carBaseBean.getIndexTittle());
        }

    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    //head
    public class HeadHolder extends RecyclerView.ViewHolder {

        public HeadHolder(View itemView) {
            super(itemView);

        }
    }


    //group
    public class GroupHolder extends RecyclerView.ViewHolder {

        private final TextView tv_tittle;

        public GroupHolder(View itemView) {
            super(itemView);
            tv_tittle = itemView.findViewById(R.id.tv_tittle);
        }
    }

    //child
    public class ChildHolder extends RecyclerView.ViewHolder {

        private final TextView tv_child_tittle;

        public ChildHolder(View itemView) {
            super(itemView);
            tv_child_tittle = itemView.findViewById(R.id.tv_child_tittle);

        }
    }


}
