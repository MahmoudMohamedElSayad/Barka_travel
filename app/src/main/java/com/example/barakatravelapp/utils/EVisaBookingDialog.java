package com.example.barakatravelapp.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.data.model.DateTxt;
import com.example.barakatravelapp.data.model.bookEvisaResponce.BookEvisaResponce;
import com.example.barakatravelapp.data.model.getDiscoverHomeResponce.GetDiscoverHomeResponce;
import com.example.barakatravelapp.data.model.getHotelsResponce.GetRoom;
import com.example.barakatravelapp.data.model.getHotelsResponce.HotelData;
import com.example.barakatravelapp.data.model.userLoginResponce.UserData;
import com.example.barakatravelapp.utils.DialogAdapterCallback;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;

import static com.example.barakatravelapp.data.api.ApiClient.getApiClient;
import static com.example.barakatravelapp.data.local.SharedPreferencesManger.LoadUserData;
import static com.example.barakatravelapp.utils.GeneralRequest.sentUserRateAndBookHotelCallBack;
import static com.example.barakatravelapp.utils.HelperMethod.showCalender;


public class EVisaBookingDialog {
    private static int adultNum;
    private DialogAdapterCallback dialogAdapterCallback;
    private TextView country_type,countryTypePrice,servicesFees,totalPrice;
    private UserData userData;
    private int idPackage,idHotel;
    private DateTxt checkinDate, checkoutDate;
    public void showDialog(final Activity activity, BookEvisaResponce bookEvisaResponce) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.dialog_evisa_booking);
        dialog.setCanceledOnTouchOutside(true);
        userData= LoadUserData(activity);
        country_type = (TextView) dialog.findViewById(R.id.dialog_evisa_booking_country_type_tv);
        countryTypePrice = (TextView) dialog.findViewById(R.id.dialog_evisa_booking_country_type_price_tv);
        servicesFees = (TextView) dialog.findViewById(R.id.dialog_evisa_booking_servise_price_tv);
        totalPrice = (TextView) dialog.findViewById(R.id.dialog_evisa_booking_total_price_tv);
        ImageView eVisaBookingCloseBtn = (ImageView) dialog.findViewById(R.id.dialog_evisa_booking_close_btn);
        Button bayNowBtn = (Button) dialog.findViewById(R.id.dialog_evisa_booking_pay_now_btn);
        setData(bookEvisaResponce);

        eVisaBookingCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dialogAdapterCallback.onMethodCallback(getHomeDisscoverGetItemsListData.get(position));
                dialog.cancel();
            }
        });

        bayNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = bookEvisaResponce.getPayment();
                try {
                    Intent i = new Intent("android.intent.action.MAIN");
                    i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
                    i.addCategory("android.intent.category.LAUNCHER");
                    i.setData(Uri.parse(url));
                    activity.startActivity(i);
                }
                catch(ActivityNotFoundException e) {
                    // Chrome is not installed
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    activity.startActivity(i);
                    dialog.cancel();
                }

            }
        });


                dialog.show();

            }


    private void setData(BookEvisaResponce bookEvisaResponce) {
        country_type.setText(bookEvisaResponce.getCountryType()+" fees  :");
        countryTypePrice.setText(bookEvisaResponce.getTotal().toString()+" $");
        servicesFees.setText(bookEvisaResponce.getFees().toString()+" $");
        totalPrice.setText(bookEvisaResponce.getTotalAmount().toString()+" $");

    }







}
