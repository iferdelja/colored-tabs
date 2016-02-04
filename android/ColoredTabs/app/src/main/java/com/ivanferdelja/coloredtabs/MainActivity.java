package com.ivanferdelja.coloredtabs;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    StoryAdapter storyAdapter;
    ShareStreamAdapter shareStreamAdapter;
    ElevationScrollControl elevationScrollControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.my_tabbar);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        elevationScrollControl = new ElevationScrollControl();
        elevationScrollControl.setView(tabLayout);

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

        storyAdapter = new StoryAdapter(getApplicationContext(), 0);
        storyAdapter.add(Story.create(R.drawable.story1, getString(R.string.story1_title)));
        storyAdapter.add(Story.create(R.drawable.story2, getString(R.string.story2_title)));
        storyAdapter.add(Story.create(R.drawable.story3, getString(R.string.story3_title)));

        shareStreamAdapter = new ShareStreamAdapter(getApplicationContext());
        ShareStream stream = new ShareStream();
        stream.shareItems.add(ShareItem.create(R.drawable.face1, "Share item a1", R.drawable.nature9));
        stream.shareItems.add(ShareItem.create(R.drawable.face2, "Share item a2", R.drawable.nature8));
        stream.shareItems.add(ShareItem.create(R.drawable.face3, "Share item a3", R.drawable.nature5));
        stream.shareItems.add(ShareItem.create(R.drawable.face4, "Share item b1", R.drawable.nature6));
        shareStreamAdapter.add(stream);

        ShareStream stream3 = new ShareStream();
        stream3.shareItems.add(ShareItem.create(R.drawable.face2, "Share item c1", R.drawable.nature7));
        stream3.shareItems.add(ShareItem.create(R.drawable.face3, "Share item c2", R.drawable.nature4));
        stream3.shareItems.add(ShareItem.create(R.drawable.face1, "Share item c3", R.drawable.nature3));
        shareStreamAdapter.add(stream3);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        StoryFragment storyFragment = new StoryFragment();
                        storyFragment.setListAdapter(storyAdapter);
                        storyFragment.setEsc(elevationScrollControl);
                        return storyFragment;
                    case 1:
                        PhotoFragment photoFragment = new PhotoFragment();
                        photoFragment.setEsc(elevationScrollControl);
                        return photoFragment;
                    case 2:
                        ShareFragment shareFragment = new ShareFragment();
                        shareFragment.setListAdapter(shareStreamAdapter);
                        shareFragment.setEsc(elevationScrollControl);
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
            tabLayout.setBackgroundColor(color);
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

    public static class BaseListFragment extends ListFragment {

        ElevationScrollControl esc;

        public void setEsc(ElevationScrollControl esc) {
            this.esc = esc;
        }
        protected void updateElevationScrollControl(final AbsListView view) {
            view.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView absListView, int i) {
                }

                @Override
                public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                    View first = view.getChildAt(0);
                    if (first != null) {
                        esc.scrollingUpdate(first.getTop() < 0);
                    }
                }
            });
        }
    }

    public static class StoryFragment extends BaseListFragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            final View view = inflater.inflate(R.layout.fragment_stories, container, false);
            updateElevationScrollControl((ListView)view);
            return view;
        }
    }

    public static class PhotoFragment extends Fragment {
        ElevationScrollControl esc;
        public void setEsc(ElevationScrollControl esc) {
            this.esc = esc;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            final View view = inflater.inflate(R.layout.fragment_photos, container, false);
            final GridView gridView = (GridView) view.findViewById(R.id.grid);
            gridView.setAdapter(new ImageAdapter(getContext()));
            gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView absListView, int i) {
                }

                @Override
                public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                    View first = gridView.getChildAt(0);
                    if (first != null) {
                        esc.scrollingUpdate(first.getTop() < 0);
                    }
                }
            });
            return view;
        }
    }

    public static class ShareFragment extends BaseListFragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_share, container, false);
            updateElevationScrollControl((ListView)view);
            return view;
        }
    }

    public class ElevationScrollControl {
        View view;

        public void setView(View view) {
            this.view = view;
        }

        public void scrollingUpdate(boolean isScrolling) {
            view.setElevation(isScrolling ? getResources().getDimensionPixelSize(R.dimen.elevation) : 0);
        }
    }

}
