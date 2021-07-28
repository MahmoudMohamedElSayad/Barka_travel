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
import com.example.barakatravelapp.data.model.getFlightResponce.FlightData;
import com.example.barakatravelapp.view.activity.BaseActivity;
import com.example.barakatravelapp.view.activity.HomeCycleActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.barakatravelapp.utils.HelperMethod.onLoadImageFromUrl;


public class GetFlightsItemsAdapter extends RecyclerView.Adapter<GetFlightsItemsAdapter.ViewHolder> {



    private BaseActivity activity;
    private Context context;
    private List<FlightData> flightsListData;
    private NavController navController;

    public GetFlightsItemsAdapter(Activity activity, Context context, List<FlightData> flightsListData, NavController navController) {
        this.activity = (BaseActivity) activity;
        this.context = context;
        this.flightsListData = flightsListData;
        this.navController = navController;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_flight_offers_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);

    }

    private void setData(ViewHolder holder, int position) {
        holder.position = position;
        FlightData flightList = flightsListData.get(position);
//        if(position == 0){
//            holder.cardviewFlightOffersItemTopFlightsTitleTv.setVisibility(View.VISIBLE);
//        }
        holder.cardviewFlightOffersItemFlightNameTv.setText(flightList.getFlightName());
        holder.cardviewFlightOffersItemLocationFromToTv.setText("Location : " + flightList.getFrom() + " To " + flightList.getTo());
        holder.cardviewFlightOffersItemReservationFromToDateTv.setText("Reservation " + flightList.getReservationFrom() + " To " + flightList.getReservationTo());
        holder.cardviewFlightOffersItemOferPresedgeTv.setText(flightList.getIsOffer() + " %");
//        if (flightList.getIsOffer()!=null)
        holder.cardviewFlightOffersItemOfferNumTv.setText(flightList.getIsOffer() + " %");
//        Glide.with(context).load(foodList.getPhotoUrl()).asBitmap().override(1080, 600).into(holder.cardviewItemClientFoodOrderMenuImg);
        onLoadImageFromUrl(holder.cardviewFlightOffersItemFlightAirportLogoImg, flightList.getImage(), context);
    }


    private void setAction(ViewHolder holder, int position) {
        holder.cardviewFlightOffersItemBookNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AddOrderFragment addOrderFragment = new AddOrderFragment();
//                addOrderFragment.item = foodListData.get(position);
//                replaceFragment(activity.getSupportFragmentManager(), R.id.home_activity_fram, new ContactWithUsFragment(),"t");
                Bundle bundle = new Bundle();
//                bundle.putString("DiscoverOrHajjOrUmrah", hajjOrUmrah);
                bundle.putString("BookingFlight", "Flight");
                bundle.putSerializable("Object",  flightsListData.get(position));
                navController.navigate(R.id.action_navigation_flight_to_flightDetailsFragment,bundle);
                HomeCycleActivity navigationActivity = (HomeCycleActivity) activity;
                navigationActivity.setNavigation("g");
            }
        });
    }

    @Override
    public int getItemCount() {
        return flightsListData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardview_flight_offers_item_flight_airport_logo_img)
        ImageView cardviewFlightOffersItemFlightAirportLogoImg;
        @BindView(R.id.cardview_flight_offers_item_offer_shadow_img)
        ImageView cardviewFlightOffersItemOfferShadowImg;
        @BindView(R.id.cardview_flight_offers_item_offer_num_tv)
        TextView cardviewFlightOffersItemOfferNumTv;
        @BindView(R.id.cardview_flight_offers_item_flight_name_tv)
        TextView cardviewFlightOffersItemFlightNameTv;
        @BindView(R.id.cardview_flight_offers_item_location_from_to_tv)
        TextView cardviewFlightOffersItemLocationFromToTv;
        @BindView(R.id.cardview_flight_offers_item_reservation_from_to_date_tv)
        TextView cardviewFlightOffersItemReservationFromToDateTv;
        @BindView(R.id.cardview_flight_offers_item_ofer_presedge_tv)
        TextView cardviewFlightOffersItemOferPresedgeTv;
        @BindView(R.id.cardview_flight_offers_item_book_now_btn)
        TextView cardviewFlightOffersItemBookNowBtn;
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
