package com.example.barakatravelapp.view.fragment.HomeCycle2.discover;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.view.fragment.BaSeFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.barakatravelapp.utils.HelperMethod.replaceFragment;


public class ConfirmBookingFragment extends BaSeFragment {

    public ConfirmBookingFragment() {
        // Required empty public constructor
    }
    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_confirm_booking, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);

        return root;
    }

    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new LuxuryUmrahPackageFragment());
//        navController.navigate(R.id.action_confirmBookingFragment_to_luxuryUmrahPackageFragment);
    }

    @OnClick(R.id.fragment_confirm_booking_confirm_pay_btn)
    public void onViewClicked() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new SelectPaymentMethodFragment());
        try {
//            navController.navigate(R.id.action_confirmBookingFragment_to_selectPaymentMethodFragment);
        }
        catch(Exception e) {
            //  Block of code to handle errors
        }

    }
}