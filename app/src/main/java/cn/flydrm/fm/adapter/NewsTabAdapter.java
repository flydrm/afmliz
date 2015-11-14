package cn.flydrm.fm.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2015/11/9.
 */
public class NewsTabAdapter extends FragmentStatePagerAdapter {

    private List<String> ls;
    List<Fragment> fs;

    public NewsTabAdapter(FragmentManager fm,List<Fragment> fragments,List<String> labs) {
        super(fm);
        ls=labs;
        fs = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fs.get(position);
    }

    @Override
    public int getCount() {
        return ls.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ls.get(position);
    }
}
