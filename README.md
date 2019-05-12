一.ViewPage2添加新特性：
      *  从右到左的布局支持
      *  垂直方向
      *  RecyclerView.Adapter取代PagerAdapter
      *  registerOnPageChangeCallback 取代 addPageChangeListener
      *  更高效的notifyDataSetChanged

二.以下案例是使用RecyclerView结合ViewPage2实现上下滑动

1.依赖导入
    //ViewPage2
    implementation 'androidx.viewpager2:viewpager2:1.0.0-alpha01'
    //recyclerview
    implementation 'androidx.recyclerview:recyclerview:1.0.0-beta01'

2.Activity和Adapter布局设置
  
   1>.Activity布局
       <?xml version="1.0" encoding="utf-8"?>
       <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">
    
          <androidx.viewpager2.widget.ViewPager2
            android:id="@+id/viewpager2"
            android:layout_width="match_parent"
            android:layout_height="match_parent"/>

        </androidx.constraintlayout.widget.ConstraintLayout>

   2>.recycleview_item.xml适配器布局
      <?xml version="1.0" encoding="utf-8"?>
      <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
         xmlns:tools="http://schemas.android.com/tools"
         android:id="@+id/container"
         android:layout_width="match_parent"
         android:layout_height="match_parent">
         //原生控件
        <androidx.appcompat.widget.AppCompatTextView
           android:id="@+id/tvTitle"
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:layout_centerInParent="true"
           android:textColor="@android:color/white"
           android:textSize="32sp"
           tools:text="item" />
       </RelativeLayout>

3.ViewPage2的适配器

    package com.wd.viewpage2demo;
    import android.content.Context;
    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.RecyclerView;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.RelativeLayout;
    import android.widget.TextView;
    import java.util.List;

    import androidx.viewpager2.widget.ViewPager2;

   /**
   * Author : 张自力
   * Created on time.
   *
   * ViewPage2的适配器
   *
   */
   public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {

     private List<String> mData;
     private LayoutInflater mInflater;
     private ViewPager2 viewPager2;
     //定义一个色彩背景数组
    private int[] colorArray = new int[]{android.R.color.black, android.R.color.holo_blue_dark, 
                                          android.R.color.holo_green_dark, android.R.color.holo_red_dark};

    public ViewPagerAdapter(Context context, List<String> data, ViewPager2 viewPager2) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public ViewPagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycleview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerAdapter.ViewHolder holder, int position) {
        String animal = mData.get(position);
        holder.myTextView.setText(animal);
        holder.relativeLayout.setBackgroundResource(colorArray[position]);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;
        RelativeLayout relativeLayout;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tvTitle);
            relativeLayout = itemView.findViewById(R.id.container);

          }
       }

    }


4.Activity界面编写
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
}


