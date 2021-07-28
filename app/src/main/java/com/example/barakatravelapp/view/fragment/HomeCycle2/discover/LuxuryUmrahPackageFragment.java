package com.example.barakatravelapp.view.fragment.HomeCycle2.discover;

import android.graphics.RectF;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.adapter.GetPricingtemsAdapter;
import com.example.barakatravelapp.adapter.HajjOrHotelPhotoGallaryHzRvAdapter;
import com.example.barakatravelapp.adapter.HajjPackagesIncludedHzRvAdapter;
import com.example.barakatravelapp.data.model.getUmrahAndHujjResponce.GetTopUmarAndTophajjPackage;
import com.example.barakatravelapp.utils.GeneralHajjDescriptionDetailsDialog;
import com.example.barakatravelapp.utils.PhotoGallaryAdapterCallback;
import com.example.barakatravelapp.utils.ShowDayByDayDialog;
import com.example.barakatravelapp.utils.WriteRateDialog;
import com.example.barakatravelapp.view.activity.HomeCycleActivity;
import com.example.barakatravelapp.view.fragment.BaSeFragment;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.ortiz.touchview.TouchImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.barakatravelapp.utils.HelperMethod.onLoadImageFromUrl;


public class LuxuryUmrahPackageFragment extends BaSeFragment implements PhotoGallaryAdapterCallback {

    @BindView(R.id.fragment_luxury_umrah_package_name_tv)
    TextView fragmentLuxuryUmrahPackageNameTv;
    @BindView(R.id.fragment_luxury_umrah_package_from_date_tv)
    TextView fragmentLuxuryUmrahPackageFromDateTv;
    @BindView(R.id.fragment_luxury_umrah_package_to_date_tv)
    TextView fragmentLuxuryUmrahPackageToDateTv;
    @BindView(R.id.cardview_general_vert_hajj_and_hotels_item_additional_part)
    LinearLayout cardviewGeneralVertHajjAndHotelsItemAdditionalPart;
    @BindView(R.id.fragment_luxury_umrah_package_duration_tv)
    TextView fragmentLuxuryUmrahPackageDurationTv;
    @BindView(R.id.fragment_luxury_umrah_package_price_tv)
    TextView fragmentLuxuryUmrahPackagePriceTv;
    @BindView(R.id.fragment_luxury_umrah_package_rate_tv)
    TextView fragmentLuxuryUmrahPackageRateTv;
    @BindView(R.id.hajj_hide_part_ly)
    LinearLayout hajjHidePartLy;
    //    @BindView(R.id.fragment_luxury_umrah_package_mv_map)
//    MapView fragmentLuxuryUmrahPackageMvMap;
    @BindView(R.id.home_discover_fragment_sub_home_rv_item_hz3_sr_vw)
    ScrollView homeDiscoverFragmentSubHomeRvItemHz3SrVw;
    @BindView(R.id.fragment_luxury_umrah_package_photo_galary_rv_item_hz_rv)
    RecyclerView fragmentLuxuryUmrahPackagePhotoGalaryRvItemHzRv;
    @BindView(R.id.fragment_luxury_umrah_package_package_include_rv_item_hz2_rv)
    RecyclerView fragmentLuxuryUmrahPackagePackageIncludeRvItemHz2Rv;
    @BindView(R.id.fragment_luxury_umrah_package_pricing_rv_item_hz3_rv)
    RecyclerView fragmentLuxuryUmrahPackagePricingRvItemHz3Rv;
    @BindView(R.id.content_bottom_sheet_photo_gallery_item_img)
    TouchImageView cardviewHzHajjDetailsPhotoGalleryItemImg2;
    @BindView(R.id.fragment_luxury_umrah_package_mv_map)
    WebView fragmentLuxuryUmrahPackageMvMap;
    @BindView(R.id.home_discover_fragment_sub_home_pricing_ly)
    LinearLayout homeDiscoverFragmentSubHomePricingLy;
    private NavController navController;
    private GetTopUmarAndTophajjPackage getTopUmarAndTophajjPackage;
    private LinearLayoutManager linearLayoutHorizental;
    private HajjOrHotelPhotoGallaryHzRvAdapter hajjPhotoGallaryHzRvAdapter;
    private HajjPackagesIncludedHzRvAdapter HajjPackagesIncludedHzRvAdapter;
    private GetPricingtemsAdapter getPricingtemsAdapter;
    private BottomSheetBehavior bottomSheetBehavior;
    private boolean openSheet = false;
    private String isDiscoverOrHajjOrUmarah;
    private int bookPricId;

    public LuxuryUmrahPackageFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (this.getArguments() != null) {
            getTopUmarAndTophajjPackage = (GetTopUmarAndTophajjPackage) this.getArguments().getSerializable("Object");
            isDiscoverOrHajjOrUmarah = this.getArguments().getString("DiscoverOrHajjOrUmrah");
            bookPricId = this.getArguments().getInt("getPackagePricingId");
        }
        View root = inflater.inflate(R.layout.bottom_sheets_abb_bar, container, false);

        ButterKnife.bind(this, root);
//        fragmentLuxuryUmrahPackageMvMap.onCreate(savedInstanceState);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        HomeCycleActivity homeCycleActivity = (HomeCycleActivity) getActivity();
        homeCycleActivity.setNavigation("g");
        bottomSheetBehavior = BottomSheetBehavior.from(root.findViewById(R.id.bottom1));
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        setData();
//        setScroll(root);
//        if(getTopUmarAndTophajjPackage!=null){
//        showToast(getActivity(), String.valueOf(getTopUmarAndTophajjPackage.getPricing()));}
        return root;
    }

    private void setScroll(View root) {
        root.findViewById(R.id.fragment_luxury_umrah_package_scroll_parent).setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                View childV = root.findViewById(R.id.fragment_luxury_umrah_package_mv_map);
                if (childV != null) {
                    int[] l = new int[2];
                    childV.getLocationOnScreen(l);
                    RectF rect = new RectF(l[0], l[1], l[0] + childV.getWidth(), l[1] + childV.getHeight());
                    if (rect.contains(event.getX(), event.getY())) {
                        childV.getParent()
                                .requestDisallowInterceptTouchEvent(false);
                        childV.onTouchEvent(event);
                        return true;
                    }
                }
                childV.getParent()
                        .requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }


    private void setData() {
        fragmentLuxuryUmrahPackageNameTv.setText(getTopUmarAndTophajjPackage.getUmar().getName());
        fragmentLuxuryUmrahPackageFromDateTv.setText(getTopUmarAndTophajjPackage.getUmar().getStartDateInformat());
        fragmentLuxuryUmrahPackageToDateTv.setText(getTopUmarAndTophajjPackage.getUmar().getEndDateInformat());
        fragmentLuxuryUmrahPackageDurationTv.setText(getTopUmarAndTophajjPackage.getUmar().getDuration().toString());
        fragmentLuxuryUmrahPackagePriceTv.setText(getTopUmarAndTophajjPackage.getUmar().getMinPrice().toString());
        fragmentLuxuryUmrahPackageRateTv.setText(getTopUmarAndTophajjPackage.getUmar().getCalRate().toString());
        if (getTopUmarAndTophajjPackage.getUmar().getPackageType().equalsIgnoreCase("hajj") || isDiscoverOrHajjOrUmarah.equalsIgnoreCase("hajj")) {
            hajjHidePartLy.setVisibility(View.VISIBLE);

        }
        initHozental(getTopUmarAndTophajjPackage, fragmentLuxuryUmrahPackagePhotoGalaryRvItemHzRv, 1);
        initHozental(getTopUmarAndTophajjPackage, fragmentLuxuryUmrahPackagePackageIncludeRvItemHz2Rv, 2);
//        if (isDiscoverOrHajjOrUmarah.equalsIgnoreCase(getString(R.string.My_Umrah_Bookings)) || isDiscoverOrHajjOrUmarah.equalsIgnoreCase(getString(R.string.My_Hajj_Bookings))) {
//
//            homeDiscoverFragmentSubHomePricingLy.setVisibility(View.GONE);
//        }else {
            initHozental(getTopUmarAndTophajjPackage, fragmentLuxuryUmrahPackagePricingRvItemHz3Rv,3);
//    }
        initializeUI();
//        fragmentLuxuryUmrahPackageMvMap.onResume(); // needed to get the map to display immediately

//        try {
//            MapsInitializer.initialize(getActivity().getApplicationContext());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        fragmentLuxuryUmrahPackageMvMap.getMapAsync(new OnMapReadyCallback() {
//            @Override
//            public void onMapReady(GoogleMap googleMap) {
//                try {
//
//                    MarkerOptions currentUserLocation = new MarkerOptions();
//                    currentUserLocation.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_marker_alt_solid));
//                    LatLng currentUserLatLang = new LatLng(Double.parseDouble("999"), Double.parseDouble("98831371"));
//                    currentUserLocation.position(currentUserLatLang);
//                    googleMap.addMarker(currentUserLocation);
//                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentUserLatLang, 16));
//
//                    float zoom = 10;
//                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentUserLatLang, zoom));
//
//                } catch (Exception e) {
//
//                }
//            }
//        });
    }

    private void initializeUI() {
        String iframe = "<iframe src=https://www.google.com/maps/embed?pb=!1m16!1m12!1m3!1d434549.40374533384!2d74.24349628287739!3d31.690830957117996!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!2m1!1sPakistan+Lahore!5e0!3m2!1sen!2s!4v1395138949280 width=600 height=450 frameborder=0 style=border:0</iframe>";

        String iframe2 = getTopUmarAndTophajjPackage.getUmar().getLocation();
        fragmentLuxuryUmrahPackageMvMap.canZoomOut();
        fragmentLuxuryUmrahPackageMvMap.setInitialScale(100);
        fragmentLuxuryUmrahPackageMvMap.getSettings().setJavaScriptEnabled(true);
        fragmentLuxuryUmrahPackageMvMap.loadData(iframe2, "text/html", "utf-8");
//        fragmentLuxuryUmrahPackageMvMap.getSettings().setUseWideViewPort(true);
//        fragmentLuxuryUmrahPackageMvMap.getSettings().setLoadWithOverviewMode(true);
    }

    private void initHozental(GetTopUmarAndTophajjPackage getHomeDisscoverGetItemsListData, RecyclerView fragmentLuxuryUmrahPackageGeneralRvItemHzRv, int itemNum) {
        linearLayoutHorizental = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        fragmentLuxuryUmrahPackageGeneralRvItemHzRv.setLayoutManager(linearLayoutHorizental);
        fragmentLuxuryUmrahPackageGeneralRvItemHzRv.setHasFixedSize(true);
//        clientGetRestaurantsFiltterList(0);

        if (itemNum == 1) {
            String hajjOrHotel = "hajj";
            hajjPhotoGallaryHzRvAdapter = new HajjOrHotelPhotoGallaryHzRvAdapter(getContext(), getActivity(), hajjOrHotel, getHomeDisscoverGetItemsListData.getUmarImages(), this::onMethodCallback);
            fragmentLuxuryUmrahPackageGeneralRvItemHzRv.setAdapter(hajjPhotoGallaryHzRvAdapter);
//                    showToast(getActivity(), String.valueOf(getTopUmarAndTophajjPackage.getUmarImages().get(0)));

        }

        if (itemNum == 2) {
            HajjPackagesIncludedHzRvAdapter = new HajjPackagesIncludedHzRvAdapter(getContext(), getActivity(), getHomeDisscoverGetItemsListData.getPackagesInclude());
            fragmentLuxuryUmrahPackageGeneralRvItemHzRv.setAdapter(HajjPackagesIncludedHzRvAdapter);
        }

        if (itemNum == 3) {
            getPricingtemsAdapter = new GetPricingtemsAdapter(getContext(), getActivity(),bookPricId, isDiscoverOrHajjOrUmarah, getHomeDisscoverGetItemsListData, getHomeDisscoverGetItemsListData.getPricing(), navController);
            fragmentLuxuryUmrahPackageGeneralRvItemHzRv.setAdapter(getPricingtemsAdapter);
        }
    }

    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new DiscoverFragment());
        if (openSheet) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//            replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, new ProfileFragment());
            openSheet = false;
        } else {
            if (isDiscoverOrHajjOrUmarah.equalsIgnoreCase("discover")) {
                navController.navigate(R.id.action_luxuryUmrahPackageFragment_to_navigation_discover);
                homeCycleActivity.setNavigation("v");
            }
            if (isDiscoverOrHajjOrUmarah.equalsIgnoreCase("hajj")) {
                navController.navigate(R.id.action_luxuryUmrahPackageFragment_to_navigation_hajj);
                homeCycleActivity.setNavigation("v");
            }
            if (isDiscoverOrHajjOrUmarah.equalsIgnoreCase("umrah")) {
                navController.navigate(R.id.action_luxuryUmrahPackageFragment_to_navigation_umrah);
                homeCycleActivity.setNavigation("v");
            }
            if (isDiscoverOrHajjOrUmarah.equalsIgnoreCase(getString(R.string.My_Umrah_Bookings)) || isDiscoverOrHajjOrUmarah.equalsIgnoreCase(getString(R.string.My_Hajj_Bookings))) {
                Bundle bundle = new Bundle();
                bundle.putString("BookingType", isDiscoverOrHajjOrUmarah);
                navController.navigate(R.id.action_luxuryUmrahPackageFragment_to_myUmrahBookingFragment, bundle);

            }
        }

//        homeCycleActivity.bottomNavView.getMenu().getItem(0).setChecked(true);
    }

    @OnClick({R.id.fragment_luxury_umrah_package_back_btn, R.id.fragment_luxury_umrah_package_makka_details_btn, R.id.fragment_luxury_umrah_package_madina_details_btn, R.id.fragment_luxury_umrah_package_manasik_details_btn, R.id.fragment_luxury_umrah_package_air_details_btn, R.id.fragment_luxury_umrah_package_day_by_day_btn, R.id.fragment_luxury_umrah_package_included_btn, R.id.fragment_luxury_umrah_package_not_included_btn, R.id.fragment_luxury_umrah_package_important_nots_btn, R.id.fragment_luxury_umrah_package_how_to_book_btn, R.id.fragment_luxury_umrah_package_write_your_rating_here_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_luxury_umrah_package_back_btn:
                onBack();
//                navController.navigate(R.id.action_luxuryUmrahPackageFragment_to_navigation_discover);
//                homeCycleActivity.setNavigation("v");
                break;
            case R.id.fragment_luxury_umrah_package_makka_details_btn:
                new GeneralHajjDescriptionDetailsDialog().showDialog(getActivity(), getTopUmarAndTophajjPackage, "makka");
                break;
            case R.id.fragment_luxury_umrah_package_madina_details_btn:
                new GeneralHajjDescriptionDetailsDialog().showDialog(getActivity(), getTopUmarAndTophajjPackage, "madina");
                break;
            case R.id.fragment_luxury_umrah_package_manasik_details_btn:
                new GeneralHajjDescriptionDetailsDialog().showDialog(getActivity(), getTopUmarAndTophajjPackage, "manasik");
                break;
            case R.id.fragment_luxury_umrah_package_air_details_btn:
                new GeneralHajjDescriptionDetailsDialog().showDialog(getActivity(), getTopUmarAndTophajjPackage, "air");
                break;
            case R.id.fragment_luxury_umrah_package_day_by_day_btn:
                final ShowDayByDayDialog dialog = new ShowDayByDayDialog(getTopUmarAndTophajjPackage);
                dialog.show(getActivity().getSupportFragmentManager(), "example");
                break;
            case R.id.fragment_luxury_umrah_package_included_btn:
                new GeneralHajjDescriptionDetailsDialog().showDialog(getActivity(), getTopUmarAndTophajjPackage, "included");
                break;
            case R.id.fragment_luxury_umrah_package_not_included_btn:
                new GeneralHajjDescriptionDetailsDialog().showDialog(getActivity(), getTopUmarAndTophajjPackage, "not_included");
                break;
            case R.id.fragment_luxury_umrah_package_important_nots_btn:
                new GeneralHajjDescriptionDetailsDialog().showDialog(getActivity(), getTopUmarAndTophajjPackage, "important_nots");
                break;
            case R.id.fragment_luxury_umrah_package_how_to_book_btn:
                new GeneralHajjDescriptionDetailsDialog().showDialog(getActivity(), getTopUmarAndTophajjPackage, "how_to_book");
                break;
            case R.id.fragment_luxury_umrah_package_write_your_rating_here_btn:
                new WriteRateDialog().showDialog(getActivity(), getTopUmarAndTophajjPackage.getUmar().getId(), "package");
                break;
        }
    }

    @Override
    public void onMethodCallback(String photoPath) {
        openSheet = true;
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        onLoadImageFromUrl(cardviewHzHajjDetailsPhotoGalleryItemImg2, photoPath, getContext());

    }

//    @OnClick(R.id.fragment_luxury_umrah_package_book_btn)
//    public void onViewClicked() {
////        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new ConfirmBookingFragment());
//        navController.navigate(R.id.action_luxuryUmrahPackageFragment_to_confirmBookingFragment);
//
//    }
}