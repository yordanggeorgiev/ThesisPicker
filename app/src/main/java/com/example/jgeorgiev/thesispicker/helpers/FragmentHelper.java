package com.example.jgeorgiev.thesispicker.helpers;


import android.app.Fragment;
import android.app.FragmentManager;

import com.example.jgeorgiev.thesispicker.Interfaces.Stackable;
import com.example.jgeorgiev.thesispicker.R;
import com.example.jgeorgiev.thesispicker.ThesisPickerActivity;

/**
 * Created by jgeorgiev on 5/21/17.
 */

public class FragmentHelper implements FragmentManager.OnBackStackChangedListener {

    private final ThesisPickerActivity activity;

    public FragmentHelper(ThesisPickerActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onBackStackChanged() {
        int count = activity.getFragmentManager().getBackStackEntryCount();
        if (count > 0) {
            // get last
            FragmentManager.BackStackEntry entry = activity.getFragmentManager().getBackStackEntryAt(count - 1);
            Fragment f = activity.getFragmentManager().findFragmentByTag(entry.getName());
            if (f != null) {
                ((Stackable) f).setupActionBar();
                ((Stackable) f).setupInitialState();
            }
        }
    }

    boolean isVisibleFragment(String fragmentTag) {
        Fragment f = activity.getFragmentManager().findFragmentByTag(fragmentTag);
        return f != null && f.isVisible();
    }

    public void addFragment(Fragment fragment) {
        activity.getFragmentManager().beginTransaction().add(
                R.id.fragment_container, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName()).commit();
    }

    void addFragment(Fragment fragment, boolean removeAllFragments) {
        if (removeAllFragments) {
            removeAllFragments();
        }
        addFragment(fragment);
    }

    public void removeLastFragment() {
        if (activity.isFinishing() || activity.isDestroyed()) {
            return; // Do nothing if activity is destroyed
        }
        activity.getFragmentManager().popBackStack();
    }

    public void removeAllFragments() {
        if (activity.isFinishing() || activity.isDestroyed()) {
            return; // Do nothing if activity is destroyed
        }
        activity.getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void goToFragment(String tag) {
        if (activity.isFinishing() || activity.isDestroyed()) {
            return; // Do nothing if activity is destroyed
        }
        activity.getFragmentManager().popBackStackImmediate(tag, 0);
    }

    public void goToFragmentSlow(String tag) {
        if (activity.isFinishing() || activity.isDestroyed()) {
            return; // Do nothing if activity is destroyed
        }
        activity.getFragmentManager().popBackStack(tag, 0);
    }

    public Fragment getFragmentByTag(String tag) {
        return activity.getFragmentManager().findFragmentByTag(tag);
    }
}
