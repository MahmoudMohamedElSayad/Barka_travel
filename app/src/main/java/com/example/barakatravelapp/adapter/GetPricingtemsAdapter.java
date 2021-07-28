package com.example.barakatravelapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.data.model.getUmrahAndHujjResponce.GetTopUmarAndTophajjPackage;
import com.example.barakatravelapp.data.model.getUmrahAndHujjResponce.Pricing;
import com.example.barakatravelapp.view.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.barakatravelapp.utils.HelperMethod.showToast;

public class GetPricingtemsAdapter extends RecyclerView.Adapter<GetPricingtemsAdapter.ViewHolder> {


    private final GetTopUmarAndTophajjPackage getHomeDisscoverGetItemsListData;
    private final String isDiscoverOrHajjOrUmarah;
    private final int bookPricId;
    private BaseActivity activity;
    private Context context;
    private List<Pricing> getPricingItemsListData = new ArrayList<>();
    private List<String> hotelImages = new ArrayList<>();
    private NavController navController;
    private static boolean show = false;

    public GetPricingtemsAdapter(Context context, Activity activity, int bookPricId, String isDiscoverOrHajjOrUmarah, GetTopUmarAndTophajjPackage getHomeDisscoverGetItemsListData, List<Pricing> getHomeDisscoverGetHotelsDataItemsListData, NavController navController) {
        getPricingItemsListData.clear();
        this.activity = (BaseActivity) activity;
        this.context = context;
        this.bookPricId = bookPricId;
        this.isDiscoverOrHajjOrUmarah = isDiscoverOrHajjOrUmarah;
        this.getPricingItemsListData = getHomeDisscoverGetHotelsDataItemsListData;
        this.getHomeDisscoverGetItemsListData = getHomeDisscoverGetItemsListData;

        this.navController = navController;

//                showToast(activity, String.valueOf(itemNum));
//        showToast(activity, String.valueOf(getDisscoverGetHotelsItemsListData.size()));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.cardview_hz_hajj_details_packages_pricing_item,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {


        Pricing pricingData = getPricingItemsListData.get(position);
        if (isDiscoverOrHajjOrUmarah.equalsIgnoreCase(activity.getString(R.string.My_Umrah_Bookings)) || isDiscoverOrHajjOrUmarah.equalsIgnoreCase(activity.getString(R.string.My_Hajj_Bookings))) {
            if (pricingData.getId() != bookPricId) {
                holder.cardviewHzHajjDetailsPackagesPricingItemLy.setVisibility(View.GONE);
            } else {
//                showToast(activity, String.valueOf(pricingData.getId() + " " + bookPricId));
                holder.cardviewHzHajjDetailsPackagesPricingItemNameTv.setText(pricingData.getName());
                holder.cardviewHzHajjDetailsPackagesPricingItemCostTv.setText("$ " + pricingData.getPrice());
                holder.cardviewHzHajjDetailsPackagesPricingItemNumBerRoomTv.setText(pricingData.getNumberPerRoom() + " People per room");
                holder.cardviewHzHajjDetailsPackagesPricingItemSendInquiryBtn.setVisibility(View.GONE);
            }

        } else {
            holder.cardviewHzHajjDetailsPackagesPricingItemNameTv.setText(pricingData.getName());
            holder.cardviewHzHajjDetailsPackagesPricingItemCostTv.setText("$ " + pricingData.getPrice());
            holder.cardviewHzHajjDetailsPackagesPricingItemNumBerRoomTv.setText(pricingData.getNumberPerRoom() + " People per room");
        }

    }

    private void setAction(ViewHolder holder, int position) {

        holder.cardviewHzHajjDetailsPackagesPricingItemSendInquiryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showToast(activity, "here");
                Bundle bundle = new Bundle();
                bundle.putSerializable("Object", getHomeDisscoverGetItemsListData);
                bundle.putSerializable("Object2", getPricingItemsListData.get(position));
                bundle.putString("DiscoverOrHajjOrUmrah", isDiscoverOrHajjOrUmarah);

                navController.navigate(R.id.action_luxuryUmrahPackageFragment_to_hajjAndUmrahBookingFragment, bundle);
//                HomeCycleActivity navigationActivity = (HomeCycleActivity) activity;
//                navigationActivity.setNavigation("g");
//                FoodMenueFragment foodMenueFragment=new FoodMenueFragment();
//                foodMenueFragment.restaurantsListData = clientRestaurantsDataList.get(position);
//                HelperMethod.replaceFragment(navigationActivity.getSupportFragmentManager(), R.id.home_activity_fram, new RestaurantCategoryTabsFragment());

            }
        });
    }

    @Override
    public int getItemCount() {
        return getPricingItemsListData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cardview_hz_hajj_details_packages_pricing_item_name_tv)
        TextView cardviewHzHajjDetailsPackagesPricingItemNameTv;
        @BindView(R.id.cardview_hz_hajj_details_packages_pricing_item_cost_tv)
        TextView cardviewHzHajjDetailsPackagesPricingItemCostTv;
        @BindView(R.id.cardview_hz_hajj_details_packages_pricing_item_num_ber_room_tv)
        TextView cardviewHzHajjDetailsPackagesPricingItemNumBerRoomTv;
        @BindView(R.id.cardview_hz_hajj_details_packages_pricing_item_send_inquiry_btn)
        Button cardviewHzHajjDetailsPackagesPricingItemSendInquiryBtn;
        @BindView(R.id.cardview_hz_hajj_details_packages_pricing_item_ly)
        LinearLayout cardviewHzHajjDetailsPackagesPricingItemLy;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
//            navController = Navigation.findNavController(activity, R.id.home_activity_fragment);

        }
    }
}
