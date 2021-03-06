package com.spectraapps.aakarat.fragments.main;


import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spectraapps.aakarat.MainActivity;
import com.spectraapps.aakarat.R;
import com.spectraapps.aakarat.adapter.HomeAdapter;
import com.spectraapps.aakarat.helper.BaseBackPressedListener;
import com.spectraapps.aakarat.interfaces.MainViewsCallBack;
import com.spectraapps.aakarat.model.HomeModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HomeFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    HomeAdapter homeAdapter;
    ArrayList<HomeModel> mDataList;
    Unbinder unbinder;
    private MainViewsCallBack mMainViewsCallBack;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this,rootView);
        initUI(rootView);

        return rootView;
    }

    private void initUI(View rootView) {
        initRecyclerView();
        initAdapter();
    }
    private void initRecyclerView() {
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        }
    }

    private void initAdapter() {
        mDataList = new ArrayList<>() ;
       // Integer[] images = {R.drawable.place_holder};
        String[] names = {getString(R.string.apartment), getString(R.string.villa), getString(R.string.house),
                getString(R.string.resturant), getString(R.string.warehouse), getString(R.string.farms)};
        for (int i = 0; i < names.length; i++) {
            HomeModel th = new HomeModel(names[i]);
            mDataList.add(i, th);
        }

        homeAdapter = new HomeAdapter(getActivity().getApplicationContext(), mDataList,
                new HomeAdapter.ListAllListeners() {
                    @Override
                    public void onItemViewClick(HomeModel homeModel, int adapterPosition) {
                                getFragmentManager().beginTransaction()
                                        .replace(R.id.main_frameLayout,new ProductsFragment()).commit();
                    }

                });
        recyclerView.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();
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
        mMainViewsCallBack.toolbarTitle(getString(R.string.home));
    }

    private void fireBackButtonEvent() {
        try {
            ((MainActivity) getActivity()).setOnBackPressedListener(new BaseBackPressedListener(getActivity()) {
                @Override
                public void onBackPressed() {
                    getActivity().finish();
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