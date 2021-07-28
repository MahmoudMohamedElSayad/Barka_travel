package com.example.barakatravelapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.data.model.getHotelsResponce.GetAmenity;
import com.example.barakatravelapp.data.model.getUmrahAndHujjResponce.PackagesInclude;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.barakatravelapp.utils.HelperMethod.showToast;

public class HotelAminitesHzRvAdapter extends RecyclerView.Adapter<HotelAminitesHzRvAdapter.ViewHolder> {



//    private final DialogAdapterCallback dialogAdapterCallback;

    private Context context;
    private Activity activity;
    private LinearLayoutManager lLayout;
    private int itemNum;
    private List<GetAmenity> amenities = new ArrayList<>();


    public HotelAminitesHzRvAdapter(Context context, Activity activity,
                                    List<GetAmenity> amenities
    ) {
        this.context = context;
        this.activity = activity;
//        this.clientRestaurantsDataList.clear();
        this.amenities = amenities;
//        this.dialogAdapterCallback = dialogAdapterCallback;
//        showToast(activity, "list=" + getHomeDisscoverGetHotelsDataItemsListData.get(0).getCity());

//        showToast(activity, String.valueOf(itemNum));
//        clientData = LoadUserData(activity);

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_hz_hotel_aminities_item,
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
//            final int itemType = getItemViewType(position);
            holder.position = position;

            GetAmenity getAmenity = amenities.get(position);
//            showToast(activity, String.valueOf(position));
//            String packageIcon = "https://www.barakatravel.net/" + packageData.getIcon().trim();
//            onLoadImageFromUrl(holder.cardviewHzHajjDetailsPackagesIncludedItemImg, packageIcon.trim(), context);
            holder.cardviewHzHotelAminitiesItemItemNameTv.setText(getAmenity.getName());

        } catch (Exception e) {

        }

    }


    private void setAction(final ViewHolder holder, final int position) {

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showToast(activity, "here");
//                dialogAdapterCallback.onMethodCallback(getTopUmarAndTophajjPackages.get(position));

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

        return amenities.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardview_hz_hotel_aminities_item_item_name_tv)
        TextView cardviewHzHotelAminitiesItemItemNameTv;
        private View view;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);

        }
    }
}
