package com.example.barakatravelapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.data.model.getBookingHotelsResponce.BookingsHotel;
import com.example.barakatravelapp.data.model.getHotelsResponce.GetRoom;
import com.example.barakatravelapp.data.model.getHotelsResponce.HotelData;
import com.example.barakatravelapp.data.model.userLoginResponce.UserData;
import com.example.barakatravelapp.utils.GeneralBookDescriptionDetailsDialog;
import com.example.barakatravelapp.view.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.barakatravelapp.data.local.SharedPreferencesManger.LoadUserData;

public class GetBookingsHotelsItemsAdapter extends RecyclerView.Adapter<GetBookingsHotelsItemsAdapter.ViewHolder> {


    private final String bookingType;

    private BaseActivity activity;
    private Context context;
    private List<BookingsHotel> hotelDataList = new ArrayList<>();
    //    private List<String> hotelImages = new ArrayList<>();
    private NavController navController;
    private static boolean show = false;
    private UserData userData;

    public GetBookingsHotelsItemsAdapter(Activity activity, Context context, String bookingType, NavController navController, List<BookingsHotel> hotelDataList) {
        hotelDataList.clear();
        this.activity = (BaseActivity) activity;
        this.context = context;
        this.navController = navController;
        this.bookingType = bookingType;
        this.hotelDataList = hotelDataList;
        userData = LoadUserData(activity);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.cardview_book_hotels_item,
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


        HotelData hotelList = hotelDataList.get(position).getHotel();
        List<GetRoom> getRoom = hotelList.getGetRooms();
        holder.cardviewBookHotelsItemUserNameTv.setText("Name : " + userData.getFirstName() + " " + userData.getLastName());
        holder.cardviewBookHotelsItemHotelNameTv.setText("Hotel Name : " + hotelList.getName());
        holder.cardviewBookHotelsItemFromToDateTv.setText("Reservation From : " + hotelDataList.get(position).getReservefrom() + " To " + hotelDataList.get(position).getReserveto());
        for (int i = 0; i < getRoom.size(); i++) {
            if (hotelDataList.get(position).getRoomPrice().equalsIgnoreCase(getRoom.get(i).getPrice())) {
                holder.cardviewBookHotelsItemTypeOfRoomTv.setText("Type of Room : " + getRoom.get(i).getName());
            }
        }
        int nightsNum = Integer.valueOf(hotelDataList.get(position).getTotalPrice()) / Integer.valueOf(hotelDataList.get(position).getRoomPrice());
        holder.cardviewBookHotelsItemNumberOfNightsTv.setText("Number Of Nights : " + nightsNum);
        holder.cardviewBookHotelsItemAddressTv.setText("Address : " + hotelList.getAddress());
        holder.cardviewBookHotelsItemPriceTv.setText("Price : $ " + hotelDataList.get(position).getRoomPrice() + " / Night");
        holder.cardviewBookHotelsItemTotalPriceTv.setText("Totsl Price : $ " + hotelDataList.get(position).getTotalPrice());
        String status = hotelDataList.get(position).getStatus();
        if (!status.equalsIgnoreCase("complete")) {
            holder.cardviewBookHotelsItemStatusLy.setBackgroundResource(R.drawable.circle_btn_yello_shape);
        }
        holder.cardviewBookHotelsItemStatusTv.setText(status);
    }

    private void setAction(ViewHolder holder, int position) {

        holder.cardviewBookHotelsItemDiscriptionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GeneralBookDescriptionDetailsDialog().showDialog(activity, hotelDataList.get(position).getHotel().getDescription(), "hotel");

//                Bundle bundle = new Bundle();
//                bundle.putSerializable("Object", hotelDataList.get(position).getHotel());
//                bundle.putString("DiscoverOrHotel", "book_hotel");
//                bundle.putString("getRoomPrice", hotelDataList.get(position).getRoomPrice());
//                navController.navigate(R.id.action_myUmrahBookingFragment_to_hottelViewFragment, bundle);
//                HomeCycleActivity navigationActivity = (HomeCycleActivity) activity;
//                navigationActivity.setNavigation("g");

            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        }
        return 1;
    }

    @Override
    public int getItemCount() {
        return hotelDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardview_book_hotels_item_status_tv)
        TextView cardviewBookHotelsItemStatusTv;
        @BindView(R.id.cardview_book_hotels_item_status_ly)
        LinearLayout cardviewBookHotelsItemStatusLy;
        @BindView(R.id.cardview_book_hotels_item_user_name_tv)
        TextView cardviewBookHotelsItemUserNameTv;
        @BindView(R.id.cardview_book_hotels_item_hotel_name_tv)
        TextView cardviewBookHotelsItemHotelNameTv;
        @BindView(R.id.cardview_book_hotels_item_from_to_date_tv)
        TextView cardviewBookHotelsItemFromToDateTv;
        @BindView(R.id.cardview_book_hotels_item_type_of_room_tv)
        TextView cardviewBookHotelsItemTypeOfRoomTv;
        @BindView(R.id.cardview_book_hotels_item_number_of_nights_tv)
        TextView cardviewBookHotelsItemNumberOfNightsTv;
        @BindView(R.id.cardview_book_hotels_item_address_tv)
        TextView cardviewBookHotelsItemAddressTv;
        @BindView(R.id.cardview_book_hotels_item_price_tv)
        TextView cardviewBookHotelsItemPriceTv;
        @BindView(R.id.cardview_book_hotels_item_total_price_tv)
        TextView cardviewBookHotelsItemTotalPriceTv;
        @BindView(R.id.cardview_book_hotels_item_discription_btn)
        TextView cardviewBookHotelsItemDiscriptionBtn;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
//            navController = Navigation.findNavController(activity, R.id.home_activity_fragment);

        }
    }
}
