package com.spectraapps.aakarat.fragments.main;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.BubbleThumbRangeSeekbar;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.spectraapps.aakarat.MainActivity;
import com.spectraapps.aakarat.R;
import com.spectraapps.aakarat.adapter.spinners.CountriesAdapter;
import com.spectraapps.aakarat.helper.BaseBackPressedListener;
import com.spectraapps.aakarat.interfaces.MainViewsCallBack;
import com.spectraapps.aakarat.model.spinners.CountriesModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestFragment extends Fragment {

    @BindView(R.id.rangeSeekbar)
    CrystalRangeSeekbar rangeSeekbar;
    @BindView(R.id.textMin)
    TextView minTV;
    @BindView(R.id.textMax)
    TextView maxTV;
    @BindView(R.id.city_spinner)
    Spinner city_spinner;
    @BindView(R.id.distinct_spinner)
    Spinner distinct_spinner;
    @BindView(R.id.type_spinner)
    Spinner type_spinner;
    @BindView(R.id.dept_spinner)
    Spinner dept_spinner;
    @BindView(R.id.bedroom_spinner)
    Spinner bedroom_spinner;
    @BindView(R.id.bathroom_spinner)
    Spinner bathroom_spinner;
    @BindView(R.id.garage_spinner)
    Spinner garage_spinner;

    private MainViewsCallBack mMainViewsCallBack;

    public RequestFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_request, container, false);
        ButterKnife.bind(this, rootView);
        initUI(rootView);
        return rootView;
    }

    private void initUI(View rootView) {
        initRangeBar();
        initCitySpinner();
        initTypeSpinner();
        initDeptSpinner();
        initDistinctSpinner();
        initGarageSpinner();
        initBedRoomSpinner();
        initBathroomSpinner();

    }

    private void initBathroomSpinner() {
        String[] typeStringNames = getResources().getStringArray(R.array.bathroom);
        ArrayList<CountriesModel> countriesModelsList = new ArrayList<>();
        for (String countries : typeStringNames) {
            CountriesModel item = new CountriesModel(countries);
            countriesModelsList.add(item);
        }
        CountriesAdapter adapter = new CountriesAdapter(getActivity().getApplicationContext(),
                R.layout.single_item_spinner_countries, R.id.textView, countriesModelsList);
        bathroom_spinner.setAdapter(adapter);
    }

    private void initBedRoomSpinner() {
        String[] typeStringNames = getResources().getStringArray(R.array.bedroom);
        ArrayList<CountriesModel> countriesModelsList = new ArrayList<>();
        for (String countries : typeStringNames) {
            CountriesModel item = new CountriesModel(countries);
            countriesModelsList.add(item);
        }
        CountriesAdapter adapter = new CountriesAdapter(getActivity().getApplicationContext(),
                R.layout.single_item_spinner_countries, R.id.textView, countriesModelsList);
        bedroom_spinner.setAdapter(adapter);
    }

    private void initGarageSpinner() {
        String[] typeStringNames = getResources().getStringArray(R.array.garage);
        ArrayList<CountriesModel> countriesModelsList = new ArrayList<>();
        for (String countries : typeStringNames) {
            CountriesModel item = new CountriesModel(countries);
            countriesModelsList.add(item);
        }
        CountriesAdapter adapter = new CountriesAdapter(getActivity().getApplicationContext(),
                R.layout.single_item_spinner_countries, R.id.textView, countriesModelsList);
        garage_spinner.setAdapter(adapter);
    }

    private void initRangeBar() {
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                minTV.setText(String.valueOf(minValue));
                maxTV.setText(String.valueOf(maxValue));
            }
        });
    }

    private void initDeptSpinner() {
        String[] typeStringNames = getResources().getStringArray(R.array.dept);
        ArrayList<CountriesModel> countriesModelsList = new ArrayList<>();
        for (String countries : typeStringNames) {
            CountriesModel item = new CountriesModel(countries);
            countriesModelsList.add(item);
        }
        CountriesAdapter adapter = new CountriesAdapter(getActivity().getApplicationContext(),
                R.layout.single_item_spinner_countries, R.id.textView, countriesModelsList);
        dept_spinner.setAdapter(adapter);
    }

    private void initDistinctSpinner() {
        String[] typeStringNames = getResources().getStringArray(R.array.distinct);
        ArrayList<CountriesModel> countriesModelsList = new ArrayList<>();
        for (String countries : typeStringNames) {
            CountriesModel item = new CountriesModel(countries);
            countriesModelsList.add(item);
        }
        CountriesAdapter adapter = new CountriesAdapter(getActivity().getApplicationContext(),
                R.layout.single_item_spinner_countries, R.id.textView, countriesModelsList);
        dept_spinner.setAdapter(adapter);
    }

    private void initTypeSpinner() {
        String[] typeStringNames = getResources().getStringArray(R.array.type);
        ArrayList<CountriesModel> countriesModelsList = new ArrayList<>();
        for (String countries : typeStringNames) {
            CountriesModel item = new CountriesModel(countries);
            countriesModelsList.add(item);
        }
        CountriesAdapter adapter = new CountriesAdapter(getActivity().getApplicationContext(),
                R.layout.single_item_spinner_countries, R.id.textView, countriesModelsList);
        type_spinner.setAdapter(adapter);
    }

    private void initCitySpinner() {
        String[] countryStringNames = getResources().getStringArray(R.array.countries);
        ArrayList<CountriesModel> countriesModelsList = new ArrayList<>();
        for (String countries : countryStringNames) {
            CountriesModel item = new CountriesModel(countries);
            countriesModelsList.add(item);
        }
        CountriesAdapter adapter = new CountriesAdapter(getActivity().getApplicationContext(),
                R.layout.single_item_spinner_countries, R.id.textView, countriesModelsList);
        city_spinner.setAdapter(adapter);
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
        mMainViewsCallBack.toolbarTitle(getString(R.string.request_a_building));
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
