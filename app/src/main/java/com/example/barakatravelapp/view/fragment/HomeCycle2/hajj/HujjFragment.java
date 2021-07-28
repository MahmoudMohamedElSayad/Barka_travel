package com.example.barakatravelapp.view.fragment.HomeCycle2.hajj;

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
import com.example.barakatravelapp.adapter.GetHajjAndUmrahItemsAdapter;
import com.example.barakatravelapp.data.model.getUmrahAndHujjResponce.GetTopUmarAndTophajjPackage;
import com.example.barakatravelapp.data.model.getUmrahAndHujjResponce.GetUmrahAndHujjResponce;
import com.example.barakatravelapp.data.model.userLoginResponce.UserData;
import com.example.barakatravelapp.utils.DialogAdapterCallback;
import com.example.barakatravelapp.utils.OnEndLess;
import com.example.barakatravelapp.view.activity.HomeCycleActivity;
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


public class HujjFragment extends BaSeFragment implements DialogAdapterCallback {
//    @BindView(R.id.top_part_in_nav_genral_part_search_til)
//    TextInputLayout topPartInNavGenralPartSearchTil;
    @BindView(R.id.top_part_in_nav_genral_part_hajj_and_umrah_toggle_btn)
    TextView topPartInNavGenralPartHajjAndUmrahToggleBtn;
    @BindView(R.id.fragment_home_hujj_recycler_view)
    RecyclerView fragmentHomeHujjRecyclerView;
    @BindView(R.id.load_more)
    RelativeLayout loadMore;
    @BindView(R.id.error_title)
    TextView errorTitle;
    @BindView(R.id.no_result_error_title)
    TextView noResultErrorTitle;
    @BindView(R.id.error_sub_view)
    LinearLayout errorSubView;
    @BindView(R.id.fragment_home_hujj_sr_refresh)
    SwipeRefreshLayout fragmentHomeHujjSrRefresh;
    private NavController navController;
    private LinearLayoutManager linearLayout;
    public List<GetTopUmarAndTophajjPackage> getTopUmarAndTophajjPackagesData = new ArrayList<GetTopUmarAndTophajjPackage>();
    public GetHajjAndUmrahItemsAdapter getHajjAndUmrahItemsAdapter;
    private ViewModelGetLists viewModel;
    public Integer maxPage = 0;
    private OnEndLess onEndLess;
    private boolean Filter = false;
    private UserData clientData;
    //    private int citiesSelectedId = 0;
    private String keyword;
    private String hajjOrUmrah = "hajj";

    public HujjFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home_hujj, container, false);

        ButterKnife.bind(this, root);
//        topPartInNavGenralPartSearchTil.setVisibility(View.VISIBLE);
        topPartInNavGenralPartHajjAndUmrahToggleBtn.setVisibility(View.VISIBLE);
        topPartInNavGenralPartHajjAndUmrahToggleBtn.setText(getString(R.string.umrah));
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        setUpActivity();
        initListener();
//        clientData = LoadUserData(getActivity());
        init();
        return root;
    }

    private void initListener() {

        viewModel = ViewModelProviders.of(this).get(ViewModelGetLists.class);

        viewModel.makeGetHajjAndUmrahDataList().observe(getViewLifecycleOwner(), new Observer<GetUmrahAndHujjResponce>() {
            @Override
            public void onChanged(@Nullable GetUmrahAndHujjResponce response) {
                try {
                    if (response != null) {
                        if (response.getStatus().equals("success")) {

//                                showToast(getActivity(), "max="+maxPage);

                            if (response.getPackage() != null ) {
                                getTopUmarAndTophajjPackagesData.clear();
                                getTopUmarAndTophajjPackagesData.addAll(response.getPackage());
//                                showToast(getActivity(), "list="+clientrestaurantsListData.get(1));

                                getHajjAndUmrahItemsAdapter.notifyDataSetChanged();
//                                if(getHotelsItemsListData.size()){
                                maxPage++;
//                                }
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
        fragmentHomeHujjRecyclerView.setLayoutManager(linearLayout);

        onEndLess = new OnEndLess(linearLayout, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        loadMore.setVisibility(View.VISIBLE);
                        if (Filter) {
                            getHajjAndUmrahListByFilter(current_page);
                        } else {
                            getHajjAndUmrahHomeList(current_page);
                        }

                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                } else {
                    onEndLess.current_page = onEndLess.previous_page;
                }
            }
        };
        fragmentHomeHujjRecyclerView.addOnScrollListener(onEndLess);

        getHajjAndUmrahItemsAdapter = new GetHajjAndUmrahItemsAdapter(getActivity(), getContext(), hajjOrUmrah, this, getTopUmarAndTophajjPackagesData);
        fragmentHomeHujjRecyclerView.setAdapter(getHajjAndUmrahItemsAdapter);
//            showToast(getActivity(), "success adapter");




        if (getTopUmarAndTophajjPackagesData.size() == 0) {
            getHajjAndUmrahHomeList(0);
        }
//        else {
//            clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//            clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
//        }

        fragmentHomeHujjSrRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                maxPage=0;
                if (Filter) {
                    getHajjAndUmrahListByFilter(0);
                } else {
                    getHajjAndUmrahHomeList(0);
                }

            }
        });
    }

    private void getHajjAndUmrahListByFilter(int page) {

        Filter = true;
        if(page == 0){ maxPage=0;}
//        keyword = topPartInNavGenralPartSearchTil.getEditText().getText().toString().trim();
//        keyword="jfk";
        Call<GetUmrahAndHujjResponce> getUmrahAndHujjResponceCall;
        getUmrahAndHujjResponceCall = getApiClient().getHajjAndUmrahItemList("hajj", page);
//        getUmrahAndHujjResponceCall = getApiClient().getHajjAndUmrahItemListByFilter("hajj", page,keyword);
//        startShimmer(page);
        viewModel.getHajjAndUmrahDataList(getActivity(), errorSubView, getUmrahAndHujjResponceCall,fragmentHomeHujjSrRefresh, loadMore);


    }

    private void getHajjAndUmrahHomeList(int page) {
        Filter = false;
        if(page == 0){ maxPage=0;}
        Call<GetUmrahAndHujjResponce> getUmrahAndHujjResponceCall;

//        startShimmer(page);

        reInit();
        getUmrahAndHujjResponceCall = getApiClient().getHajjAndUmrahItemList("hajj",page);

//            clientRestaurantsCall = getApiClient().getRestaurantsWithoutFiltter(page);
        viewModel.getHajjAndUmrahDataList(getActivity(), errorSubView, getUmrahAndHujjResponceCall,fragmentHomeHujjSrRefresh, loadMore);
//            showToast(getActivity(), "success without fillter");



    }




    private void reInit() {
        onEndLess.previousTotal = 0;
        onEndLess.current_page = 1;
        onEndLess.previous_page = 1;
        getTopUmarAndTophajjPackagesData = new ArrayList<>();
        getHajjAndUmrahItemsAdapter = new GetHajjAndUmrahItemsAdapter(getActivity(), getContext(), hajjOrUmrah, this, getTopUmarAndTophajjPackagesData);
        fragmentHomeHujjRecyclerView.setAdapter(getHajjAndUmrahItemsAdapter);

    }


    public void setError(String errorTitleTxt) {
        View.OnClickListener action = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Filter) {
                    getHajjAndUmrahListByFilter(0);
                } else {
                    getHajjAndUmrahHomeList(0);
                }

            }
        };
        errorSubView.setVisibility(View.VISIBLE);
        errorTitle.setText(errorTitleTxt);

    }


    @Override
    public void onBack() {
        navController.navigate(R.id.action_navigation_hajj_to_navigation_discover);

//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment, new DiscoverFragment());
//        homeCycleActivity.setNavigation("v");
//        homeCycleActivity.bottomNavView.getMenu().getItem(0).setChecked(true);      }
    }

    @OnClick({ R.id.top_part_in_nav_genral_part_hajj_and_umrah_toggle_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            case R.id.top_part_in_nav_genral_part_filter_til:
//                getTopUmarAndTophajjPackagesData.clear();
//                topPartInNavGenralPartSearchTil.setErrorEnabled(false);
//                if (!validationLengthZero(topPartInNavGenralPartSearchTil, getString(R.string.invalid_search), 0)) {
////                    onCreateErrorToast(getActivity(), getString(R.string.invalid_search));
//                    getHajjAndUmrahHomeList(0);
//                    return;
//                }
//                if (!validationLength(topPartInNavGenralPartSearchTil, getString(R.string.invalid_search), 3)) {
//                    onCreateErrorToast(getActivity(), getString(R.string.invalid_search));
//
//                    return;
//                }else {
//                    getHajjAndUmrahListByFilter(0);
//                }
//                break;
            case R.id.top_part_in_nav_genral_part_hajj_and_umrah_toggle_btn:
                navController.navigate(R.id.action_navigation_hajj_to_navigation_umrah);
                break;
        }
    }

    @Override
    public void onMethodCallback(GetTopUmarAndTophajjPackage getTopUmarAndTophajjPackage) {
        Bundle bundle = new Bundle();
        bundle.putString("DiscoverOrHajjOrUmrah", hajjOrUmrah);
        bundle.putSerializable("Object",  getTopUmarAndTophajjPackage);
        navController.navigate(R.id.action_navigation_hajj_to_luxuryUmrahPackageFragment,bundle);
        homeCycleActivity.setNavigation("g");

    }
}