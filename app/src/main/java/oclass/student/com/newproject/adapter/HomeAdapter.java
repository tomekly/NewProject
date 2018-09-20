package oclass.student.com.newproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import oclass.student.com.newproject.R;
import oclass.student.com.newproject.bean.BaseWeather;
import oclass.student.com.newproject.bean.Weather;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    private List<String> mDatas;
//    private List<BaseWeather> mDatas;
    private Context mContext;
    private LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(int position);

        void onLongClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public HomeAdapter(Context context, List<String> datas) {
        this.mContext = context;
        this.mDatas = datas;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemCount() {

        return mDatas.size();
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

//        holder.tv.setText(mDatas.get(position));

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onLongClick(position);
                    return false;
                }
            });
        }
    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_home, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        //绑定数据
//        holder.tv.setText(mDatas.get(viewType).getCity());
//        holder.data_shidu.setText(mDatas.get(viewType).getCity());
//        holder.yes_date.setText(mDatas.get(viewType).getDate().);
//        holder.for_sunrise.setText(list.get(viewType).getData().getForecast());


        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        TextView data_shidu;
        TextView yes_date;
        TextView for_sunrise;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.city);
            data_shidu = (TextView) view.findViewById(R.id.data_shidu);
            yes_date = (TextView) view.findViewById(R.id.yes_date);
            for_sunrise = (TextView) view.findViewById(R.id.for_sunrise);
        }

    }

    public void addData(int position) {
//        mDatas.add(position, "Insert item");
        notifyItemInserted(position);
        notifyItemRangeChanged(position, mDatas.size());
    }

    public void removeData(int position) {
        mDatas.remove(position);
        notifyDataSetChanged();
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mDatas.size());
    }

}
