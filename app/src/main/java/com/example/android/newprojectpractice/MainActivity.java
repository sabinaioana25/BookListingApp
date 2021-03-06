package com.example.android.newprojectpractice;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdapter);

        final TabLayout fragmentTab = (TabLayout) findViewById(R.id.tab_layout);
        fragmentTab.setupWithViewPager(viewPager);

        for (int i = 0; i < fragmentTab.getTabCount(); i++) {
            int iconId = -1;
            switch (i) {
                case 0:
                    iconId = R.drawable.ic_trending_up_white_48dp;
                    break;
                case 1:
                    iconId = R.drawable.ic_query_builder_white_48dp;
                    break;
                case 2:
                    iconId = R.drawable.ic_library_books_white_48dp;
                    break;
                case 3:
                    iconId = R.drawable.ic_language_white_48dp;
                    break;
            }

            fragmentTab.setTabGravity(TabLayout.GRAVITY_FILL);
            fragmentTab.setPadding(0, 40, 0, 0);
            fragmentTab.getTabAt(i).setIcon(iconId);


            viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    fragmentTab.getTabAt(position).select();
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });

            fragmentTab.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    super.onTabSelected(tab);
                }
            });
        }
    }
}
