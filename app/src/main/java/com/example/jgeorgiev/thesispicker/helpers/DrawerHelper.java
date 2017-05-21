package com.example.jgeorgiev.thesispicker.helpers;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.jgeorgiev.thesispicker.R;
import com.example.jgeorgiev.thesispicker.ThesisPickerActivity;
import com.example.jgeorgiev.thesispicker.Utils.Logger;
import com.example.jgeorgiev.thesispicker.fragments.LoginFragment;
import com.example.jgeorgiev.thesispicker.fragments.StudentInfoFragment;


/**
 * Created by jgeorgiev on 5/21/17.
 */

public class DrawerHelper implements NavigationView.OnNavigationItemSelectedListener {

    private final ThesisPickerActivity activity;
    private final DrawerLayout drawer;
    private final Toolbar toolbar;

    public DrawerHelper(final ThesisPickerActivity activity, DrawerLayout drawer, Toolbar toolbar) {
        this.activity = activity;
        this.drawer = drawer;
        this.toolbar = toolbar;

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this.activity, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (activity.getFragmentHelper().isVisibleFragment(LoginFragment.class.getSimpleName()) && activity.getInputManager() != null) {
                    activity.getInputManager().showSoftInput(activity.getCurrentFocus(), InputMethodManager.SHOW_IMPLICIT);
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (activity.getInputManager() != null) {
                    activity.getInputManager().hideSoftInputFromWindow(drawerView.getWindowToken(), 0);
                }
            }
        };
        this.drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    public void initDrawer(NavigationView navigationView) {
        toolbar.setTitle("");
        activity.setSupportActionBar(toolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.student_info:
                if (!activity.getFragmentHelper().isVisibleFragment(StudentInfoFragment.class.getSimpleName())) {
                    activity.getFragmentHelper().addFragment(new StudentInfoFragment(), true);
                }
                break;
            case R.id.teachers:
                if (!activity.getFragmentHelper().isVisibleFragment(StudentInfoFragment.class.getSimpleName())) {
                    activity.getFragmentHelper().addFragment(new StudentInfoFragment(), true);
                }
                break;
            case R.id.theses:
                if (!activity.getFragmentHelper().isVisibleFragment(StudentInfoFragment.class.getSimpleName())) {
                    activity.getFragmentHelper().addFragment(new StudentInfoFragment(), true);
                }
                break;
            default:
                Logger.getInstance().e(getClass().getSimpleName(), "Unknown menu id " + id);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void lockDrawer() {
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    public void unlockDrawer() {
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

}


