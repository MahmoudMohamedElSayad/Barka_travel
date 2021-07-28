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


public class SelectPaymentMethodFragment extends BaSeFragment {

    private NavController navController;

    public SelectPaymentMethodFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_select_payment_method, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);

        return root;
    }

    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new ConfirmBookingFragment());
//        navController.navigate(R.id.action_selectPaymentMethodFragment_to_confirmBookingFragment);

    }

    @OnClick(R.id.fragment_select_payment_method_pay_btn)
    public void onViewClicked() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new SuccessfulPaymentFragment());
//        navController.navigate(R.id.action_selectPaymentMethodFragment_to_successfulPaymentFragment);

    }
}