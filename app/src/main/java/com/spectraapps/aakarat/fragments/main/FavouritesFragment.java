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
import com.spectraapps.aakarat.adapter.FavouriteAdapter;
import com.spectraapps.aakarat.helper.BaseBackPressedListener;
import com.spectraapps.aakarat.interfaces.MainViewsCallBack;
import com.spectraapps.aakarat.model.FavModel;
import com.spectraapps.aakarat.model.HomeModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouritesFragment extends Fragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private ArrayList<FavModel> mDataList;
    private FavouriteAdapter favAdapter;
    private MainViewsCallBack mMainViewsCallBack;


    public FavouritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_favourites, container, false);
        ButterKnife.bind(this,rootView);
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
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        }
    }

    private void initAdapter() {
        mDataList = new ArrayList<>();
        FavModel th;
        for (int i = 0; i < 6; i++) {
                th = new FavModel("Fav"+i, R.drawable.housepng);
            mDataList.add(i, th);
        }

        favAdapter = new FavouriteAdapter(getActivity().getApplicationContext(), mDataList,
                new FavouriteAdapter.ListAllListeners() {
                    @Override
                    public void onItemViewClick(FavModel favModel, int adapterPosition) {
//                        getFragmentManager().beginTransaction().replace(R.id.main_frameLayout,new ProductsFragment()).commit();
                    }
                });

        recyclerView.setAdapter(favAdapter);
        favAdapter.notifyDataSetChanged();
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
        mMainViewsCallBack.toolbarTitle(getString(R.string.my_favourite));
    }

    private void fireBackButtonEvent() {
        try {
            ((MainActivity) getActivity()).setOnBackPressedListener(new BaseBackPressedListener(getActivity()) {
                @Override
                public void onBackPressed() {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_frameLayout, new HomeFragment())
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
