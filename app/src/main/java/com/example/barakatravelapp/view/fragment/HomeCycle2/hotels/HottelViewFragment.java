package com.example.barakatravelapp.view.fragment.HomeCycle2.hotels;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.adapter.GetHotelRomesItemsAdapter;
import com.example.barakatravelapp.adapter.HajjOrHotelPhotoGallaryHzRvAdapter;
import com.example.barakatravelapp.adapter.HotelAminitesHzRvAdapter;
import com.example.barakatravelapp.data.model.getHotelsResponce.HotelData;
import com.example.barakatravelapp.utils.PhotoGallaryAdapterCallback;
import com.example.barakatravelapp.utils.WriteRateDialog;
import com.example.barakatravelapp.view.activity.HomeCycleActivity;
import com.example.barakatravelapp.view.fragment.BaSeFragment;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.ortiz.touchview.TouchImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.barakatravelapp.utils.HelperMethod.onLoadImageFromUrl;


public class HottelViewFragment extends BaSeFragment implements PhotoGallaryAdapterCallback {

    @BindView(R.id.fragment_home_hottel_view_name_tv)
    TextView fragmentHomeHottelViewNameTv;
    //    @BindView(R.id.fragment_home_hottel_view_from_date_tv)
//    TextView fragmentHomeHottelViewFromDateTv;
//    @BindView(R.id.fragment_home_hottel_view_to_date_tv)
//    TextView fragmentHomeHottelViewToDateTv;
    @BindView(R.id.fragment_home_hottel_view_duration_tv)
    TextView fragmentHomeHottelViewDurationTv;
    @BindView(R.id.fragment_home_hottel_view_price_tv)
    TextView fragmentHomeHottelViewPriceTv;
    @BindView(R.id.fragment_home_hottel_view_rate_tv)
    TextView fragmentHomeHottelViewRateTv;
    @BindView(R.id.fragment_home_hottel_view_about_hotel_tv)
    TextView fragmentHomeHottelViewAboutHotelTv;
    @BindView(R.id.fragment_home_hottel_view_photo_galary_rv_item_hz_rv)
    RecyclerView fragmentHomeHottelViewPhotoGalaryRvItemHzRv;
    @BindView(R.id.fragment_home_hottel_view_wv_mv_map)
    WebView fragmentHomeHottelViewWvMvMap;
    @BindView(R.id.fragment_home_hottel_view_hotel_amenities_rv_item_hz2_rv)
    RecyclerView fragmentHomeHottelViewHotelAmenitiesRvItemHz2Rv;
    @BindView(R.id.fragment_home_hottel_view_rooms_availability_rv_item_hz3_rv)
    RecyclerView fragmentHomeHottelViewRoomsAvailabilityRvItemHz3Rv;
    @BindView(R.id.content_bottom_sheet_photo_gallery_item_img)
    TouchImageView cardviewHzHajjDetailsPhotoGalleryItemImg;
    @BindView(R.id.home_discover_fragment_sub_home_rooms_ly)
    LinearLayout homeDiscoverFragmentSubHomeRoomsLy;
    private NavController navController;
    private String isSuccessfulOrFlightOrDiscoffer;
    private HotelData hotelData;
    private String isDiscoverOrHotel;
    private LinearLayoutManager linearLayoutHorizental;
    private HajjOrHotelPhotoGallaryHzRvAdapter hajjOrHotelPhotoGallaryHzRvAdapter;
    private BottomSheetBehavior bottomSheetBehavior;
    private boolean openSheet = false;
    private HotelAminitesHzRvAdapter hotelAminitesHzRvAdapter;
    private GetHotelRomesItemsAdapter getHotelRomesItemsAdapter;
    private String roomPrice;

    public HottelViewFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (this.getArguments() != null) {
//            isSuccessfulOrFlightOrDiscoffer = this.getArguments().getString("ISSUCCESSFUL");
            isDiscoverOrHotel = this.getArguments().getString("DiscoverOrHotel");
            roomPrice = this.getArguments().getString("getRoomPrice");
            hotelData = (HotelData) this.getArguments().getSerializable("Object");

        }
        View root = inflater.inflate(R.layout.hottel_bottom_sheets_abb_bar2, container, false);
        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        HomeCycleActivity navigationActivity = (HomeCycleActivity) getActivity();
        navigationActivity.setNavigation("g");
        bottomSheetBehavior = BottomSheetBehavior.from(root.findViewById(R.id.bottom1));
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        setData();
        return root;
    }

    private void setData() {


        fragmentHomeHottelViewNameTv.setText(hotelData.getName());
//        fragmentHomeHottelViewFromDateTv.setText(hotelData.getGetRooms().get(0).getFromDate());
//        fragmentHomeHottelViewToDateTv.setText(hotelData.getGetRooms().get(0).getToDate());
        fragmentHomeHottelViewDurationTv.setText(hotelData.getDuration().toString());
        fragmentHomeHottelViewPriceTv.setText(hotelData.getMinPrice().toString());
        fragmentHomeHottelViewRateTv.setText(hotelData.getRate().toString());
//        fragmentHomeHottelViewAboutHotelTv.setText(hotelData.getDescription().toString());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            fragmentHomeHottelViewAboutHotelTv.setText(Html.fromHtml(hotelData.getDescription(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            fragmentHomeHottelViewAboutHotelTv.setText(Html.fromHtml(hotelData.getDescription()));
        }
        initHozental(hotelData, fragmentHomeHottelViewPhotoGalaryRvItemHzRv, 1);
        initHozental(hotelData, fragmentHomeHottelViewHotelAmenitiesRvItemHz2Rv, 2);
//        if (isDiscoverOrHotel.equalsIgnoreCase("book_hotel")) {
//        homeDiscoverFragmentSubHomeRoomsLy.setVisibility(View.GONE);
//        } else {
            initHozental(hotelData, fragmentHomeHottelViewRoomsAvailabilityRvItemHz3Rv, 3);
//        }

        String iframe2 = hotelData.getLocation();
        fragmentHomeHottelViewWvMvMap.canZoomOut();
        fragmentHomeHottelViewWvMvMap.setInitialScale(100);
        fragmentHomeHottelViewWvMvMap.getSettings().setJavaScriptEnabled(true);
        fragmentHomeHottelViewWvMvMap.loadData(iframe2, "text/html", "utf-8");

    }

    private void initHozental(HotelData hotelData, RecyclerView fragmentGeneralRvItemHzRv, int itemNum) {
        linearLayoutHorizental = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        fragmentGeneralRvItemHzRv.setLayoutManager(linearLayoutHorizental);
        fragmentGeneralRvItemHzRv.setHasFixedSize(true);
//        clientGetRestaurantsFiltterList(0);

        if (itemNum == 1) {
            hajjOrHotelPhotoGallaryHzRvAdapter = new HajjOrHotelPhotoGallaryHzRvAdapter(getContext(), getActivity(), "hotel", hotelData.getHotelImages(), this::onMethodCallback);
            fragmentGeneralRvItemHzRv.setAdapter(hajjOrHotelPhotoGallaryHzRvAdapter);
//                    showToast(getActivity(), String.valueOf(getTopUmarAndTophajjPackage.getUmarImages().get(0)));

        }

        if (itemNum == 2) {
            hotelAminitesHzRvAdapter = new HotelAminitesHzRvAdapter(getContext(), getActivity(), hotelData.getGetAmenities());
            fragmentGeneralRvItemHzRv.setAdapter(hotelAminitesHzRvAdapter);
        }

        if (itemNum == 3) {
            getHotelRomesItemsAdapter = new GetHotelRomesItemsAdapter(getContext(), getActivity(),roomPrice,isDiscoverOrHotel, hotelData, hotelData.getGetRooms(), navController);
            fragmentGeneralRvItemHzRv.setAdapter(getHotelRomesItemsAdapter);
        }
    }

    @Override
    public void onBack() {
        if (openSheet) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            openSheet = false;
        } else {
            if (isDiscoverOrHotel.equalsIgnoreCase("discover")) {
                navController.navigate(R.id.action_hottelViewFragment_to_navigation_discover);
                homeCycleActivity.setNavigation("v");
            }
            if (isDiscoverOrHotel.equalsIgnoreCase("hotel")) {
                navController.navigate(R.id.action_hottelViewFragment_to_navigation_hotels);
                homeCycleActivity.setNavigation("v");

            }
            if (isDiscoverOrHotel.equalsIgnoreCase("book_hotel")) {
                Bundle bundle = new Bundle();
                bundle.putString("BookingType", getString(R.string.My_Hotel_Bookings));
                navController.navigate(R.id.action_hottelViewFragment_to_myUmrahBookingFragment, bundle);
            }
        }
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new SuccessfulPaymentFragment());
//        Bundle bundle = new Bundle();
//        if (isSuccessfulOrFlightOrDiscoffer.equalsIgnoreCase("successfullPayment")) {
////            navController.navigate(R.id.action_hottelViewFragment_to_successfulPaymentFragment);
//        }else if (isSuccessfulOrFlightOrDiscoffer.equalsIgnoreCase("discover")) {
//            bundle.putString("ISDISCOVER", "discover");
//            navController.navigate(R.id.action_hottelViewFragment_to_confirmWithTheSupportFragment,bundle);
//        }else {
//            bundle.putString("ISDISCOVER","flight");
//            navController.navigate(R.id.action_hottelViewFragment_to_confirmWithTheSupportFragment,bundle);
//
//        }
    }

    @OnClick({R.id.fragment_home_hottel_view_back_btn, R.id.fragment_home_hottel_view_write_your_rating_here_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_home_hottel_view_back_btn:
                onBack();
                break;
            case R.id.fragment_home_hottel_view_write_your_rating_here_btn:
                new WriteRateDialog().showDialog(getActivity(), hotelData.getId(), "hotel");

                break;
        }
    }

    @Override
    public void onMethodCallback(String photoPath) {
        openSheet = true;
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        String hotelImage = "https://www.barakatravel.net/" + photoPath.trim();
        onLoadImageFromUrl(cardviewHzHajjDetailsPhotoGalleryItemImg, hotelImage, getContext());

    }

//    @OnClick(R.id.fragment_home_hottel_view_book_btn)
//    public void onViewClicked() {
//        Bundle bundle = new Bundle();
//        bundle.putString("ISDISCOVER", "discover");
//        ConfirmWithTheSupportFragment confirmWithTheSupportFragment=new ConfirmWithTheSupportFragment();
//        confirmWithTheSupportFragment.setArguments(bundle);
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,confirmWithTheSupportFragment);
//        navController.navigate(R.id.action_hottelViewFragment_to_confirmWithTheSupportFragment, bundle);
//
//    }
}