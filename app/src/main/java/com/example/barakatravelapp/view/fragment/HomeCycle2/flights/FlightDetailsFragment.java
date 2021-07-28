package com.example.barakatravelapp.view.fragment.HomeCycle2.flights;

import android.content.SharedPreferences;
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
import com.example.barakatravelapp.data.model.getFlightResponce.FlightData;
import com.example.barakatravelapp.utils.FlightBookingDialog;
import com.example.barakatravelapp.view.fragment.BaSeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FlightDetailsFragment extends BaSeFragment {

    @BindView(R.id.fragment_home_flight_details_name_tv)
    TextView fragmentHomeFlightDetailsNameTv;
    //    @BindView(R.id.fragment_home_flight_details_flight_img)
//    ImageView fragmentHomeFlightDetailsFlightImg;
    @BindView(R.id.client_home_fillter_search_keyword_etxt)
    TextView clientHomeFillterSearchKeywordEtxt;
    @BindView(R.id.fragment_home_flight_details_from_location_tv)
    TextView fragmentHomeFlightDetailsFromLocationTv;
    @BindView(R.id.fragment_home_flight_details_from_reservation_tv)
    TextView fragmentHomeFlightDetailsFromReservationTv;
    @BindView(R.id.fragment_home_flight_details_to_location_tv)
    TextView fragmentHomeFlightDetailsToLocationTv;
    @BindView(R.id.fragment_home_flight_details_to_reservation_tv)
    TextView fragmentHomeFlightDetailsToReservationTv;
    @BindView(R.id.fragment_home_flight_details_about_flight_tv)
    TextView fragmentHomeFlightDetailsAboutFlightTv;
    @BindView(R.id.fragment_home_flight_details_price_tv)
    TextView fragmentHomeFlightDetailsPriceTv;
    @BindView(R.id.fragment_home_flight_details_book_btn)
    TextView fragmentHomeFlightDetailsBookBtn;
    private FlightData flightData;
    private String flightOrBook;

    public FlightDetailsFragment() {
        // Required empty public constructor
    }

    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (this.getArguments() != null) {
            flightData = (FlightData) this.getArguments().getSerializable("Object");
            flightOrBook = this.getArguments().getString("BookingFlight");

        }
        View root = inflater.inflate(R.layout.fragment_home_flight_details, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        setData();
        return root;
    }

    private void setData() {
        if (flightOrBook.equalsIgnoreCase("bookingFlight")) {
         fragmentHomeFlightDetailsBookBtn.setVisibility(View.GONE);
        }
        fragmentHomeFlightDetailsNameTv.setText(flightData.getFlightName());
        fragmentHomeFlightDetailsFromLocationTv.setText(flightData.getFrom());
        fragmentHomeFlightDetailsToLocationTv.setText(flightData.getTo());
        fragmentHomeFlightDetailsFromReservationTv.setText(flightData.getReservationFrom());
        fragmentHomeFlightDetailsToReservationTv.setText(flightData.getReservationTo());
        fragmentHomeFlightDetailsAboutFlightTv.setText(flightData.getDescription());
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            fragmentHomeFlightDetailsAboutFlightTv.setText(Html.fromHtml(flightData.getDescription(), Html.FROM_HTML_MODE_COMPACT));
//        } else {
        fragmentHomeFlightDetailsAboutFlightTv.setText(Html.fromHtml(flightData.getDescription()));
//        }
        fragmentHomeFlightDetailsPriceTv.setText("$ " + flightData.getPriceAdult().toString() + " Price Per Adult");
    }

    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment, new FlightsFragment());
        if (flightOrBook.equalsIgnoreCase("bookingFlight")) {
            Bundle bundle = new Bundle();
            bundle.putString("BookingType", getString(R.string.My_Flight_Bookings));
            navController.navigate(R.id.action_flightDetailsFragment_to_myUmrahBookingFragment,bundle);

        }else {

            navController.navigate(R.id.action_flightDetailsFragment_to_navigation_flight);
        homeCycleActivity.setNavigation("v");}
//        homeCycleActivity.bottomNavView.getMenu().getItem(2).setChecked(true);
    }

    @OnClick({R.id.fragment_home_flight_details_back_btn, R.id.fragment_home_flight_details_book_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_home_flight_details_back_btn:
                onBack();
                break;
            case R.id.fragment_home_flight_details_book_btn:
//                Bundle bundle = new Bundle();
//                bundle.putString("ISDISCOVER", "flight");
//                ConfirmWithTheSupportFragment confirmWithTheSupportFragment=new ConfirmWithTheSupportFragment();
//                confirmWithTheSupportFragment.setArguments(bundle);
//                replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,confirmWithTheSupportFragment);
//                navController.navigate(R.id.action_flightDetailsFragment_to_confirmWithTheSupportFragment3, bundle);
                new FlightBookingDialog().showDialog(getActivity(), flightData);

                break;
        }
    }
}