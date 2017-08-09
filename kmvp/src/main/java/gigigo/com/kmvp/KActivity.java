package gigigo.com.kmvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Defines the Activity with base functionality, must be inherited from {@link AppCompatActivity}
 *
 * @author Juan Godínez Vera - 15/05/2017
 * @author Alan Espinosa - 16/05/2017
 * @version 2.0.0
 * @since 2.0.0
 */
public abstract class KActivity
        extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentManager.OnBackStackChangedListener {

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private KNavigationManager mNavigationManager;
    private int idFragmentContainer = 0;
    private InputMethodManager inputMethodManager;
    private NavigationView mNavigationView;

    @LayoutRes
    protected abstract int getLayoutResourceId();

    protected abstract void onInitialize();

    protected abstract void onBindView();

    protected abstract void onUnbindView();

    protected abstract void initToolbar();

    protected abstract int getFragmentContainerId();

    //region Handling the Activity Lifecycle

    // -------------------------------------------------------
    // ----------------------- Created -----------------------
    // -------------------------------------------------------
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        setIdFragmentContainer(getFragmentContainerId());

        onBindView();
        onInitialize();

        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        getSupportFragmentManager().addOnBackStackChangedListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onUnbindView();
    }

    //endregion

    //Onbackpressed method from back listener
    @Override
    public void onBackPressed() {
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here
        return true;
    }

    @Override
    public void onBackStackChanged() {
        if (getSupportActionBar() != null) {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            } else {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                toggle.syncState();
            }
        }
    }

    public void closeDrawer() {
        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    public void hidekeyboard() {
        if (toolbar != null)
            inputMethodManager.hideSoftInputFromWindow(toolbar.getWindowToken(), 0);
    }

    public void initDrawerToggle(DrawerLayout drawerLayout, NavigationView navigationView) {
        mNavigationView = navigationView;
        mNavigationView.setNavigationItemSelectedListener(this);

        drawer = drawerLayout;
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStack();
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
    }

    /**
     * Navigation listener methods
     **/

    private KNavigationFragmentListener navigationFragmentListener = new KNavigationFragmentListener() {
        @Override
        public void addFragmentBackstack(Fragment fragment) {
            mNavigationManager.addFragmentToBackStack(fragment);
        }

        @Override
        public void replaceFragment(Fragment fragment) {
            mNavigationManager.replaceFragment(fragment);
        }

        @Override
        public void showActivity(Class type) {
            Intent mActivityIntent = new Intent(KActivity.this, type);
            startActivity(mActivityIntent);
        }

        @Override
        public void showActivity(Class type, Bundle args) {
            Intent activityIntent = new Intent(KActivity.this, type);
            activityIntent.putExtras(args);
            startActivity(activityIntent);
        }
    };

    /**
     * Getter and Setter Methods
     **/

    public KNavigationManager getNavigationManager() {
        return mNavigationManager;
    }

    public void setNavigationManager(KNavigationManager navigationManager) {
        this.mNavigationManager = navigationManager;
    }

    public int getIdFragmentContainer() {
        return idFragmentContainer;
    }

    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
        setSupportActionBar(this.toolbar);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setIdFragmentContainer(@IdRes int idFragmentContainer) {
        this.idFragmentContainer = idFragmentContainer;
    }

    public DrawerLayout getDrawer() {
        return drawer;
    }

    public void setDrawer(DrawerLayout drawer) {
        this.drawer = drawer;
    }

    public ActionBarDrawerToggle getToggle() {
        return toggle;
    }

    public void setToggle(ActionBarDrawerToggle toggle) {
        this.toggle = toggle;
    }

    public void setNavigationView(NavigationView navigationView) {
        this.mNavigationView = navigationView;
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    public KNavigationFragmentListener getNavigationFragmentListener() {
        return navigationFragmentListener;
    }
}
