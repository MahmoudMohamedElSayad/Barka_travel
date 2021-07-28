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
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.data.model.getBookingFlightsResponce.BookingFlight;
import com.example.barakatravelapp.data.model.getFlightResponce.FlightData;
import com.example.barakatravelapp.data.model.userLoginResponce.UserData;
import com.example.barakatravelapp.utils.GeneralBookDescriptionDetailsDialog;
import com.example.barakatravelapp.utils.GeneralHajjDescriptionDetailsDialog;
import com.example.barakatravelapp.view.activity.BaseActivity;
import com.example.barakatravelapp.view.activity.HomeCycleActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.barakatravelapp.data.local.SharedPreferencesManger.LoadUserData;
import static com.example.barakatravelapp.utils.HelperMethod.onLoadImageFromUrl;


public class GetBookingsFlightsItemsAdapter extends RecyclerView.Adapter<GetBookingsFlightsItemsAdapter.ViewHolder> {


    private final String bookingType;
    private BaseActivity activity;
    private Context context;
    private List<BookingFlight> flightsListData;
    private NavController navController;
    private UserData userData;

    public GetBookingsFlightsItemsAdapter(Activity activity, Context context, String bookingType, List<BookingFlight> flightsListData, NavController navController) {
        this.activity = (BaseActivity) activity;
        this.context = context;
        this.bookingType = bookingType;
        this.flightsListData = flightsListData;
        this.navController = navController;
        userData = LoadUserData(activity);

//        showToast(activity, "date="+flightsListData.get(0).getFlight().getReservationTo());
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_book_flight_offers_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);

    }

    private void setData(ViewHolder holder, int position) {
        holder.position = position;
        FlightData flightList = flightsListData.get(position).getFlight();
//        showToast(activity, "list="+flightList.getReservationTo());
        holder.cardviewBookFlightOffersItemUserNameTv.setText("Name : " +userData.getFirstName()+" "+userData.getLastName());
        holder.cardviewBookFlightOffersItemFlightNameTv.setText("Flight Name : " +flightList.getFlightName());
        holder.cardviewBookFlightOffersItemPlacesCoveredTv.setText("Places Covered : " + flightList.getFrom() + " To " + flightList.getTo());
        holder.cardviewBookFlightOffersItemFromToDateTv.setText("Reservation From : " + flightList.getReservationFrom() + " To " + flightList.getReservationTo());
        holder.cardviewBookFlightOffersItemPriceTv.setText("Price : " +flightsListData.get(position).getTotalPrice());
        int totalMembers =flightsListData.get(position).getNumOfAdults()+flightsListData.get(position).getNumOfChild();
        holder.cardviewBookFlightOffersItemTotalMembersTv.setText("Total Members : " +totalMembers+" ( Adult:"+flightsListData.get(position).getNumOfAdults()+" , Children:"+flightsListData.get(position).getNumOfChild()+" )");
//        holder.cardviewBookFlightOffersItemFlightNameTv.setText("Flight Name : " +flightList.getFlightName());
        String status =flightsListData.get(position).getStatus();
       if (!status.equalsIgnoreCase("complete")){
            holder.cardviewBookFlightOffersItemStatusLy.setBackgroundResource(R.drawable.circle_btn_yello_shape);
        }
        holder.cardviewBookFlightOffersItemStatusTv.setText( flightsListData.get(position).getStatus());

    }


    private void setAction(ViewHolder holder, int position) {
        holder.cardviewBookFlightOffersItemDiscriptionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GeneralBookDescriptionDetailsDialog().showDialog(activity,flightsListData.get(position).getFlight().getDescription() , "flight");

//                Bundle bundle = new Bundle();
//                bundle.putString("BookingFlight", "bookingFlight");
//                bundle.putSerializable("Object", flightsListData.get(position).getFlight());
//                navController.navigate(R.id.action_myUmrahBookingFragment_to_flightDetailsFragment, bundle);
//                HomeCycleActivity navigationActivity = (HomeCycleActivity) activity;
//                navigationActivity.setNavigation("g");
            }
        });
    }

    @Override
    public int getItemCount() {
        return flightsListData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cardview_book_flight_offers_item_status_tv)
        TextView cardviewBookFlightOffersItemStatusTv;
        @BindView(R.id.cardview_book_flight_offers_item_status_ly)
        LinearLayout cardviewBookFlightOffersItemStatusLy;
        @BindView(R.id.cardview_book_flight_offers_item_user_name_tv)
        TextView cardviewBookFlightOffersItemUserNameTv;
        @BindView(R.id.cardview_book_flight_offers_item_flight_name_tv)
        TextView cardviewBookFlightOffersItemFlightNameTv;
        @BindView(R.id.cardview_book_flight_offers_item_from_to_date_tv)
        TextView cardviewBookFlightOffersItemFromToDateTv;
        @BindView(R.id.cardview_book_flight_offers_item_price_tv)
        TextView cardviewBookFlightOffersItemPriceTv;
        @BindView(R.id.cardview_book_flight_offers_item_total_members_tv)
        TextView cardviewBookFlightOffersItemTotalMembersTv;
        @BindView(R.id.cardview_book_flight_offers_item_places_covered_tv)
        TextView cardviewBookFlightOffersItemPlacesCoveredTv;
        @BindView(R.id.cardview_book_flight_offers_item_discription_btn)
        TextView cardviewBookFlightOffersItemDiscriptionBtn;
        View view;
        private int position;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
//            navController = Navigation.findNavController(activity, R.id.home_activity_fragment);

        }
    }
}
