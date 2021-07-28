package com.example.barakatravelapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.utils.PhotoGallaryAdapterCallback;
import com.example.barakatravelapp.view.fragment.HomeCycle2.accounts.GetEVisaFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.barakatravelapp.utils.HelperMethod.onLoadImageFromUrl;

public class EVisaPhotoGallaryHzRvAdapter extends RecyclerView.Adapter<EVisaPhotoGallaryHzRvAdapter.ViewHolder> {


//    private final DialogAdapterCallback dialogAdapterCallback;

    private Context context;
    private Activity activity;
    private LinearLayoutManager lLayout;
    private int itemNum;
    private List<String> eVisaImages = new ArrayList<>();
    private PhotoGallaryAdapterCallback photoGallaryAdapterCallback;
    private String photoType;

//    private ProfileItemAdapter homeSubHzItemAdapter;
//    private SubHomeCategoryHzRvItem2Adapter subHomeCategoryHzRvItem2Adapter;
//    List<ProductDataModel> rowListItem;
//    private ClientData clientData;
//    private ApiService apiService;

    public EVisaPhotoGallaryHzRvAdapter(Context context, Activity activity,
                                        String photoType, List<String> hajjAndUmrahImages
    ) {
        this.context = context;
        this.activity = activity;
//        this.clientRestaurantsDataList.clear();
        this.eVisaImages = hajjAndUmrahImages;
        this.photoType = photoType;
//        this.photoGallaryAdapterCallback=photoGallaryAdapterCallback;
//        this.dialogAdapterCallback = dialogAdapterCallback;
//        showToast(activity, "list=" + getHomeDisscoverGetHotelsDataItemsListData.get(0).getCity());

//        showToast(activity, String.valueOf(itemNum));
//        clientData = LoadUserData(activity);

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_hz_hajj_details_photo_gallery_item,
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

//            GetTopUmarAndTophajjPackage getTopUmarAndTophajjPackageData = getTopUmarAndTophajjPackages;
//            showToast(activity, String.valueOf(position));
//            hajjAndUmrahImages = getTopUmarAndTophajjPackages.getUmarImages();
            holder.cardviewHzHajjDetailsPhotoGalleryDeleteItemsImg.setVisibility(View.VISIBLE);
            onLoadImageFromUrl(holder.cardviewHzHajjDetailsPhotoGalleryItemImg, eVisaImages.get(position).trim(), context);


        } catch (Exception e) {

        }

    }


    private void setAction(final ViewHolder holder, final int position) {

        holder.cardviewHzHajjDetailsPhotoGalleryDeleteItemsImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              showDeleteDialog();
            }
        });

    }


    @Override
    public int getItemCount() {

        return eVisaImages.size();

    }

    private void showDeleteDialog(){
        try {
//                final View view = activity.getLayoutInflater().inflate(R.layout.dialog_restaurant_add_category, null);
//            alertDialog = new AlertDialog.Builder(HomeFragment.this).create();
            AlertDialog alertDialog;
            alertDialog = new AlertDialog.Builder(activity).create();
            alertDialog.setTitle("Delete All ?");
//            alertDialog.setMessage("Are you sure you want log out ?");
            alertDialog.setCancelable(true);

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    eVisaImages.clear();
                    notifyDataSetChanged();
                    GetEVisaFragment getEVisaFragment=  new GetEVisaFragment();
                    if (photoType.equalsIgnoreCase("passport"))
                        getEVisaFragment.mPassportPath.clear();
                    else
                        getEVisaFragment.mPersonalPath.clear();

                }
            });


            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss() ;
                }
            });

//                alertDialog.setView(view);
//            alertDialog.setOnShowListener( new DialogInterface.OnShowListener() {
//                @SuppressLint("ResourceAsColor")
//                @Override
//                public void onShow(DialogInterface arg0) {
//                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.pink);
//                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(R.color.pink);
//
//                }
//            });

            alertDialog.show();

        } catch (Exception e) {

        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cardview_hz_hajj_details_photo_gallery_item_img)
        ImageView cardviewHzHajjDetailsPhotoGalleryItemImg;
        @BindView(R.id.cardview_hz_hajj_details_photo_gallery_delete_items_img)
        ImageView cardviewHzHajjDetailsPhotoGalleryDeleteItemsImg;
        private View view;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);

        }
    }
}
