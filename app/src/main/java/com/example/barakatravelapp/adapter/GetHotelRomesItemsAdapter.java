package com.example.barakatravelapp.adapter;

import android.app.Activity;
import android.content.Context;
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
import com.example.barakatravelapp.utils.HotelBookingDialog;
import com.example.barakatravelapp.view.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.barakatravelapp.utils.HelperMethod.onLoadImageFromUrl;

public class GetHotelRomesItemsAdapter extends RecyclerView.Adapter<GetHotelRomesItemsAdapter.ViewHolder> {


    private final HotelData hotelData;
    private final String isDiscoverOrHotel;
    private final String roomPrice;
    private BaseActivity activity;
    private Context context;
    private List<GetRoom> getroomsItemsListData = new ArrayList<>();
    private List<String> hotelImages = new ArrayList<>();
    private NavController navController;
    private static boolean show = false;

    public GetHotelRomesItemsAdapter(Context context, Activity activity, String roomPrice, String isDiscoverOrHotel, HotelData hotelData, List<GetRoom> getroomsItemsListData, NavController navController) {
        this.activity = (BaseActivity) activity;
        this.context = context;
        this.roomPrice = roomPrice;
        this.isDiscoverOrHotel = isDiscoverOrHotel;
        this.getroomsItemsListData = getroomsItemsListData;
        this.hotelData = hotelData;

        this.navController = navController;

//                showToast(activity, String.valueOf(itemNum));
//        showToast(activity, String.valueOf(getDisscoverGetHotelsItemsListData.size()));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.cardview_hotel_room_item,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {
        GetRoom getRoom = getroomsItemsListData.get(position);
        if (isDiscoverOrHotel.equalsIgnoreCase("book_hotel")) {
            if(getRoom.getPrice()!=roomPrice){
                holder.cardviewHotelRoomItemCr.setVisibility(View.GONE);

            }else {
                holder.cardviewHotelRoomItemNameTv.setText(getRoom.getName());
                holder.cardviewHotelRoomItemMaxNumBerRoomTvCostTv.setText("$ " + getRoom.getPrice());
                holder.cardviewHotelRoomItemMaxNumBerRoomTv.setText(getRoom.getMaxinum().toString());
                String hotelImage = "https://www.barakatravel.net/" + getRoom.getRoomImage().trim();
                onLoadImageFromUrl(holder.cardviewHotelRoomItemImg, hotelImage, context);
                holder.cardviewHzHajjDetailsPackagesPricingItemSendInquiryBtn.setVisibility(View.GONE);
            }
        } else {
//        showToast(activity, String.valueOf(getDisscoverGetHotelsItemsListData.get(position).getRate()));
            holder.cardviewHotelRoomItemNameTv.setText(getRoom.getName());
            holder.cardviewHotelRoomItemMaxNumBerRoomTvCostTv.setText("$ " + getRoom.getPrice());
            holder.cardviewHotelRoomItemMaxNumBerRoomTv.setText(getRoom.getMaxinum().toString());
            String hotelImage = "https://www.barakatravel.net/" + getRoom.getRoomImage().trim();
            onLoadImageFromUrl(holder.cardviewHotelRoomItemImg, hotelImage, context);
        }
    }

    private void setAction(ViewHolder holder, int position) {

        holder.cardviewHzHajjDetailsPackagesPricingItemSendInquiryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showToast(activity, "here");

                new HotelBookingDialog().showDialog(activity, getroomsItemsListData.get(position), hotelData);

            }
        });
    }

    @Override
    public int getItemCount() {
        return getroomsItemsListData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cardview_hotel_room_item_img)
        ImageView cardviewHotelRoomItemImg;
        @BindView(R.id.cardview_hotel_room_item_name_tv)
        TextView cardviewHotelRoomItemNameTv;
        @BindView(R.id.cardview_hotel_room_item_max_num_ber_room_tv)
        TextView cardviewHotelRoomItemMaxNumBerRoomTv;
        @BindView(R.id.cardview_hotel_room_item_max_num_ber_room_tv_cost_tv)
        TextView cardviewHotelRoomItemMaxNumBerRoomTvCostTv;
        @BindView(R.id.cardview_hz_hajj_details_packages_pricing_item_send_inquiry_btn)
        TextView cardviewHzHajjDetailsPackagesPricingItemSendInquiryBtn;
        @BindView(R.id.cardview_hotel_room_item_cr)
        LinearLayout cardviewHotelRoomItemCr;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
//            navController = Navigation.findNavController(activity, R.id.home_activity_fragment);

        }
    }
}
