package com.spectraapps.aakarat.fragments.main;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spectraapps.aakarat.R;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddProductFragment extends Fragment {


    public AddProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_product, container, false);
        ButterKnife.bind(this,rootView);
        return rootView;
    }

}
