package com.example.barakatravelapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.data.model.getHotelsResponce.GetRoom;
import com.example.barakatravelapp.data.model.getHotelsResponce.HotelData;
import com.example.barakatravelapp.view.activity.BaseActivity;
import com.example.barakatravelapp.view.activity.HomeCycleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.barakatravelapp.utils.HelperMethod.onLoadImageFromUrl;

public class GetHotelsItemsAdapter extends RecyclerView.Adapter<GetHotelsItemsAdapter.ViewHolder> {


    private BaseActivity activity;
    private Context context;
    private List<HotelData> hotelDataList= new ArrayList<>();
    private List<String> hotelImages= new ArrayList<>();
    private NavController navController;
    private static boolean show =false;

    public GetHotelsItemsAdapter(Activity activity, Context context, NavController navController, List<HotelData> hotelDataList) {
        hotelDataList.clear();
        this.activity = (BaseActivity) activity;
        this.context = context;
        this.navController = navController;
        this.hotelDataList = hotelDataList;    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.cardview_general_vert_hajj_and_hotels_item,
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

//        if (itemType == 0 && show == false) {
//            holder.cardviewGeneralVertHajjAndHotelsItemTitleTv.setVisibility(View.VISIBLE);
//            show =true;
//            showToast(activity,String.valueOf(position));
//        }
        HotelData hotelList = hotelDataList.get(position);
        List<GetRoom> getRoom =  hotelList.getGetRooms();
        holder.cardviewGeneralVertHajjAndHotelsItemNameTv.setText(hotelList.getName());
        holder.cardviewGeneralVertHajjAndHotelsItemPriceTv.setText("$ "+hotelList.getMinPrice());
        holder.cardviewGeneralVertHajjAndHotelsItemFromDateTv.setText(getRoom.get(0).getFromDate());
        holder.cardviewGeneralVertHajjAndHotelsItemToDateTv.setText(getRoom.get(0).getToDate());
        holder.cardviewGeneralVertHajjAndHotelsItemRateImg.setVisibility(View.VISIBLE);
////        if (flightList.getIsOffer()!=null)
        holder.cardviewGeneralVertHajjAndHotelsItemTvRateAndNightsNum.setText(hotelList.getRate().toString());
        hotelImages = hotelList.getHotelImages();
        String hotelImage = "https://www.barakatravel.net/"+hotelImages.get(0).trim();
//        Glide.with(context).load(hotelImages.get(0)).asBitmap().override(1080, 600).into(holder.cardviewGeneralVertHajjAndHotelsItemHotelImg);
//                                        showToast(activity,hotelImages.get(0) );
        onLoadImageFromUrl(holder.cardviewGeneralVertHajjAndHotelsItemHotelImg, hotelImage.trim(), context);
    }

    private void setAction(ViewHolder holder, int position) {

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("Object",  hotelDataList.get(position));
                bundle.putString("DiscoverOrHotel", "hotel");
                navController.navigate(R.id.action_navigation_hotels_to_hottelViewFragment,bundle);
                HomeCycleActivity navigationActivity = (HomeCycleActivity) activity;
                navigationActivity.setNavigation("g");
//                navController.navigate(R.id.action_navigation_flight_to_flightDetailsFragment);
//                navigationActivity.setNavigation("g");
//                FoodMenueFragment foodMenueFragment=new FoodMenueFragment();
//                foodMenueFragment.restaurantsListData = clientRestaurantsDataList.get(position);
//                HelperMethod.replaceFragment(navigationActivity.getSupportFragmentManager(), R.id.home_activity_fram, new RestaurantCategoryTabsFragment());

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
        return hotelDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

//        @BindView(R.id.cardview_general_vert_hajj_and_hotels_item_title_tv)
//        TextView cardviewGeneralVertHajjAndHotelsItemTitleTv;
        @BindView(R.id.cardview_general_vert_hajj_and_hotels_item_hotel_img)
        ImageView cardviewGeneralVertHajjAndHotelsItemHotelImg;
        @BindView(R.id.cardview_general_vert_hajj_and_hotels_item_offer_shadow_img)
        ImageView cardviewGeneralVertHajjAndHotelsItemOfferShadowImg;
        @BindView(R.id.cardview_general_vert_hajj_and_hotels_item_tv_rate_and_nights_num)
        TextView cardviewGeneralVertHajjAndHotelsItemTvRateAndNightsNum;
        @BindView(R.id.cardview_general_vert_hajj_and_hotels_item_rate_img)
        ImageView cardviewGeneralVertHajjAndHotelsItemRateImg;
        @BindView(R.id.cardview_general_vert_hajj_and_hotels_item_price_tv)
        TextView cardviewGeneralVertHajjAndHotelsItemPriceTv;
        @BindView(R.id.cardview_general_vert_hajj_and_hotels_item_nights_tv)
        TextView cardviewGeneralVertHajjAndHotelsItemNightsTv;
        @BindView(R.id.cardview_general_vert_hajj_and_hotels_item_from_date_tv)
        TextView cardviewGeneralVertHajjAndHotelsItemFromDateTv;
        @BindView(R.id.cardview_general_vert_hajj_and_hotels_item_to_date_tv)
        TextView cardviewGeneralVertHajjAndHotelsItemToDateTv;
        @BindView(R.id.cardview_general_vert_hajj_and_hotels_item_additional_part)
        LinearLayout cardviewGeneralVertHajjAndHotelsItemAdditionalPart;
        @BindView(R.id.cardview_general_vert_hajj_and_hotels_item_name_tv)
        TextView cardviewGeneralVertHajjAndHotelsItemNameTv;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
//            navController = Navigation.findNavController(activity, R.id.home_activity_fragment);

        }
    }
}
