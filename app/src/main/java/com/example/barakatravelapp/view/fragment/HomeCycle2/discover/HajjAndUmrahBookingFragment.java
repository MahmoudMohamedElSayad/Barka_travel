package com.example.barakatravelapp.view.fragment.HomeCycle2.discover;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.adapter.SpinnerAdapter;
import com.example.barakatravelapp.data.model.GeneralResposeData;
import com.example.barakatravelapp.data.model.getUmrahAndHujjResponce.GetTopUmarAndTophajjPackage;
import com.example.barakatravelapp.data.model.getUmrahAndHujjResponce.Pricing;
import com.example.barakatravelapp.data.model.userLoginResponce.UserData;
import com.example.barakatravelapp.data.model.userLoginResponce.UserLoginGeneralResponce;
import com.example.barakatravelapp.utils.ToastCreator;
import com.example.barakatravelapp.view.fragment.BaSeFragment;
import com.example.barakatravelapp.view.viewModel.ViewModelUser;
import com.google.android.material.textfield.TextInputLayout;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.AlbumFile;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

import static com.example.barakatravelapp.data.api.ApiClient.getApiClient;
import static com.example.barakatravelapp.data.local.SharedPreferencesManger.LoadUserData;
import static com.example.barakatravelapp.utils.HelperMethod.convertFileToMultipart;
import static com.example.barakatravelapp.utils.HelperMethod.convertToRequestBody;
import static com.example.barakatravelapp.utils.HelperMethod.onLoadImageFromUrl;
import static com.example.barakatravelapp.utils.HelperMethod.openGalleryِAlpom;
import static com.example.barakatravelapp.utils.validation.Validation.cleanError;
import static com.example.barakatravelapp.utils.validation.Validation.validationAllEmpty;
import static com.example.barakatravelapp.utils.validation.Validation.validationEmail;
import static com.example.barakatravelapp.utils.validation.Validation.validationLength;
import static com.example.barakatravelapp.utils.validation.Validation.validationPhone;


public class HajjAndUmrahBookingFragment extends BaSeFragment {
    private static final String CLIENTPROFILEIMAGE = "image";
    @BindView(R.id.fragment_hajj_and_umrah_booking_umrah_package_name_tv)
    TextView fragmentHajjAndUmrahBookingUmrahPackageNameTv;
    @BindView(R.id.fragment_hajj_and_umrah_booking_sp_gender)
    Spinner fragmentHajjAndUmrahBookingSpGender;
    @BindView(R.id.fragment_hajj_and_umrah_booking_til_first_name)
    TextInputLayout fragmentHajjAndUmrahBookingTilFirstName;
    @BindView(R.id.fragment_hajj_and_umrah_booking_til_last_name)
    TextInputLayout fragmentHajjAndUmrahBookingTilLastName;
    @BindView(R.id.fragment_hajj_and_umrah_booking_til_address)
    TextInputLayout fragmentHajjAndUmrahBookingTilAddress;
    @BindView(R.id.fragment_hajj_and_umrah_booking_til_phone)
    TextInputLayout fragmentHajjAndUmrahBookingTilPhone;
    @BindView(R.id.fragment_hajj_and_umrah_booking_sp_passport)
    Spinner fragmentHajjAndUmrahBookingSpPassport;
    @BindView(R.id.fragment_hajj_and_umrah_booking_til_email)
    TextInputLayout fragmentHajjAndUmrahBookingTilEmail;
    @BindView(R.id.fragment_hajj_and_umrah_booking_traveling_from)
    TextInputLayout fragmentHajjAndUmrahBookingTravelingFrom;
    @BindView(R.id.fragment_hajj_and_umrah_booking_til_write_comment)
    TextInputLayout fragmentHajjAndUmrahBookingTilWriteComment;
    @BindView(R.id.fragment_hajj_and_umrah_booking_til_traveling_alone)
    TextInputLayout fragmentHajjAndUmrahBookingTilTravelingAlone;
    @BindView(R.id.fragment_hajj_and_umrah_booking_get_passport_image_img)
    ImageView fragmentHajjAndUmrahBookingGetPassportImageImg;
    @BindView(R.id.fragment_hajj_and_umrah_booking_get_personal_image_img)
    ImageView fragmentHajjAndUmrahBookingGetPersonalImageImg;
    @BindView(R.id.fragment_hajj_and_umrah_booking_til_zip_code)
    TextInputLayout fragmentHajjAndUmrahBookingTilZipCode;
    @BindView(R.id.fragment_hajj_and_umrah_booking_sp_traveling_alone)
    Spinner fragmentHajjAndUmrahBookingSpTravelingAlone;
    @BindView(R.id.fragment_hajj_and_umrah_booking_sp_visa)
    Spinner fragmentHajjAndUmrahBookingSpVisa;
    private NavController navController;
    private SpinnerAdapter genderSpinnerAdapter;
    private SpinnerAdapter passportSpinnerAdapter;
    private SpinnerAdapter travelAloneSpinnerAdapter;
    private SpinnerAdapter paymentTypeSpinnerAdapter;
    private List<GeneralResposeData> genderList = new ArrayList<GeneralResposeData>();
    private List<GeneralResposeData> passportList = new ArrayList<GeneralResposeData>();
    private List<GeneralResposeData> travelAloneList = new ArrayList<GeneralResposeData>();
    private List<GeneralResposeData> paymentTypeList = new ArrayList<GeneralResposeData>();
    private int genderSelectedId1 = 0;
    private int passportSelectedId1 = 0;
    private int travelAloneSelectedId1 = 0 ,paymentTypeSelectedId1=0;
    private ViewModelUser viewModelUser;
    private GetTopUmarAndTophajjPackage getTopUmarAndTophajjPackage;
    private Pricing pricing;
    private String mPassportPath;
    private String mPersonalPath;
    private UserData userData;
    private static ArrayList<AlbumFile> alpom = new ArrayList<>();

    private AdapterView.OnItemSelectedListener listener,listener2,listener3,listener4;
    private String isDiscoverOrHajjOrUmarah;
    private String url;

//    private List<String> passportList = new ArrayList<String>();
//    private List<String> travelAloneList = new ArrayList<String>();

    public HajjAndUmrahBookingFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (this.getArguments() != null) {
            getTopUmarAndTophajjPackage = (GetTopUmarAndTophajjPackage) this.getArguments().getSerializable("Object");
            pricing = (Pricing) this.getArguments().getSerializable("Object2");
            isDiscoverOrHajjOrUmarah = this.getArguments().getString("DiscoverOrHajjOrUmrah");

//            showToast(getActivity(), pricing.getName());
        }
        View root = inflater.inflate(R.layout.fragment_hajj_and_umrah_booking, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        userData = LoadUserData(getActivity());
        initListener();
        setSpiners();
        return root;
    }

    @SuppressLint("FragmentLiveDataObserve")
    private void initListener() {
        viewModelUser = ViewModelProviders.of(this).get(ViewModelUser.class);
        viewModelUser.makeResetAndNewPasswordResponseAndSignUpAndBooking().observe(this, new Observer<UserLoginGeneralResponce>() {
            @Override
            public void onChanged(@Nullable UserLoginGeneralResponce response) {
                if (response != null) {
                    if (response.getStatus().equals("success")) {
//                        showToast(getActivity(), "success");
                           url=response.getPaypal();
                           String type =String.valueOf(paymentTypeSpinnerAdapter.getItem(paymentTypeSelectedId1));
                       if(type.equalsIgnoreCase("Visa"))
                        {
                            showBookingDialog();
                        }else {
                           navController.navigate(R.id.action_hajjAndUmrahBookingFragment_to_successfulPaymentFragment);

                       }
                    }
                }
            }
        });
    }

    private void setSpiners() {
        fragmentHajjAndUmrahBookingUmrahPackageNameTv.setText(getTopUmarAndTophajjPackage.getUmar().getName());

        genderList.add(new GeneralResposeData(0, "male"));
        genderList.add(new GeneralResposeData(1, "female"));
        genderSpinnerAdapter = new SpinnerAdapter(getActivity());
        genderSpinnerAdapter.setData(genderList, "Gender");
        fragmentHajjAndUmrahBookingSpGender.setAdapter(genderSpinnerAdapter);
        fragmentHajjAndUmrahBookingSpGender.setSelection(genderSelectedId1);
        listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                genderSelectedId1 = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
        fragmentHajjAndUmrahBookingSpGender.setOnItemSelectedListener(listener);
        passportList.add(new GeneralResposeData(0, "US/Canadian"));
        passportList.add(new GeneralResposeData(1, "Indian/Pakistani"));
        passportList.add(new GeneralResposeData(2, "Bangladeshi"));
        passportList.add(new GeneralResposeData(3, "Other Nationality"));
        passportSpinnerAdapter = new SpinnerAdapter(getActivity());
        passportSpinnerAdapter.setData(passportList, "Passport");
        fragmentHajjAndUmrahBookingSpPassport.setAdapter(passportSpinnerAdapter);
        fragmentHajjAndUmrahBookingSpPassport.setSelection(passportSelectedId1);
        listener2 = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                passportSelectedId1 = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
        fragmentHajjAndUmrahBookingSpPassport.setOnItemSelectedListener(listener2);
        travelAloneList.add(new GeneralResposeData(0, "yes"));
        travelAloneList.add(new GeneralResposeData(1, "no"));
        travelAloneSpinnerAdapter = new SpinnerAdapter(getActivity());
        travelAloneSpinnerAdapter.setData(travelAloneList, "Traveling Alone");
        fragmentHajjAndUmrahBookingSpTravelingAlone.setAdapter(travelAloneSpinnerAdapter);
        fragmentHajjAndUmrahBookingSpTravelingAlone.setSelection(travelAloneSelectedId1);
        listener3 = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                travelAloneSelectedId1 = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
        fragmentHajjAndUmrahBookingSpTravelingAlone.setOnItemSelectedListener(listener3);

        paymentTypeList.add(new GeneralResposeData(0, "Cashe"));
        paymentTypeList.add(new GeneralResposeData(1, "Visa"));
        paymentTypeSpinnerAdapter = new SpinnerAdapter(getActivity());
        paymentTypeSpinnerAdapter.setData(paymentTypeList, "Payment Type");
        fragmentHajjAndUmrahBookingSpVisa.setAdapter(paymentTypeSpinnerAdapter);
        fragmentHajjAndUmrahBookingSpVisa.setSelection(travelAloneSelectedId1);
        listener4 = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                paymentTypeSelectedId1 = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
        fragmentHajjAndUmrahBookingSpVisa.setOnItemSelectedListener(listener4);
    }

    private void showBookingDialog(){
        try {
//                final View view = activity.getLayoutInflater().inflate(R.layout.dialog_restaurant_add_category, null);
//            alertDialog = new AlertDialog.Builder(HomeFragment.this).create();
            AlertDialog alertDialog;
            alertDialog = new AlertDialog.Builder(getActivity()).create();
//            alertDialog.setTitle("Delete");
            alertDialog.setMessage("Thank you for Package booking , Please Pay Now to complete your booking");
            alertDialog.setCancelable(false);

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Pay Now", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        Intent i = new Intent("android.intent.action.MAIN");
                        i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
                        i.addCategory("android.intent.category.LAUNCHER");
                        i.setData(Uri.parse(url));
                        getActivity().startActivity(i);
                    }
                    catch(ActivityNotFoundException e) {
                        // Chrome is not installed
                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        getActivity().startActivity(i);
                        dialog.cancel();
                    }
                    navController.navigate(R.id.action_hajjAndUmrahBookingFragment_to_successfulPaymentFragment);
                    alertDialog.dismiss() ;

                }
            });



//                alertDialog.setView(view);
//            alertDialog.setOnShowListener( new DialogInterface.OnShowListener() {
//                @SuppressLint("ResourceAsColor")
//                @Override
//                public void onShow(DialogInterface arg0) {
//                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.red);
////                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(R.color.pink);
//
//                }
//            });

            alertDialog.show();

        } catch (Exception e) {

        }
    }

@Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment, new ChangeDetailsFragment());
        Bundle bundle = new Bundle();
        bundle.putSerializable("Object", getTopUmarAndTophajjPackage);
        bundle.putString("DiscoverOrHajjOrUmrah", isDiscoverOrHajjOrUmarah);
        navController.navigate(R.id.action_hajjAndUmrahBookingFragment_to_luxuryUmrahPackageFragment, bundle);
    }

    @OnClick({R.id.fragment_hajj_and_umrah_booking_back_btn, R.id.fragment_hajj_and_umrah_booking_get_passport_image_btn, R.id.fragment_hajj_and_umrah_booking_get_personal_image_btn, R.id.fragment_hajj_and_umrah_booking_subbmet_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_hajj_and_umrah_booking_back_btn:
                  onBack();
                break;
            case R.id.fragment_hajj_and_umrah_booking_get_passport_image_btn:
                openGalleryِAlpom(getActivity(), alpom, new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        mPassportPath = result.get(0).getPath();
                        if (mPassportPath != null) {
                            onLoadImageFromUrl(fragmentHajjAndUmrahBookingGetPassportImageImg, mPassportPath, getContext());
                        }
                    }
                }, 1);
                break;
            case R.id.fragment_hajj_and_umrah_booking_get_personal_image_btn:
                openGalleryِAlpom(getActivity(), alpom, new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        mPersonalPath = result.get(0).getPath();
                        if (mPersonalPath != null) {
                            onLoadImageFromUrl(fragmentHajjAndUmrahBookingGetPersonalImageImg, mPersonalPath, getContext());
                        }
                    }
                }, 1);
                break;
            case R.id.fragment_hajj_and_umrah_booking_subbmet_btn:
//                showToast(getActivity(),String.valueOf(genderSpinnerAdapter.getItem(genderSelectedId1)));
                onValidation();
                break;
        }
    }

    private void onValidation() {

        List<EditText> editTexts = new ArrayList<>();
        List<TextInputLayout> textInputLayouts = new ArrayList<>();
        List<Spinner> spinners = new ArrayList<>();

        textInputLayouts.add(fragmentHajjAndUmrahBookingTilFirstName);
        textInputLayouts.add(fragmentHajjAndUmrahBookingTilLastName);
        textInputLayouts.add(fragmentHajjAndUmrahBookingTilAddress);
        textInputLayouts.add(fragmentHajjAndUmrahBookingTilPhone);
        textInputLayouts.add(fragmentHajjAndUmrahBookingTilEmail);
        textInputLayouts.add(fragmentHajjAndUmrahBookingTravelingFrom);
        textInputLayouts.add(fragmentHajjAndUmrahBookingTilZipCode);
        textInputLayouts.add(fragmentHajjAndUmrahBookingTilWriteComment);
        spinners.add(fragmentHajjAndUmrahBookingSpGender);
        spinners.add(fragmentHajjAndUmrahBookingSpPassport);
        spinners.add(fragmentHajjAndUmrahBookingSpTravelingAlone);
        spinners.add(fragmentHajjAndUmrahBookingSpVisa);


        cleanError(textInputLayouts);

        if (!validationAllEmpty(editTexts, textInputLayouts, spinners, getString(R.string.empty))) {

            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.empty));
            return;
        }
        if (!validationLength(fragmentHajjAndUmrahBookingTilFirstName, getString(R.string.invalid_first_name), 3)) {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_first_name));

            return;
        }

        if (!validationLength(fragmentHajjAndUmrahBookingTilLastName, getString(R.string.invalid_last_name), 3)) {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_last_name));
            return;
        }

        if (!validationLength(fragmentHajjAndUmrahBookingTilAddress, getString(R.string.invalid_address_required_field), 1)) {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_address_required_field));

            return;
        }

        if (!validationPhone(getActivity(), fragmentHajjAndUmrahBookingTilPhone)) {
            ToastCreator.onCreateErrorToast(getActivity(), "Enter Phone");
            return;
        }

        if (!validationEmail(getActivity(), fragmentHajjAndUmrahBookingTilEmail)) {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_email_required_field));
            return;
        }

        if (!validationLength(fragmentHajjAndUmrahBookingTravelingFrom, getString(R.string.invalid_traveling_from_required_field), 1)) {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_traveling_from_required_field));
            return;
        }

        if (!validationLength(fragmentHajjAndUmrahBookingTilZipCode, getString(R.string.invalid_zip_required_field), 1)) {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_zip_required_field));
            return;
        }

        if (!validationLength(fragmentHajjAndUmrahBookingTilWriteComment, getString(R.string.invalid_comment_required_field), 1)) {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_comment_required_field));
            return;
        }

        if (fragmentHajjAndUmrahBookingSpGender.getSelectedItemPosition() == 0) {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.select_gender));
            return;
        }

        if (fragmentHajjAndUmrahBookingSpPassport.getSelectedItemPosition() == 0) {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.select_passport));
            return;
        }

        if (fragmentHajjAndUmrahBookingSpVisa.getSelectedItemPosition() == 0) {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.select_pay_type));
            return;
        }

        if (fragmentHajjAndUmrahBookingSpTravelingAlone.getSelectedItemPosition() == 0) {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.select_travel));
            return;
        }


        if (mPassportPath != null && mPersonalPath != null) {
            onCall();


        } else {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.select_image));
//                showToast(getActivity(), "Please Select Image First");
            return;
        }


    }

    private void onCall() {
        RequestBody userId = convertToRequestBody(String.valueOf(userData.getId()));
        RequestBody PrisingId = convertToRequestBody(String.valueOf(pricing.getId()));
        int packagePricing = pricing.getPrice();
        RequestBody Price = convertToRequestBody(String.valueOf(packagePricing));
        RequestBody firstName = convertToRequestBody(fragmentHajjAndUmrahBookingTilFirstName.getEditText().getText().toString());
        RequestBody lastName = convertToRequestBody(fragmentHajjAndUmrahBookingTilLastName.getEditText().getText().toString());
        RequestBody email = convertToRequestBody(fragmentHajjAndUmrahBookingTilEmail.getEditText().getText().toString());
        RequestBody contactNum = convertToRequestBody(fragmentHajjAndUmrahBookingTilPhone.getEditText().getText().toString());
        RequestBody address = convertToRequestBody(fragmentHajjAndUmrahBookingTilAddress.getEditText().getText().toString());
        RequestBody travelingFrom = convertToRequestBody(fragmentHajjAndUmrahBookingTravelingFrom.getEditText().getText().toString());
        RequestBody commetMessage = convertToRequestBody(fragmentHajjAndUmrahBookingTilWriteComment.getEditText().getText().toString());
        RequestBody zipCode = convertToRequestBody(fragmentHajjAndUmrahBookingTilZipCode.getEditText().getText().toString());
//        genderSelectedId1 = genderSpinnerAdapter.selectedId;
        RequestBody genderIdValue = convertToRequestBody(String.valueOf(genderSpinnerAdapter.getItem(genderSelectedId1)));
        RequestBody passportIdValue = convertToRequestBody(String.valueOf(passportSpinnerAdapter.getItem(passportSelectedId1)));
        RequestBody travelAloneIdValue = convertToRequestBody(String.valueOf(travelAloneSpinnerAdapter.getItem(travelAloneSelectedId1)));
        RequestBody paymentType = convertToRequestBody(String.valueOf(paymentTypeSpinnerAdapter.getItem(paymentTypeSelectedId1)));
        RequestBody paid = convertToRequestBody(String.valueOf(packagePricing));
        RequestBody remaining = convertToRequestBody(String.valueOf(0));
//        showToast(getActivity(), String.valueOf(mPassportPath));
//        List<MultipartBody.Part> imageFiles = new ArrayList<MultipartBody.Part>();
//
//        for(int i=0; i < imageFiles.size(); i++){
//            imageFiles.add(convertFileToMultipart2("file_"+(i+1), mPassportPath));
//            //Where selectedFiles is selected file URI list
//        }
        MultipartBody.Part clientPassportPhoto = convertFileToMultipart(mPassportPath, "passport_image", getActivity());
        MultipartBody.Part clientPersonalPhoto = convertFileToMultipart(mPersonalPath, "personal_image", getActivity());

        Call<UserLoginGeneralResponce> clientBookingCall;

        clientBookingCall = getApiClient().umrahAndHajjBooking(userId, PrisingId, Price, address, contactNum, email, travelingFrom, commetMessage, travelAloneIdValue, paymentType, paid, remaining, firstName, lastName, zipCode, genderIdValue, passportIdValue, clientPassportPhoto, clientPersonalPhoto);
        viewModelUser.setAndMakeResetAndNewPasswordResponseAndSignUpAndBooking(getActivity(), clientBookingCall, "Succes Package Booking", false);


    }
}