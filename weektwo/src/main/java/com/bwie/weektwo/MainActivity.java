package com.bwie.weektwo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private ExpandableListView elv;
    private CheckBox chb;
    private TextView tv;
    private List<Bean> list;
    private List<List<Bean>> lists;
    private MyExpandableList melv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chb= (CheckBox) findViewById(R.id.chb);
        tv= (TextView) findViewById(R.id.tv);
        chb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chb.isChecked()){
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setIschecked(true);
                    }
                    melv.notifyDataSetChanged();
                    for (int j = 0;j<lists.size(); j++) {
                        List<Bean> ll=lists.get(j);
                        for (int z = 0; z <ll.size() ; z++) {
                            ll.get(z).setIschecked(true);
                        }
                    }
                    melv.notifyDataSetChanged();
                }else{
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setIschecked(false);
                    }
                    melv.notifyDataSetChanged();
                    for (int j = 0;j<lists.size()  ; j++) {
                        List<Bean> ll=lists.get(j);
                        for (int z = 0; z <ll.size() ; z++) {
                            ll.get(z).setIschecked(false);
                        }
                    }
                    melv.notifyDataSetChanged();
                }
            }
        });

        list=new ArrayList<>();

        lists=new ArrayList<>();
        list.add(new Bean(false,"西游记"));
        list.add(new Bean(false,"红楼梦"));
        list.add(new Bean(false,"三国演义"));
        list.add(new Bean(false,"水浒传"));

        List<Bean> list1=new ArrayList<>();
        list1.add(new Bean(false,"孙悟空"));
        list1.add(new Bean(false,"唐僧"));
        list1.add(new Bean(false,"猪八戒"));
        list1.add(new Bean(false,"沙僧"));
        list1.add(new Bean(false,"白龙马"));
        lists.add(list1);
        List<Bean> list2=new ArrayList<>();
        list2.add(new Bean(false,"孙悟空1"));
        list2.add(new Bean(false,"唐僧1"));
        list2.add(new Bean(false,"猪八戒1"));
        list2.add(new Bean(false,"沙僧1"));
        list2.add(new Bean(false,"白龙马1"));
        lists.add(list2);
        List<Bean> list3=new ArrayList<>();
        list3.add(new Bean(false,"孙悟空2"));
        list3.add(new Bean(false,"唐僧2"));
        list3.add(new Bean(false,"猪八戒2"));
        list3.add(new Bean(false,"沙僧2"));
        list3.add(new Bean(false,"白龙马2"));
        lists.add(list3);
        List<Bean> list4=new ArrayList<>();
        list4.add(new Bean(false,"孙悟空3"));
        list4.add(new Bean(false,"唐僧3"));
        list4.add(new Bean(false,"猪八戒3"));
        list4.add(new Bean(false,"沙僧3"));
        list4.add(new Bean(false,"白龙马3"));
        lists.add(list4);

        chb= (CheckBox) findViewById(R.id.chb);
        tv= (TextView) findViewById(R.id.tv);
        elv= (ExpandableListView) findViewById(R.id.elv);
        elv.setGroupIndicator(null);
        melv = new MyExpandableList();
        elv.setAdapter(melv);
        elv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, list.get(i).getName(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Toast.makeText(MainActivity.this, list.get(i).getName()+" "+lists.get(i).get(i1).getName(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });


    }
    class MyExpandableList extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return list.size();
        }

        @Override
        public int getChildrenCount(int i) {
            return lists.get(i).size();
        }

        @Override
        public Object getGroup(int i) {
            return list.get(i);
        }

        @Override
        public Object getChild(int i, int i1) {
            return lists.get(i).get(i1);
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
            final GroupHolder gh;
            if(view==null){
                view=View.inflate(MainActivity.this,R.layout.ss,null);
                gh=new GroupHolder();
                gh.cb= (CheckBox) view.findViewById(R.id.cb);
                gh.tv= (TextView) view.findViewById(R.id.tv);
                view.setTag(gh);
            }else{
                gh= (GroupHolder) view.getTag();
            }
            gh.cb.setChecked(list.get(i).ischecked());
            gh.tv.setText(list.get(i).getName());
            gh.cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(list.get(i).ischecked()){
                        list.get(i).setIschecked(false);
                        chb.setChecked(false);
                        for (int j=0;j<lists.get(i).size();j++){
                            lists.get(i).get(j).setIschecked(false);
                        }
                        notifyDataSetChanged();
                    }else{
                        int z=0;
                        list.get(i).setIschecked(true);
                        for (int j=0;j<lists.get(i).size();j++){
                            lists.get(i).get(j).setIschecked(true);
                        }
                        for (int k=0;k<list.size();k++){
                            if(list.get(k).ischecked()){
                                z++;
                            }
                        }
                        if(z==list.size()){
                            chb.setChecked(true);
                        }
                        notifyDataSetChanged();
                    }

                }

            });
            return view;
        }

        @Override
        public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {

            final ChildHolder chh;
            if(view==null){
                view=View.inflate(MainActivity.this,R.layout.sss,null);
                chh=new ChildHolder();
                chh.cb1= (CheckBox) view.findViewById(R.id.cb);
                chh.tv1= (TextView) view.findViewById(R.id.tv);
                view.setTag(chh);
            }else{
                chh=(ChildHolder) view.getTag();
            }

            chh.tv1.setText(lists.get(i).get(i1).getName());
            chh.cb1.setChecked(lists.get(i).get(i1).ischecked());
            chh.cb1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(lists.get(i).get(i1).ischecked()){
                        lists.get(i).get(i1).setIschecked(false);
                        list.get(i).setIschecked(false);
                        chb.setChecked(false);
                        notifyDataSetChanged();

                    }else{
                        lists.get(i).get(i1).setIschecked(true);
                        int n=0;
                        int y=0;
                        for (int j = 0; j <lists.get(i).size() ; j++) {
                            if(lists.get(i).get(j).ischecked()){
                                n++;
                            }
                        }
                        if(n ==lists.get(i).size()){
                            list.get(i).setIschecked(true);
                        }

                        for (int k=0;k<list.size();k++){
                            if(list.get(k).ischecked()){
                                y++;
                            }
                        }
                        if(y ==list.size()){
                            chb.setChecked(true);
                        }
                        notifyDataSetChanged();
                    }







                }
            });


            /*int num1=0;
            for (int j = 0; j <lists.size() ; j++) {
                List<Bean> li=lists.get(j);
                for (int k = 0; k <li.size() ; k++) {
                    if(li.get(k).ischecked()){
                        num1++;
                    }
                }
            }
            Toast.makeText(MainActivity.this, num1+"num1", Toast.LENGTH_SHORT).show();*/
            return view;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
    }
    class GroupHolder{
        CheckBox cb;
        TextView tv;
    }
    class ChildHolder{
        CheckBox cb1;
        TextView tv1;
    }

}
