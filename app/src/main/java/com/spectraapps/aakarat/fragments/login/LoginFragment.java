package com.spectraapps.aakarat.fragments.login;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

    }

    @OnClick(R.id.loginBtn)
    protected void onLoginClick(){
//        getFragmentManager().beginTransaction().replace(R.id.login_frame,new HomeFragment()).commit();
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
//                assert getFragmentManager() != null;
//                getFragmentManager().beginTransaction()
//                        .replace(R.id.login_frame, new RegSelectorFragment())
//                        .commit();
            }
        });
    }//end back pressed

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
       fireBackButtonEvent();
    }

}