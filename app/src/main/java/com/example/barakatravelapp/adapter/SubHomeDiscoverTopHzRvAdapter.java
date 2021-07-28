package com.example.barakatravelapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.data.model.getHotelsResponce.HotelData;
import com.example.barakatravelapp.data.model.getUmrahAndHujjResponce.GetTopUmarAndTophajjPackage;
import com.example.barakatravelapp.utils.DialogAdapterCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.barakatravelapp.utils.HelperMethod.onLoadImageFromUrl;
import static com.example.barakatravelapp.utils.HelperMethod.showToast;

public class SubHomeDiscoverTopHzRvAdapter extends RecyclerView.Adapter<SubHomeDiscoverTopHzRvAdapter.ViewHolder> {


    private final DialogAdapterCallback dialogAdapterCallback;
    private Context context;
    private Activity activity;
    private List<GetTopUmarAndTophajjPackage> getHomeDisscoverGetItemsListData = new ArrayList<>();
    private List<HotelData> getHomeDisscoverGetHotelsDataItemsListData=new ArrayList<>();
    private LinearLayoutManager lLayout;
    private int itemNum;
    private List<String> hotelImages;

//    private ProfileItemAdapter homeSubHzItemAdapter;
//    private SubHomeCategoryHzRvItem2Adapter subHomeCategoryHzRvItem2Adapter;
//    List<ProductDataModel> rowListItem;
//    private ClientData clientData;
//    private ApiService apiService;

    public SubHomeDiscoverTopHzRvAdapter(Context context, Activity activity, List<GetTopUmarAndTophajjPackage> getHomeDisscoverGetItemsListData,
                                         List<HotelData> getHomeDisscoverGetHotelsDataItemsListData, int itemNum,DialogAdapterCallback dialogAdapterCallback) {
        this.context = context;
        this.activity = activity;
//        this.clientRestaurantsDataList.clear();
        this.getHomeDisscoverGetItemsListData = getHomeDisscoverGetItemsListData;
        this.getHomeDisscoverGetHotelsDataItemsListData = getHomeDisscoverGetHotelsDataItemsListData;
        this.itemNum = itemNum;
        this.dialogAdapterCallback=dialogAdapterCallback;
//        showToast(activity, "list=" + getHomeDisscoverGetHotelsDataItemsListData.get(0).getCity());

//        showToast(activity, String.valueOf(itemNum));
//        clientData = LoadUserData(activity);

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_hz_discover_item,
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
//            showToast(activity, String.valueOf(position));

            if (itemNum == 1 ) {
                GetTopUmarAndTophajjPackage getTopUmarAndTophajjPackageData = getHomeDisscoverGetItemsListData.get(position);
                int nights = getTopUmarAndTophajjPackageData.getUmar().getDuration() - 1;
                holder.cardviewHzDiscoverItemTvRateAndNightsNum.setText(nights + " Nights");
                holder.cardviewHzDiscoverItemPriceTv.setText("$ "+getTopUmarAndTophajjPackageData.getUmar().getMinPrice());
                holder.cardviewHzDiscoverItemDateOrNameTv.setText(getTopUmarAndTophajjPackageData.getUmar().getStartDateInformat());
                holder.cardviewHzDiscoverItemDiscriptionGrayForTopUmrahTv.setVisibility(View.VISIBLE);
                holder.cardviewHzDiscoverItemDiscriptionGrayForTopUmrahTv.setText(getTopUmarAndTophajjPackageData.getUmar().getName());
                List<String> umarOrHajjImages=getTopUmarAndTophajjPackageData.getUmarImages();
                onLoadImageFromUrl(holder.cardviewHzDiscoverItemImg, umarOrHajjImages.get(0).trim(), context);
                if(getTopUmarAndTophajjPackageData.getUmar().getIsOffer()!= null){
                    holder.cardviewHzDiscoverItemImgShadowHide.setVisibility(View.VISIBLE);
                }
            }
             if (itemNum == 2) {
                 GetTopUmarAndTophajjPackage getTopUmarAndTophajjPackageData = getHomeDisscoverGetItemsListData.get(position);
                 int nights = getTopUmarAndTophajjPackageData.getUmar().getDuration() - 1;
                 holder.cardviewHzDiscoverItemTvRateAndNightsNum.setText(nights + " Nights");
                 holder.cardviewHzDiscoverItemPriceTv.setText("$ "+getTopUmarAndTophajjPackageData.getUmar().getMinPrice());
                 holder.cardviewHzDiscoverItemDateOrNameTv.setText(getTopUmarAndTophajjPackageData.getUmar().getName());
                 List<String> umarOrHajjImages=getTopUmarAndTophajjPackageData.getUmarImages();
                 onLoadImageFromUrl(holder.cardviewHzDiscoverItemImg, umarOrHajjImages.get(0).trim(), context);
                 if(getTopUmarAndTophajjPackageData.getUmar().getIsOffer()!= null){
                     holder.cardviewHzDiscoverItemImgShadowHide.setVisibility(View.VISIBLE);
                 }
            }
//             if (itemNum == 3 ) {
//                 HotelData hotelData = getHomeDisscoverGetHotelsDataItemsListData.get(position);
////                 showToast(activity, String.valueOf(hotelData.getMinPrice()));
//                 holder.cardviewHzDiscoverItemRateImgHide.setVisibility(View.VISIBLE);
//                 holder.cardviewHzDiscoverItemTvRateAndNightsNum.setText(hotelData.getRate());
//                 holder.cardviewHzDiscoverItemPriceTv.setText("$ "+hotelData.getMinPrice());
//                 holder.cardviewHzDiscoverItemDateOrNameTv.setText(hotelData.getGetRooms().get(0).getFromDate());
////                holder.cardviewHzDiscoverItemDateOrNameTv.setText(hotelData.getName());
//                 holder.cardviewHzDiscoverItemDateTv.setText(hotelData.getName());
//                 hotelImages = hotelData.getHotelImages();
//                 String hotelImage = "https://www.barakatravel.net/"+hotelImages.get(0).trim();
//                 onLoadImageFromUrl(holder.cardviewHzDiscoverItemImg, hotelImage, context);
//                 if(hotelData.getRate()!= null){
//                     holder.cardviewHzDiscoverItemImgShadowHide.setVisibility(View.VISIBLE);
//                 }}
//                 if (itemNum == 4 ) {
//                     HotelData hotelData = getHomeDisscoverGetHotelsDataItemsListData.get(position);
//                     showToast(activity, String.valueOf(getHomeDisscoverGetHotelsDataItemsListData.size()));
//                     holder.cardviewHzDiscoverItemRateImgHide.setVisibility(View.VISIBLE);
//                     holder.cardviewHzDiscoverItemTvRateAndNightsNum.setText(hotelData.getRate());
//                     holder.cardviewHzDiscoverItemPriceTv.setText("$ "+hotelData.getMinPrice());
//                     holder.cardviewHzDiscoverItemDateOrNameTv.setText(hotelData.getGetRooms().get(0).getFromDate());
////                holder.cardviewHzDiscoverItemDateOrNameTv.setText(hotelData.getName());
//                     holder.cardviewHzDiscoverItemDateTv.setText(hotelData.getName());
//                     hotelImages = hotelData.getHotelImages();
//                     String hotelImage = "https://www.barakatravel.net/"+hotelImages.get(0).trim();
//                     onLoadImageFromUrl(holder.cardviewHzDiscoverItemImg, hotelImage, context);
//                     if(hotelData.getRate()!= null){
//                         holder.cardviewHzDiscoverItemImgShadowHide.setVisibility(View.VISIBLE);
//                     }
//            }

//            else if (itemNum == 4) {
//
//            }



        } catch (Exception e) {

        }

    }


    private void setAction(final ViewHolder holder, final int position) {

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showToast(activity, "here");
                dialogAdapterCallback.onMethodCallback(getHomeDisscoverGetItemsListData.get(position));

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
    public int getItemViewType(int position) {
        if (itemNum == 1 || itemNum == 2) {
            return 0;
        }
        return 1;
    }

    @Override
    public int getItemCount() {
//        if (itemNum == 1 || itemNum == 2) {
//            return getHomeDisscoverGetItemsListData.size();
//
//        }else {
            return getHomeDisscoverGetItemsListData.size();

//        }
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
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);

        }
    }
}
