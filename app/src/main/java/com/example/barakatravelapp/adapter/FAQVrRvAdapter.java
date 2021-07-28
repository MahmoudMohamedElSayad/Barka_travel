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
import androidx.recyclerview.widget.RecyclerView;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.data.model.ItemGeneralObjectModel;
import com.example.barakatravelapp.data.model.getFaqResponce.Faq;
import com.example.barakatravelapp.view.activity.HomeCycleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FAQVrRvAdapter extends RecyclerView.Adapter<FAQVrRvAdapter.ViewHolder> {



    //    private final NavController navController;
    private Context context;
    private Activity activity;
    private List<Faq> faqsListItem = new ArrayList<>();
    private boolean showToggle = true;

    public FAQVrRvAdapter(Context context,
                          Activity activity, List<Faq> faqsListItem) {
        this.context = context;
        this.activity = activity;
//        this.clientRestaurantsDataList.clear();
        this.faqsListItem = faqsListItem;
//        this.navController = navController;
//        clientData = LoadUserData(activity);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_ques_and_answer_item,
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
            holder.cardviewQuesAndAnswerItemQuesTv.setText(faqsListItem.get(position).getQuestion());
            holder.cardviewQuesAndAnswerItemAnsTv.setText(faqsListItem.get(position).getAnswer());

        } catch (Exception e) {

        }

    }


    private void setAction(final ViewHolder holder, final int position) {

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(holder.cardviewQuesAndAnswerItemAnsTv.getVisibility()!=View.VISIBLE) {

                   holder.cardviewQuesAndAnswerItemAnsTv.setVisibility(View.VISIBLE);
//                   showToggle=false;
               }else {
                   holder.cardviewQuesAndAnswerItemAnsTv.setVisibility(View.GONE);
//                   showToggle=true;
               }
            }
        });

    }


    @Override
    public int getItemCount() {
        return faqsListItem.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cardview_ques_and_answer_item_ques_tv)
        TextView cardviewQuesAndAnswerItemQuesTv;
        @BindView(R.id.cardview_ques_and_answer_item_show_btn)
        TextView cardviewQuesAndAnswerItemShowBtn;
        @BindView(R.id.cardview_ques_and_answer_item_ans_tv)
        TextView cardviewQuesAndAnswerItemAnsTv;
        private View view;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);

        }
    }
}
