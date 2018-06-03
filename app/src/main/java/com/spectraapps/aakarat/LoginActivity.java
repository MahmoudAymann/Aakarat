package com.spectraapps.aakarat;

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

public class LoginActivity extends AppCompatActivity {
    protected IOnBackPressed onBackPressedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getFragmentManager().beginTransaction().replace(R.id.login_frame,new LoginFragment()).commit();
    }
    public void setOnBackPressedListener(IOnBackPressed onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }


}
