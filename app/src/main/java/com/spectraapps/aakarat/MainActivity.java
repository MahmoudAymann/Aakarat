package com.spectraapps.aakarat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import com.spectraapps.aakarat.fragments.main.AddProductFragment;
import com.spectraapps.aakarat.fragments.main.FavouritesFragment;
import com.spectraapps.aakarat.fragments.main.HomeFragment;
import com.spectraapps.aakarat.fragments.main.ProductsFragment;
import com.spectraapps.aakarat.fragments.main.RequestFragment;
import com.spectraapps.aakarat.interfaces.IOnBackPressed;
import com.spectraapps.aakarat.interfaces.MainViewsCallBack;
import com.spectraapps.aakarat.util.ListSharedPreference;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,MainViewsCallBack {

    Toolbar toolbar;
    private Locale locale;
    ListSharedPreference.Set setSharedPreference;
    ListSharedPreference.Get getSharedPreference;
    protected IOnBackPressed onBackPressedListener;
    private boolean mIsLogged;
    private AlertDialog.Builder alertDialogBuilder;
    TextView toolbarTitle;
    ImageButton filterBtn;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSharedPreference = new ListSharedPreference.Set(MainActivity.this.getApplicationContext());
        getSharedPreference = new ListSharedPreference.Get(MainActivity.this.getApplicationContext());

        setLayoutLanguage();
        initNavDrawer();
        initUI();
        getFragmentManager().beginTransaction().replace(R.id.main_frameLayout,new HomeFragment()).commit();
    }

    private void initUI() {
        toolbarTitle = findViewById(R.id.toolbar_title);
        filterBtn = findViewById(R.id.toolbar_filter_button);
        fab = findViewById(R.id.main_fab);
    }

    private void initNavDrawer() {
        toolbar = findViewById(R.id.toolbar);
        DrawerLayout mDrawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (onBackPressedListener != null)
                onBackPressedListener.onBackPressed();
            else
                super.onBackPressed();
        }

    }
    private void setLayoutLanguage() {
        if (getLang().equals("en")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            locale = new Locale("en");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            this.getApplicationContext().getResources().updateConfiguration(config, null);
        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            locale = new Locale("ar");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            this.getApplicationContext().getResources().updateConfiguration(config, null);
        }

        this.setContentView(R.layout.activity_main);
        NavigationView navigationView = this.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(MainActivity.this);
    }

    public void setOnBackPressedListener(IOnBackPressed onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Checks the locale has changed
        if (!locale.equals(newConfig.locale)) {
            locale = newConfig.locale;

            this.setContentView(R.layout.activity_main);
            NavigationView navigationView = this.findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(MainActivity.this);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_myfavourite) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.main_frameLayout,new FavouritesFragment())
                    .commit();
        } else if (id == R.id.nav_request) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.main_frameLayout,new RequestFragment())
                    .commit();
        } else if (id == R.id.nav_offer) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.main_frameLayout,new AddProductFragment())
                    .commit();
        } else if (id == R.id.language_nav) {
            setAlertDialog(1);
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        } else if (id == R.id.nav_whoUs) {

        }else if (id == R.id.nav_about) {

        }else if (id == R.id.nav_privacy_policy) {

        }else if (id == R.id.logout_nav) {
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void setAlertDialog(int i) {
        switch (i) {
            case 1:
                alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage(getString(R.string.change_language_prompt));

                alertDialogBuilder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        if (getSharedPreference.getLanguage().equals("en")) {
                            setSharedPreference.setLanguage("ar");
                            restartActivity(MainActivity.this);
                        } else {
                            setSharedPreference.setLanguage("en");
                            restartActivity(MainActivity.this);
                        }
                    }
                }).setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                break;
            default:
                break;
        }
    }

    private void restartActivity(Activity activity) {
            activity.recreate();
    }

    private String getLang() {
        return getSharedPreference.getLanguage();
    }

    @Override
    public void fabVisibility(boolean b) {
        if (b) {
            fab.setVisibility(View.VISIBLE);
        } else {
            fab.setVisibility(View.GONE);
        }
    }

    @Override
    public void toolbarFilterBtn(boolean b) {
        if (b) {
            filterBtn.setVisibility(View.VISIBLE);
        } else {
            filterBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void toolbarTitle(String string) {
      toolbarTitle.setText(string);
    }
}
