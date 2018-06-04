package com.spectraapps.aakarat.fragments.login;


import android.app.Activity;
import android.app.Fragment;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.spectraapps.aakarat.LoginActivity;
import com.spectraapps.aakarat.R;
import com.spectraapps.aakarat.adapter.PhoneAdapter;
import com.spectraapps.aakarat.helper.BaseBackPressedListener;
import com.spectraapps.aakarat.model.spinners.PhoneSpinnerModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.signupBtn)
    Button signUpBtn;
    @BindView(R.id.signinBtn)
    Button signInBtn;
    @BindView(R.id.registerBtn)
    Button registerBtn;
    @BindView(R.id.phoneCountry_spinner)
    Spinner spinner;
    List<PhoneSpinnerModel> rowItems;
    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =inflater.inflate(R.layout.fragment_register, container, false);
        unbinder = ButterKnife.bind(this,rootView);
        initUI(rootView);
        return rootView;
    }

    private void initUI(View rootView) {
        fillPhoneSpinner(rootView);
    }

    private void fillPhoneSpinner(View rootView) {
        TypedArray img = getResources().obtainTypedArray(R.array.countries_images);
        String[] titles = getResources().getStringArray(R.array.countries_phone_code);

        rowItems = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            PhoneSpinnerModel item = new PhoneSpinnerModel(titles[i], img.getResourceId(i, 0));
            rowItems.add(item);
        }
        spinner = rootView.findViewById(R.id.phoneCountry_spinner);
        //spinner.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        PhoneAdapter adapter = new PhoneAdapter(getActivity().getApplicationContext(),
                R.layout.single_item_spinner_phone_countries, R.id.textView, rowItems);
        spinner.setAdapter(adapter);
        img.recycle();
    }

    @OnClick(R.id.signupBtn)
    protected void onLoginClick(){
//        getFragmentManager().beginTransaction().replace(R.id.login_frame,new HomeFragment()).commit();
    }

    @OnClick(R.id.signinBtn)
    protected void onSigninClick(){
        getFragmentManager().beginTransaction()
                .replace(R.id.login_frame,new LoginFragment()).commit();
    }

    @OnClick(R.id.registerBtn)
    protected void onRegisterClick(){
        getFragmentManager().beginTransaction()
                .replace(R.id.login_frame,new RegisterFragment()).commit();
    }

    private void fireBackButtonEvent() {
        ((LoginActivity) getActivity()).setOnBackPressedListener(new BaseBackPressedListener(getActivity()) {
            @Override
            public void onBackPressed() {
                getFragmentManager().beginTransaction()
                        .replace(R.id.login_frame,new LoginFragment()).commit();
            }
        });
    }//end back pressed

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        fireBackButtonEvent();
    }
}
