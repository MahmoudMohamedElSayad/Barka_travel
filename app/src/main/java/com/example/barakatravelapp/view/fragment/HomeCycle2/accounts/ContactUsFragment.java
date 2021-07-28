package com.example.barakatravelapp.view.fragment.HomeCycle2.accounts;

import android.annotation.SuppressLint;
import android.os.Bundle;
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
import com.example.barakatravelapp.view.fragment.userCycle.LoginFragment;
import com.example.barakatravelapp.view.viewModel.ViewModelUser;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

import static com.example.barakatravelapp.data.api.ApiClient.getApiClient;
import static com.example.barakatravelapp.data.local.SharedPreferencesManger.LoadUserData;
import static com.example.barakatravelapp.utils.HelperMethod.disappearKeypad;
import static com.example.barakatravelapp.utils.HelperMethod.replaceFragmentWithAnimation;
import static com.example.barakatravelapp.utils.validation.Validation.cleanError;
import static com.example.barakatravelapp.utils.validation.Validation.validationAllEmpty;
import static com.example.barakatravelapp.utils.validation.Validation.validationConfirmPassword;
import static com.example.barakatravelapp.utils.validation.Validation.validationEmail;
import static com.example.barakatravelapp.utils.validation.Validation.validationLength;
import static com.example.barakatravelapp.utils.validation.Validation.validationPassword;
import static com.example.barakatravelapp.utils.validation.Validation.validationPhone;


public class ContactUsFragment extends BaSeFragment {

    @BindView(R.id.fragment_contact_us_name)
    TextInputLayout fragmentContactUsName;
    @BindView(R.id.fragment_contact_us_til_phone)
    TextInputLayout fragmentContactUsTilPhone;
    @BindView(R.id.fragment_contact_us_til_email)
    TextInputLayout fragmentContactUsTilEmail;
    @BindView(R.id.fragment_contact_us_til_message)
    TextInputLayout fragmentContactUsTilMessage;
    private NavController navController;
    private ViewModelUser viewModelUser;
    private UserData userData;

    public ContactUsFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_contact_us, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        disappearKeypad(getActivity(), getView());
        initListener();
        userData = LoadUserData(getActivity());
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

                    }

                }
            }
        });
    }

    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new ConfirmBookingFragment());
        navController.navigate(R.id.action_contactUsFragment_to_changeDetailsMoreFragment);

    }

    @OnClick({R.id.ffragment_contact_us_back_img, R.id.fragment_contact_us_btn_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ffragment_contact_us_back_img:
                onBack();
                break;
            case R.id.fragment_contact_us_btn_send:
                onValidation();
                break;
        }
    }


    private void onValidation() {

        List<EditText> editTexts = new ArrayList<>();
        List<TextInputLayout> textInputLayouts = new ArrayList<>();
        List<Spinner> spinners = new ArrayList<>();

        textInputLayouts.add(fragmentContactUsName);
        textInputLayouts.add(fragmentContactUsTilPhone);
        textInputLayouts.add(fragmentContactUsTilEmail);
        textInputLayouts.add(fragmentContactUsTilMessage);

        cleanError(textInputLayouts);

        if (!validationAllEmpty(editTexts, textInputLayouts, spinners, getString(R.string.empty))) {

            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.empty));
            return;
        }

        if (!validationLength(fragmentContactUsName, getString(R.string.invalid_first_name), 3)) {
            return;
        }

        if (!validationPhone(getActivity(), fragmentContactUsTilPhone)) {
            ToastCreator.onCreateErrorToast(getActivity(), "Enter Phone");
            return;
        }

        if (!validationEmail(getActivity(), fragmentContactUsTilEmail)) {

            return;
        }

        if (!validationLength(fragmentContactUsTilMessage, getString(R.string.invalid_message), 3)) {
            return;
        }


        onCall();
    }

    private void onCall() {
        String name = fragmentContactUsName.getEditText().getText().toString();
        int userId = userData.getId();
        String email = fragmentContactUsTilEmail.getEditText().getText().toString();
        String phone = fragmentContactUsTilPhone.getEditText().getText().toString();
        String message = fragmentContactUsTilMessage.getEditText().getText().toString();
//        String passwordSave = fragmentSignUpTilConfirmPassword.getEditText().getText().toString();
        Call<UserLoginGeneralResponce> clientCall;


        clientCall = getApiClient().contactUs(userId,name,phone ,email, message);
        viewModelUser.setAndMakeResetAndNewPasswordResponseAndSignUpAndBooking(getActivity(), clientCall,"Succes send",true);





    }
}