package com.ivanferdelja.coloredtabs;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.my_tabbar);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tabA)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tabB)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tabC)));
        tabLayout.setSelectedTabIndicatorHeight(0);

        tabLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(getColor(android.R.color.transparent));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (getString(R.string.tabA).equals(tab.getText())) {
                    viewPager.setCurrentItem(0);

                    fab.show();
                } else if (getString(R.string.tabB).equals(tab.getText())) {
                    viewPager.setCurrentItem(1);

                    fab.hide();
                } else if (getString(R.string.tabC).equals(tab.getText())) {
                    viewPager.setCurrentItem(2);

                    fab.hide();
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

        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        StoryFragment storyFragment = new StoryFragment();
                        //storyFragment.setEsc(elevationScrollControl);
                        return storyFragment;
                    case 1:
                        PhotoFragment photoFragment = new PhotoFragment();
                        //photoFragment.setEsc(elevationScrollControl);
                        return photoFragment;
                    case 2:
                        ShareFragment shareFragment = new ShareFragment();
//                        shareFragment.setListAdapter(shareStreamAdapter);
//                        shareFragment.setEsc(elevationScrollControl);
                        return shareFragment;
                    default:
                        return new Fragment();
                }
            }

            @Override
            public int getCount() {
                return 3;
            }

        });

        viewPager.addOnPageChangeListener(new PageChangeListener(tabLayout));

        viewPager.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener {

        private TabLayout tabLayout;
        ArgbEvaluator evaluator;

        public PageChangeListener(TabLayout tabLayout) {
            this.tabLayout = tabLayout;
            evaluator = new ArgbEvaluator();
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            int startColor;
            int endColor;
            float elevation = 0;

            if (position == 0) {
                startColor = getColor(R.color.colorA);
                endColor = getColor(R.color.colorB);
            } else if (position == 2) {
                startColor = endColor = getColor(R.color.colorC);
                elevation = getResources().getDimensionPixelSize(R.dimen.tabbar_elevation);
            } else {
                startColor = getColor(R.color.colorB);
                endColor = getColor(R.color.colorC);
                elevation = getResources().getDimensionPixelSize(R.dimen.tabbar_elevation) * positionOffset;
            }

            //Log.d("Colored tabs", "Position " + position + " Position offset: " + positionOffset + " Elev " + elevation);
            int color = (int) evaluator.evaluate(positionOffset, startColor, endColor);
            tabLayout.getRootView().setBackgroundColor(color);
            tabLayout.setBackgroundColor(color);
            tabLayout.setElevation(elevation);
        }

        @Override
        public void onPageSelected(int position) {
            TabLayout.Tab tab = tabLayout.getTabAt(position);
            if (tab != null) {
                tab.select();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}
