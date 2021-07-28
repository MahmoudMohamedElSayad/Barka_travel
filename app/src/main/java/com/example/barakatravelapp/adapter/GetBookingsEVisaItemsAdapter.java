package com.example.barakatravelapp.adapter;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.example.barakatravelapp.data.model.getBookingEvisaResponce.EVisaDate;
import com.example.barakatravelapp.data.model.getBookingEvisaResponce.Evisa;
import com.example.barakatravelapp.data.model.getBookingEvisaResponce.EvisaDetail;
import com.example.barakatravelapp.utils.EvisaMoreRVDialog;
import com.example.barakatravelapp.utils.PhotoGallaryAdapterCallback;
import com.example.barakatravelapp.view.activity.BaseActivity;
import com.example.barakatravelapp.view.activity.HomeCycleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GetBookingsEVisaItemsAdapter extends RecyclerView.Adapter<GetBookingsEVisaItemsAdapter.ViewHolder> {


    private final String bookingType;
    private PhotoGallaryAdapterCallback photoGallaryAdapterCallback;

    private BaseActivity activity;
    private Context context;
    private List<EVisaDate> eVisaDates;
    List<EvisaDetail> evisaDetail=new ArrayList<EvisaDetail>();;
    private NavController navController;

    public GetBookingsEVisaItemsAdapter(Activity activity, Context context, String bookingType, List<EVisaDate> eVisaDates,
                                        NavController navController, PhotoGallaryAdapterCallback photoGallaryAdapterCallback) {
        this.activity = (BaseActivity) activity;
        this.context = context;
        this.bookingType = bookingType;
        this.eVisaDates = eVisaDates;
        this.navController = navController;
        this.photoGallaryAdapterCallback = photoGallaryAdapterCallback;
//        showToast(activity, "date="+flightsListData.get(0).getFlight().getReservationTo());
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_book_e_visa_offers_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);

    }

    private void setData(ViewHolder holder, int position) {
        holder.position = position;
        Evisa evisa = eVisaDates.get(position).getEvisa();
//        showToast(activity, "list="+flightList.getReservationTo());
        holder.cardviewBookEVisaOffersItemUserNameTv.setText("Name : " +evisa.getFirstName()+" "+evisa.getLastName());
        holder.cardviewBookEVisaOffersItemPhoneTv.setText("Contact Number : " +evisa.getContactNumber());
        holder.cardviewBookEVisaOffersItemEmailTv.setText("Email : " +evisa.getEmail());
        String status =evisa.getStatus();
        if (!status.equalsIgnoreCase("complete")){
            holder.cardviewBookEVisaOffersItemStatusLy.setBackgroundResource(R.drawable.circle_btn_yello_shape);
            holder.cardviewBookEVisaOffersItemCompletePaymentBtn.setVisibility(View.VISIBLE);
        }
        holder.cardviewBookEVisaOffersItemStatusTv.setText(status);

    }


    private void setAction(ViewHolder holder, int position) {
        holder.cardviewBookEVisaOffersItemCompletePaymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = eVisaDates.get(position).getCompletePayment();
                try {
                    Intent i = new Intent("android.intent.action.MAIN");
                    i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
                    i.addCategory("android.intent.category.LAUNCHER");
                    i.setData(Uri.parse(url));
                    activity.startActivity(i);
                }
                catch(ActivityNotFoundException e) {
                    // Chrome is not installed
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    activity.startActivity(i);
                }
            }
        });
        holder.cardviewBookEVisaOffersItemMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evisaDetail = eVisaDates.get(position).getEvisaDetail();
                final EvisaMoreRVDialog dialog = new EvisaMoreRVDialog(evisaDetail,photoGallaryAdapterCallback);
                dialog.show(activity.getSupportFragmentManager(), "example");
            }
        });
    }

    @Override
    public int getItemCount() {
        return eVisaDates.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cardview_book_e_visa_offers_item_status_tv)
        TextView cardviewBookEVisaOffersItemStatusTv;
        @BindView(R.id.cardview_book_e_visa_offers_item_status_ly)
        LinearLayout cardviewBookEVisaOffersItemStatusLy;
        @BindView(R.id.cardview_book_e_visa_offers_item_user_name_tv)
        TextView cardviewBookEVisaOffersItemUserNameTv;
        @BindView(R.id.cardview_book_e_visa_offers_item_phone_tv)
        TextView cardviewBookEVisaOffersItemPhoneTv;
        @BindView(R.id.cardview_book_e_visa_offers_item_email_tv)
        TextView cardviewBookEVisaOffersItemEmailTv;
        @BindView(R.id.cardview_book_e_visa_offers_item_more_btn)
        TextView cardviewBookEVisaOffersItemMoreBtn;
        @BindView(R.id.cardview_book_e_visa_offers_item_complete_payment_btn)
        TextView cardviewBookEVisaOffersItemCompletePaymentBtn;
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
