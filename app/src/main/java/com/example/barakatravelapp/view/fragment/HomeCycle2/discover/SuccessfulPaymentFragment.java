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
import com.example.barakatravelapp.view.fragment.HomeCycle2.hotels.HottelViewFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.barakatravelapp.utils.HelperMethod.replaceFragment;


public class SuccessfulPaymentFragment extends BaSeFragment {

    private NavController navController;

    public SuccessfulPaymentFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_successful_payment, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);

        return root;
    }

    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new SelectPaymentMethodFragment());
//        navController.navigate(R.id.action_successfulPaymentFragment_to_selectPaymentMethodFragment);

    }

    @OnClick({R.id.fragment_successful_payment_view_my_booking_btn, R.id.fragment_successful_payment_return_home_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_successful_payment_view_my_booking_btn:
//                replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new HottelViewFragment());
                try {
//                    Bundle bundle = new Bundle();
//                    bundle.putString("ISSUCCESSFUL", "successfullPayment");
//                    navController.navigate(R.id.action_successfulPaymentFragment_to_hottelViewFragment,bundle);
                    navController.navigate(R.id.action_successfulPaymentFragment_to_navigation_account);
                    homeCycleActivity.setNavigation("v");
                }
                catch(Exception e) {
                    //  Block of code to handle errors
                }
                break;
            case R.id.fragment_successful_payment_return_home_btn:
//                replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new DiscoverFragment());
                navController.navigate(R.id.action_successfulPaymentFragment_to_navigation_discover);
                homeCycleActivity.setNavigation("v");
//                homeCycleActivity.bottomNavView.getMenu().getItem(0).setChecked(true);
                break;
        }
    }
}