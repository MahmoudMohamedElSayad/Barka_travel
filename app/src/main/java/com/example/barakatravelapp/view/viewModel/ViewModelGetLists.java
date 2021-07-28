package com.example.barakatravelapp.view.viewModel;


import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.data.model.getDiscoverHomeResponce.GetDiscoverHomeResponce;
import com.example.barakatravelapp.data.model.getFaqResponce.GetFaqResponce;
import com.example.barakatravelapp.data.model.getFlightResponce.GetFlightResponce;
import com.example.barakatravelapp.data.model.getHotelsResponce.GetHotelsResponce;
import com.example.barakatravelapp.data.model.getUmrahAndHujjResponce.GetTopUmarAndTophajjPackage;
import com.example.barakatravelapp.data.model.getUmrahAndHujjResponce.GetUmrahAndHujjResponce;
import com.example.barakatravelapp.data.model.userLoginResponce.UserLoginGeneralResponce;
import com.example.barakatravelapp.utils.HelperMethod;
import com.example.barakatravelapp.utils.ToastCreator;
import com.example.barakatravelapp.view.activity.HomeCycleActivity;
import com.facebook.shimmer.ShimmerFrameLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.barakatravelapp.data.local.SharedPreferencesManger.REMEMBER_ME;
import static com.example.barakatravelapp.data.local.SharedPreferencesManger.SaveData;
import static com.example.barakatravelapp.data.local.SharedPreferencesManger.USER_DATA;
import static com.example.barakatravelapp.data.local.SharedPreferencesManger.USER_PASSWORD;
import static com.example.barakatravelapp.utils.HelperMethod.dismissProgressDialog;
import static com.example.barakatravelapp.utils.HelperMethod.progressDialog;
import static com.example.barakatravelapp.utils.HelperMethod.showLongToast;
import static com.example.barakatravelapp.utils.HelperMethod.showToast;
import static com.example.barakatravelapp.utils.ToastCreator.onCreateErrorToast;
import static com.example.barakatravelapp.utils.network.InternetState.isConnected;


public class ViewModelGetLists extends ViewModel {

//    private UserRepository userRepository;
    private MutableLiveData<GetFlightResponce> getFlightResponce = new MutableLiveData<>();
    private MutableLiveData<GetHotelsResponce> getHotelsResponce = new MutableLiveData<>();
    private MutableLiveData<GetDiscoverHomeResponce> getHomeDiscoverResponce = new MutableLiveData<>();
    private MutableLiveData<GetUmrahAndHujjResponce> getHomeHajjAndUmrahResponce = new MutableLiveData<>();
    private MutableLiveData<GetFaqResponce> getFaqResponce = new MutableLiveData<>();



    public MutableLiveData<GetFlightResponce> makeGetFlightsDataList() {
        return getFlightResponce;
    }

    public void getFlightsDataList(final Activity activity, final LinearLayout errorSubView, final Call<GetFlightResponce> method, final SwipeRefreshLayout flightsHomeFragmentSrRefreshRv, final RelativeLayout loadMore) {
        if (isConnected(activity)) {

            flightsHomeFragmentSrRefreshRv.setRefreshing(true);
            errorSubView.setVisibility(View.GONE);

            method.enqueue(new Callback<GetFlightResponce>() {
                @Override
                public void onResponse(Call<GetFlightResponce> call, Response<GetFlightResponce> response) {

                    if (response.body() != null) {
                        try {
//                            clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                            clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                            loadMore.setVisibility(View.GONE);
                            flightsHomeFragmentSrRefreshRv.setRefreshing(false);
                            if (response.body().getStatus().equals("success")) {
//
                                getFlightResponce.postValue(response.body());

                                ToastCreator.onCreateSuccessToast(activity, "Success");
                            } else {
                                onCreateErrorToast(activity, response.body().getMessage());
//                                new HomeFragment().setError(String.valueOf(R.string.error_list));

                            }

                        } catch(Exception e){

                        }
                    }

                }

                @Override
                public void onFailure(Call<GetFlightResponce> call, Throwable t) {
                    try {
//                        clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                        clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                        loadMore.setVisibility(View.GONE);
                        flightsHomeFragmentSrRefreshRv.setRefreshing(false);
//                        new HomeFragment().setError(String.valueOf(R.string.error_list));
                        getFlightResponce.postValue(null);
                    } catch (Exception e) {

                    }
                }
            });
        } else {
            try {
//                clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                loadMore.setVisibility(View.GONE);
                flightsHomeFragmentSrRefreshRv.setRefreshing(false);
                errorSubView.setVisibility(View.VISIBLE);
//                new HomeFragment().setError(String.valueOf(R.string.error_inter_net));
//                onCreateErrorToast(activity, activity.getString(R.string.error_inter_net));
            } catch (Exception e) {

            }

        }
    }

    public MutableLiveData<GetHotelsResponce> makeGetHotelsDataList() {
        return getHotelsResponce;
    }

    public void getHotelsDataList(final Activity activity, final LinearLayout errorSubView, final Call<GetHotelsResponce> method, final SwipeRefreshLayout hotelsHomeFragmentSrRefreshRv, final RelativeLayout loadMore) {
        if (isConnected(activity)) {

            hotelsHomeFragmentSrRefreshRv.setRefreshing(true);
            errorSubView.setVisibility(View.GONE);


            method.enqueue(new Callback<GetHotelsResponce>() {
                @Override
                public void onResponse(Call<GetHotelsResponce> call, Response<GetHotelsResponce> response) {

                    if (response.body() != null) {
                        try {
//                            clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                            clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                            loadMore.setVisibility(View.GONE);
                            hotelsHomeFragmentSrRefreshRv.setRefreshing(false);
                            if (response.body().getStatus().equals("success")) {
//
                                getHotelsResponce.postValue(response.body());

                                ToastCreator.onCreateSuccessToast(activity, "Success");
                            } else {
                                onCreateErrorToast(activity, response.body().getMessage());
//                                new HomeFragment().setError(String.valueOf(R.string.error_list));

                            }

                        } catch(Exception e){

                        }
                    }

                }

                @Override
                public void onFailure(Call<GetHotelsResponce> call, Throwable t) {
                    try {
//                        clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                        clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                        loadMore.setVisibility(View.GONE);
                        hotelsHomeFragmentSrRefreshRv.setRefreshing(false);
//                        new HomeFragment().setError(String.valueOf(R.string.error_list));
                        getHotelsResponce.postValue(null);
                    } catch (Exception e) {

                    }
                }
            });
        } else {
            try {
//                clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                loadMore.setVisibility(View.GONE);
                hotelsHomeFragmentSrRefreshRv.setRefreshing(false);
                errorSubView.setVisibility(View.VISIBLE);
//                new HomeFragment().setError(String.valueOf(R.string.error_inter_net));
//                onCreateErrorToast(activity, activity.getString(R.string.error_inter_net));
            } catch (Exception e) {

            }

        }
    }

    public MutableLiveData<GetUmrahAndHujjResponce> makeGetHajjAndUmrahDataList() {
        return getHomeHajjAndUmrahResponce;
    }

    public void getHajjAndUmrahDataList(final Activity activity, final LinearLayout errorSubView, final Call<GetUmrahAndHujjResponce> method, final SwipeRefreshLayout hajjAndUmrahHomeFragmentSrRefreshRv, final RelativeLayout loadMore) {
        if (isConnected(activity)) {

            hajjAndUmrahHomeFragmentSrRefreshRv.setRefreshing(true);
            errorSubView.setVisibility(View.GONE);

            method.enqueue(new Callback<GetUmrahAndHujjResponce>() {
                @Override
                public void onResponse(Call<GetUmrahAndHujjResponce> call, Response<GetUmrahAndHujjResponce> response) {

                    if (response.body() != null) {
                        try {
//                            clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                            clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                            loadMore.setVisibility(View.GONE);
                            hajjAndUmrahHomeFragmentSrRefreshRv.setRefreshing(false);
                            if (response.body().getStatus().equals("success")) {
//
                                getHomeHajjAndUmrahResponce.postValue(response.body());

                                ToastCreator.onCreateSuccessToast(activity, "Success");
                            } else {
                                onCreateErrorToast(activity, response.body().getMessage());
//                                new HomeFragment().setError(String.valueOf(R.string.error_list));

                            }

                        } catch(Exception e){

                        }
                    }

                }

                @Override
                public void onFailure(Call<GetUmrahAndHujjResponce> call, Throwable t) {
                    try {
//                        clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                        clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                        loadMore.setVisibility(View.GONE);
                        hajjAndUmrahHomeFragmentSrRefreshRv.setRefreshing(false);
//                        new HomeFragment().setError(String.valueOf(R.string.error_list));
                        getHomeHajjAndUmrahResponce.postValue(null);
                    } catch (Exception e) {

                    }
                }
            });
        } else {
            try {
//                clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                loadMore.setVisibility(View.GONE);
                hajjAndUmrahHomeFragmentSrRefreshRv.setRefreshing(false);
                errorSubView.setVisibility(View.VISIBLE);
//                new HomeFragment().setError(String.valueOf(R.string.error_inter_net));
//                onCreateErrorToast(activity, activity.getString(R.string.error_inter_net));
            } catch (Exception e) {

            }

        }
    }


    public MutableLiveData<GetDiscoverHomeResponce> makeGetHomeDisscoverDataList() {
        return getHomeDiscoverResponce;
    }

    public void getHomeDisscoverDataList(final Activity activity, final LinearLayout errorSubView, final Call<GetDiscoverHomeResponce> method, final SwipeRefreshLayout discoverHomeFragmentSrRefreshRv, final RelativeLayout loadMore) {
        if (isConnected(activity)) {

            discoverHomeFragmentSrRefreshRv.setRefreshing(true);
//            errorSubView.setVisibility(View.GONE);


            method.enqueue(new Callback<GetDiscoverHomeResponce>() {
                @Override
                public void onResponse(Call<GetDiscoverHomeResponce> call, Response<GetDiscoverHomeResponce> response) {

                    if (response.body() != null) {
                        try {
//                            clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                            clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                            loadMore.setVisibility(View.GONE);
                            discoverHomeFragmentSrRefreshRv.setRefreshing(false);
                            if (response.body().getStatus().equals("success")) {
//
                                getHomeDiscoverResponce.postValue(response.body());

                                ToastCreator.onCreateSuccessToast(activity, "Success");
                            } else {
                                onCreateErrorToast(activity, response.body().getMessage());
//                                new HomeFragment().setError(String.valueOf(R.string.error_list));

                            }

                        } catch(Exception e){

                        }
                    }

                }

                @Override
                public void onFailure(Call<GetDiscoverHomeResponce> call, Throwable t) {
                    try {
//                        clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                        clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                        loadMore.setVisibility(View.GONE);
                        discoverHomeFragmentSrRefreshRv.setRefreshing(false);
//                        new HomeFragment().setError(String.valueOf(R.string.error_list));
                        getHomeDiscoverResponce.postValue(null);
                    } catch (Exception e) {

                    }
                }
            });
        } else {
            try {
//                clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                loadMore.setVisibility(View.GONE);
                discoverHomeFragmentSrRefreshRv.setRefreshing(false);
//                errorSubView.setVisibility(View.VISIBLE);
//                new HomeFragment().setError(String.valueOf(R.string.error_inter_net));
                onCreateErrorToast(activity, activity.getString(R.string.error_inter_net));
            } catch (Exception e) {

            }

        }
    }

    public MutableLiveData<GetFaqResponce> makeGetFaqDataList() {
        return getFaqResponce;
    }
    public void getFaqDataList(final Activity activity, final LinearLayout errorSubView, final Call<GetFaqResponce> method, final SwipeRefreshLayout discoverHomeFragmentSrRefreshRv, final RelativeLayout loadMore) {
        if (isConnected(activity)) {

            discoverHomeFragmentSrRefreshRv.setRefreshing(true);
//            errorSubView.setVisibility(View.GONE);


            method.enqueue(new Callback<GetFaqResponce>() {
                @Override
                public void onResponse(Call<GetFaqResponce> call, Response<GetFaqResponce> response) {

                    if (response.body() != null) {
                        try {
//                            clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                            clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                            loadMore.setVisibility(View.GONE);
                            discoverHomeFragmentSrRefreshRv.setRefreshing(false);
                            if (response.body().getStatus().equals("success")) {
//
                                getFaqResponce.postValue(response.body());

                                ToastCreator.onCreateSuccessToast(activity, "Success");
                            } else {
                                onCreateErrorToast(activity, response.body().getMessage());
//                                new HomeFragment().setError(String.valueOf(R.string.error_list));

                            }

                        } catch(Exception e){

                        }
                    }

                }

                @Override
                public void onFailure(Call<GetFaqResponce> call, Throwable t) {
                    try {
//                        clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                        clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                        loadMore.setVisibility(View.GONE);
                        discoverHomeFragmentSrRefreshRv.setRefreshing(false);
//                        new HomeFragment().setError(String.valueOf(R.string.error_list));
                        getFaqResponce.postValue(null);
                    } catch (Exception e) {

                    }
                }
            });
        } else {
            try {
//                clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                loadMore.setVisibility(View.GONE);
                discoverHomeFragmentSrRefreshRv.setRefreshing(false);
//                errorSubView.setVisibility(View.VISIBLE);
//                new HomeFragment().setError(String.valueOf(R.string.error_inter_net));
                onCreateErrorToast(activity, activity.getString(R.string.error_inter_net));
            } catch (Exception e) {

            }

        }
    }
}
