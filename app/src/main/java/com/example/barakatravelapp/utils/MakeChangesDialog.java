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


public class MakeChangesDialog {
//    private DialogAdapterCallback dialogAdapterCallback;

    public void showDialog(final Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.dialog_package_request);
        dialog.setCanceledOnTouchOutside(true);


//        TextView adultNumTv = (TextView) dialog.findViewById(R.id.dialog_choose_persons_rooms_adults_num_tv);
//        TextView childNumTv = (TextView) dialog.findViewById(R.id.dialog_choose_persons_rooms_childs_num_tv);
//        TextView roomsNumTv = (TextView) dialog.findViewById(R.id.dialog_choose_persons_rooms_rooms_num_tv);



//        minusAdultsBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              adultNum=5;
//            }
//        });
//
//        plusAdultsBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        minusChildsBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });






                dialog.show();

            }


        }
