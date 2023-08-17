package com.example.cn_htcsgt.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import java.util.ArrayList;
import java.util.List;

public abstract class CustomBaseAdapter<T>  extends BaseAdapter {

    protected List<T> Datas = new ArrayList<>();
    protected List<T> FilterDatas = new ArrayList<>();
    private final Context context;

    public CustomBaseAdapter(Context context) {
        this.context = context;
    }
    @Override
    public int getCount() {
        return Datas.size();
    }

    public void setDatas(List<T> datas){
        this.Datas = datas;
        this.FilterDatas = datas;
        notifyDataSetChanged();
    }

    public T getItemData(int position) {
        return FilterDatas.get(position);
    }
    @Override
    public Object getItem(int position) {
        return FilterDatas.size() > position ? FilterDatas.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null) {
            viewHolder= new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
            viewHolder.tv =  convertView.findViewById(android.R.id.text1);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        if(FilterDatas.size() > position){
            viewHolder.tv.setText(GetText(FilterDatas.get(position)));
        }
        return convertView;
    }

    protected abstract String GetText(T t);

    private static final class ViewHolder {
        private CheckedTextView tv;
    }
}
