package com.example.wanandroid.presentation.main;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.wanandroid.R;
import com.example.wanandroid.presentation.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager = findViewById(R.id.view_pager);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);

        viewPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return new HomeFragment();
            }

            @Override
            public int getItemCount() {
                return 1;
            }
        });
    }

    private void setupViewPager() {
        ViewPager2 viewPager = findViewById(R.id.view_pager);


        viewPager.setAdapter(new androidx.viewpager2.adapter.FragmentStateAdapter(this) {
            @NonNull
            @Override
            public androidx.fragment.app.Fragment createFragment(int position) {
                return new HomeFragment();
            }

            @Override
            public int getItemCount() {
                return 1;
            }
        });
    }
}