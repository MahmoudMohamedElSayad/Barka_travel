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
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.data.model.ItemGeneralObjectModel;
import com.example.barakatravelapp.data.model.getBookingEvisaResponce.EvisaDetail;
import com.example.barakatravelapp.utils.EvisaMoreRVDialog;
import com.example.barakatravelapp.utils.PhotoGallaryAdapterCallback;
import com.example.barakatravelapp.view.activity.BaseActivity;
import com.example.barakatravelapp.view.activity.HomeCycleActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.barakatravelapp.utils.HelperMethod.onLoadImageFromUrl;


public class EVisaMoreItemsAdapter extends RecyclerView.Adapter<EVisaMoreItemsAdapter.ViewHolder> {


    private final EvisaMoreRVDialog evisaMoreRVDialog;
    private PhotoGallaryAdapterCallback photoGallaryAdapterCallback;
    private BaseActivity activity;
    private Context context;
    private List<EvisaDetail> evisaDetails;
    private NavController navController;

    public EVisaMoreItemsAdapter(Context activity, Context context, List<EvisaDetail> evisaDetails, PhotoGallaryAdapterCallback photoGallaryAdapterCallback, EvisaMoreRVDialog evisaMoreRVDialog) {
        this.activity = (BaseActivity) activity;
        this.context = context;
        this.evisaDetails = evisaDetails;
        this.navController = navController;
        this.photoGallaryAdapterCallback = photoGallaryAdapterCallback;
        this.evisaMoreRVDialog = evisaMoreRVDialog;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_evisa_more_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);

    }

    private void setData(ViewHolder holder, int position) {
        holder.position = position;

        holder.cardviewEvisaMoreItemPassportTypeTv.setText("Passport Type : "+evisaDetails.get(position).getPassportType());
        String passportImage = "https://www.barakatravel.net/"+evisaDetails.get(position).getPassportPhoto().trim();
        onLoadImageFromUrl(holder.cardviewEvisaMoreItemPassportPhotoImg, passportImage.trim(), context);

        String personalImage = "https://www.barakatravel.net/"+evisaDetails.get(position).getPhoto().trim();
        onLoadImageFromUrl(holder.cardviewEvisaMoreItemPersonalPhotoImg, personalImage.trim(), context);



    }


    private void setAction(ViewHolder holder, int position) {
        holder.cardviewEvisaMoreItemPassportPhotoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passportImage = "https://www.barakatravel.net/"+evisaDetails.get(position).getPassportPhoto().trim();
                photoGallaryAdapterCallback.onMethodCallback(passportImage);
                evisaMoreRVDialog.dismiss();
            }
        });
        holder.cardviewEvisaMoreItemPersonalPhotoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String personalImage = "https://www.barakatravel.net/"+evisaDetails.get(position).getPhoto().trim();
                photoGallaryAdapterCallback.onMethodCallback(personalImage);
                evisaMoreRVDialog.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return evisaDetails.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardview_evisa_more_item_passport_photo_img)
        ImageView cardviewEvisaMoreItemPassportPhotoImg;
        @BindView(R.id.cardview_evisa_more_item_personal_photo_img)
        ImageView cardviewEvisaMoreItemPersonalPhotoImg;
        @BindView(R.id.cardview_evisa_more_item_passport_Type_tv)
        TextView cardviewEvisaMoreItemPassportTypeTv;
        @BindView(R.id.cardview_evisa_static_item_padding_tv)
        TextView cardviewEvisaStaticItemPaddingTv;
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
