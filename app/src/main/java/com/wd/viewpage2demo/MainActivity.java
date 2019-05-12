package com.wd.viewpage2demo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewpager2;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        //初始化数据
        initDatas();
        //ViewPage2设置
        setViewPage2S();
    }

    /**
     * ViewPage2设置
     *
     * */
    private void setViewPage2S() {

        /**
         * 垂直方向属性
         * 默认是水平方向ORIENTATION_HORIZONTAL,垂直是ORIENTATION_VERTICAL
         *
         * */
         //设置方向
         viewpager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);

         //设置adapter
         viewpager2.setAdapter(new ViewPagerAdapter(this, list, viewpager2));

         viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
             @Override
             public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                 super.onPageScrolled(position, positionOffset, positionOffsetPixels);
             }

             @Override
             public void onPageSelected(int position) {
                 super.onPageSelected(position);
             }

             @Override
             public void onPageScrollStateChanged(int state) {
                 super.onPageScrollStateChanged(state);
             }
         });


    }

    /**
     * 初始化数据
     *
     * */
    private void initDatas() {
        list = new ArrayList<>();
        list.add("页面一");
        list.add("页面二");
        list.add("页面三");
        list.add("页面四");

    }

    /**
     * 初始化控件
     *
     * */
    private void initView() {
        //初始化ViewPage2
        viewpager2 = (ViewPager2) findViewById(R.id.viewpager2);
    }





    /**
     * 涉及到的知识点：
     *
     *  Android ViewPager2的特性：
     *        从右到左的布局支持
     *       垂直方向
     *       RecyclerView.Adapter取代PagerAdapter
     *       registerOnPageChangeCallback 取代 addPageChangeListener
     *       更高效的notifyDataSetChanged
     *
     * */

}
