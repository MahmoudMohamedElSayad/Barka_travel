package com.example.barakatravelapp.view.fragment.HomeCycle2.accounts;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.data.model.appSettingResponce.AppSetting;
import com.example.barakatravelapp.view.fragment.BaSeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AboutUsFragment extends BaSeFragment {

    @BindView(R.id.fragment_about_us_about_barka_agency_tv)
    TextView fragmentAboutUsAboutBarkaAgencyTv;
    @BindView(R.id.fragment_about_us_about_barka_address_tv)
    TextView fragmentAboutUsAboutBarkaAddressTv;
    @BindView(R.id.fragment_about_us_about_barka_phone1_tv)
    TextView fragmentAboutUsAboutBarkaPhone1Tv;
    @BindView(R.id.fragment_about_us_about_barka_phone2_tv)
    TextView fragmentAboutUsAboutBarkaPhone2Tv;
    @BindView(R.id.fragment_about_us_about_barka_phone3_tv)
    TextView fragmentAboutUsAboutBarkaPhone3Tv;
    @BindView(R.id.fragment_about_us_about_barka_email_tv)
    TextView fragmentAboutUsAboutBarkaEmailTv;
    @BindView(R.id.fragment_about_us_wv_mv_map)
    WebView fragmentAboutUsWvMvMap;
    @BindView(R.id.fragment_about_us_about_us_description_tv)
    TextView fragmentAboutUsAboutUsDescriptionTv;
    @BindView(R.id.fragment_about_us_about_about_us_title_tv)
    TextView fragmentAboutUsAboutAboutUsTitleTv;
    private NavController navController;
    private AppSetting appSetting;

    public AboutUsFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (this.getArguments() != null) {
            appSetting = (AppSetting) this.getArguments().getSerializable("ObjectAppSettings");

        }
        View root = inflater.inflate(R.layout.fragment_about_us, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        setData();
        return root;
    }

    private void setData() {
        fragmentAboutUsAboutAboutUsTitleTv.setText(appSetting.getAboutUs());
        fragmentAboutUsAboutBarkaAgencyTv.setText(Html.fromHtml(appSetting.getDescription()));
        fragmentAboutUsAboutBarkaAddressTv.setText(appSetting.getAddress());
        fragmentAboutUsAboutBarkaPhone1Tv.setText(appSetting.getPhone());
        fragmentAboutUsAboutBarkaPhone2Tv.setText(appSetting.getPhone1());
        fragmentAboutUsAboutBarkaPhone3Tv.setText(appSetting.getPhone2());
        fragmentAboutUsAboutBarkaEmailTv.setText(appSetting.getEmailwebsite());
        fragmentAboutUsAboutUsDescriptionTv.setText(Html.fromHtml(appSetting.getAboutUsDescription()));
        String iframe = appSetting.getLocationGoogleMap();
        fragmentAboutUsWvMvMap.canZoomOut();
        fragmentAboutUsWvMvMap.setInitialScale(150);
        fragmentAboutUsWvMvMap.getSettings().setJavaScriptEnabled(true);
        fragmentAboutUsWvMvMap.loadData(iframe, "text/html", "utf-8");

    }

    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new ConfirmBookingFragment());
        navController.navigate(R.id.action_aboutUsFragment_to_changeDetailsMoreFragment);

    }


    @OnClick(R.id.fragment_policy_and_conditions_back_img)
    public void onViewClicked() {
        onBack();
    }
}