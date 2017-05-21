package com.example.jgeorgiev.thesispicker;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import com.example.jgeorgiev.thesispicker.data.DatabaseHelper;
import com.example.jgeorgiev.thesispicker.fragments.LoginFragment;
import com.example.jgeorgiev.thesispicker.helpers.DrawerHelper;
import com.example.jgeorgiev.thesispicker.helpers.FragmentHelper;


public class ThesisPickerActivity extends AppCompatActivity {

    private static SQLiteDatabase database;

    private DrawerLayout drawer;
    private InputMethodManager imm;
    private NavigationView navigationView;
    private Toolbar toolbar;

    private FragmentHelper frgHelper;
    private DrawerHelper drawerHelper;

    public static SQLiteDatabase getDatabase() {
        return database;
    }

    public static void setDatabase(SQLiteDatabase database) {
        ThesisPickerActivity.database = database;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thesis_picker);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();

        setup();

        frgHelper.addFragment(new LoginFragment());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        release();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerIconClicked();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void drawerIconClicked() {
        if (getFragmentManager().getBackStackEntryCount() > 1) { // if it is back
            onBackPressed();
        } else if (!drawer.isDrawerOpen(GravityCompat.START)) { // if it is hamburger
            drawer.openDrawer(GravityCompat.START);
        }
    }

    public void checkDrawerItemAtPosition(final int position) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                navigationView.getMenu().getItem(position).setChecked(true);
            }
        });
    }

    public void setDrawerItemCheckable(int position, boolean isCheckable) {
        navigationView.getMenu().getItem(position).setCheckable(isCheckable);
    }

    public void createMaterialToolbar(boolean isHome, String title) {
        toolbar.setTitle(title);
        toolbar.setNavigationIcon(isHome ? R.drawable.icon_hamburger_menu : R.drawable.icon_arrow_back);
    }

    private void setup() {
        setDatabase(new DatabaseHelper(this).getWritableDatabase());
        drawerHelper = new DrawerHelper(this, drawer, toolbar);
        drawerHelper.initDrawer(navigationView);
        frgHelper = new FragmentHelper(this);
        getFragmentManager().addOnBackStackChangedListener(frgHelper);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    private void release() {
        if (getDatabase() != null) {
            getDatabase().close();
        }
        setDatabase(null);

        if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
    }

    public FragmentHelper getFragmentHelper() {
        return frgHelper;
    }

    public InputMethodManager getInputManager() {
        return imm;
    }

    public DrawerHelper getDrawerHelper() {
        return drawerHelper;
    }

    public void setSessionExpired() {
        frgHelper.removeAllFragments();
        frgHelper.addFragment(new LoginFragment());
    }
}

