package com.example.barakatravelapp.view.fragment.HomeCycle2.discover;

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
import com.example.barakatravelapp.adapter.SubHomeDiscoverVrRvAdapter;
import com.example.barakatravelapp.data.model.ItemGeneralObjectModel;
import com.example.barakatravelapp.data.model.getDiscoverHomeResponce.GetDiscoverHomeResponce;
import com.example.barakatravelapp.data.model.getHotelsResponce.HotelData;
import com.example.barakatravelapp.data.model.getUmrahAndHujjResponce.GetTopUmarAndTophajjPackage;
import com.example.barakatravelapp.utils.DialogAdapterCallback;
import com.example.barakatravelapp.view.fragment.BaSeFragment;
import com.example.barakatravelapp.view.viewModel.ViewModelGetLists;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

import static com.example.barakatravelapp.data.api.ApiClient.getApiClient;
import static com.example.barakatravelapp.utils.network.InternetState.isConnected;


public class DiscoverFragment extends BaSeFragment implements DialogAdapterCallback {


    @BindView(R.id.home_discover_fragment_recycler_view)
    RecyclerView homeDiscoverFragmentRecyclerView;
    @BindView(R.id.load_more)
    RelativeLayout loadMore;
    @BindView(R.id.error_title)
    TextView errorTitle;
    @BindView(R.id.error_sub_view)
    LinearLayout errorSubView;
    @BindView(R.id.discover_fragment_sr_refresh_discove_items)
    SwipeRefreshLayout discoverFragmentSrRefreshDiscoveItems;
    @BindView(R.id.no_result_error_title)
    TextView noResultErrorTitle;
    private NavController navController;
    private LinearLayoutManager lLayout;
    private List<GetTopUmarAndTophajjPackage> getHomeDisscoverGetHajjDataItemsListData = new ArrayList<GetTopUmarAndTophajjPackage>();
    private List<GetTopUmarAndTophajjPackage> getHomeDisscoverGetUmrahDataItemsListData = new ArrayList<GetTopUmarAndTophajjPackage>();
    private List<HotelData> getHomeDisscoverGetHotelsDataItemsListData = new ArrayList<HotelData>();
    private ViewModelGetLists viewModel;
    private SubHomeDiscoverVrRvAdapter subHomeDiscoverVrRvAdapter;
    private List<ItemGeneralObjectModel> rowListItem;
    public DiscoverFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home_discover, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
//        discoverFragmentSrRefreshDiscoveItems.setRefreshing(true);

        setUpActivity();
        initListener();
//        clientData = LoadUserData(getActivity());
        if (isConnected(getActivity())) {
        init();
        }
        getHotelsHomeList();

        return root;
    }

    private void initListener() {

        viewModel = ViewModelProviders.of(this).get(ViewModelGetLists.class);

        viewModel.makeGetHomeDisscoverDataList().observe(getViewLifecycleOwner(), new Observer<GetDiscoverHomeResponce>() {
            @Override
            public void onChanged(@Nullable GetDiscoverHomeResponce response) {
                try {
                    if (response != null) {
                        if (response.getStatus().equals("success")) {
//                            maxPage = response.getData().getLastPage();
//                                showToast(getActivity(), "max="+maxPage);

                            if (response.getGetTophajj() != null || response.getGetTopUmar() != null || response.getGetTopHotels() != null) {
                                getHomeDisscoverGetHajjDataItemsListData.clear();
                                getHomeDisscoverGetHajjDataItemsListData.addAll(response.getGetTophajj());

                                getHomeDisscoverGetUmrahDataItemsListData.clear();
                                getHomeDisscoverGetUmrahDataItemsListData.addAll(response.getGetTopUmar());

                                getHomeDisscoverGetHotelsDataItemsListData.clear();
                                getHomeDisscoverGetHotelsDataItemsListData.addAll(response.getGetTopHotels());
//                                showToast(getActivity(), "list="+response.getGetTopHotels().get(1));

                                subHomeDiscoverVrRvAdapter.notifyDataSetChanged();

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

    private List<ItemGeneralObjectModel> getAllItemList() {

        List<ItemGeneralObjectModel> allItems = new ArrayList<ItemGeneralObjectModel>();
        allItems.add(new ItemGeneralObjectModel(getString(R.string.top_umrah_packages)));
        allItems.add(new ItemGeneralObjectModel(getString(R.string.top_hajj_packages)));
        allItems.add(new ItemGeneralObjectModel(getString(R.string.top_hotels_packages)));
        allItems.add(new ItemGeneralObjectModel(getString(R.string.top_hotels_packages)));


        return allItems;
    }

    private void init() {

        lLayout = new LinearLayoutManager(getActivity());

        homeDiscoverFragmentRecyclerView.setLayoutManager(lLayout);
        if(getHomeDisscoverGetUmrahDataItemsListData.size()!=0 || getHomeDisscoverGetHajjDataItemsListData.size()!=0 || getHomeDisscoverGetHotelsDataItemsListData.size()!=0) {
            rowListItem = getAllItemList();
        }
        subHomeDiscoverVrRvAdapter = new SubHomeDiscoverVrRvAdapter(getContext(), getActivity(),navController,this ,rowListItem,getHomeDisscoverGetHajjDataItemsListData,getHomeDisscoverGetUmrahDataItemsListData,getHomeDisscoverGetHotelsDataItemsListData);
        homeDiscoverFragmentRecyclerView.setAdapter(subHomeDiscoverVrRvAdapter);



    }


    private void getHotelsHomeList() {
        discoverFragmentSrRefreshDiscoveItems.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                getHotelsHomeList();


            }
        });
        Call<GetDiscoverHomeResponce> getDiscoverHomeResponceCall;

//        startShimmer(page);

        reInit();
        getDiscoverHomeResponceCall = getApiClient().getHomeDiscoverItemList();

        viewModel.getHomeDisscoverDataList(getActivity(), errorSubView, getDiscoverHomeResponceCall,discoverFragmentSrRefreshDiscoveItems, loadMore);



    }




    private void reInit() {
        rowListItem = getAllItemList();
        getHomeDisscoverGetHajjDataItemsListData = new ArrayList<GetTopUmarAndTophajjPackage>();
        getHomeDisscoverGetUmrahDataItemsListData = new ArrayList<GetTopUmarAndTophajjPackage>();
        getHomeDisscoverGetHotelsDataItemsListData = new ArrayList<HotelData>();
        subHomeDiscoverVrRvAdapter = new SubHomeDiscoverVrRvAdapter(getContext(), getActivity(), navController, this, rowListItem,getHomeDisscoverGetHajjDataItemsListData,getHomeDisscoverGetUmrahDataItemsListData,getHomeDisscoverGetHotelsDataItemsListData);
        homeDiscoverFragmentRecyclerView.setAdapter(subHomeDiscoverVrRvAdapter);


    }


    public void setError(String errorTitleTxt) {
        View.OnClickListener action = new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    getHotelsHomeList();


            }
        };
        errorSubView.setVisibility(View.VISIBLE);
        errorTitle.setText(errorTitleTxt);

    }

    @Override
    public void onBack() {
        getActivity().finish();
    }

//    @OnClick(R.id.cardview_hz_discover_item_img)
//    public void onViewClicked() {
//        new ChoosePersonsRoomsDialog().showDialog(getActivity(), this);
//
//    }

    @Override
    public void onMethodCallback(GetTopUmarAndTophajjPackage getTopUmarAndTophajjPackageData) {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment, new LuxuryUmrahPackageFragment());
//        LuxuryUmrahPackageFragment luxuryUmrahPackageFragment = new LuxuryUmrahPackageFragment();
//        luxuryUmrahPackageFragment.getTopUmarAndTophajjPackage=getTopUmarAndTophajjPackageData;
        Bundle bundle = new Bundle();
        bundle.putString("DiscoverOrHajjOrUmrah", "discover");
        bundle.putSerializable("Object",  getTopUmarAndTophajjPackageData);
        navController.navigate(R.id.action_navigation_discover_to_luxuryUmrahPackageFragment,bundle);
        homeCycleActivity.setNavigation("g");

    }
}