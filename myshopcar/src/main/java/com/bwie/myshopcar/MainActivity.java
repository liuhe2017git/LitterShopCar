package com.bwie.myshopcar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.selectAll)
    CheckBox selectAll;
    @BindView(R.id.priceAll)
    TextView priceAll;
    @BindView(R.id.gotoSubmit)
    Button gotoSubmit;
    private List<ShopBean> list;
    private int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        //模拟数据
        initData();
        //做适配
        setAdapter();
    }

    private void setAdapter() {
        listView.setAdapter(new ShopAdapter(MainActivity.this,list));
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i =0;i<10;i++){
            int price = (int) (Math.random() * 899 + 100);
            list.add(new ShopBean("购物车里边的第"+i+"件东西",price,false));
        }
    }
    @Subscribe
    public void onEvent(EventBean bean){
        boolean isSelectAll = bean.isSelectAll;
        list = bean.getList();

        selectAll.setChecked(isSelectAll);

        for (int i =0;i<list.size();i++){
            if(list.get(i).isSelected){
                count+=list.get(i).getPrice();
            }
        }
        priceAll.setText(count+"");
        count=0;
        listView.setAdapter(new ShopAdapter(MainActivity.this,list));
    }

    @OnClick(R.id.selectAll)
    public void onSelectAllClicked() {

        for (int i =0;i<list.size();i++){
            if(selectAll.isChecked()){
                count+=list.get(i).getPrice();
                if(!list.get(i).isSelected){
                    list.get(i).setSelected(true);
                }
            }else {
                count=0;
                list.get(i).setSelected(false);
            }
        }
        priceAll.setText(count+"");
        count=0;
        listView.setAdapter(new ShopAdapter(MainActivity.this,list));
    }

    @OnClick(R.id.gotoSubmit)
    public void onGotoSubmitClicked() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
