package com.example.barakatravelapp.utils;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.data.model.getDiscoverHomeResponce.GetDiscoverHomeResponce;
import com.example.barakatravelapp.data.model.userLoginResponce.UserData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

import static com.example.barakatravelapp.data.api.ApiClient.getApiClient;
import static com.example.barakatravelapp.data.local.SharedPreferencesManger.LoadUserData;
import static com.example.barakatravelapp.utils.GeneralRequest.sentUserRateAndBookHotelCallBack;
import static com.example.barakatravelapp.utils.validation.Validation.validationEditTextsEmpty;
import static com.example.barakatravelapp.utils.validation.Validation.validationLength;
import static com.example.barakatravelapp.utils.validation.Validation.validationPhone;


public class WriteRateDialog {
    private static int adultNum;
    private DialogAdapterCallback dialogAdapterCallback;
    private EditText rateNameTv,ratePhoneTv,rateMessageTv;
    private RatingBar ratingBar;
    List<EditText> editTexts = new ArrayList<>();
    private UserData userData;
    private int idPackage,idHotel;
    public void showDialog(final Activity activity, Integer id, String hotelOrPackage) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.dialog_write_rate);
        dialog.setCanceledOnTouchOutside(true);
        userData= LoadUserData(activity);
        if (hotelOrPackage.equalsIgnoreCase("hotel")){
            idHotel=id;
        }else {
        idPackage=id;
        }
         rateNameTv = (EditText) dialog.findViewById(R.id.dialog_write_rate_name_et);
         ratePhoneTv = (EditText) dialog.findViewById(R.id.dialog_write_rate_phone_et);
         rateMessageTv = (EditText) dialog.findViewById(R.id.dialog_write_rate_message_et);

        ImageView rateCloseBtn = (ImageView) dialog.findViewById(R.id.dialog_write_rate_close_btn);
         ratingBar = (RatingBar) dialog.findViewById(R.id.dialog_write_rate_simple_rating_bar);


        Button saveBtn = (Button) dialog.findViewById(R.id.dialog_write_rate_save_changes_btn);



        rateCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dialogAdapterCallback.onMethodCallback(getHomeDisscoverGetItemsListData.get(position));
                dialog.cancel();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dialogAdapterCallback.onMethodCallback(getHomeDisscoverGetItemsListData.get(position));
                onValidate(activity,dialog,hotelOrPackage);

            }
        });

                dialog.show();

            }

    private void onValidate(Activity activity, Dialog dialog, String hotelOrPackage) {
//        cleanError(editTexts);
        editTexts.add(rateNameTv);
        editTexts.add(ratePhoneTv);
        editTexts.add(rateMessageTv);
        if (!validationEditTextsEmpty(editTexts, activity.getString(R.string.empty))) {
            return;
        }
        if (!validationLength(rateNameTv, activity.getString(R.string.invalid_user_name), 2)) {
            return;
        }
        if (!validationPhone(activity, ratePhoneTv)) {
            ToastCreator.onCreateErrorToast(activity, "Enter Phone");
            return;
        }
        if (!validationLength(rateMessageTv, activity.getString(R.string.invalid_message), 2)) {
            return;
        }
        onCall(activity,dialog,hotelOrPackage);
    }

    private void onCall(Activity activity, Dialog dialog, String hotelOrPackage) {
        String name = rateNameTv.getText().toString();
        String message = rateMessageTv.getText().toString();
        String phone = ratePhoneTv.getText().toString();
        int userId = userData.getId();
        int rate = (int) ratingBar.getRating();
        int packageId = idPackage;
        Call<GetDiscoverHomeResponce> updateItemCal= null;
        if (hotelOrPackage.equalsIgnoreCase("hotel")) {
            updateItemCal = getApiClient().sendHotelRate(userId, name, phone, message, rate, idHotel);
        }else {
            updateItemCal = getApiClient().sendHujjAndUmrahRate(userId, name, phone, message, rate, packageId);
        }
        sentUserRateAndBookHotelCallBack(activity,updateItemCal, "Success rate send");
        dialog.cancel();

    }


}
