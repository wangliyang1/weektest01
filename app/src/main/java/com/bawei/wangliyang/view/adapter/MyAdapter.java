package com.bawei.wangliyang.view.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.wangliyang.R;
import com.bawei.wangliyang.model.bean.Bean;
import com.bawei.wangliyang.util.NetUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
/*
 * 功能：adapter
 * 作者：王黎杨
 * 时间：2019年12月31日09:43:29
 * */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<Bean.RankingBean> ranking;
    int i=7;
    public MyAdapter(List<Bean.RankingBean> ranking) {
        this.ranking = ranking;
    }

    @NonNull
    @Override
    //创建视图
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(parent.getContext(), R.layout.child, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //绑定视图
        Bean.RankingBean rankingBean = ranking.get(i--);
        Log.i("xxx",""+position);
        holder.childOne.setText(rankingBean.getRank());
        holder.childTwo.setText(rankingBean.getName());
        NetUtil.getInstance().getPhono(rankingBean.getAvatar(),holder.childImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClick(rankingBean.getName());
            }
        });
    }
    //返回总数量
    @Override
    public int getItemCount() {
        return ranking.size();
    }
    //创建内部类并绑定控件
    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.child_one)
        TextView childOne;
        @BindView(R.id.child_image)
        ImageView childImage;
        @BindView(R.id.child_two)
        TextView childTwo;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    //自定义接口回调
    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onClick(String s);
    }

}
