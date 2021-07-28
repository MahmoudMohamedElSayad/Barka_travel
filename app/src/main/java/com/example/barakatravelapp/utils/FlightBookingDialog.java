package com.example.barakatravelapp.utils;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.adapter.DialogCenterSpinnerAdapter;
import com.example.barakatravelapp.data.model.GeneralResposeData;
import com.example.barakatravelapp.data.model.getDiscoverHomeResponce.GetDiscoverHomeResponce;
import com.example.barakatravelapp.data.model.getFlightResponce.FlightData;
import com.example.barakatravelapp.data.model.userLoginResponce.UserData;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;

import static com.example.barakatravelapp.data.api.ApiClient.getApiClient;
import static com.example.barakatravelapp.data.local.SharedPreferencesManger.LoadUserData;
import static com.example.barakatravelapp.utils.GeneralRequest.sentUserRateAndBookHotelCallBack;
import static com.example.barakatravelapp.utils.HelperMethod.showToast;
import static com.example.barakatravelapp.utils.validation.Validation.validationEditTextsEmpty;
import static com.example.barakatravelapp.utils.validation.Validation.validationEmail;
import static com.example.barakatravelapp.utils.validation.Validation.validationLength;
import static com.example.barakatravelapp.utils.validation.Validation.validationPhone;


public class FlightBookingDialog {
    private static int adultNum;
    private DialogAdapterCallback dialogAdapterCallback;
    private EditText nameTv, phoneTv, emailTv;
    private TextView fromLocation, toLocation, fromDate,toDate,showAdultNum,showChildNum;
    private Spinner chooseAdultsNum, choosechildsNum;

    private RatingBar ratingBar;
    List<EditText> editTexts = new ArrayList<>();
    private UserData userData;
    private int idPackage,idHotel;
    private DialogCenterSpinnerAdapter chooseAdultSpinnerAdapter,chooseChildsSpinnerAdapter;
    private List<GeneralResposeData> chooseListList = new ArrayList<GeneralResposeData>();
    private List<GeneralResposeData> chooseListList2 = new ArrayList<GeneralResposeData>();
    private int adultSelectedId1 =0 ,childsSelectedId1=0;
    private AdapterView.OnItemSelectedListener listener;

    public void showDialog(final Activity activity, FlightData flightData) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.dialog_fright_booking);
        dialog.setCanceledOnTouchOutside(true);
        userData= LoadUserData(activity);

         nameTv = (EditText) dialog.findViewById(R.id.dialog_fright_booking_name_et);
         phoneTv = (EditText) dialog.findViewById(R.id.dialog_fright_booking_phone_et);
         emailTv = (EditText) dialog.findViewById(R.id.dialog_fright_booking_email_et);

        fromLocation = (TextView) dialog.findViewById(R.id.dialog_fright_booking_from_location_tv);
        toLocation = (TextView) dialog.findViewById(R.id.dialog_fright_booking_to_location_tv);
        fromDate = (TextView) dialog.findViewById(R.id.dialog_fright_booking_from_date_tv_tv);
        toDate = (TextView) dialog.findViewById(R.id.dialog_fright_booking_to_date_tv);
//        showAdultNum = (TextView) dialog.findViewById(R.id.dialog_fright_booking_choose_adults_et);
        showChildNum = (TextView) dialog.findViewById(R.id.dialog_fright_booking_choose_childs_et);

        chooseAdultsNum = (Spinner) dialog.findViewById(R.id.dialog_fright_booking_sp_choose_adults);
        choosechildsNum = (Spinner) dialog.findViewById(R.id.dialog_fright_booking_sp_choose_childs);

        ImageView CloseBtn = (ImageView) dialog.findViewById(R.id.dialog_fright_booking_close_btn);

        Button bookBtn = (Button) dialog.findViewById(R.id.dialog_fright_booking_book_now_btn);

        setData(activity,flightData);

        CloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dialogAdapterCallback.onMethodCallback(getHomeDisscoverGetItemsListData.get(position));
                dialog.cancel();
            }
        });

        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dialogAdapterCallback.onMethodCallback(getHomeDisscoverGetItemsListData.get(position));
                onValidate(activity,dialog,flightData);

            }
        });

                dialog.show();

            }

    private void setData(Activity activity, FlightData flightData) {
        nameTv.setText(userData.getFirstName());
        phoneTv.setText(userData.getMobile());
        emailTv.setText(userData.getEmail());

        fromLocation.setText(flightData.getFrom());
        toLocation.setText(flightData.getTo());
        fromDate.setText(flightData.getReservationFrom());
        toDate.setText(flightData.getReservationTo());

//        for (int i=0;i>=5;i++){
//        chooseListList.add(new GeneralResposeData(i,""+(i+1)));}
//        showToast(activity, String.valueOf(chooseListList.get(5)));
        chooseListList.add(new GeneralResposeData(0,""+1));
        chooseListList.add(new GeneralResposeData(1,""+2));
        chooseListList.add(new GeneralResposeData(2,""+3));
        chooseListList.add(new GeneralResposeData(3,""+4));
        chooseListList.add(new GeneralResposeData(4,""+5));
        chooseListList.add(new GeneralResposeData(5,""+6));

        chooseAdultSpinnerAdapter = new DialogCenterSpinnerAdapter(activity);
        chooseAdultSpinnerAdapter.setData(chooseListList, "Choose number of Adults");
        chooseAdultsNum.setAdapter(chooseAdultSpinnerAdapter);
        chooseAdultsNum.setSelection(adultSelectedId1);
        listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                showAdultNum.setText(chooseAdultSpinnerAdapter.getItem(adultSelectedId1).toString());
                adultSelectedId1 = i;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
        chooseAdultsNum.setOnItemSelectedListener(listener);

        chooseListList2.add(new GeneralResposeData(0,""+0));
        chooseListList2.addAll(chooseListList);
        chooseChildsSpinnerAdapter = new DialogCenterSpinnerAdapter(activity);
        chooseChildsSpinnerAdapter.setData(chooseListList2, "Choose number of Childs");
        choosechildsNum.setAdapter(chooseChildsSpinnerAdapter);
        choosechildsNum.setSelection(childsSelectedId1);
        listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                showChildNum.setText(chooseChildsSpinnerAdapter.getItem(childsSelectedId1).toString());
                childsSelectedId1 = i;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
        choosechildsNum.setOnItemSelectedListener(listener);
    }

    private void onValidate(Activity activity, Dialog dialog, FlightData flightData) {
//        cleanError(editTexts);
        editTexts.add(nameTv);
        editTexts.add(phoneTv);
        editTexts.add(emailTv);
        if (!validationEditTextsEmpty(editTexts, activity.getString(R.string.empty))) {
            return;
        }
        if (!validationLength(nameTv, activity.getString(R.string.invalid_user_name), 2)) {
            return;
        }
        if (!validationPhone(activity, phoneTv)) {
            ToastCreator.onCreateErrorToast(activity, "Enter Phone");
            return;
        }
        if (!validationEmail(activity, emailTv)) {

            return;
        }

        if (chooseAdultsNum.getSelectedItemPosition() == 0) {
            ToastCreator.onCreateErrorToast(activity, "Choose number of Adults First");
            return;
        }

        if (choosechildsNum.getSelectedItemPosition() == 0) {
            ToastCreator.onCreateErrorToast(activity, "Choose number of Childs First");
            return;
        }
        onCall(activity,dialog,flightData);
    }

    private void onCall(Activity activity, Dialog dialog, FlightData flightData) {
        int flightId = flightData.getId();
        int numForAdult = Integer.valueOf(chooseAdultSpinnerAdapter.getItem(adultSelectedId1).toString());
        int numForChild = Integer.valueOf(chooseChildsSpinnerAdapter.getItem(childsSelectedId1).toString());
//        int phone = phoneTv.getText().toString();
        int userId = userData.getId();

        Call<GetDiscoverHomeResponce> flightBookingCal= null;
            flightBookingCal = getApiClient().bookFlight(flightId,numForAdult,numForChild,userId);

        sentUserRateAndBookHotelCallBack(activity,flightBookingCal, "Success Flight Booking");
        dialog.cancel();

    }


}
