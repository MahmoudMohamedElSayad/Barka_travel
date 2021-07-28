package com.example.barakatravelapp.view.fragment.HomeCycle2.accounts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.adapter.GetBookingsEVisaItemsAdapter;
import com.example.barakatravelapp.adapter.GetBookingsFlightsItemsAdapter;
import com.example.barakatravelapp.adapter.GetBookingsHajjAndUmrahItemsAdapter;
import com.example.barakatravelapp.adapter.GetBookingsHotelsItemsAdapter;
import com.example.barakatravelapp.data.model.getBookingEvisaResponce.EVisaDate;
import com.example.barakatravelapp.data.model.getBookingEvisaResponce.GetBookingEvisaResponce;
import com.example.barakatravelapp.data.model.getBookingFlightsResponce.BookingFlight;
import com.example.barakatravelapp.data.model.getBookingFlightsResponce.GetBookingFlightsResponce;
import com.example.barakatravelapp.data.model.getBookingHotelsResponce.BookingsHotel;
import com.example.barakatravelapp.data.model.getBookingHotelsResponce.GetBookingHotelsResponce;
import com.example.barakatravelapp.data.model.getBookingPackageResponce.BookingPackage;
import com.example.barakatravelapp.data.model.getBookingPackageResponce.GetBookingPackageResponce;
import com.example.barakatravelapp.data.model.userLoginResponce.UserData;
import com.example.barakatravelapp.utils.OnEndLess;
import com.example.barakatravelapp.utils.PhotoGallaryAdapterCallback;
import com.example.barakatravelapp.view.fragment.BaSeFragment;
import com.example.barakatravelapp.view.viewModel.ViewModelGetBookingsLists;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.ortiz.touchview.TouchImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

import static com.example.barakatravelapp.data.api.ApiClient.getApiClient;
import static com.example.barakatravelapp.data.local.SharedPreferencesManger.LoadUserData;
import static com.example.barakatravelapp.utils.HelperMethod.onLoadImageFromUrl;


public class MyAllBookingFragment extends BaSeFragment implements PhotoGallaryAdapterCallback {

    @BindView(R.id.fragment_my_all_booking_name_title_tv)
    TextView fragmentMyAllBookingNameTitleTv;
    @BindView(R.id.fragment_my_all_booking_recycler_view)
    RecyclerView fragmentMyAllBookingRecyclerView;
    @BindView(R.id.load_more)
    RelativeLayout loadMore;
    @BindView(R.id.error_title)
    TextView errorTitle;
    @BindView(R.id.error_sub_view)
    LinearLayout errorSubView;
    @BindView(R.id.no_result_error_title)
    TextView noResultErrorTitle;
    @BindView(R.id.fragment_my_all_booking_sr_refresh)
    SwipeRefreshLayout fragmentMyAllBookingSrRefresh;
    @BindView(R.id.content_bottom_sheet_photo_gallery_item_img)
    TouchImageView cardviewHzHajjDetailsPhotoGalleryItemImg2;
    private NavController navController;
    private String bookingType;
    private UserData userData;
    private LinearLayoutManager linearLayout;
    private List<BookingFlight> getFlightsItemsListData = new ArrayList<BookingFlight>();
    private GetBookingsFlightsItemsAdapter getFlightsItemsAdapter;
    private List<BookingsHotel> getHotelsItemsListData = new ArrayList<BookingsHotel>();
    private GetBookingsHotelsItemsAdapter getHotelsItemsAdapter;
    private List<BookingPackage> getTopUmarAndTophajjPackagesData = new ArrayList<BookingPackage>();
    private GetBookingsHajjAndUmrahItemsAdapter getHajjAndUmrahItemsAdapter;
    private GetBookingsEVisaItemsAdapter getBookingsEVisaItemsAdapter;
    private List<EVisaDate> eVisaDates= new ArrayList<EVisaDate>();
    private BottomSheetBehavior bottomSheetBehavior;
    private boolean openSheet = false;
    private ViewModelGetBookingsLists viewModel;
    public Integer maxPage = 0;
    private OnEndLess onEndLess;
    public MyAllBookingFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (this.getArguments() != null) {
            bookingType = this.getArguments().getString("BookingType");
        }
        View root = inflater.inflate(R.layout.evisa_bottom_sheets_abb_bar3, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        fragmentMyAllBookingNameTitleTv.setText(bookingType);
        userData = LoadUserData(getActivity());
        bottomSheetBehavior = BottomSheetBehavior.from(root.findViewById(R.id.bottom1));
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        setUpActivity();
        initListener();
        init();
        return root;
    }

    private void initListener() {

        viewModel = ViewModelProviders.of(this).get(ViewModelGetBookingsLists.class);
       if(bookingType.equalsIgnoreCase(getString(R.string.My_Flight_Bookings))){

           viewModel.makeGetBookingsFlightsDataList().observe(getViewLifecycleOwner(), new Observer<GetBookingFlightsResponce>() {
            @Override
            public void onChanged(@Nullable GetBookingFlightsResponce response) {
                try {
                    if (response != null) {
//                        showToast(getActivity(), "success1");
                        if (response.getStatus().equals("success")) {
//                            maxPage = response.getData().getLastPage();

                            if (response.getFlights() != null) {
                                getFlightsItemsListData.clear();
                                getFlightsItemsListData.addAll(response.getFlights());
//                                showToast(getActivity(), "list="+getFlightsItemsListData.get(0));

                                getFlightsItemsAdapter.notifyDataSetChanged();
                                maxPage++;
                                noResultErrorTitle.setVisibility(View.GONE);
                                if(getFlightsItemsListData.size()==0){
                                    noResultErrorTitle.setVisibility(View.VISIBLE);

                                }
                            }else {
                                noResultErrorTitle.setVisibility(View.VISIBLE);
                            }

                        }
                    } else {
//                        showToast(getActivity(), "success1");

                    }

                } catch (Exception e) {
                }
            }
        });
       }
        if(bookingType.equalsIgnoreCase(getString(R.string.My_Hotel_Bookings))){

            viewModel.makeGetBookingsHotelsDataList().observe(getViewLifecycleOwner(), new Observer<GetBookingHotelsResponce>() {
            @Override
            public void onChanged(@Nullable GetBookingHotelsResponce response) {
                try {
                    if (response != null) {
                        if (response.getStatus().equals("success")) {

//                                showToast(getActivity(), "max="+maxPage);

                            if (response.getHotels() != null ) {
                                getHotelsItemsListData.clear();
                                getHotelsItemsListData.addAll(response.getHotels());
//                                showToast(getActivity(), "list="+clientrestaurantsListData.get(1));

                                getHotelsItemsAdapter.notifyDataSetChanged();
//                                if(getHotelsItemsListData.size()){
                                maxPage++;
                                noResultErrorTitle.setVisibility(View.GONE);
                                if(getHotelsItemsListData.size()==0){
                                    noResultErrorTitle.setVisibility(View.VISIBLE);

                                }
                            }else {
                                noResultErrorTitle.setVisibility(View.VISIBLE);
                            }
//                                showToast(getActivity(), "success1");

                        }
                    } else {

                    }

                } catch (Exception e) {
                }
            }
        });
        }

        if(bookingType.equalsIgnoreCase(getString(R.string.My_Umrah_Bookings))||bookingType.equalsIgnoreCase(getString(R.string.My_Hajj_Bookings))) {

            viewModel.makeGetBookingsHajjAndUmrahDataList().observe(getViewLifecycleOwner(), new Observer<GetBookingPackageResponce>() {
                @Override
                public void onChanged(@Nullable GetBookingPackageResponce response) {
                    try {
                        if (response != null) {
                            if (response.getStatus().equals("success")) {

//                                showToast(getActivity(), "max="+maxPage);

                                if (response.getPackage() != null) {
                                    getTopUmarAndTophajjPackagesData.clear();
                                    getTopUmarAndTophajjPackagesData.addAll(response.getPackage());
//                                showToast(getActivity(), "list="+clientrestaurantsListData.get(1));

                                    getHajjAndUmrahItemsAdapter.notifyDataSetChanged();
//                                if(getHotelsItemsListData.size()){
                                    maxPage++;
//                                }
                                    noResultErrorTitle.setVisibility(View.GONE);
                                    if(getTopUmarAndTophajjPackagesData.size()==0){
                                        noResultErrorTitle.setVisibility(View.VISIBLE);

                                    }
                                } else {
                                    noResultErrorTitle.setVisibility(View.VISIBLE);
                                }
//                                showToast(getActivity(), "success1");

                            }
                        } else {

                        }

                    } catch (Exception e) {
                    }
                }
            });
        }

        if(bookingType.equalsIgnoreCase(getString(R.string.My_E_Visa_Bookings))){

            viewModel.makeGetBookingsEVisaDataList().observe(getViewLifecycleOwner(), new Observer<GetBookingEvisaResponce>() {
                @Override
                public void onChanged(@Nullable GetBookingEvisaResponce response) {
                    try {
                        if (response != null) {
//                        showToast(getActivity(), "success1");
                            if (response.getStatus().equals("success")) {
//                            maxPage = response.getData().getLastPage();

                                if (response.getData() != null) {
                                    eVisaDates.clear();
                                    eVisaDates.addAll(response.getData());
//                                showToast(getActivity(), "list="+getFlightsItemsListData.get(0));

                                    getBookingsEVisaItemsAdapter.notifyDataSetChanged();
                                    maxPage++;
                                    noResultErrorTitle.setVisibility(View.GONE);
                                    if(eVisaDates.size()==0){
                                        noResultErrorTitle.setVisibility(View.VISIBLE);
                                    }
                                }else {
                                    noResultErrorTitle.setVisibility(View.VISIBLE);

                                }

                            }
                        } else {
//                        showToast(getActivity(), "success1");

                        }

                    } catch (Exception e) {
                    }
                }
            });
        }

    }
    private void init() {
        linearLayout = new LinearLayoutManager(getActivity());
        fragmentMyAllBookingRecyclerView.setLayoutManager(linearLayout);

        onEndLess = new OnEndLess(linearLayout, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        loadMore.setVisibility(View.VISIBLE);
                        getByType(current_page);


                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                } else {
                    onEndLess.current_page = onEndLess.previous_page;
                }
            }
        };
        fragmentMyAllBookingRecyclerView.addOnScrollListener(onEndLess);

        if(bookingType.equalsIgnoreCase(getString(R.string.My_Hotel_Bookings))){
            getHotelsItemsAdapter = new GetBookingsHotelsItemsAdapter(getActivity(), getContext(),bookingType, navController, getHotelsItemsListData);
            fragmentMyAllBookingRecyclerView.setAdapter(getHotelsItemsAdapter);
        }
        if(bookingType.equalsIgnoreCase(getString(R.string.My_Flight_Bookings))) {
            getFlightsItemsAdapter = new GetBookingsFlightsItemsAdapter(getActivity(), getContext(), bookingType,getFlightsItemsListData, navController);
            fragmentMyAllBookingRecyclerView.setAdapter(getFlightsItemsAdapter);
        }
        if(bookingType.equalsIgnoreCase(getString(R.string.My_Hajj_Bookings))) {
            getHajjAndUmrahItemsAdapter = new GetBookingsHajjAndUmrahItemsAdapter(getActivity(), getContext(),navController, bookingType,"hajj", getTopUmarAndTophajjPackagesData);
            fragmentMyAllBookingRecyclerView.setAdapter(getHajjAndUmrahItemsAdapter);

        }
        if(bookingType.equalsIgnoreCase(getString(R.string.My_Umrah_Bookings))) {
            getHajjAndUmrahItemsAdapter = new GetBookingsHajjAndUmrahItemsAdapter(getActivity(), getContext(), navController, bookingType,"umrah", getTopUmarAndTophajjPackagesData);
            fragmentMyAllBookingRecyclerView.setAdapter(getHajjAndUmrahItemsAdapter);

        }

        if(bookingType.equalsIgnoreCase(getString(R.string.My_E_Visa_Bookings))) {
            getBookingsEVisaItemsAdapter = new GetBookingsEVisaItemsAdapter(getActivity(), getContext(), bookingType, eVisaDates,navController,this::onMethodCallback);
            fragmentMyAllBookingRecyclerView.setAdapter(getBookingsEVisaItemsAdapter);

        }
//            showToast(getActivity(), "success adapter");



        getSizeByType();

//        else {
//            clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//            clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
//        }

        fragmentMyAllBookingSrRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                maxPage=0;
                getByType(0);



            }
        });
    }

    private void getSizeByType() {
        if(bookingType.equalsIgnoreCase(getString(R.string.My_Hotel_Bookings))){
            if (getHotelsItemsListData.size() == 0) {
                getHotelsHomeList(0);
            }
        }
        if(bookingType.equalsIgnoreCase(getString(R.string.My_Flight_Bookings))) {
            if (getFlightsItemsListData.size() == 0) {
                getFlightsList(0);
            }
        }
        if(bookingType.equalsIgnoreCase(getString(R.string.My_Hajj_Bookings))) {

            if (getTopUmarAndTophajjPackagesData.size() == 0) {
                getHajjAndUmrahHomeList(0,"hajj");
            }
        }
        if(bookingType.equalsIgnoreCase(getString(R.string.My_Umrah_Bookings))) {

            if (getTopUmarAndTophajjPackagesData.size() == 0) {
                getHajjAndUmrahHomeList(0,"umrah");
            }
        }
        if(bookingType.equalsIgnoreCase(getString(R.string.My_E_Visa_Bookings))) {
            if (getTopUmarAndTophajjPackagesData.size() == 0) {
                getEVisaList(0);
            }
        }
        }

    private void getByType(int page) {
        if(bookingType.equalsIgnoreCase(getString(R.string.My_Hotel_Bookings))){
            getHotelsHomeList(page);

        }
        if(bookingType.equalsIgnoreCase(getString(R.string.My_Flight_Bookings))) {
            getFlightsList(page);

        }
        if(bookingType.equalsIgnoreCase(getString(R.string.My_Hajj_Bookings))) {

            getHajjAndUmrahHomeList(page,"hajj");

        }
        if(bookingType.equalsIgnoreCase(getString(R.string.My_Umrah_Bookings))) {
            getHajjAndUmrahHomeList(page,"umrah");


        }
        if(bookingType.equalsIgnoreCase(getString(R.string.My_E_Visa_Bookings))) {
            getEVisaList(page);

        }
        }


    private void getHotelsHomeList(int page) {
        if(page == 0){
            maxPage=0;}
        Call<GetBookingHotelsResponce> getHotelsResponceCall;

//        startShimmer(page);

        reInit();
        getHotelsResponceCall = getApiClient().getBookingHotelsItemList(userData.getId(),page);

//            clientRestaurantsCall = getApiClient().getRestaurantsWithoutFiltter(page);
        viewModel.getHBookingsotelsDataList(getActivity(), errorSubView, getHotelsResponceCall,fragmentMyAllBookingSrRefresh, loadMore);
//            showToast(getActivity(), "success without fillter");



    }

    private void getEVisaList(int page) {
        if (page == 0) {
            maxPage = 0;
        }

        Call<GetBookingEvisaResponce> getEVisaResponceCall;
//        startShimmer(page);

        reInit();
        getEVisaResponceCall = getApiClient().getBookingEVisaItemList(page,userData.getId());

//            clientRestaurantsCall = getApiClient().getRestaurantsWithoutFiltter(page);
        viewModel.getBookingsEVsaDataList(getActivity(), errorSubView, getEVisaResponceCall, fragmentMyAllBookingSrRefresh, loadMore);
//            showToast(getActivity(), "success ");


    }

    private void getFlightsList(int page) {
        if (page == 0) {
            maxPage = 0;
        }

        Call<GetBookingFlightsResponce> getFlightResponceCall;
//        startShimmer(page);

        reInit();
        getFlightResponceCall = getApiClient().getBookingFlightItemList(userData.getId(),page);

//            clientRestaurantsCall = getApiClient().getRestaurantsWithoutFiltter(page);
        viewModel.getBookingsFlightsDataList(getActivity(), errorSubView, getFlightResponceCall, fragmentMyAllBookingSrRefresh, loadMore);
//            showToast(getActivity(), "success ");


    }

    private void getHajjAndUmrahHomeList(int page, String type) {
        if(page == 0){ maxPage=0;}
        Call<GetBookingPackageResponce> getUmrahAndHujjResponceCall;

//        startShimmer(page);

        reInit();
        getUmrahAndHujjResponceCall = getApiClient().getBookingHajjAndUmrahItemList(userData.getId(),type,page);

//            clientRestaurantsCall = getApiClient().getRestaurantsWithoutFiltter(page);
        viewModel.getBookingsHajjAndUmrahDataList(getActivity(), errorSubView, getUmrahAndHujjResponceCall,fragmentMyAllBookingSrRefresh, loadMore);
//            showToast(getActivity(), "success without fillter");



    }
    private void reInit() {
        onEndLess.previousTotal = 0;
        onEndLess.current_page = 1;
        onEndLess.previous_page = 1;
        if(bookingType.equalsIgnoreCase(getString(R.string.My_Hotel_Bookings))){
            getHotelsItemsListData = new ArrayList<>();
            getHotelsItemsAdapter = new GetBookingsHotelsItemsAdapter(getActivity(), getContext(),bookingType, navController, getHotelsItemsListData);
            fragmentMyAllBookingRecyclerView.setAdapter(getHotelsItemsAdapter);
        }
        if(bookingType.equalsIgnoreCase(getString(R.string.My_Flight_Bookings))) {
            getFlightsItemsListData = new ArrayList<>();
            getFlightsItemsAdapter = new GetBookingsFlightsItemsAdapter(getActivity(), getContext(), bookingType, getFlightsItemsListData, navController);
            fragmentMyAllBookingRecyclerView.setAdapter(getFlightsItemsAdapter);
        }
        if(bookingType.equalsIgnoreCase(getString(R.string.My_Hajj_Bookings))) {
            getTopUmarAndTophajjPackagesData = new ArrayList<>();
            getHajjAndUmrahItemsAdapter = new GetBookingsHajjAndUmrahItemsAdapter(getActivity(), getContext(), navController, bookingType,"hajj", getTopUmarAndTophajjPackagesData);
            fragmentMyAllBookingRecyclerView.setAdapter(getHajjAndUmrahItemsAdapter);

        }
        if(bookingType.equalsIgnoreCase(getString(R.string.My_Umrah_Bookings))) {
            getTopUmarAndTophajjPackagesData = new ArrayList<>();
            getHajjAndUmrahItemsAdapter = new GetBookingsHajjAndUmrahItemsAdapter(getActivity(), getContext(), navController, bookingType,"umrah", getTopUmarAndTophajjPackagesData);
            fragmentMyAllBookingRecyclerView.setAdapter(getHajjAndUmrahItemsAdapter);
        }
        if(bookingType.equalsIgnoreCase(getString(R.string.My_E_Visa_Bookings))) {
            eVisaDates= new ArrayList<EVisaDate>();
            getBookingsEVisaItemsAdapter = new GetBookingsEVisaItemsAdapter(getActivity(), getContext(), bookingType, eVisaDates,navController,this::onMethodCallback);
            fragmentMyAllBookingRecyclerView.setAdapter(getBookingsEVisaItemsAdapter);

        }
        }


    public void setError(String errorTitleTxt) {
        View.OnClickListener action = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getByType(0);



            }
        };
        errorSubView.setVisibility(View.VISIBLE);
        errorTitle.setText(errorTitleTxt);

    }

    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment, new AccountFragment());
        if (openSheet) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//            replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, new ProfileFragment());
            openSheet = false;
        } else {
        navController.navigate(R.id.action_myUmrahBookingFragment_to_navigation_account);
        homeCycleActivity.setNavigation("v");}
//        homeCycleActivity.bottomNavView.getMenu().getItem(4).setChecked(true);
    }

    @OnClick(R.id.fragment_my_all_booking_back_img)
    public void onViewClicked() {
        onBack();
    }
    @Override
    public void onMethodCallback(String photoPath) {
        openSheet = true;
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        onLoadImageFromUrl(cardviewHzHajjDetailsPhotoGalleryItemImg2, photoPath, getContext());

    }
}