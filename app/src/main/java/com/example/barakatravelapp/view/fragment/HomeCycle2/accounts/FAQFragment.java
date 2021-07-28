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
import com.example.barakatravelapp.adapter.FAQVrRvAdapter;
import com.example.barakatravelapp.data.model.getFaqResponce.Faq;
import com.example.barakatravelapp.data.model.getFaqResponce.GetFaqResponce;
import com.example.barakatravelapp.data.model.userLoginResponce.UserData;
import com.example.barakatravelapp.utils.OnEndLess;
import com.example.barakatravelapp.view.fragment.BaSeFragment;
import com.example.barakatravelapp.view.viewModel.ViewModelGetLists;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

import static com.example.barakatravelapp.data.api.ApiClient.getApiClient;


public class FAQFragment extends BaSeFragment {

    @BindView(R.id.fragment_faq_recycler_view)
    RecyclerView fragmentFaqRecyclerView;
    @BindView(R.id.load_more)
    RelativeLayout loadMore;
    @BindView(R.id.error_title)
    TextView errorTitle;
    @BindView(R.id.error_sub_view)
    LinearLayout errorSubView;
    @BindView(R.id.no_result_error_title)
    TextView noResultErrorTitle;
    @BindView(R.id.fragment_faq_sr_refresh)
    SwipeRefreshLayout fragmentFaqSrRefresh;
    private NavController navController;
    private LinearLayoutManager linearLayout;
    private UserData userData;
    private ViewModelGetLists viewModel;
    public Integer maxPage = 0;
    private OnEndLess onEndLess;
    private List<Faq> faqsListItem= new ArrayList<Faq>();
    //    private List<ItemGeneralObjectModel> rowListItem;
    private FAQVrRvAdapter faqVrRvAdapter;

    public FAQFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_faq, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        setUpActivity();
        initListener();
        init();
        return root;
    }

    private void initListener() {

        viewModel = ViewModelProviders.of(this).get(ViewModelGetLists.class);

        viewModel.makeGetFaqDataList().observe(getViewLifecycleOwner(), new Observer<GetFaqResponce>() {
            @Override
            public void onChanged(@Nullable GetFaqResponce response) {
                try {
                    if (response != null) {
//                        showToast(getActivity(), "success1");
                        if (response.getStatus().equals("success")) {
//                            maxPage = response.getData().getLastPage();

                            if (response.getFaq() != null) {
                                faqsListItem.clear();
                                faqsListItem.addAll(response.getFaq());
//                                showToast(getActivity(), "list="+getFlightsItemsListData.get(0));

                                faqVrRvAdapter.notifyDataSetChanged();
                                maxPage++;
                                if (faqsListItem.size() != 0) {
                                    noResultErrorTitle.setVisibility(View.GONE);
                                } else {
                                    noResultErrorTitle.setVisibility(View.VISIBLE);
                                }
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

    private void init() {
        linearLayout = new LinearLayoutManager(getActivity());
        fragmentFaqRecyclerView.setLayoutManager(linearLayout);

        onEndLess = new OnEndLess(linearLayout, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        loadMore.setVisibility(View.VISIBLE);

                        getFaqDataList(current_page);


                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                } else {
                    onEndLess.current_page = onEndLess.previous_page;
                }
            }
        };
        fragmentFaqRecyclerView.addOnScrollListener(onEndLess);


        faqVrRvAdapter = new FAQVrRvAdapter(getContext(), getActivity(), faqsListItem);
        fragmentFaqRecyclerView.setAdapter(faqVrRvAdapter);
//            showToast(getActivity(), "success adapter");


        if (faqsListItem.size() == 0) {
            getFaqDataList(0);
        }
//        else {
//            clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//            clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
//        }

        fragmentFaqSrRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                maxPage = 0;

                    getFaqDataList(0);


            }
        });
    }


    private void getFaqDataList(int page) {
        if (page == 0) {
            maxPage = 0;
        }
        Call<GetFaqResponce> getFaqResponceCall;

//        startShimmer(page);

        reInit();
        getFaqResponceCall = getApiClient().getFaq(page);

//            clientRestaurantsCall = getApiClient().getRestaurantsWithoutFiltter(page);
        viewModel.getFaqDataList(getActivity(), errorSubView, getFaqResponceCall, fragmentFaqSrRefresh, loadMore);
//            showToast(getActivity(), "success without fillter");


    }


    private void reInit() {
        onEndLess.previousTotal = 0;
        onEndLess.current_page = 1;
        onEndLess.previous_page = 1;
        faqsListItem = new ArrayList<>();
        faqVrRvAdapter = new FAQVrRvAdapter(getContext(), getActivity(), faqsListItem);
        fragmentFaqRecyclerView.setAdapter(faqVrRvAdapter);

    }


    public void setError(String errorTitleTxt) {
        View.OnClickListener action = new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                getFaqDataList(0);


            }
        };
        errorSubView.setVisibility(View.VISIBLE);
        errorTitle.setText(errorTitleTxt);

    }


//    private void init() {
//
//        lLayout = new LinearLayoutManager(getActivity());
//
//        fragmentFaqRecyclerView.setLayoutManager(lLayout);
////        if(getHomeDisscoverGetUmrahDataItemsListData.size()!=0 || getHomeDisscoverGetHajjDataItemsListData.size()!=0 || getHomeDisscoverGetHotelsDataItemsListData.size()!=0) {
//        rowListItem = getAllItemList();
////        }
//        faqVrRvAdapter = new FAQVrRvAdapter(getContext(), getActivity(), rowListItem);
//        fragmentFaqRecyclerView.setAdapter(faqVrRvAdapter);
//
//
//    }

//    private List<ItemGeneralObjectModel> getAllItemList() {
//
//        List<ItemGeneralObjectModel> allItems = new ArrayList<ItemGeneralObjectModel>();
//        allItems.add(new ItemGeneralObjectModel(getString(R.string.ques1), getString(R.string.ans1)));
//        allItems.add(new ItemGeneralObjectModel(getString(R.string.ques2), getString(R.string.ans2)));
//        allItems.add(new ItemGeneralObjectModel(getString(R.string.ques3), getString(R.string.ans3)));
//        allItems.add(new ItemGeneralObjectModel(getString(R.string.ques4), getString(R.string.ans4)));
//        allItems.add(new ItemGeneralObjectModel(getString(R.string.ques5), getString(R.string.ans5)));
//        allItems.add(new ItemGeneralObjectModel(getString(R.string.ques6), getString(R.string.ans6)));
//        allItems.add(new ItemGeneralObjectModel(getString(R.string.ques7), getString(R.string.ans7)));
//        allItems.add(new ItemGeneralObjectModel(getString(R.string.ques8), getString(R.string.ans8)));
//        allItems.add(new ItemGeneralObjectModel(getString(R.string.ques9), getString(R.string.ans9)));
//        allItems.add(new ItemGeneralObjectModel(getString(R.string.ques10), getString(R.string.ans10)));
//
//        return allItems;
//    }

    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new ConfirmBookingFragment());
        navController.navigate(R.id.action_FAQFragment_to_changeDetailsMoreFragment);

    }


    @OnClick(R.id.fragment_faq_back_img)
    public void onViewClicked() {
        onBack();
    }
}