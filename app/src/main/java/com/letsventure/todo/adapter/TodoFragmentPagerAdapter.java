package com.letsventure.todo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.letsventure.todo.fragments.TasksFragment;

import java.util.HashMap;

/**
 * Created by Alok Omkar on 2016-11-26.
 */

public class TodoFragmentPagerAdapter extends FragmentPagerAdapter {

    private HashMap<Integer, TasksFragment> tasksFragmentHashMap;
    private String tabTitles[] = new String[] { "Today", "Later" };
    public TodoFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        tasksFragmentHashMap = new HashMap<>();
    }

    @Override
    public Fragment getItem(int position) {
        return getFragmentForPosition(position);
    }

    private Fragment getFragmentForPosition(int position) {
        TasksFragment tasksFragment = tasksFragmentHashMap.get(position);
        if( tasksFragment == null ) {
            tasksFragment = new TasksFragment();
            tasksFragment.setPosition(position);
            tasksFragmentHashMap.put(position, tasksFragment);
        }
        return tasksFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

}
