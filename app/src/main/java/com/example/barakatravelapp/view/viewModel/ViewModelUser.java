package com.example.barakatravelapp.view.viewModel;


import android.app.Activity;
import android.content.Intent;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.data.model.appSettingResponce.AppSettingResponce;
import com.example.barakatravelapp.data.model.bookEvisaResponce.BookEvisaResponce;
import com.example.barakatravelapp.data.model.userLoginResponce.UserLoginGeneralResponce;
import com.example.barakatravelapp.utils.HelperMethod;
import com.example.barakatravelapp.utils.ToastCreator;
import com.example.barakatravelapp.view.activity.HomeCycleActivity;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.barakatravelapp.data.local.SharedPreferencesManger.REMEMBER_ME;
import static com.example.barakatravelapp.data.local.SharedPreferencesManger.SaveData;
import static com.example.barakatravelapp.data.local.SharedPreferencesManger.USER_DATA;
import static com.example.barakatravelapp.data.local.SharedPreferencesManger.USER_PASSWORD;
import static com.example.barakatravelapp.data.local.SharedPreferencesManger.clean;
import static com.example.barakatravelapp.utils.HelperMethod.dismissProgressDialog;
import static com.example.barakatravelapp.utils.HelperMethod.progressDialog;
import static com.example.barakatravelapp.utils.HelperMethod.showLongToast;
import static com.example.barakatravelapp.utils.HelperMethod.showToast;
import static com.example.barakatravelapp.utils.ToastCreator.onCreateErrorToast;
import static com.example.barakatravelapp.utils.network.InternetState.isConnected;


public class ViewModelUser extends ViewModel {

//    private UserRepository userRepository;
    private MutableLiveData<UserLoginGeneralResponce> generalLoginAndUpdateProfileResponse = new MutableLiveData<>();
    private MutableLiveData<UserLoginGeneralResponce> generalRegisterationAndForgetPasswordResponse = new MutableLiveData<>();
    private MutableLiveData<BookEvisaResponce> eVisaBookingResponse = new MutableLiveData<>();
    private MutableLiveData<AppSettingResponce> appSettingsResponse = new MutableLiveData<>();


    private String token;
    private String apiToken;

    public MutableLiveData<UserLoginGeneralResponce> setGeneralLoginAndUpdateProfile() {
        return generalLoginAndUpdateProfileResponse;
    }

    public void setGeneralLoginAndUpdateProfile(final Activity activity, final Call<UserLoginGeneralResponce> method, final String password, final boolean remember, final boolean auth) {
        if (isConnected(activity)) {

            if (progressDialog == null) {
                HelperMethod.showProgressDialog(activity, activity.getString(R.string.wait));
            } else {
                if (!progressDialog.isShowing()) {
                    HelperMethod.showProgressDialog(activity, activity.getString(R.string.wait));
                }
            }
//            userRepository = new UserRepository();

//            UserRepository.getInstance().clientLogin(activity, email, password, remember, auth)

                method.enqueue(new Callback<UserLoginGeneralResponce>() {
                @Override
                public void onResponse(Call<UserLoginGeneralResponce> call, Response<UserLoginGeneralResponce> response) {

                    if (response.body() != null) {
                        try {

                            if (response.body().getStatus().equals("success")) {
//                                if (response.body().getMessage() != "the user is not vertified to login please visit your email") {
                                clean(activity);
                                    SaveData(activity, USER_PASSWORD, password);
                                SaveData(activity, USER_DATA, response.body().getUser());
                                SaveData(activity, REMEMBER_ME, remember);
                                if (auth) {
//                                    Call<UserLoginGeneralResponce> tokenCall = null;
//                                    token=new ClientFireBaseToken().getToken();
//                                    apiToken=response.body().getData().getApiToken();
//                                    showToast(activity, "token"+apiToken);
//                                    if (ISCLIENT.equals("true")) {
//
//                                        tokenCall = getApiClient().clientSignUpToken(token, "android",apiToken);
//                                    } else if(ISCLIENT=="false") {
//                                        tokenCall = getApiClient().restaurantSignUpToken(token, "android",apiToken);
//                                    }
//
//
//                                    getAndMakeRegisterToken(activity,tokenCall);
                                    Intent intent = new Intent(activity, HomeCycleActivity.class);
                                    activity.startActivity(intent);
//                                    showToast(activity, t.getCause());
                                    activity.finish();
                                }
//                            }

                                dismissProgressDialog();
                                generalLoginAndUpdateProfileResponse.postValue(response.body());
//                                if (response.body().getMessage()!=null) {
                                ToastCreator.onCreateSuccessToast(activity, "Success");

//                                }

                            } else {
                                dismissProgressDialog();
                                showLongToast(activity, response.body().getMessage());
//                                onCreateErrorToast(activity, response.body().getMessage());
                            }
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<UserLoginGeneralResponce> call, Throwable t) {
                    dismissProgressDialog();
                    onCreateErrorToast(activity, activity.getString(R.string.error));
                    generalLoginAndUpdateProfileResponse.postValue(null);
                }
            });
        } else {
            try {
                onCreateErrorToast(activity, activity.getString(R.string.error_inter_net));
            } catch (Exception e) {

            }

        }

    }

    public MutableLiveData<UserLoginGeneralResponce> makeResetAndNewPasswordResponseAndSignUpAndBooking() {
        return generalRegisterationAndForgetPasswordResponse;
    }

    public void setAndMakeResetAndNewPasswordResponseAndSignUpAndBooking(final Activity activity, final Call<UserLoginGeneralResponce> method, String succes_send, boolean book) {
        if (isConnected(activity)) {

            if (progressDialog == null) {
                HelperMethod.showProgressDialog(activity, activity.getString(R.string.wait));
            } else {
                if (!progressDialog.isShowing()) {
                    HelperMethod.showProgressDialog(activity, activity.getString(R.string.wait));
                }
            }

            method.enqueue(new Callback<UserLoginGeneralResponce>() {
                @Override
                public void onResponse(Call<UserLoginGeneralResponce> call, Response<UserLoginGeneralResponce> response) {

                    if (response.body() != null) {
                        try {

                            if (response.body().getStatus().equals("success")) {
                                dismissProgressDialog();
                                generalRegisterationAndForgetPasswordResponse.postValue(response.body());
                                ToastCreator.onCreateSuccessToast(activity, succes_send);
//                                if(!book){
//                                    ToastCreator.onCreateSuccessToast(activity, response.body().getMessage());
//                                }
                        } else {
                                dismissProgressDialog();
                                onCreateErrorToast(activity, response.body().getMessage());

                            }
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<UserLoginGeneralResponce> call, Throwable t) {
                    dismissProgressDialog();
                    showToast(activity, String.valueOf(t.getMessage()));
                    onCreateErrorToast(activity, activity.getString(R.string.error));
                    generalRegisterationAndForgetPasswordResponse.postValue(null);
                }
            });
        } else {
            try {
                onCreateErrorToast(activity, activity.getString(R.string.error_inter_net));
            } catch (Exception e) {

            }

        }

    }

    public MutableLiveData<BookEvisaResponce> makeeVisaBooking() {
        return eVisaBookingResponse;
    }

    public void setVisaBooking(final Activity activity, final Call<BookEvisaResponce> method, boolean dimiss) {
        if (isConnected(activity)) {

            if (progressDialog == null) {
                HelperMethod.showProgressDialog(activity, activity.getString(R.string.wait));
            } else {
                if (!progressDialog.isShowing()) {
                    HelperMethod.showProgressDialog(activity, activity.getString(R.string.wait));
                }
            }

            method.enqueue(new Callback<BookEvisaResponce>() {
                @Override
                public void onResponse(Call<BookEvisaResponce> call, Response<BookEvisaResponce> response) {

                    if (response.body() != null) {
                        try {

                            if (response.body().getStatus().equals("success")) {
                                dismissProgressDialog();
                                eVisaBookingResponse.postValue(response.body());
                                ToastCreator.onCreateSuccessToast(activity, "Success E-Visa Booking");
                            } else {
                                dismissProgressDialog();
                                onCreateErrorToast(activity, response.body().getMessage());

                            }
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<BookEvisaResponce> call, Throwable t) {
                    dismissProgressDialog();
//                    Log.e(TAG, t.getLocalizedMessage());
//                    Log.e(TAG, t.getMessage());
//                    t.printStackTrace();
                    showToast(activity, String.valueOf(t.getCause()));
                    onCreateErrorToast(activity, activity.getString(R.string.error));
                    eVisaBookingResponse.postValue(null);
                }
            });
        } else {
            try {
                onCreateErrorToast(activity, activity.getString(R.string.error_inter_net));
            } catch (Exception e) {

            }

        }

    }

    public MutableLiveData<AppSettingResponce> makeGetAppSettings() {
        return appSettingsResponse;
    }

    public void getAppSettings(final Activity activity, final Call<AppSettingResponce> method, boolean dimiss) {
        if (isConnected(activity)) {

            if (progressDialog == null) {
                HelperMethod.showProgressDialog(activity, activity.getString(R.string.wait));
            } else {
                if (!progressDialog.isShowing()) {
                    HelperMethod.showProgressDialog(activity, activity.getString(R.string.wait));
                }
            }

            method.enqueue(new Callback<AppSettingResponce>() {
                @Override
                public void onResponse(Call<AppSettingResponce> call, Response<AppSettingResponce> response) {

                    if (response.body() != null) {
                        try {

                            if (response.body().getStatus().equals("success")) {
                                dismissProgressDialog();
                                appSettingsResponse.postValue(response.body());
                                ToastCreator.onCreateSuccessToast(activity, "Success");
                            } else {
                                dismissProgressDialog();
                                onCreateErrorToast(activity, response.body().getMessage());

                            }
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<AppSettingResponce> call, Throwable t) {
                    dismissProgressDialog();
//                    Log.e(TAG, t.getLocalizedMessage());
//                    Log.e(TAG, t.getMessage());
//                    t.printStackTrace();
                    showToast(activity, String.valueOf(t.getCause()));
                    onCreateErrorToast(activity, activity.getString(R.string.error));
                    appSettingsResponse.postValue(null);
                }
            });
        } else {
            try {
                onCreateErrorToast(activity, activity.getString(R.string.error_inter_net));
            } catch (Exception e) {

            }

        }

    }



}
