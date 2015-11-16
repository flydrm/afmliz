package cn.flydrm.fm.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import cn.flydrm.fm.R;
import cn.flydrm.fm.fragment.BlogFragment;
import cn.flydrm.fm.fragment.NewsAmusementFragment;
import cn.flydrm.fm.fragment.NewsFinanceFragment;
import cn.flydrm.fm.fragment.NewsFragment;
import cn.flydrm.fm.fragment.NewsMilitaryFragment;
import cn.flydrm.fm.fragment.NewsMoviesFragment;
import cn.flydrm.fm.fragment.NewsSportsFragment;
import cn.flydrm.fm.fragment.NewsTimeNewsFragment;
import cn.flydrm.fm.fragment.SosoFragment;
import cn.flydrm.fm.fragment.ToolsFragment;

public class MainActivity extends AppCompatActivity implements NewsTimeNewsFragment.OnFragmentInteractionListener,NewsSportsFragment.OnFragmentInteractionListener,NewsMoviesFragment.OnFragmentInteractionListener,NewsMilitaryFragment.OnFragmentInteractionListener,NewsFinanceFragment.OnFragmentInteractionListener,NewsAmusementFragment.OnFragmentInteractionListener,NewsFragment.OnNewsFragmentInteractionListener,BlogFragment.OnBlogFragmentInteractionListener,SosoFragment.OnSosoFragmentInteractionListener,ToolsFragment.OnToolsFragmentInteractionListener{

    //标签类
    private Class[] tabFragments = new Class[]{NewsFragment.class, BlogFragment.class, SosoFragment.class, ToolsFragment.class};
    //标签对应的名字
    private int[] tabNames = {R.string.wl_news_text,R.string.wl_blog_text,R.string.wl_soso_text,R.string.wl_tools_text};
    //标签对应的icon图片
    private int[] tabIcons = {R.drawable.news_icon,R.drawable.blog_icon,R.drawable.soso_icon,R.drawable.tools_icon};

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private ActionBarDrawerToggle toggle;

    private FragmentTabHost tabHost;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.wl_toobar);
        toolbar.setTitle("主页");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.wl_drawlayout);
//        drawerLayout.setDrawerListener(new FmDrawerListenner()) ;
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_nav_drawer, R.string.close_nav_drawer);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

//      导航栏
        navigationView = (NavigationView) findViewById(R.id.wl_nav_view);
        navigationView.setNavigationItemSelectedListener(new FmNaviItemSelectedListener());

//        标签tab

        initView();


    }

    private void initView() {
        inflater = LayoutInflater.from(this);
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.real_tab);

        int count = tabFragments.length;
        for(int i=0;i<count;i++){
            TabHost.TabSpec tab = tabHost.newTabSpec(getResources().getString(tabNames[i])).setIndicator(getView(i));
            tabHost.addTab(tab, tabFragments[i], null);
        }

    }

    private View getView(int index) {
        View view = inflater.inflate(R.layout.tab_item, null);
        TextView tv = (TextView) view.findViewById(R.id.tab_item_name);
        tv.setText(tabNames[index]);

        ImageView iv = (ImageView) view.findViewById(R.id.tab_item_imageview);
//        iv.setBackgroundResource(tabIcons[index]);
        iv.setBackgroundResource(R.drawable.icon);
        return view;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        return true;
    }

    /**
     * fm 博客交互
     * */
    @Override
    public void onBlogFragmentInteraction(Uri uri) {
    }

    /**
     * fm 搜索交互
     * */
    @Override
    public void onSosoFragmentInteraction(Uri uri) {

    }


    /**
     * fm 工具交互
     * */
    @Override
    public void onToolsFragmentInteraction(Uri uri) {

    }

    /**
     * fm 新闻交互
     * */
    @Override
    public void onNewsFragmentInteraction(Uri uri) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    class FmNaviItemSelectedListener implements NavigationView.OnNavigationItemSelectedListener{

        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {


            DrawerLayout drawer= (DrawerLayout) findViewById(R.id.wl_drawlayout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    }


}
