package com.fireflyest.rmoment.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.fireflyest.rmoment.R;
import com.fireflyest.rmoment.fragment.absorbed.AbsorbedFragment;
import com.fireflyest.rmoment.fragment.matter.MatterFragment;
import com.fireflyest.rmoment.fragment.mine.MineFragment;
import com.fireflyest.rmoment.fragment.note.NoteFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private View root;
    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);
        Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar_home);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        bottomNavigationView = root.findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_absorbed:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.nav_matter:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.nav_note:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.nav_mine:
                        viewPager.setCurrentItem(3);
                        break;
                    default:
                }
                return true;
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new AbsorbedFragment());
        fragmentList.add(new MatterFragment());
        fragmentList.add(new NoteFragment());
        fragmentList.add(new MineFragment());
        HomePagerAdapter adapter = new HomePagerAdapter(getChildFragmentManager(), fragmentList);
        viewPager = root.findViewById(R.id.home_pager);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private MenuItem menuItem;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Toolbar bar = (Toolbar)root.findViewById(R.id.toolbar_home);
                if(bar == null)return;
                bar.setTitle("");
                Menu menu = bar.getMenu();
                switch (position){
                    case 0:
                        menu.setGroupVisible(R.id.absorbed_menu, true);
                        menu.setGroupVisible(R.id.add_menu, false);
                        menu.setGroupVisible(R.id.mine_menu, false);
                        bar.setNavigationIcon(R.drawable.ic_navigation);
                        break;
                    case 1:
                    case 2:
                        menu.setGroupVisible(R.id.absorbed_menu, false);
                        menu.setGroupVisible(R.id.add_menu, true);
                        menu.setGroupVisible(R.id.mine_menu, false);
                        if(positionOffset == 0) bar.setTitle(menuItem.getTitle());
                        bar.setNavigationIcon(null);
                        break;
                    default:
                        menu.setGroupVisible(R.id.absorbed_menu, false);
                        menu.setGroupVisible(R.id.add_menu, false);
                        menu.setGroupVisible(R.id.mine_menu, true);
                        bar.setNavigationIcon(null);
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {
//                if (menuItem != null) {
//                    menuItem.setChecked(false);
//                } else {
//                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
//                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home, menu);
        menu.setGroupVisible(R.id.add_menu, false);
        menu.setGroupVisible(R.id.mine_menu, false);
    }
}
