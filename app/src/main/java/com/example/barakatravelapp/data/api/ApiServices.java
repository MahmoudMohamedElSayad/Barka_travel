package com.example.barakatravelapp.data.api;


import com.example.barakatravelapp.data.model.appSettingResponce.AppSettingResponce;
import com.example.barakatravelapp.data.model.bookEvisaResponce.BookEvisaResponce;
import com.example.barakatravelapp.data.model.getBookingEvisaResponce.GetBookingEvisaResponce;
import com.example.barakatravelapp.data.model.getBookingFlightsResponce.GetBookingFlightsResponce;
import com.example.barakatravelapp.data.model.getBookingHotelsResponce.GetBookingHotelsResponce;
import com.example.barakatravelapp.data.model.getBookingPackageResponce.GetBookingPackageResponce;
import com.example.barakatravelapp.data.model.getDiscoverHomeResponce.GetDiscoverHomeResponce;
import com.example.barakatravelapp.data.model.getFaqResponce.GetFaqResponce;
import com.example.barakatravelapp.data.model.getFlightResponce.GetFlightResponce;
import com.example.barakatravelapp.data.model.getHotelsResponce.GetHotelsResponce;
import com.example.barakatravelapp.data.model.getUmrahAndHujjResponce.GetUmrahAndHujjResponce;
import com.example.barakatravelapp.data.model.userLoginResponce.UserLoginGeneralResponce;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiServices {

    @POST("login")
    @FormUrlEncoded
    Call<UserLoginGeneralResponce> userLogin(@Field("email") String email,
                                             @Field("password") String password);

    @POST("register")
    @FormUrlEncoded
    Call<UserLoginGeneralResponce> onSignUp(@Field("first_name") String first_name,
                                            @Field("last_name") String last_name,
                                            @Field("mobile") String mobile,
                                            @Field("email") String email,
                                            @Field("password") String password);

    @POST("forgetPassword")
    @FormUrlEncoded
    Call<UserLoginGeneralResponce> userResetPassword(@Field("email") String email);


//    @POST("updateProfile")
//    @FormUrlEncoded
//    Call<UserLoginGeneralResponce> userChangeProfileDetails(
//            @Field("userId") String userId,
//            @Field("email") String email,
//            @Field("first_name") String first_name,
//            @Field("last_name") String last_name,
//            @Field("mobile") String mobile,
//            @Field("password") String password,
//            @Field("image") String image
//
//    );

    @Multipart
    @POST("updateProfile")
    Call<UserLoginGeneralResponce> userChangeProfileDetails(
            @Part("userId") RequestBody userId,
            @Part("email") RequestBody email,
            @Part("first_name") RequestBody first_name,
            @Part("last_name") RequestBody last_name,
            @Part("mobile") RequestBody mobile,
            @Part("password") RequestBody password,
            @Part() MultipartBody.Part profileImage
    );

//    @Multipart
//    @POST("person/img")
//    Call<ResponseBody> upImageMany(@Part List<MultipartBody.Part> file);

    @POST("getHotels")
    @FormUrlEncoded
    Call<GetHotelsResponce> getHotelsItemListByFilter(
            @Field("page") int page,
            @Field("search") String search
    );

    @POST("getHotels")
    @FormUrlEncoded
    Call<GetHotelsResponce> getHotelsItemList(
            @Field("page") int page
    );

    @POST("getBookingHotels")
    @FormUrlEncoded
    Call<GetBookingHotelsResponce> getBookingHotelsItemList(
            @Field("userId") int userId,
            @Field("page") int page
    );

    @POST("getFlights")
    @FormUrlEncoded
    Call<GetFlightResponce> getFlightItemListByFilter(
            @Field("search") String search,
            @Field("page") int page
    );

    @POST("getFlights")
    @FormUrlEncoded
    Call<GetFlightResponce> getFlightItemList(
            @Field("page") int page
    );

    @POST("getBookingFlights")
    @FormUrlEncoded
    Call<GetBookingFlightsResponce> getBookingFlightItemList(
            @Field("userId") int userId,
            @Field("page") int page
    );

    @POST("getPackage")
    @FormUrlEncoded
    Call<GetUmrahAndHujjResponce> getHajjAndUmrahItemListByFilter(
            @Field("type") String type,
            @Field("page") int page,
            @Field("search") String search
            );

    @POST("getPackage")
    @FormUrlEncoded
    Call<GetUmrahAndHujjResponce> getHajjAndUmrahItemList(
            @Field("type") String type,
            @Field("page") int page    );

    @POST("getBookingPackage")
    @FormUrlEncoded
    Call<GetBookingPackageResponce> getBookingHajjAndUmrahItemList(
            @Field("userId") int userId,
            @Field("type") String type,
            @Field("page") int page    );

    @POST("home")
    Call<GetDiscoverHomeResponce> getHomeDiscoverItemList(
    );

    @POST("submitRate")
    @FormUrlEncoded
    Call<GetDiscoverHomeResponce> sendHujjAndUmrahRate(@Field("userId") int userId,
                                                        @Field("name") String name,
                                                        @Field("mobile") String mobile,
                                                        @Field("message") String message,
                                                        @Field("rate") int rate,
                                                        @Field("packageId") int packageId
    );

    @POST("submitRate")
    @FormUrlEncoded
    Call<GetDiscoverHomeResponce> sendHotelRate(@Field("userId") int userId,
                                                 @Field("name") String name,
                                                 @Field("mobile") String mobile,
                                                 @Field("message") String message,
                                                 @Field("rate") int rate,
                                                 @Field("hotel_id") int hotel_id
    );

    @POST("bookHotel")
    @FormUrlEncoded
    Call<GetDiscoverHomeResponce> bookHotel(@Field("reserveFrom") String reserveFrom,
                                                @Field("reserveTo") String reserveTo,
                                                @Field("userId") int userId,
                                                @Field("hotelId") int hotelId,
                                                @Field("roomId") int roomId
    );

    @POST("bookFlight")
    @FormUrlEncoded
    Call<GetDiscoverHomeResponce> bookFlight(@Field("flightId") int flightId,
                                            @Field("numForAdult") int numForAdult,
                                            @Field("numForChild") int numForChild,
                                            @Field("userId") int userId
    );

    @Multipart
    @POST("bookPackage")
    Call<UserLoginGeneralResponce> umrahAndHajjBooking(
            @Part("userId") RequestBody userId,
            @Part("pricingId") RequestBody pricingId,
            @Part("pricing") RequestBody pricing,
            @Part("address") RequestBody address,
            @Part("contact_number") RequestBody contact_number,
            @Part("email") RequestBody email,
            @Part("travlFrm") RequestBody travlFrm,
            @Part("prief_travel") RequestBody prief_travel,
            @Part("travel_alone") RequestBody travel_alone,
            @Part("payment_type") RequestBody payment_type,
            @Part("paid") RequestBody paid,
            @Part("remaining") RequestBody remaining,
            @Part("first_name") RequestBody first_name,
            @Part("last_name") RequestBody last_name,
            @Part("zip_code") RequestBody zip_code,
            @Part("gender") RequestBody gender,
            @Part("passport") RequestBody passport,
//            @Part("check_number") RequestBody check_number,
            @Part() MultipartBody.Part passport_image,
            @Part() MultipartBody.Part personal_image
    );

    @Multipart
    @POST("bookEvisa")
    Call<BookEvisaResponce> evisaBooking(
            @Part("userId") RequestBody userId,
            @Part("firstName") RequestBody firstName,
            @Part("lastName") RequestBody lastName,
            @Part("contactNumber") RequestBody contactNumber,
            @Part("email") RequestBody email,
            @Part("countryType") RequestBody countryType,
            @Part List<MultipartBody.Part> passportFiles,
            @Part List<MultipartBody.Part> personalFiles

    );

    @POST("getBookingEvisa")
    @FormUrlEncoded
    Call<GetBookingEvisaResponce> getBookingEVisaItemList(
            @Field("page") int page,
            @Field("userId") int userId
    );

    @POST("contactUS")
    @FormUrlEncoded
    Call<UserLoginGeneralResponce> contactUs(@Field("userId") int userId,
                                            @Field("name") String name,
                                            @Field("mobile") String mobile,
                                            @Field("email") String email,
                                            @Field("message") String message);

    @POST("appSetting")
    Call<AppSettingResponce> appSettings(

    );

    @POST("getFaq")
    @FormUrlEncoded
    Call<GetFaqResponce> getFaq(
            @Field("page") int page    );

}
