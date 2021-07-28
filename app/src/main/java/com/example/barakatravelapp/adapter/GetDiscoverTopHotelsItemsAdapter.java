package com.example.barakatravelapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.data.model.getHotelsResponce.HotelData;
import com.example.barakatravelapp.view.activity.BaseActivity;
import com.example.barakatravelapp.view.activity.HomeCycleActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.barakatravelapp.utils.HelperMethod.onLoadImageFromUrl;

public class GetDiscoverTopHotelsItemsAdapter extends RecyclerView.Adapter<GetDiscoverTopHotelsItemsAdapter.ViewHolder> {


    private final int itemNum;
    private BaseActivity activity;
    private Context context;
    private List<HotelData> getDisscoverGetHotelsItemsListData = new ArrayList<>();
    private List<String> hotelImages= new ArrayList<>();
    private NavController navController;
    private static boolean show =false;

    public GetDiscoverTopHotelsItemsAdapter(Context context, Activity activity, NavController navController, List<HotelData> getHomeDisscoverGetHotelsDataItemsListData, int itemNum) {
        getDisscoverGetHotelsItemsListData.clear();
        this.activity = (BaseActivity) activity;
        this.context = context;
        this.navController = navController;
        this.getDisscoverGetHotelsItemsListData = getHomeDisscoverGetHotelsDataItemsListData;
        this.itemNum = itemNum;
//                showToast(activity, String.valueOf(itemNum));
//        showToast(activity, String.valueOf(getDisscoverGetHotelsItemsListData.size()));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.cardview_hz_discover_item,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {

        final int itemType = getItemViewType(position);
//        showToast(activity, String.valueOf(getDisscoverGetHotelsItemsListData.get(position).getRate()));
        HotelData hotelData = getDisscoverGetHotelsItemsListData.get(position);
//        if (itemNum == 3 ) {
            holder.cardviewHzDiscoverItemRateImgHide.setVisibility(View.VISIBLE);
            holder.cardviewHzDiscoverItemTvRateAndNightsNum.setText(String.valueOf(hotelData.getRate()));
            holder.cardviewHzDiscoverItemPriceTv.setText("$ "+hotelData.getMinPrice());
//        showToast(activity, String.valueOf(getDisscoverGetHotelsItemsListData.size()+ "  " +String.valueOf(itemNum)));
        holder.cardviewHzDiscoverItemDateTv.setVisibility(View.VISIBLE);
                holder.cardviewHzDiscoverItemDateOrNameTv.setText(hotelData.getName());
            holder.cardviewHzDiscoverItemDateTv.setText(hotelData.getGetRooms().get(0).getFromDate());
            hotelImages = hotelData.getHotelImages();
            String hotelImage = "https://www.barakatravel.net/"+hotelImages.get(0).trim();
            onLoadImageFromUrl(holder.cardviewHzDiscoverItemImg, hotelImage, context);
//            if(hotelData.getRate()!= null){
//                holder.cardviewHzDiscoverItemImgShadowHide.setVisibility(View.VISIBLE);
//            }
//    }
//        if (itemNum == 4 ) {
//            showToast(activity, String.valueOf(getDisscoverGetHotelsItemsListData.size()));
//            holder.cardviewHzDiscoverItemRateImgHide.setVisibility(View.VISIBLE);
//            holder.cardviewHzDiscoverItemTvRateAndNightsNum.setText(hotelData.getRate());
//            holder.cardviewHzDiscoverItemPriceTv.setText("$ "+hotelData.getMinPrice());
//            holder.cardviewHzDiscoverItemDateOrNameTv.setText(hotelData.getGetRooms().get(0).getFromDate());
////                holder.cardviewHzDiscoverItemDateOrNameTv.setText(hotelData.getName());
//            holder.cardviewHzDiscoverItemDateTv.setText(hotelData.getName());
//            hotelImages = hotelData.getHotelImages();
//            String hotelImage = "https://www.barakatravel.net/"+hotelImages.get(0).trim();
//            onLoadImageFromUrl(holder.cardviewHzDiscoverItemImg, hotelImage, context);
//            if(hotelData.getRate()!= null){
//                holder.cardviewHzDiscoverItemImgShadowHide.setVisibility(View.VISIBLE);
//            }
//        }
    }

    private void setAction(ViewHolder holder, int position) {

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("Object",  getDisscoverGetHotelsItemsListData.get(position));
                bundle.putString("DiscoverOrHotel", "discover");
                navController.navigate(R.id.action_navigation_discover_to_hottelViewFragment,bundle);
                HomeCycleActivity navigationActivity = (HomeCycleActivity) activity;
                navigationActivity.setNavigation("g");
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return 0;
        }
        return 1;
    }
    @Override
    public int getItemCount() {
        return getDisscoverGetHotelsItemsListData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cardview_hz_discover_item_img)
        ImageView cardviewHzDiscoverItemImg;
        @BindView(R.id.cardview_hz_discover_item_img_shadow_hide)
        ImageView cardviewHzDiscoverItemImgShadowHide;
        @BindView(R.id.cardview_hz_discover_item_tv_rate_and_nights_num)
        TextView cardviewHzDiscoverItemTvRateAndNightsNum;
        @BindView(R.id.cardview_hz_discover_item_rate_img_hide)
        ImageView cardviewHzDiscoverItemRateImgHide;
        @BindView(R.id.cardview_hz_discover_item_price_tv)
        TextView cardviewHzDiscoverItemPriceTv;
        @BindView(R.id.cardview_hz_discover_item_date_tv)
        TextView cardviewHzDiscoverItemDateTv;
        @BindView(R.id.cardview_hz_discover_item_date_or_name_tv)
        TextView cardviewHzDiscoverItemDateOrNameTv;
        @BindView(R.id.cardview_hz_discover_item_discription_gray_for_top_umrah_tv)
        TextView cardviewHzDiscoverItemDiscriptionGrayForTopUmrahTv;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
//            navController = Navigation.findNavController(activity, R.id.home_activity_fragment);

        }
    }
}
