package gigigo.com.kmvp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by ajea on 16/05/17.
 */

public class KNavigationManager {

    private FragmentManager fragmentManager;
    private int idContainer;

    public KNavigationManager(@NonNull FragmentManager fragmentManager, @NonNull int idContainer) {
        this.fragmentManager = fragmentManager;
        this.idContainer = idContainer;
    }

    public void replaceFragment(Fragment fragment) {
        fragmentManager.beginTransaction().replace(idContainer, fragment, fragment.getClass().getName()).commit();
    }

    public void addFragment(@NonNull Fragment fragment) {
        if(fragment != null && idContainer > 0) {
            if (!exitsFragment(fragment.getClass().getName())) {
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.add(idContainer, fragment);
                ft.commit();
            }
        }
    }

    public void addFragmentToBackStack(@NonNull Fragment fragment) {
        if(fragment != null && idContainer > 0) {
            if (!exitsFragment(fragment.getClass().getName())) {
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.add(idContainer, fragment);
                ft.addToBackStack(fragment.getClass().getName());
                ft.commit();
            }
        }
    }

    public void addFragmentToBackStackPopIfExist(@NonNull Fragment fragment) {
        if(fragment != null && idContainer > 0) {
            if (!exitsFragmentRemove(fragment.getClass().getName())) {
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.add(idContainer, fragment);
                ft.addToBackStack(fragment.getClass().getName());
                ft.commit();
            }
        }
    }

    public void addFragmentToBackStackNoValidate(@NonNull Fragment fragment) {
        if(fragment != null && idContainer > 0) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(idContainer, fragment);
            ft.addToBackStack(fragment.getClass().getName());
            ft.commit();
        }
    }

    public void replaceFragmentToBackStack(@NonNull Fragment fragment) {
        if(fragment != null && idContainer > 0) {
            if (!exitsFragment(fragment.getClass().getName())) {
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(idContainer, fragment);
                ft.addToBackStack(fragment.getClass().getName());
                ft.commit();
            }
        }
    }

    public void removeFragment(Fragment fragment) {
        if(fragment != null) {
            fragmentManager.beginTransaction().remove(fragment);
            fragmentManager.beginTransaction().commit();
            fragmentManager.popBackStack();
        }
    }

    public void navigateBack(Activity baseActivity) {
        if (fragmentManager.getBackStackEntryCount() == 0) {
            baseActivity.finish();
        } else {
            fragmentManager.popBackStackImmediate();
        }
    }

    public void openAsRoot(Fragment fragment) {
        if(fragment != null && idContainer > 0) {
            popAllFragment();
            replaceFragment(fragment);
        }
    }

    private void popAllFragment() {
        // Clear all back stack.
        int backStackCount = fragmentManager.getBackStackEntryCount();
        for (int i = 0; i < backStackCount; i++) {
            // Get the back stack fragment id.
            int backStackId = fragmentManager.getBackStackEntryAt(i).getId();
            fragmentManager.popBackStack(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } /* end of for */
    }

    public void popFragments(String fragmentName){
        int backStackCount = fragmentManager.getBackStackEntryCount();
        if(backStackCount > 0) {
            for (int i = 1; i < backStackCount; i++) {
                fragmentManager.popBackStackImmediate(fragmentName, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        }
    }

    public Fragment getFragmentByTag(String fragmentTag) {
        if (fragmentManager != null) {
            return fragmentManager.findFragmentByTag(fragmentTag);
        }

        return null;
    }

    public boolean exitsFragment(String name) {

        if(fragmentManager.getFragments() != null){

            for (Fragment fragment : fragmentManager.getFragments()) {
                if (fragment != null && fragment.isAdded() && fragment.getClass().getName().equals(name)) {
                    return true;
                }
            }

        }

        return false;
    }

    public boolean exitsFragmentRemove(String name) {
        if(fragmentManager.getFragments() != null){
            for (Fragment fragment : fragmentManager.getFragments()) {
                if (fragment != null && fragment.isAdded() && fragment.getClass().getName().equals(name)) {
                    fragmentManager.beginTransaction().remove(fragment).commit();
                }
            }
        }
        return false;
    }
}
