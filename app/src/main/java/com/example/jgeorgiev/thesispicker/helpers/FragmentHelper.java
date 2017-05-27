package com.example.jgeorgiev.thesispicker.helpers;


import android.app.Fragment;
import android.app.FragmentManager;

import com.example.jgeorgiev.thesispicker.R;
import com.example.jgeorgiev.thesispicker.ThesisPickerActivity;
import com.example.jgeorgiev.thesispicker.interfaces.Stackable;

/**
 * Fragment for student info
 * Created by ygeorgiev on 21-May-17.
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

    public void addFragment(Fragment fragment, boolean removeAllFragments) {
        if (removeAllFragments) {
            removeAllFragments();
        }
        addFragment(fragment);
    }

    private void removeAllFragments() {
        if (activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        activity.getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
