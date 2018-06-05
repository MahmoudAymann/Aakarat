package com.spectraapps.aakarat.fragments.main;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spectraapps.aakarat.MainActivity;
import com.spectraapps.aakarat.R;
import com.spectraapps.aakarat.helper.BaseBackPressedListener;
import com.spectraapps.aakarat.interfaces.MainViewsCallBack;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    private MainViewsCallBack mMainViewsCallBack;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this,rootView);
        initUI(rootView);
        return rootView;
    }

    private void initUI(View rootView) {

    }
    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        try {
            mMainViewsCallBack = (MainViewsCallBack) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "error");
        }
        fireBackButtonEvent();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMainViewsCallBack.fabVisibility(false);
        mMainViewsCallBack.toolbarFilterBtn(false);
        mMainViewsCallBack.toolbarTitle(getString(R.string.profile));
    }

    private void fireBackButtonEvent() {
        try {
            ((MainActivity) getActivity()).setOnBackPressedListener(new BaseBackPressedListener(getActivity()) {
                @Override
                public void onBackPressed() {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_frameLayout,new HomeFragment())
                            .commit();
                }
            });
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }//end back pressed

    @Override
    public void onDetach() {
        super.onDetach();
        mMainViewsCallBack = null;
    }
}
