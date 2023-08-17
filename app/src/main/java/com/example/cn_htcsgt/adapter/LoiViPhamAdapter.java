package com.example.cn_htcsgt.adapter;

import android.content.Context;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.cn_htcsgt.base.CustomBaseAdapter;
import com.example.cn_htcsgt.model.LoiViPham.GetLoiViPhamResponse;

import java.util.ArrayList;
import java.util.List;

public class LoiViPhamAdapter extends CustomBaseAdapter<GetLoiViPhamResponse.Data> implements Filterable {

    public LoiViPhamAdapter(Context context) {
        super(context);
    }

    @Override
    protected String GetText(GetLoiViPhamResponse.Data data) {
        return data.tenloi;
    }@Override
    public Object getItem(int position) {
        return FilterDatas.size() > position ? FilterDatas.get(position).tenloi : null;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    //no constraint given, just return all the data. (no search)
                    results.count = Datas.size();
                    results.values = Datas;
                } else {//do the search
                    List<GetLoiViPhamResponse.Data> resultsData = new ArrayList<>();
                    for (GetLoiViPhamResponse.Data o : Datas)
                        if (o.SeachText(constraint.toString()))
                            resultsData.add(o);
                    results.count = resultsData.size();
                    results.values = resultsData;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                FilterDatas = (List<GetLoiViPhamResponse.Data>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}

