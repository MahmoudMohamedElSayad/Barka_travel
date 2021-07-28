package com.example.barakatravelapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.data.model.getUmrahAndHujjResponce.UmarhDay;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowDayByDayVrRvAdapter extends RecyclerView.Adapter<ShowDayByDayVrRvAdapter.ViewHolder> {



    private Context context;
    private Activity activity;
    private List<UmarhDay> getUmarDayItemsListData = new ArrayList<UmarhDay>();


    public ShowDayByDayVrRvAdapter(Context context,
                                   Activity activity,
                                   List<UmarhDay> getUmarDayItemsListData) {
        this.context = context;
        this.activity = activity;
//        this.clientRestaurantsDataList.clear();
        this.getUmarDayItemsListData = getUmarDayItemsListData;
//        clientData = LoadUserData(activity);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_hz_hajj_details_day_by_day_dialog_item,
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
            holder.position = position;
            UmarhDay umarhDayList= getUmarDayItemsListData.get(position);
            holder.cardviewHzHajjDetailsDayByDayDialogItemDateAndDayNumTv.setText("Day  "+umarhDayList.getDayNumber()+"  :   "+umarhDayList.getName());
            holder.cardviewHzHajjDetailsDayByDayDialogItemDescriptionTv.setText(umarhDayList.getDesciption());


        } catch (Exception e) {

        }

    }


    private void setAction(final ViewHolder holder, final int position) {

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                HomeCycleActivity homeCycleActivity = (HomeCycleActivity) activity;
//                Toast.makeText(v.getContext(), "Clicked Country Position = " + position, Toast.LENGTH_SHORT).show();
//                if (position == 0) {
//                    replaceFragmentWithAnimation(homeCycleActivity.getSupportFragmentManager(), R.id.home_activity_fram, new SubCategoryFragment(), "t");
//                    homeCycleActivity.setNavigationAndToolBar(View.GONE, true);
//                }
            }
        });


        //for Scrolling of textView
//        holder.cardviewHzHajjDetailsDayByDayDialogItemDescriptionTv.setMovementMethod(new ScrollingMovementMethod());
//to stop parent linearlayout scrollview when touched on textview
        holder.cardviewHzHajjDetailsDayByDayDialogItemDescriptionTv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

    }


    @Override
    public int getItemCount() {
        return getUmarDayItemsListData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cardview_hz_hajj_details_day_by_day_dialog_item_date_and_day_num_tv)
        TextView cardviewHzHajjDetailsDayByDayDialogItemDateAndDayNumTv;
        @BindView(R.id.cardview_hz_hajj_details_day_by_day_dialog_item_description_tv)
        TextView cardviewHzHajjDetailsDayByDayDialogItemDescriptionTv;
        private View view;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);

        }
    }
}
