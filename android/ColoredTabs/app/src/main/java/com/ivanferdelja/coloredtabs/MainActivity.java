package com.ivanferdelja.coloredtabs;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.my_tabbar);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);


        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tabA)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tabB)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tabC)));
        tabLayout.setSelectedTabIndicatorHeight(0);


        tabLayout.getRootView().setBackgroundColor(getColor(R.color.colorA));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (getString(R.string.tabA).equals(tab.getText())) {
                    tabLayout.setBackgroundColor(getColor(R.color.colorA));
                    tabLayout.getRootView().setBackgroundColor(getColor(R.color.colorA));
                    viewPager.setCurrentItem(0);
                } else if (getString(R.string.tabB).equals(tab.getText())) {
                    tabLayout.setBackgroundColor(getColor(R.color.colorB));
                    tabLayout.getRootView().setBackgroundColor(getColor(R.color.colorB));
                    viewPager.setCurrentItem(1);
                } else if (getString(R.string.tabC).equals(tab.getText())) {
                    tabLayout.setBackgroundColor(getColor(R.color.colorC));
                    tabLayout.getRootView().setBackgroundColor(getColor(R.color.colorC));
                    viewPager.setCurrentItem(2);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.setTabTextColors(getColorStateList(R.color.tab_color));


        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new StoryFragment();
                    case 1:
                        return new PhotoFragment();
                    case 2:
                        return new ShareFragment();
                    default:
                        return new Fragment();
                }
            }

            @Override
            public int getCount() {
                return 3;
            }

        });

//        ObjectAnimator objectAnimator = ObjectAnimator.ofArgb(tabLayout.getRootView(), "backgroundColor",
//                getColor(R.color.));

        final float lastPositionOffset = 0;
        boolean movingLeft = false;

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("ColoredTab", position + " " + positionOffset + " " + positionOffsetPixels);


                lastPositionOffset = positionOffset;
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("ColoredTab", "pageSelected " + position);
                TabLayout.Tab tab = tabLayout.getTabAt(position);
                if (tab != null) {
                    tab.select();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    public static class StoryFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_stories, container, false);
        }
    }

    public static class PhotoFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_photos, container, false);
            GridView gridView = (GridView) view.findViewById(R.id.grid);
            gridView.setAdapter(new ImageAdapter(getContext()));
            return view;
        }
    }

    public static class ShareFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_share, container, false);
        }
    }

}
