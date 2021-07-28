package com.example.barakatravelapp.view.fragment.HomeCycle2.accounts;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class PolicyFragment extends BaSeFragment {

    @BindView(R.id.fragment_policy_and_conditions_terms_tv)
    TextView fragmentPolicyAndConditionsTermsTv;
    private NavController navController;
    private AppSetting appSetting;

    public PolicyFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (this.getArguments() != null) {
            appSetting = (AppSetting) this.getArguments().getSerializable("ObjectAppSettings");
        }
        View root = inflater.inflate(R.layout.fragment_policy_and_conditions, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        fragmentPolicyAndConditionsTermsTv.setText(Html.fromHtml(appSetting.getPolicyTerms()));

        return root;
    }

    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new ConfirmBookingFragment());
        navController.navigate(R.id.action_policyFragment_to_changeDetailsMoreFragment);

    }


    @OnClick(R.id.fragment_policy_and_conditions_back_img)
    public void onViewClicked() {
        onBack();
    }
}