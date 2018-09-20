package oclass.student.com.newproject.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import oclass.student.com.newproject.R;
import oclass.student.com.newproject.adapter.MyPagerAdapter;
import oclass.student.com.newproject.fragmet.OneFragement;
import oclass.student.com.newproject.fragmet.TwoFragement;

public class ThreeActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragmentList;
    private List<String> list_Title;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.three_layout);
        init();
    }

    private void init() {
        viewPager=findViewById(R.id.viewpager);
        tabLayout=findViewById(R.id.tablayout);
        fragmentList = new ArrayList<>();
        list_Title = new ArrayList<>();
        fragmentList.add(new OneFragement());
        fragmentList.add(new TwoFragement());
        list_Title.add("one");
        list_Title.add("two");
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),ThreeActivity.this,fragmentList,list_Title));
        tabLayout.setupWithViewPager(viewPager);//此方法就是让tablayout和ViewPager联动

    }
}
