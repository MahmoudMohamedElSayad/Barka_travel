package com.example.barakatravelapp.utils;

import android.app.Activity;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.data.model.getDiscoverHomeResponce.GetDiscoverHomeResponce;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.barakatravelapp.utils.HelperMethod.dismissProgressDialog;
import static com.example.barakatravelapp.utils.HelperMethod.progressDialog;
import static com.example.barakatravelapp.utils.ToastCreator.onCreateErrorToast;

public class GeneralRequest {

    public static void sentUserRateAndBookHotelCallBack(final Activity activity, final Call<GetDiscoverHomeResponce> method, String success) {
        if (progressDialog == null) {
            HelperMethod.showProgressDialog(activity, activity.getString(R.string.wait));
        } else {
            if (!progressDialog.isShowing()) {
                HelperMethod.showProgressDialog(activity, activity.getString(R.string.wait));
            }
        }

        method.enqueue(new Callback<GetDiscoverHomeResponce>() {
            @Override
            public void onResponse(Call<GetDiscoverHomeResponce> call, Response<GetDiscoverHomeResponce> response) {

                if (response.body() != null) {
                    try {
                        dismissProgressDialog();

                        if (response.body().getStatus().equals("success")) {

                            ToastCreator.onCreateSuccessToast(activity,success);
//                            ToastCreator.onCreateSuccessToast(activity,response.message());

                        } else {
                            onCreateErrorToast(activity, response.body().getMessage());
                        }
                    } catch (Exception e) {

                    }
                }
            }

            @Override
            public void onFailure(Call<GetDiscoverHomeResponce> call, Throwable t) {
                dismissProgressDialog();
//                new HomeFragment().isDialogDataAddSuccess=false;
                onCreateErrorToast(activity, activity.getString(R.string.error));
            }
        });
    }



}
