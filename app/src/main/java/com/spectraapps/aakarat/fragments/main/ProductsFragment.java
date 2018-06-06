package com.spectraapps.aakarat.fragments.main;


import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.michael.easydialog.EasyDialog;
import com.spectraapps.aakarat.MainActivity;
import com.spectraapps.aakarat.R;
import com.spectraapps.aakarat.adapter.HomeAdapter;
import com.spectraapps.aakarat.adapter.ProductsAdapter;
import com.spectraapps.aakarat.helper.BaseBackPressedListener;
import com.spectraapps.aakarat.interfaces.MainViewsCallBack;
import com.spectraapps.aakarat.model.ProductsModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ProductsFragment extends Fragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    ProductsAdapter productsAdapter;
    ArrayList<ProductsModel> mDataList;
    private MainViewsCallBack mMainViewsCallBack;
    CrystalRangeSeekbar rangeSeekbar;
    TextView minTV,maxTV,minTV2,maxTV2;
    private CrystalRangeSeekbar rangeSeekbar2;

    public ProductsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_products, container, false);
        ButterKnife.bind(this, rootView);
        initUI(rootView);

        return rootView;
    }

    private void initUI(View rootView) {
        initRecyclerView();
        initAdapter();
        ImageButton imageButton = getActivity().findViewById(R.id.toolbar_filter_button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp();
            }
        });
    }

    private void initRecyclerView() {
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        }
    }
    private void showPopUp() {
        View popupView = View.inflate(new ContextThemeWrapper(
                getActivity().getApplicationContext(),
                R.style.AppTheme), R.layout.popup_filter_layout,null);
        final EasyDialog easyDialog = new EasyDialog(getActivity())
                .setLayout(popupView)
                .setLocationByAttachedView(getActivity().findViewById(R.id.toolbar_filter_button))
                .setBackgroundColor(getResources().getColor(R.color.grey_static))
                .setGravity(EasyDialog.GRAVITY_BOTTOM)
                .setAnimationTranslationShow(EasyDialog.DIRECTION_X, 600, -600, 100, -50, 50, 0)
                .setAnimationAlphaShow(100, 0.3f, 1.0f)
                .setAnimationTranslationDismiss(EasyDialog.DIRECTION_X, 300, -50, 500)
                .setAnimationAlphaDismiss(100, 1.0f, 0.0f)
                .setTouchOutsideDismiss(true)
                .setMatchParent(true)
                .setMarginLeftAndRight(30, 30)
                .show();
        initPopupUI(popupView, easyDialog);
    }
    private void initRangeBar(View popupView) {
        rangeSeekbar = popupView.findViewById(R.id.rangeSeekbar);
        minTV = popupView.findViewById(R.id.textMin);
        maxTV = popupView.findViewById(R.id.textMax);
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                minTV.setText(String.valueOf(minValue));
                maxTV.setText(String.valueOf(maxValue));
            }
        });
    }
    private void initRangeBar2(View popupView) {
        rangeSeekbar2 = popupView.findViewById(R.id.rangeSeekbar2);
        minTV2 = popupView.findViewById(R.id.textMin2);
        maxTV2 = popupView.findViewById(R.id.textMax2);
        rangeSeekbar2.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                minTV2.setText(String.valueOf(minValue));
                maxTV2.setText(String.valueOf(maxValue));
            }
        });
    }
    private void initPopupUI(View popupView, EasyDialog easyDialog) {
        initRangeBar(popupView);
        initRangeBar2(popupView);
    }

    private void initAdapter() {
        mDataList = new ArrayList<>();
        // Integer[] images = {R.drawable.place_holder};
        ProductsModel th;
        for (int i = 0; i < 6; i++) {
            if (i > 3)
                th = new ProductsModel(getString(R.string.for_rent), R.drawable.housepng);
            else
                th = new ProductsModel(getString(R.string.for_sale), R.drawable.housepng);
            mDataList.add(i, th);
        }

        productsAdapter = new ProductsAdapter(getActivity().getApplicationContext(), mDataList,
                new ProductsAdapter.ListAllListeners() {
                    @Override
                    public void onItemViewClick(ProductsModel productsModel, int adapterPosition) {
                        getFragmentManager().beginTransaction()
                                .replace(R.id.main_frameLayout,new ProductDetailFragment()).commit();
                    }
                });

        recyclerView.setAdapter(productsAdapter);
        productsAdapter.notifyDataSetChanged();
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
        mMainViewsCallBack.toolbarFilterBtn(true);
        mMainViewsCallBack.toolbarTitle(getString(R.string.products));
    }

    private void fireBackButtonEvent() {
        try {
            ((MainActivity) getActivity()).setOnBackPressedListener(new BaseBackPressedListener(getActivity()) {
                @Override
                public void onBackPressed() {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_frameLayout,new HomeFragment()).commit();
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
