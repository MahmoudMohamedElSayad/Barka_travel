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
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.data.model.ItemGeneralObjectModel;
import com.example.barakatravelapp.view.activity.BaseActivity;
import com.example.barakatravelapp.view.activity.HomeCycleActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EVisaIStatictemsAdapter extends RecyclerView.Adapter<EVisaIStatictemsAdapter.ViewHolder> {



    private BaseActivity activity;
    private Context context;
    private List<ItemGeneralObjectModel> itemGeneralObjectModels;
    private NavController navController;

    public EVisaIStatictemsAdapter(Activity activity, Context context, List<ItemGeneralObjectModel> itemGeneralObjectModels, NavController navController) {
        this.activity = (BaseActivity) activity;
        this.context = context;
        this.itemGeneralObjectModels = itemGeneralObjectModels;
        this.navController = navController;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_evisa_static_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);

    }

    private void setData(ViewHolder holder, int position) {
        holder.position = position;

        holder.cardviewEvisaStaticItemPassportNameTv.setText(itemGeneralObjectModels.get(position).getPassportName());
        holder.cardviewEvisaStaticItemPassportPriceTv.setText(itemGeneralObjectModels.get(position).getPassportPrice());
        holder.cardviewEvisaStaticItemPassportYearVaildTv.setText(itemGeneralObjectModels.get(position).getPassportvalidaty());
        holder.cardviewEvisaStaticItemPassportVisaTimeTv.setText(itemGeneralObjectModels.get(position).getPassportVisaTime());
        holder.cardviewEvisaStaticItemPhotoImg.setImageResource(itemGeneralObjectModels.get(position).getPhoto());
        holder.cardviewEvisaStaticItemPassportImg.setImageResource(itemGeneralObjectModels.get(position).getPassportPhoto());
        if (position == getItemCount() - 1) {
            holder.cardviewEvisaStaticItemPhotoImg.setVisibility(View.GONE);
            holder.cardviewEvisaStaticItemPaddingTv.setVisibility(View.VISIBLE);

        }

    }


    private void setAction(ViewHolder holder, int position) {
        holder.fragmentHomeFlightsCardviewFlightEvisaItemLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                bundle.putString("DiscoverOrHajjOrUmrah", hajjOrUmrah);

                Bundle bundle = new Bundle();
                if (position==0){bundle.putString("CountryType", "USACanadian"); }
                if (position==1){bundle.putString("CountryType", "IndianPakistani"); }
                if (position==2){bundle.putString("CountryType", "Bangladeshi"); }
                navController.navigate(R.id.action_fragment_choose_static_evisa_to_getEVisaFragment, bundle);
                HomeCycleActivity navigationActivity = (HomeCycleActivity) activity;
                navigationActivity.setNavigation("g");
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemGeneralObjectModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardview_evisa_static_item_passport_img)
        ImageView cardviewEvisaStaticItemPassportImg;
        @BindView(R.id.cardview_evisa_static_item_photo_img)
        ImageView cardviewEvisaStaticItemPhotoImg;
        @BindView(R.id.cardview_evisa_static_item_passport_name_tv)
        TextView cardviewEvisaStaticItemPassportNameTv;
        @BindView(R.id.cardview_evisa_static_item_passport_price_tv)
        TextView cardviewEvisaStaticItemPassportPriceTv;
        @BindView(R.id.cardview_evisa_static_item_passport_year_vaild_tv)
        TextView cardviewEvisaStaticItemPassportYearVaildTv;
        @BindView(R.id.cardview_evisa_static_item_passport_visa_time_tv)
        TextView cardviewEvisaStaticItemPassportVisaTimeTv;
        @BindView(R.id.cardview_evisa_static_item_padding_tv)
        TextView cardviewEvisaStaticItemPaddingTv;
        @BindView(R.id.fragment_home_flights_cardview_flight_evisa_item_ly)
        CardView fragmentHomeFlightsCardviewFlightEvisaItemLy;
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
