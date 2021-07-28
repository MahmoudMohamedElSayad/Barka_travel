package com.example.barakatravelapp.view.fragment.HomeCycle2.flights;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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
import com.example.barakatravelapp.adapter.GetFlightsItemsAdapter;
import com.example.barakatravelapp.data.model.getFlightResponce.FlightData;
import com.example.barakatravelapp.data.model.getFlightResponce.GetFlightResponce;
import com.example.barakatravelapp.data.model.userLoginResponce.UserData;
import com.example.barakatravelapp.utils.OnEndLess;
import com.example.barakatravelapp.view.fragment.BaSeFragment;
import com.example.barakatravelapp.view.viewModel.ViewModelGetLists;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

import static com.example.barakatravelapp.data.api.ApiClient.getApiClient;
import static com.example.barakatravelapp.utils.ToastCreator.onCreateErrorToast;
import static com.example.barakatravelapp.utils.validation.Validation.validationLength;
import static com.example.barakatravelapp.utils.validation.Validation.validationLengthZero;


public class FlightsFragment extends BaSeFragment {

    @BindView(R.id.top_part_in_nav_flight_part)
    LinearLayout topPartInNavFlightPart;
    @BindView(R.id.top_part_in_nav_genral_part_from_spin_id)
    Spinner topPartInNavGenralPartFromSpinId;
    @BindView(R.id.top_part_in_nav_genral_part_to_spin_id)
    Spinner topPartInNavGenralPartToSpinId;
    @BindView(R.id.fragment_home_flights_recycler_view)
    RecyclerView fragmentHomeFlightsRecyclerView;
    @BindView(R.id.load_more)
    RelativeLayout loadMore;
    @BindView(R.id.error_title)
    TextView errorTitle;
    @BindView(R.id.error_sub_view)
    LinearLayout errorSubView;
    @BindView(R.id.fragment_home_flights_sr_refresh_donations)
    SwipeRefreshLayout fragmentHomeFlightsSrRefresh;
    @BindView(R.id.no_result_error_title)
    TextView noResultErrorTitle;
    @BindView(R.id.top_part_in_nav_genral_part_search_til)
    TextInputLayout topPartInNavGenralPartSearchTil;
    private NavController navController;

    //    private SpinnerAdapter cityFilterAdapter;
    private LinearLayoutManager linearLayout;
    public List<FlightData> getFlightsItemsListData = new ArrayList<FlightData>();
    public GetFlightsItemsAdapter getFlightsItemsAdapter;
    private ViewModelGetLists viewModel;
    public Integer maxPage = 0;
    private OnEndLess onEndLess;
    private boolean Filter = false;
    private UserData clientData;
    //    private int citiesSelectedId = 0;
    private String keyword;
    private AdapterView.OnItemSelectedListener listener;

    public FlightsFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home_flights, container, false);

        ButterKnife.bind(this, root);
//        topPartInNavFlightPart.setVisibility(View.VISIBLE);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        topPartInNavGenralPartSearchTil.setVisibility(View.VISIBLE);
        setUpActivity();
        initListener();
//        clientData = LoadUserData(getActivity());
        init();
        return root;
    }

    private void initListener() {

        viewModel = ViewModelProviders.of(this).get(ViewModelGetLists.class);

        viewModel.makeGetFlightsDataList().observe(getViewLifecycleOwner(), new Observer<GetFlightResponce>() {
            @Override
            public void onChanged(@Nullable GetFlightResponce response) {
                try {
                    if (response != null) {
                        if (response.getStatus().equals("success")) {
//                            maxPage = response.getData().getLastPage();
//                                showToast(getActivity(), "max="+maxPage);

                            if (response.getFlights() != null) {
                                getFlightsItemsListData.clear();
                                getFlightsItemsListData.addAll(response.getFlights());
//                                showToast(getActivity(), "list="+clientrestaurantsListData.get(1));

                                getFlightsItemsAdapter.notifyDataSetChanged();
                                maxPage++;
                                noResultErrorTitle.setVisibility(View.GONE);

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

    private void init() {
        linearLayout = new LinearLayoutManager(getActivity());
        fragmentHomeFlightsRecyclerView.setLayoutManager(linearLayout);

        onEndLess = new OnEndLess(linearLayout, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        loadMore.setVisibility(View.VISIBLE);
                        if (Filter) {
                            getFlightsListByFilter(current_page);
                        } else {
                            getFlightsList(current_page);
                        }

                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                } else {
                    onEndLess.current_page = onEndLess.previous_page;
                }
            }
        };
        fragmentHomeFlightsRecyclerView.addOnScrollListener(onEndLess);

        getFlightsItemsAdapter = new GetFlightsItemsAdapter(getActivity(), getContext(), getFlightsItemsListData, navController);
        fragmentHomeFlightsRecyclerView.setAdapter(getFlightsItemsAdapter);
//            showToast(getActivity(), "success adapter");


        if (getFlightsItemsListData.size() == 0) {
            getFlightsList(0);
        }
//        else {
//            clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//            clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
//        }

        fragmentHomeFlightsSrRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                maxPage = 0;
                if (Filter) {
                    getFlightsListByFilter(0);
                } else {
                    getFlightsList(0);
                }

            }
        });
    }

    private void getFlightsListByFilter(int page) {

        Filter = true;
        if (page == 0) {
            maxPage = 0;
        }
        keyword = topPartInNavGenralPartSearchTil.getEditText().getText().toString().trim();
//        keyword = "jfk";
        Call<GetFlightResponce> getFlightResponceCall;
        getFlightResponceCall = getApiClient().getFlightItemListByFilter(keyword, page);
//        startShimmer(page);
        viewModel.getFlightsDataList(getActivity(), errorSubView, getFlightResponceCall, fragmentHomeFlightsSrRefresh, loadMore);


    }

    private void getFlightsList(int page) {
        Call<GetFlightResponce> getFlightResponceCall;
        if (page == 0) {
            maxPage = 0;
        }

//        startShimmer(page);

        reInit();
        getFlightResponceCall = getApiClient().getFlightItemList(page);

//            clientRestaurantsCall = getApiClient().getRestaurantsWithoutFiltter(page);
        viewModel.getFlightsDataList(getActivity(), errorSubView, getFlightResponceCall, fragmentHomeFlightsSrRefresh, loadMore);
//            showToast(getActivity(), "success without fillter");


    }


    private void reInit() {
        onEndLess.previousTotal = 0;
        onEndLess.current_page = 0;
        onEndLess.previous_page = 0;
        getFlightsItemsListData = new ArrayList<>();
        getFlightsItemsAdapter = new GetFlightsItemsAdapter(getActivity(), getContext(), getFlightsItemsListData, navController);
        fragmentHomeFlightsRecyclerView.setAdapter(getFlightsItemsAdapter);

    }


    public void setError(String errorTitleTxt) {
        View.OnClickListener action = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Filter) {
                    getFlightsListByFilter(0);
                } else {
                    getFlightsList(0);
                }

            }
        };
        errorSubView.setVisibility(View.VISIBLE);
        errorTitle.setText(errorTitleTxt);

    }

    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment, new DiscoverFragment());
        navController.navigate(R.id.action_navigation_flight_to_navigation_discover);
//        homeCycleActivity.bottomNavView.getMenu().getItem(0).setChecked(true);
    }


    @OnClick(R.id.top_part_in_nav_genral_part_filter_til)
    public void onViewClicked() {
        getFlightsItemsListData.clear();
        topPartInNavGenralPartSearchTil.setErrorEnabled(false);
        if (!validationLengthZero(topPartInNavGenralPartSearchTil, getString(R.string.invalid_search), 0)) {
//                    onCreateErrorToast(getActivity(), getString(R.string.invalid_search));
            getFlightsList(0);
                    return;
        }
        if (!validationLength(topPartInNavGenralPartSearchTil, getString(R.string.invalid_search), 3)) {
            onCreateErrorToast(getActivity(), getString(R.string.invalid_search));

            return;
        }else {
            getFlightsListByFilter(0);
        }

    }
}