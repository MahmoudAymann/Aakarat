package com.spectraapps.aakarat;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.spectraapps.aakarat.fragments.login.LoginFragment;
import com.spectraapps.aakarat.interfaces.IOnBackPressed;
import com.spectraapps.aakarat.util.ListSharedPreference;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

    private ListSharedPreference.Set setSharedPreference;
    private ListSharedPreference.Get getSharedPreference;
    Locale locale;
    protected IOnBackPressed onBackPressedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setSharedPreference = new ListSharedPreference.Set(LoginActivity.this.getApplicationContext());
        getSharedPreference = new ListSharedPreference.Get(LoginActivity.this.getApplicationContext());

        setLayoutLanguage();

        assert getFragmentManager() != null;
        getFragmentManager().beginTransaction()
                .replace(R.id.login_frame, new LoginFragment())
                .commit();
    }
    public void setOnBackPressedListener(IOnBackPressed onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }
    private void setLayoutLanguage() {
        String langStr = getSharedPreference.getLanguage();
        if (langStr.equals("en")) {
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
    }

    @Override
    public void onBackPressed() {
        if (onBackPressedListener != null)
            onBackPressedListener.onBackPressed();
        else
            super.onBackPressed();
    }



}
