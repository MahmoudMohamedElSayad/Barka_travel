package com.example.barakatravelapp.view.fragment.HomeCycle2.accounts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.adapter.AccountMyBookingsVrRvAdapter;
import com.example.barakatravelapp.adapter.EVisaIStatictemsAdapter;
import com.example.barakatravelapp.data.model.ItemGeneralObjectModel;
import com.example.barakatravelapp.view.fragment.BaSeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ChooseStaticEVisaFragment extends BaSeFragment {

    @BindView(R.id.fragment_choose_static_evisa_recycler_view)
    RecyclerView fragmentChooseStaticEvisaRecyclerView;
    private NavController navController;
    private LinearLayoutManager lLayout;
//    private UserData userData;
    private List<ItemGeneralObjectModel> rowListItem;
    private EVisaIStatictemsAdapter chooseStaticEVisaFragment;
    public ChooseStaticEVisaFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_choose_static_evisa, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        init();
        return root;
    }

    private void init() {

        lLayout = new LinearLayoutManager(getActivity());

        fragmentChooseStaticEvisaRecyclerView.setLayoutManager(lLayout);
//        if(getHomeDisscoverGetUmrahDataItemsListData.size()!=0 || getHomeDisscoverGetHajjDataItemsListData.size()!=0 || getHomeDisscoverGetHotelsDataItemsListData.size()!=0) {
        rowListItem = getAllItemList();
//        }
        chooseStaticEVisaFragment = new EVisaIStatictemsAdapter(getActivity(), getContext(),rowListItem,navController);
        fragmentChooseStaticEvisaRecyclerView.setAdapter(chooseStaticEVisaFragment);


    }

    private List<ItemGeneralObjectModel> getAllItemList() {

        List<ItemGeneralObjectModel> allItems = new ArrayList<ItemGeneralObjectModel>();
        allItems.add(new ItemGeneralObjectModel(getString(R.string.USA_Canadian_Passport),getString(R.string.canda_price),getString(R.string.canda_year_validaty),getString(R.string.canda_visa_time),R.drawable.us1,R.drawable.us2_canada));
        allItems.add(new ItemGeneralObjectModel(getString(R.string.indian_Passport),getString(R.string.indian_price),getString(R.string.indian_year_validaty),getString(R.string.india_visa_time),R.drawable.india,R.drawable.pak_india));
        allItems.add(new ItemGeneralObjectModel(getString(R.string.bngiladish_Passport),getString(R.string.bngladish_price),getString(R.string.bngladish_year_validaty),getString(R.string.bngladish_visa_time),R.drawable.greenred,R.drawable.greenred));



        return allItems;
    }
    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment, new ChangeDetailsFragment());
        navController.navigate(R.id.action_fragment_choose_static_evisa_to_navigation_account);

    }

    @OnClick(R.id.fragment_choose_static_evisa_back_btn)
    public void onViewClicked() {
        onBack();
    }
}