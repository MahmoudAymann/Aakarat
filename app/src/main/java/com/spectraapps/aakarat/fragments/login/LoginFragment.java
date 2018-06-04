package com.spectraapps.aakarat.fragments.login;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.spectraapps.aakarat.LoginActivity;
import com.spectraapps.aakarat.R;
import com.spectraapps.aakarat.helper.BaseBackPressedListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.loginBtn)
    Button loginBtn;
    @BindView(R.id.signinBtn)
    Button signInBtn;
    @BindView(R.id.signupBtn)
    Button signupBtn;

    @BindView(R.id.emailET)
    EditText emailET;
    @BindView(R.id.passwordET)
    EditText passwordET;
    @BindView(R.id.btn_pass_visibility)
    ImageButton btn_pass_visibility;
    private boolean isPasswordShown;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this,rootView);
        initUI(rootView);

        return rootView;
    }

    private void initUI(View rootView) {
        fixPasswordETLanguageDirection();
    }

    private void fixPasswordETLanguageDirection() {
        passwordET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    if (passwordET.length() > 0 || passwordET.length() == 0) {
                        passwordET.setInputType(InputType.TYPE_CLASS_TEXT |
                                InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    }
                } else {
                    if (passwordET.length() > 0)
                        passwordET.setInputType(InputType.TYPE_CLASS_TEXT |
                                InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    else {
                        passwordET.setInputType(InputType.TYPE_CLASS_TEXT |
                                InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                    }
                }
            }
        });
    }

    @OnClick(R.id.btn_pass_visibility)
    public void onBtnPassVisibleClick() {
        if (isPasswordShown) {
            btn_pass_visibility.setImageResource(R.drawable.ic_visibility_accent_24dp);
            passwordET.setInputType(InputType.TYPE_CLASS_TEXT |
                    InputType.TYPE_TEXT_VARIATION_PASSWORD);
            isPasswordShown = false;
        } else {
            btn_pass_visibility.setImageResource(R.drawable.ic_visibility_off_accent_24dp);
            passwordET.setInputType(InputType.TYPE_CLASS_TEXT);
            isPasswordShown = true;
        }
    }
    @OnClick(R.id.loginBtn)
    protected void onLoginClick(){
        Intent myIntent = new Intent();
        myIntent.setClassName("com.spectraapps.aakarat", "com.spectraapps.aakarat.MainActivity");
        startActivity(myIntent);
        getActivity().finish();
    }

    @OnClick(R.id.signinBtn)
    protected void onSigninClick(){
        //nothing
    }

    @OnClick(R.id.signupBtn)
    protected void onSignupClick(){
        getFragmentManager().beginTransaction().replace(R.id.login_frame,new RegisterFragment()).commit();
    }

    private void fireBackButtonEvent() {
        ((LoginActivity) getActivity()).setOnBackPressedListener(new BaseBackPressedListener(getActivity()) {
            @Override
            public void onBackPressed() {
                getActivity().finish();
            }
        });
    }//end back pressed

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
       fireBackButtonEvent();
    }

}