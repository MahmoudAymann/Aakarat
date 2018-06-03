package com.spectraapps.aakarat.fragments.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spectraapps.aakarat.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CodeVerifyFragment extends Fragment {


    public CodeVerifyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_code_verify, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
