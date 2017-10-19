package com.bwie.myshopcar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * 作者：刘贺
 * 时间: 2017-10-19.
 * 功能:
 */

public class ShopAdapter extends BaseAdapter {

    private List<ShopBean> shopBeen;
    Context context;
    public ShopAdapter(Context context,List<ShopBean> shopBeen) {
        this.context = context;
        this.shopBeen = shopBeen;
    }
    @Override
    public int getCount() {
        return shopBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return shopBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = View.inflate(context,R.layout.listview_item,null);
            viewHolder = new ViewHolder();
            viewHolder.shop_image = (ImageView) convertView.findViewById(R.id.shop_image);
            viewHolder.shopSelect = (CheckBox) convertView.findViewById(R.id.shopSelect);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText(shopBeen.get(position).getShopName());
        viewHolder.tv_price.setText("¥"+shopBeen.get(position).getPrice());
        viewHolder.shopSelect.setChecked(shopBeen.get(position).isSelected());
        viewHolder.shopSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopBeen.get(position).setSelected(!shopBeen.get(position).isSelected);

                boolean b = true;
                for (int i =0;i<shopBeen.size();i++){
                    if(!shopBeen.get(i).isSelected){
                        b = false;
                    }
                }
                EventBus.getDefault().post(new EventBean(b,shopBeen));
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
    class ViewHolder{
        TextView tv_name;
        TextView tv_price;
        ImageView shop_image;
        CheckBox shopSelect;
    }
}
