package com.example.barakatravelapp.view.fragment.HomeCycle2.accounts;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.barakatravelapp.R;
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
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

import static com.example.barakatravelapp.data.api.ApiClient.getApiClient;
import static com.example.barakatravelapp.data.local.SharedPreferencesManger.LoadData;
import static com.example.barakatravelapp.data.local.SharedPreferencesManger.LoadUserData;
import static com.example.barakatravelapp.data.local.SharedPreferencesManger.USER_PASSWORD;
import static com.example.barakatravelapp.utils.HelperMethod.convertFileToMultipart;
import static com.example.barakatravelapp.utils.HelperMethod.convertToRequestBody;
import static com.example.barakatravelapp.utils.HelperMethod.disappearKeypad;
import static com.example.barakatravelapp.utils.HelperMethod.onLoadCirImageFromUrl;
import static com.example.barakatravelapp.utils.HelperMethod.openGalleryِAlpom;
import static com.example.barakatravelapp.utils.HelperMethod.showToast;
import static com.example.barakatravelapp.utils.validation.Validation.cleanError;
import static com.example.barakatravelapp.utils.validation.Validation.validationAllEmpty;
import static com.example.barakatravelapp.utils.validation.Validation.validationConfirmPassword;
import static com.example.barakatravelapp.utils.validation.Validation.validationEmail;
import static com.example.barakatravelapp.utils.validation.Validation.validationLength;
import static com.example.barakatravelapp.utils.validation.Validation.validationPassword;
import static com.example.barakatravelapp.utils.validation.Validation.validationPhone;


public class ChangeDetailsFragment extends BaSeFragment {

    @BindView(R.id.fragment_change_details_my_profile_photo_circularImageView)
    CircleImageView fragmentChangeDetailsMyProfilePhotoCircularImageView;
    @BindView(R.id.fragment_change_details_til_first_name)
    TextInputLayout fragmentChangeDetailsTilFirstName;
    @BindView(R.id.fragment_change_details_til_last_name)
    TextInputLayout fragmentChangeDetailsTilLastName;
    @BindView(R.id.fragment_change_details_til_email)
    TextInputLayout fragmentChangeDetailsTilEmail;
    @BindView(R.id.fragment_change_details_til_phone)
    TextInputLayout fragmentChangeDetailsTilPhone;
    @BindView(R.id.fragment_change_details_til_password)
    TextInputLayout fragmentChangeDetailsTilPassword;
    @BindView(R.id.fragment_change_details_til_confirm_password)
    TextInputLayout fragmentChangeDetailsTilConfirmPassword;
    private NavController navController;
    private UserData userData;
    private ViewModelUser viewModelUser;
    private static ArrayList<AlbumFile> alpom = new ArrayList<>();
    private static String mPath;
    private static final String CLIENTPROFILEIMAGE = "image";
    public ChangeDetailsFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_change_details, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        userData = LoadUserData(getActivity());
        disappearKeypad(getActivity(), getView());
        initListener();
        fragmentChangeDetailsTilPassword.getEditText().setTypeface(Typeface.DEFAULT);
        fragmentChangeDetailsTilPassword.getEditText().setTransformationMethod(new PasswordTransformationMethod());
        fragmentChangeDetailsTilConfirmPassword.getEditText().setTypeface(Typeface.DEFAULT);
        fragmentChangeDetailsTilConfirmPassword.getEditText().setTransformationMethod(new PasswordTransformationMethod());

        setUserData();
        return root;
    }

    @SuppressLint("FragmentLiveDataObserve")
    private void initListener() {
        viewModelUser = ViewModelProviders.of(this).get(ViewModelUser.class);
        viewModelUser.setGeneralLoginAndUpdateProfile().observe(this, new Observer<UserLoginGeneralResponce>() {
            @Override
            public void onChanged(@Nullable UserLoginGeneralResponce response) {
                if (response != null) {
                    if (response.getStatus().equals("success")) {
//                        replaceFragment(getActivity().getSupportFragmentManager(), R.id.user_activity_fram, new LoginFragment());
//                        showToast(getActivity(), "success");

                    }
                }
            }
        });
    }

    private void setUserData() {

        fragmentChangeDetailsTilFirstName.getEditText().setText(userData.getFirstName());
        fragmentChangeDetailsTilLastName.getEditText().setText(userData.getLastName());
        fragmentChangeDetailsTilEmail.getEditText().setText(userData.getEmail());
        fragmentChangeDetailsTilPhone.getEditText().setText(userData.getMobile());
        String password=LoadData(getActivity(), USER_PASSWORD);
        fragmentChangeDetailsTilConfirmPassword.getEditText().setText(password);
        fragmentChangeDetailsTilPassword.getEditText().setText(password);
        if(userData.getImage()!=null){
            String personalImage = "https://www.barakatravel.net/"+userData.getImage().trim();
            mPath=personalImage;
            onLoadCirImageFromUrl(fragmentChangeDetailsMyProfilePhotoCircularImageView,personalImage.trim(), getContext());}
    }
        @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment, new AccountFragment());
        navController.navigate(R.id.action_changeDetailsFragment_to_navigation_account);
        homeCycleActivity.setNavigation("v");
//        homeCycleActivity.bottomNavView.getMenu().getItem(4).setChecked(true);
    }

    @OnClick({R.id.fragment_change_details_my_profile_photo_circularImageView, R.id.fragment_change_details_my_profile_photo_btn, R.id.fragment_change_details_save_changes_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_change_details_my_profile_photo_circularImageView:
//                new MakeChangesDialog().showDialog(getActivity());
//                replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment, new ChooseStaticEVisaFragment());
//                navController.navigate(R.id.action_changeDetailsFragment_to_paymentsFragment);
                openGalleryِAlpom(getActivity(), alpom, new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        mPath = result.get(0).getPath();
                        if(mPath!=null){
                            onLoadCirImageFromUrl(fragmentChangeDetailsMyProfilePhotoCircularImageView,mPath, getContext());}
                    }
                }, 1);
                break;
            case R.id.fragment_change_details_my_profile_photo_btn:
                openGalleryِAlpom(getActivity(), alpom, new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        mPath = result.get(0).getPath();
                        if(mPath!=null){
                            onLoadCirImageFromUrl(fragmentChangeDetailsMyProfilePhotoCircularImageView,mPath, getContext());}
                    }
                }, 1);
                break;
            case R.id.fragment_change_details_save_changes_btn:
                onValidation();

//                replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment, new ChangeDetailsMoreFragment());
//                navController.navigate(R.id.action_changeDetailsFragment_to_changeDetailsMoreFragment);

                break;
        }
    }


    private void onValidation() {

        List<EditText> editTexts = new ArrayList<>();
        List<TextInputLayout> textInputLayouts = new ArrayList<>();
        List<Spinner> spiner = new ArrayList<>();

        textInputLayouts.add(fragmentChangeDetailsTilFirstName);
        textInputLayouts.add(fragmentChangeDetailsTilLastName);
        textInputLayouts.add(fragmentChangeDetailsTilEmail);
        textInputLayouts.add(fragmentChangeDetailsTilPassword);
        textInputLayouts.add(fragmentChangeDetailsTilConfirmPassword);
        textInputLayouts.add(fragmentChangeDetailsTilPhone);




        cleanError(textInputLayouts);


        if (!validationAllEmpty(editTexts, textInputLayouts, spiner, getString(R.string.empty))) {

            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.empty));
            return;
        }

        if (!validationLength(fragmentChangeDetailsTilFirstName, getString(R.string.invalid_first_name), 3)) {
            return;
        }

        if (!validationLength(fragmentChangeDetailsTilLastName, getString(R.string.invalid_last_name), 3)) {
            return;
        }

        if (!validationEmail(getActivity(), fragmentChangeDetailsTilEmail)) {

            return;
        }

        if (!validationPhone(getActivity(), fragmentChangeDetailsTilPhone)) {
            ToastCreator.onCreateErrorToast(getActivity(), "Enter Phone");
            return;
        }

        if (!validationPassword(fragmentChangeDetailsTilPassword, 8, getString(R.string.invalid_password))) {
            return;
        }

        if (!validationConfirmPassword(getActivity(), fragmentChangeDetailsTilPassword, fragmentChangeDetailsTilConfirmPassword)) {
            return;
        }

        if (mPath !=null) {
            onCall();
        }else { ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.select_image));
            return;

        }
    }

    private void onCall() {
//        String userId = String.valueOf(userData.getId());
//        String email = fragmentChangeDetailsTilEmail.getEditText().getText().toString();
//        String firstName = fragmentChangeDetailsTilFirstName.getEditText().getText().toString();
//        String lastName = fragmentChangeDetailsTilLastName.getEditText().getText().toString();
//        String phone = fragmentChangeDetailsTilPhone.getEditText().getText().toString();
//        String password = fragmentChangeDetailsTilPassword.getEditText().getText().toString();
//        String clientProfilePhoto = mPath;
        RequestBody userId = convertToRequestBody(String.valueOf(userData.getId()));
        RequestBody email = convertToRequestBody(fragmentChangeDetailsTilEmail.getEditText().getText().toString());
        RequestBody firstName = convertToRequestBody(fragmentChangeDetailsTilFirstName.getEditText().getText().toString());
        RequestBody lastName = convertToRequestBody(fragmentChangeDetailsTilLastName.getEditText().getText().toString());
        RequestBody phone = convertToRequestBody(fragmentChangeDetailsTilPhone.getEditText().getText().toString());
        RequestBody password = convertToRequestBody(fragmentChangeDetailsTilPassword.getEditText().getText().toString());
        MultipartBody.Part clientProfilePhoto = convertFileToMultipart(mPath, CLIENTPROFILEIMAGE,getActivity());
//        showToast(getActivity(), String.valueOf(clientProfilePhoto));

        boolean remember = true;
        String passwordSave = fragmentChangeDetailsTilPassword.getEditText().getText().toString();
        Call<UserLoginGeneralResponce> clientCall;


        clientCall = getApiClient().userChangeProfileDetails(userId,email,firstName,lastName , phone, password,clientProfilePhoto);
        viewModelUser.setGeneralLoginAndUpdateProfile(getActivity(),clientCall, passwordSave, remember, false);





    }

}