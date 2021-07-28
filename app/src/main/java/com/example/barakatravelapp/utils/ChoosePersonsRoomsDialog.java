package com.example.barakatravelapp.utils;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.barakatravelapp.R;


public class ChoosePersonsRoomsDialog {
    private static int adultNum;
    private DialogAdapterCallback dialogAdapterCallback;

    public void showDialog(final Activity activity,DialogAdapterCallback dialogAdapterCallback) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.dialog_choose_persons_rooms);
        dialog.setCanceledOnTouchOutside(true);

        adultNum=0;

        TextView adultNumTv = (TextView) dialog.findViewById(R.id.dialog_choose_persons_rooms_adults_num_tv);
        TextView childNumTv = (TextView) dialog.findViewById(R.id.dialog_choose_persons_rooms_childs_num_tv);
        TextView roomsNumTv = (TextView) dialog.findViewById(R.id.dialog_choose_persons_rooms_rooms_num_tv);

        ImageButton minusAdultsBtn = (ImageButton) dialog.findViewById(R.id.dialog_choose_persons_rooms_minus_adults_btn);
        ImageButton plusAdultsBtn = (ImageButton) dialog.findViewById(R.id.dialog_choose_persons_rooms_plus_adults_btn);
        ImageButton minusChildsBtn = (ImageButton) dialog.findViewById(R.id.dialog_choose_persons_rooms_minus_child_btn);
        ImageButton plusChildsBtn = (ImageButton) dialog.findViewById(R.id.dialog_choose_persons_rooms_plus_child_btn);
        ImageButton minusRoomsBtn = (ImageButton) dialog.findViewById(R.id.dialog_choose_persons_rooms_minus_room_btn);
        ImageButton plusRoomsBtn = (ImageButton) dialog.findViewById(R.id.dialog_choose_persons_rooms_plus_room_btn);

        Button saveBtn = (Button) dialog.findViewById(R.id.dialog_choose_persons_rooms_save_btn);

        minusAdultsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              adultNum=5;
            }
        });

        plusAdultsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        minusChildsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        plusChildsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        minusRoomsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        plusRoomsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dialogAdapterCallback.onMethodCallback(getHomeDisscoverGetItemsListData.get(position));
                dialog.cancel();
            }
        });

//        dialogImageOk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
                //Call
//                clientData = LoadUserData(activity);
//                clean(activity);
//                Call<RestaurantCategoryResponse> removetTokenCall = null;
//                String token=new ClientFireBaseToken().getToken();
//                String apiToken=clientData.getApiToken();
//                if (ISCLIENT.equals("true")) {
//
//                    removetTokenCall = getApiClient().clientRemoveToken(token,apiToken);
//                }  if(ISCLIENT=="false") {
//                    removetTokenCall = getApiClient().restaurantRemoveToken(token,apiToken);
//                }
//                deleteAndUpdateItemCallBack(activity,removetTokenCall);
                // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.

//                dialog.setCanceledOnTouchOutside(true);
//        ImageButton dialogImageNo = (ImageButton) dialog.findViewById(R.id.item_sign_out_dialog_btn_no);
//        dialogImageNo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.cancel();
//            }
//        });


                dialog.show();

            }


        }
