package com.example.barakatravelapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.data.model.ItemGeneralObjectModel;
import com.example.barakatravelapp.data.model.getHotelsResponce.HotelData;
import com.example.barakatravelapp.data.model.getUmrahAndHujjResponce.GetTopUmarAndTophajjPackage;
import com.example.barakatravelapp.utils.DialogAdapterCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubHomeDiscoverVrRvAdapter extends RecyclerView.Adapter<SubHomeDiscoverVrRvAdapter.ViewHolder> {


    private final DialogAdapterCallback dialogAdapterCallback;
    private final NavController navController;
    private Context context;
    private Activity activity;
    private List<ItemGeneralObjectModel> itemList = new ArrayList<>();
    private LinearLayoutManager lLayout;
    private List<GetTopUmarAndTophajjPackage> getHomeDisscoverGetHajjDataItemsListData = new ArrayList<GetTopUmarAndTophajjPackage>();
    private List<GetTopUmarAndTophajjPackage> getHomeDisscoverGetUmrahDataItemsListData = new ArrayList<GetTopUmarAndTophajjPackage>();
    private List<HotelData> getHomeDisscoverGetHotelsDataItemsListData = new ArrayList<HotelData>();
    private List<HotelData> getHomeDisscoverGetHotelsMakahDataItemsListData = new ArrayList<HotelData>();
    private List<HotelData> getHomeDisscoverGetHotelsMadinaDataItemsListData = new ArrayList<HotelData>();
    private LinearLayoutManager linearLayoutHorizental;
    private SubHomeDiscoverTopHzRvAdapter SubHomeDiscoverTopHzRvAdapter;
    private SubHomeDiscoverTopHzRvAdapter SubHomeDiscoverTopHzRvAdapter2;
    private GetDiscoverTopHotelsItemsAdapter SubHomeDiscoverTopHzRvAdapter3;
    private GetDiscoverTopHotelsItemsAdapter SubHomeDiscoverTopHzRvAdapter4;
    private LinearLayoutManager linearLayoutHorizental2;
//    private ProfileItemAdapter homeSubHzItemAdapter;
//    private SubHomeCategoryHzRvItem2Adapter subHomeCategoryHzRvItem2Adapter;
//    List<ProductDataModel> rowListItem;
//    private ClientData clientData;
//    private ApiService apiService;

    public SubHomeDiscoverVrRvAdapter(Context context,
                                      Activity activity, NavController navController, DialogAdapterCallback dialogAdapterCallback,
                                      List<ItemGeneralObjectModel> itemList,
                                      List<GetTopUmarAndTophajjPackage> getHomeDisscoverGetHajjDataItemsListData, List<GetTopUmarAndTophajjPackage> getHomeDisscoverGetUmrahDataItemsListData, List<HotelData> getHomeDisscoverGetHotelsDataItemsListData) {
        this.context = context;
        this.activity = activity;
//        this.clientRestaurantsDataList.clear();
        this.itemList = itemList;
        this.navController = navController;
        this.getHomeDisscoverGetHajjDataItemsListData = getHomeDisscoverGetHajjDataItemsListData;
        this.getHomeDisscoverGetUmrahDataItemsListData = getHomeDisscoverGetUmrahDataItemsListData;
        this.getHomeDisscoverGetHotelsDataItemsListData = getHomeDisscoverGetHotelsDataItemsListData;
        this.dialogAdapterCallback=dialogAdapterCallback;
//        clientData = LoadUserData(activity);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_sub_hz_rv_item,
                parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        setData(holder, position);
        setAction(holder, position);

    }

    private void setData(ViewHolder holder, int position) {
        try {
            final int itemType = getItemViewType(position);
            holder.position = position;
            holder.homeDiscoverFragmentSubHomeRvItemTv.setText(itemList.get(position).getName());
//            holder.homeDiscoverFragmentSubHomeRvItemHzSrVw.setVisibility(View.VISIBLE);
            if (position == 0) {
//                holder.homeDiscoverFragmentSubHomeRvItemHzSrVw.setVisibility(View.VISIBLE);
                initHozental(holder, getHomeDisscoverGetUmrahDataItemsListData, null, 1);
            }
            if (position == 1) {
                initHozental(holder, getHomeDisscoverGetHajjDataItemsListData, null, 2);

            }
//            if (position == 2) {
//                holder.homeDiscoverFragmentSubHomeRvItemHzSrVw.setVisibility(View.VISIBLE);
//                showToast(activity, String.valueOf(getHomeDisscoverGetHotelsDataItemsListData.get(0).getMinPrice()));
//                initHozental2(holder, getHomeDisscoverGetHajjDataItemsListData, null, 3);
//
//            }
            if (position == 2) {
                splitTopHotelToMakkaAndMadina();
                holder.subHomeVervItem2CategoryNameTv.setVisibility(View.VISIBLE);
                holder.subHomeVervItem2CategoryNameTv.setText(activity.getString(R.string.makkah));
                initHozental2(holder,null,getHomeDisscoverGetHotelsMakahDataItemsListData,3);
//                holder.homeDiscoverFragmentSubHomeRvItemVrRv.setVisibility(View.VISIBLE);
//                List<ItemGeneralObjectModel> rowListItem = getAllItemList();
//                lLayout = new LinearLayoutManager(activity);
//
//                holder.homeDiscoverFragmentSubHomeRvItemVrRv.setLayoutManager(lLayout);
//
//                ShowDayByDayVrRvAdapter rcAdapter = new ShowDayByDayVrRvAdapter(context, activity, rowListItem, getHomeDisscoverGetHotelsMadinaDataItemsListData, getHomeDisscoverGetHotelsMakahDataItemsListData);
//                holder.homeDiscoverFragmentSubHomeRvItemVrRv.setAdapter(rcAdapter);
            }
            if (position == 3) {
//                splitTopHotelToMakkaAndMadina();
                holder.subHomeVervItem2CategoryNameTv.setVisibility(View.VISIBLE);
                holder.homeDiscoverFragmentSubHomeRvItemTv.setVisibility(View.GONE);
                holder.subHomeVervItem2CategoryNameTv.setText(activity.getString(R.string.madinah));
//                holder.subHomeVervItem2CategoryLy.setVisibility(View.VISIBLE);
                initHozental2(holder,null, getHomeDisscoverGetHotelsMadinaDataItemsListData, 4);
                holder.subHomeVervItemPaddingTv.setVisibility(View.VISIBLE);
            }


        } catch (Exception e) {

        }

    }

    private void splitTopHotelToMakkaAndMadina() {
        for (int ind = 0; ind < getHomeDisscoverGetHotelsDataItemsListData.size(); ind++) {
            HotelData hotelData = getHomeDisscoverGetHotelsDataItemsListData.get(ind);
            if (hotelData.getCity().equals("Makkah")||hotelData.getCity().equals("Mecca")) {
                getHomeDisscoverGetHotelsMakahDataItemsListData.add(hotelData);
//                showToast(activity, "list=" + getHomeDisscoverGetHotelsMakahDataItemsListData.get(1).getCity());
            } else {
//            if(hotelData.getCity().equals("Makkah")){
                getHomeDisscoverGetHotelsMadinaDataItemsListData.add(hotelData);
            }
        }
    }


    private void setAction(final ViewHolder holder, final int position) {

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                HomeCycleActivity homeCycleActivity = (HomeCycleActivity) activity;
//                Toast.makeText(v.getContext(), "Clicked Country Position = " + position, Toast.LENGTH_SHORT).show();
//                if (position == 0) {
//                    replaceFragmentWithAnimation(homeCycleActivity.getSupportFragmentManager(), R.id.home_activity_fram, new SubCategoryFragment(), "t");
//                    homeCycleActivity.setNavigationAndToolBar(View.GONE, true);
//                }
            }
        });

    }

    private List<ItemGeneralObjectModel> getAllItemList() {

        List<ItemGeneralObjectModel> allItems = new ArrayList<ItemGeneralObjectModel>();
        allItems.add(new ItemGeneralObjectModel(activity.getString(R.string.makkah)));
        allItems.add(new ItemGeneralObjectModel(activity.getString(R.string.madinah)));


        return allItems;
    }


    private void initHozental(ViewHolder holder, List<GetTopUmarAndTophajjPackage> getHomeDisscoverGetItemsListData, List<HotelData> getHomeDisscoverGetHotelsDataItemsListData, int itemNum) {
        linearLayoutHorizental = new LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false);
        holder.homeDiscoverFragmentSubHomeRvItemHzRv.setLayoutManager(linearLayoutHorizental);
        holder.homeDiscoverFragmentSubHomeRvItemHzRv.setHasFixedSize(true);
//        clientGetRestaurantsFiltterList(0);

        if (itemNum == 1) {
            SubHomeDiscoverTopHzRvAdapter = new SubHomeDiscoverTopHzRvAdapter(context, activity, getHomeDisscoverGetItemsListData, getHomeDisscoverGetHotelsDataItemsListData, itemNum,dialogAdapterCallback);
            holder.homeDiscoverFragmentSubHomeRvItemHzRv.setAdapter(SubHomeDiscoverTopHzRvAdapter);
        }

        if (itemNum == 2) {
            SubHomeDiscoverTopHzRvAdapter2 = new SubHomeDiscoverTopHzRvAdapter(context, activity, getHomeDisscoverGetItemsListData, getHomeDisscoverGetHotelsDataItemsListData, itemNum,dialogAdapterCallback);
            holder.homeDiscoverFragmentSubHomeRvItemHzRv.setAdapter(SubHomeDiscoverTopHzRvAdapter2);
        }
//        if (itemNum ==3){
//            SubHomeDiscoverTopHzRvAdapter3 = new GetDiscoverTopHotelsItemsAdapter(context, activity,getHomeDisscoverGetHotelsDataItemsListData,itemNum);
//            holder.homeDiscoverFragmentSubHomeRvItemHzRv.setAdapter(SubHomeDiscoverTopHzRvAdapter3);}
//
//        if (itemNum ==4){
//            SubHomeDiscoverTopHzRvAdapter4 = new GetDiscoverTopHotelsItemsAdapter(context, activity,getHomeDisscoverGetHotelsDataItemsListData,itemNum);
//            holder.homeDiscoverFragmentSubHomeRvItemHzRv.setAdapter(SubHomeDiscoverTopHzRvAdapter4);}


    }

    private void initHozental2(ViewHolder holder, List<GetTopUmarAndTophajjPackage> getHomeDisscoverGetItemsListData, List<HotelData> getHomeDisscoverGetHotelsDataItemsListData, int itemNum) {
        linearLayoutHorizental2 = new LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false);
        holder.homeDiscoverFragmentSubHomeRvItemHzRv.setLayoutManager(linearLayoutHorizental2);
        holder.homeDiscoverFragmentSubHomeRvItemHzRv.setHasFixedSize(true);
//        clientGetRestaurantsFiltterList(0);

        if (itemNum ==3){
            SubHomeDiscoverTopHzRvAdapter3 = new GetDiscoverTopHotelsItemsAdapter(context, activity,navController,getHomeDisscoverGetHotelsDataItemsListData,itemNum);
            holder.homeDiscoverFragmentSubHomeRvItemHzRv.setAdapter(SubHomeDiscoverTopHzRvAdapter3);
//            showToast(activity, String.valueOf(itemNum));

        }

        if (itemNum ==4){
            SubHomeDiscoverTopHzRvAdapter4 = new GetDiscoverTopHotelsItemsAdapter(context, activity, navController, getHomeDisscoverGetHotelsDataItemsListData,itemNum);
            holder.homeDiscoverFragmentSubHomeRvItemHzRv.setAdapter(SubHomeDiscoverTopHzRvAdapter4);}




    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_discover_fragment_sub_home_rv_item_tv)
        TextView homeDiscoverFragmentSubHomeRvItemTv;
        @BindView(R.id.home_discover_fragment_sub_home_rv_item_hz_rv)
        RecyclerView homeDiscoverFragmentSubHomeRvItemHzRv;
        @BindView(R.id.home_discover_fragment_sub_home_rv_item_hz_sr_vw)
        ScrollView homeDiscoverFragmentSubHomeRvItemHzSrVw;
//        @BindView(R.id.home_discover_fragment_sub_home_rv_item_vr_rv)
//        RecyclerView homeDiscoverFragmentSubHomeRvItemVrRv;
        @BindView(R.id.sub_home_verv_item_padding_tv)
        TextView subHomeVervItemPaddingTv;
        @BindView(R.id.home_discover_fragment_sub_home__name2_tv)
        TextView subHomeVervItem2CategoryNameTv;
//        @BindView(R.id.sub_home_verv_item2_hz_rv)
//        RecyclerView subHomeVervItem2HzRv;
//        @BindView(R.id.sub_home_verv_item2_category_ly)
//        LinearLayout subHomeVervItem2CategoryLy;
        private View view;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);

        }
    }
}
