package dang.ugi.com.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DANG on 9/5/2016.
 */
public class ViewPaperAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> fragmentTitleList = new ArrayList<>();
    public ViewPaperAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
    public void addFragment(Fragment fragment,String title){
        fragmentTitleList.add(title);
        mFragmentList.add(fragment);
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return  fragmentTitleList.get(position);
    }
}
