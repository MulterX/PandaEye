package com.pandaq.pandaeye.ui.base;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.pandaq.pandaeye.R;
import com.pandaq.pandaeye.utils.BlurImageUtils;
import com.pandaq.pandaeye.utils.LogWritter;
import com.pandaq.pandaeye.ui.bubble.BubblCircleFragment;
import com.pandaq.pandaeye.ui.douban.MovieListFragment;
import com.pandaq.pandaeye.ui.news.NewsListFragment;
import com.pandaq.pandaeye.ui.zhihu.ZhihuDailyFragment;
import com.pandaq.pandaqlib.bottomnavigation.BottomNavigationBar;
import com.pandaq.pandaqlib.bottomnavigation.BottomNavigationItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by PandaQ on 2016/9/7.
 * email : 767807368@qq.com
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationBar.OnTabSelectedListener {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.navigation)
    NavigationView mNavigation;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.bottom_navgation)
    BottomNavigationBar mBottomNavgation;
    private Fragment mCurrentFrag;
    private FragmentManager fm;
    private Fragment mMovieFragment;
    private Fragment mZhihuFragment;
    private Fragment mNewsFragment;
    private Fragment mBubbleFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fm = getSupportFragmentManager();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //用于显示toolbar上的侧滑开关动画的
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
//                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        mDrawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
        mNavigation.setNavigationItemSelectedListener(this);
        initView();
    }

    private void initView() {
        /**
         * 几个Fragment都采用懒加载的方式，只有当用户可见状态下才做数据初始化操作
         */
        mMovieFragment = new MovieListFragment();
        mZhihuFragment = new ZhihuDailyFragment();
        mBubbleFragment = new BubblCircleFragment();
        mNewsFragment = new NewsListFragment();
        LinearLayout linearLayout = (LinearLayout) mNavigation.getHeaderView(0);
        CircleImageView imageView = (CircleImageView) linearLayout.findViewById(R.id.userimage);
        Bitmap overlay = BlurImageUtils.blur(imageView, 6, 5);
        linearLayout.setBackground(new BitmapDrawable(getResources(), overlay));
        initNavigation();
        switchContent(mZhihuFragment);
    }

    private void initNavigation() {
        mBottomNavgation
                .setKeepRipple(false, getResources().getColor(R.color.bottom_nav_ripple_f7065249))
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE)
                .setMode(BottomNavigationBar.MODE_SHIFTING)
                .addItem(new BottomNavigationItem(R.drawable.ic_home, "知乎日报").setActiveColorResource(R.color.teal_00796B))
                .addItem(new BottomNavigationItem(R.drawable.ic_book, "头条新闻").setActiveColorResource(R.color.teal_00796B))
                .addItem(new BottomNavigationItem(R.drawable.ic_music_note, "泡泡圈").setActiveColorResource(R.color.teal_00796B))
                .addItem(new BottomNavigationItem(R.drawable.ic_live_tv, "围观豆瓣").setActiveColorResource(R.color.teal_00796B))
                .setFirstSelectedPosition(0)
                .setTabSelectedListener(this)
                .initialise();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_item0:
                LogWritter.LogStr("nav_item0");
                mDrawerLayout.closeDrawer(GravityCompat.START, true);
                break;
            case R.id.nav_item1:
                LogWritter.LogStr("nav_item1");
                mDrawerLayout.closeDrawer(GravityCompat.START, true);
                break;
            case R.id.nav_item2:
                LogWritter.LogStr("nav_item2");
                mDrawerLayout.closeDrawer(GravityCompat.START, true);
                break;
            case R.id.nav_item3:
                LogWritter.LogStr("nav_item3");
                mDrawerLayout.closeDrawer(GravityCompat.START, true);
                break;
        }
        return true;
    }

    @OnClick({R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Snackbar.make(mFab, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
        }

    }

    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case 0:
                switchContent(mZhihuFragment);
                break;
            case 1:
                switchContent(mNewsFragment);
                break;
            case 2:
                switchContent(mBubbleFragment);
                break;
            case 3:
                switchContent(mMovieFragment);
                break;
            default:
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {
    }

    @Override
    public void onTabReselected(int position) {
    }

    /**
     * 动态添加fragment，不会重复创建fragment
     *
     * @param to 将要加载的fragment
     */
    public void switchContent(Fragment to) {
        if (mCurrentFrag != to) {
            if (!to.isAdded()) {// 如果to fragment没有被add则增加一个fragment
                if (mCurrentFrag != null) {
                    fm.beginTransaction().hide(mCurrentFrag).commit();
                }
                fm.beginTransaction()
                        .add(R.id.main_view, to)
                        .commit();
            } else {
                fm.beginTransaction().hide(mCurrentFrag).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
            mCurrentFrag = to;
        }
    }

}