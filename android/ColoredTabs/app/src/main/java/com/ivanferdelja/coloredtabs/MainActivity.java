package com.ivanferdelja.coloredtabs;

import android.animation.ArgbEvaluator;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
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

        tabLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(getColor(android.R.color.transparent));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (getString(R.string.tabA).equals(tab.getText())) {
                    viewPager.setCurrentItem(0);
                } else if (getString(R.string.tabB).equals(tab.getText())) {
                    viewPager.setCurrentItem(1);
                } else if (getString(R.string.tabC).equals(tab.getText())) {
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
                        android.support.v4.app.ListFragment fragment = new android.support.v4.app.ListFragment();
                        StoryAdapter adapter = new StoryAdapter(getApplicationContext(), 0);
                        adapter.add(Story.create(R.drawable.story1, "Story description"));
                        fragment.setListAdapter(adapter);
                        return fragment;
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

        viewPager.addOnPageChangeListener(new PageChangeListener(tabLayout));
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

            if (position == 0) {
                startColor = getColor(R.color.colorA);
                endColor = getColor(R.color.colorB);
            } else if (position == 2) {
                startColor = endColor = getColor(R.color.colorC);
            } else {
                startColor = getColor(R.color.colorB);
                endColor = getColor(R.color.colorC);
            }

            int color = (int) evaluator.evaluate(positionOffset, startColor, endColor);
            tabLayout.getRootView().setBackgroundColor(color);
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

    public static class StoryFragment extends android.support.v4.app.ListFragment {
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//            return inflater.inflate(R.layout.fragment_stories, container, false);
//        }
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
