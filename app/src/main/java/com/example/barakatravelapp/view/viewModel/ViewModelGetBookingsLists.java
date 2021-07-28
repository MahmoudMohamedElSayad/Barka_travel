package com.example.barakatravelapp.view.viewModel;


import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.barakatravelapp.data.model.getBookingEvisaResponce.GetBookingEvisaResponce;
import com.example.barakatravelapp.data.model.getBookingFlightsResponce.GetBookingFlightsResponce;
import com.example.barakatravelapp.data.model.getBookingHotelsResponce.GetBookingHotelsResponce;
import com.example.barakatravelapp.data.model.getBookingPackageResponce.GetBookingPackageResponce;
import com.example.barakatravelapp.utils.ToastCreator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.barakatravelapp.utils.ToastCreator.onCreateErrorToast;
import static com.example.barakatravelapp.utils.network.InternetState.isConnected;


public class ViewModelGetBookingsLists extends ViewModel {

//    private UserRepository userRepository;
    private MutableLiveData<GetBookingFlightsResponce> getBookingsFlightResponce = new MutableLiveData<GetBookingFlightsResponce>();
    private MutableLiveData<GetBookingHotelsResponce> getBookingsHotelsResponce = new MutableLiveData<>();
    private MutableLiveData<GetBookingPackageResponce> getBookingsHajjAndUmrahResponce = new MutableLiveData<>();
    private MutableLiveData<GetBookingEvisaResponce> getBookingsEVisaResponce = new MutableLiveData<>();



    public MutableLiveData<GetBookingFlightsResponce> makeGetBookingsFlightsDataList() {
        return getBookingsFlightResponce;
    }

    public void getBookingsFlightsDataList(final Activity activity, final LinearLayout errorSubView, final Call<GetBookingFlightsResponce> method, final SwipeRefreshLayout flightsHomeFragmentSrRefreshRv, final RelativeLayout loadMore) {
        if (isConnected(activity)) {

            flightsHomeFragmentSrRefreshRv.setRefreshing(true);
            errorSubView.setVisibility(View.GONE);

            method.enqueue(new Callback<GetBookingFlightsResponce>() {
                @Override
                public void onResponse(Call<GetBookingFlightsResponce> call, Response<GetBookingFlightsResponce> response) {

                    if (response.body() != null) {
                        try {
//                            clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                            clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                            loadMore.setVisibility(View.GONE);
                            flightsHomeFragmentSrRefreshRv.setRefreshing(false);
                            if (response.body().getStatus().equals("success")) {
//
                                getBookingsFlightResponce.postValue(response.body());

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
                public void onFailure(Call<GetBookingFlightsResponce> call, Throwable t) {
                    try {
//                        clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                        clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                        loadMore.setVisibility(View.GONE);
                        flightsHomeFragmentSrRefreshRv.setRefreshing(false);
//                        onCreateErrorToast(activity, t.getMessage());

//                        new HomeFragment().setError(String.valueOf(R.string.error_list));
                        getBookingsFlightResponce.postValue(null);
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

    public MutableLiveData<GetBookingHotelsResponce> makeGetBookingsHotelsDataList() {
        return getBookingsHotelsResponce;
    }

    public void getHBookingsotelsDataList(final Activity activity, final LinearLayout errorSubView, final Call<GetBookingHotelsResponce> method, final SwipeRefreshLayout hotelsHomeFragmentSrRefreshRv, final RelativeLayout loadMore) {
        if (isConnected(activity)) {

            hotelsHomeFragmentSrRefreshRv.setRefreshing(true);
            errorSubView.setVisibility(View.GONE);


            method.enqueue(new Callback<GetBookingHotelsResponce>() {
                @Override
                public void onResponse(Call<GetBookingHotelsResponce> call, Response<GetBookingHotelsResponce> response) {

                    if (response.body() != null) {
                        try {
//                            clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                            clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                            loadMore.setVisibility(View.GONE);
                            hotelsHomeFragmentSrRefreshRv.setRefreshing(false);
                            if (response.body().getStatus().equals("success")) {
//
                                getBookingsHotelsResponce.postValue(response.body());

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
                public void onFailure(Call<GetBookingHotelsResponce> call, Throwable t) {
                    try {
//                        clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                        clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                        loadMore.setVisibility(View.GONE);
                        hotelsHomeFragmentSrRefreshRv.setRefreshing(false);
//                        new HomeFragment().setError(String.valueOf(R.string.error_list));
                        getBookingsHotelsResponce.postValue(null);
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

    public MutableLiveData<GetBookingPackageResponce> makeGetBookingsHajjAndUmrahDataList() {
        return getBookingsHajjAndUmrahResponce;
    }

    public void getBookingsHajjAndUmrahDataList(final Activity activity, final LinearLayout errorSubView, final Call<GetBookingPackageResponce> method, final SwipeRefreshLayout hajjAndUmrahHomeFragmentSrRefreshRv, final RelativeLayout loadMore) {
        if (isConnected(activity)) {

            hajjAndUmrahHomeFragmentSrRefreshRv.setRefreshing(true);
            errorSubView.setVisibility(View.GONE);

            method.enqueue(new Callback<GetBookingPackageResponce>() {
                @Override
                public void onResponse(Call<GetBookingPackageResponce> call, Response<GetBookingPackageResponce> response) {

                    if (response.body() != null) {
                        try {
//                            clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                            clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                            loadMore.setVisibility(View.GONE);
                            hajjAndUmrahHomeFragmentSrRefreshRv.setRefreshing(false);
                            if (response.body().getStatus().equals("success")) {
//
                                getBookingsHajjAndUmrahResponce.postValue(response.body());

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
                public void onFailure(Call<GetBookingPackageResponce> call, Throwable t) {
                    try {
//                        clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                        clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                        loadMore.setVisibility(View.GONE);
                        hajjAndUmrahHomeFragmentSrRefreshRv.setRefreshing(false);
//                        new HomeFragment().setError(String.valueOf(R.string.error_list));
                        getBookingsHajjAndUmrahResponce.postValue(null);
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



    public MutableLiveData<GetBookingEvisaResponce> makeGetBookingsEVisaDataList() {
        return getBookingsEVisaResponce;
    }

    public void getBookingsEVsaDataList(final Activity activity, final LinearLayout errorSubView, final Call<GetBookingEvisaResponce> method, final SwipeRefreshLayout flightsHomeFragmentSrRefreshRv, final RelativeLayout loadMore) {
        if (isConnected(activity)) {

            flightsHomeFragmentSrRefreshRv.setRefreshing(true);
            errorSubView.setVisibility(View.GONE);

            method.enqueue(new Callback<GetBookingEvisaResponce>() {
                @Override
                public void onResponse(Call<GetBookingEvisaResponce> call, Response<GetBookingEvisaResponce> response) {

                    if (response.body() != null) {
                        try {
//                            clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                            clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                            loadMore.setVisibility(View.GONE);
                            flightsHomeFragmentSrRefreshRv.setRefreshing(false);
                            if (response.body().getStatus().equals("success")) {
//
                                getBookingsEVisaResponce.postValue(response.body());

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
                public void onFailure(Call<GetBookingEvisaResponce> call, Throwable t) {
                    try {
//                        clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                        clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                        loadMore.setVisibility(View.GONE);
                        flightsHomeFragmentSrRefreshRv.setRefreshing(false);
//                        onCreateErrorToast(activity, t.getMessage());

//                        new HomeFragment().setError(String.valueOf(R.string.error_list));
                        getBookingsEVisaResponce.postValue(null);
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
}
