package com.spectraapps.aakarat.fragments.main;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.spectraapps.aakarat.MainActivity;
import com.spectraapps.aakarat.R;
import com.spectraapps.aakarat.adapter.ImageSliderAdapter;
import com.spectraapps.aakarat.helper.BaseBackPressedListener;
import com.spectraapps.aakarat.interfaces.MainViewsCallBack;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailFragment extends Fragment {


    @BindView(R.id.viewPager)
    ViewPager mPager;
    @BindView(R.id.indicator)
    CircleIndicator circleIndicator;

    private static int currentPage = 0;

    private ArrayList<Integer> sliderArray = new ArrayList<>();
    private MainViewsCallBack mMainViewsCallBack;

    public ProductDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_product_detail, container, false);
        ButterKnife.bind(this,rootView);
        initImageSlider();
        return rootView;
    }

    private void initImageSlider() {
        sliderArray = new ArrayList<>();
        sliderArray.add(R.drawable.googleads);
        sliderArray.add(R.drawable.place_holder);
        sliderArray.add(R.drawable.googleads);
        mPager.setAdapter(new ImageSliderAdapter(getActivity(), sliderArray, new ImageSliderAdapter.ListAllListeners() {
            @Override
            public void onItemViewClick(ArrayList<Integer> images, int position) {
                Toast.makeText(getActivity(), ""+position, Toast.LENGTH_SHORT).show();
            }
        }));
        circleIndicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == sliderArray.size()) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2000, 2500);

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
        mMainViewsCallBack.toolbarTitle(getString(R.string.product_detail));
    }

    private void fireBackButtonEvent() {
        try {
            ((MainActivity) getActivity()).setOnBackPressedListener(new BaseBackPressedListener(getActivity()) {
                @Override
                public void onBackPressed() {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_frameLayout,new ProductsFragment()).commit();
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
