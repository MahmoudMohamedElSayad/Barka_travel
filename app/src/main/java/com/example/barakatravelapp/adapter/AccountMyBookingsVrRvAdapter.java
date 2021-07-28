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
import com.example.barakatravelapp.data.model.ItemGeneralObjectModel;
import com.example.barakatravelapp.view.activity.HomeCycleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.barakatravelapp.utils.HelperMethod.onLoadImageFromUrl;

public class AccountMyBookingsVrRvAdapter extends RecyclerView.Adapter<AccountMyBookingsVrRvAdapter.ViewHolder> {


    private final NavController navController;
    private Context context;
    private Activity activity;
    private List<ItemGeneralObjectModel> itemList = new ArrayList<>();


    public AccountMyBookingsVrRvAdapter(Context context,
                                        Activity activity, NavController navController, List<ItemGeneralObjectModel> itemList) {
        this.context = context;
        this.activity = activity;
//        this.clientRestaurantsDataList.clear();
        this.itemList = itemList;
        this.navController = navController;
//        clientData = LoadUserData(activity);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_my_account_item,
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
            holder.cardviewMyAccountItemNameTv.setText(itemList.get(position).getName());
            holder.cardviewMyAccountItemBookingImg.setImageResource(itemList.get(position).getPhoto());
            onLoadImageFromUrl(holder.cardviewMyAccountItemBookingImg,itemList.get(position).getPhotoPath().trim(), context);

        } catch (Exception e) {

        }

    }


    private void setAction(final ViewHolder holder, final int position) {

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("BookingType", itemList.get(position).getName());
//                if (position == 0) {

//                }
//                if (position == 1) {
//                    bundle.putString("BookingType", "discover");
//
//                }
//
//                if (position == 2) {
//                    bundle.putString("BookingType", "discover");
//
//                }
//                if (position == 3) {
//                    bundle.putString("BookingType", "discover");
//
//                }
                navController.navigate(R.id.action_navigation_account_to_myUmrahBookingFragment,bundle);
                HomeCycleActivity homeCycleActivity = (HomeCycleActivity) activity;
                homeCycleActivity.setNavigation("g");
//                HomeCycleActivity homeCycleActivity = (HomeCycleActivity) activity;
//                Toast.makeText(v.getContext(), "Clicked Country Position = " + position, Toast.LENGTH_SHORT).show();
//                if (position == 0) {
//                    replaceFragmentWithAnimation(homeCycleActivity.getSupportFragmentManager(), R.id.home_activity_fram, new SubCategoryFragment(), "t");
//                    homeCycleActivity.setNavigationAndToolBar(View.GONE, true);
//                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cardview_my_account_item_booking_img)
        ImageView cardviewMyAccountItemBookingImg;
        @BindView(R.id.cardview_my_account_item_name_tv)
        TextView cardviewMyAccountItemNameTv;
        private View view;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);

        }
    }
}
