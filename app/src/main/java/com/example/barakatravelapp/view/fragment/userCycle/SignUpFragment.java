package com.example.barakatravelapp.view.fragment.userCycle;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.data.model.userLoginResponce.UserLoginGeneralResponce;
import com.example.barakatravelapp.utils.ToastCreator;
import com.example.barakatravelapp.view.fragment.BaSeFragment;
import com.example.barakatravelapp.view.viewModel.ViewModelUser;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

import static com.example.barakatravelapp.data.api.ApiClient.getApiClient;
import static com.example.barakatravelapp.utils.HelperMethod.disappearKeypad;
import static com.example.barakatravelapp.utils.HelperMethod.replaceFragmentWithAnimation;
import static com.example.barakatravelapp.utils.validation.Validation.cleanError;
import static com.example.barakatravelapp.utils.validation.Validation.validationAllEmpty;
import static com.example.barakatravelapp.utils.validation.Validation.validationConfirmPassword;
import static com.example.barakatravelapp.utils.validation.Validation.validationEmail;
import static com.example.barakatravelapp.utils.validation.Validation.validationLength;
import static com.example.barakatravelapp.utils.validation.Validation.validationPassword;
import static com.example.barakatravelapp.utils.validation.Validation.validationPhone;


public class SignUpFragment extends BaSeFragment {

    @BindView(R.id.fragment_sign_up_til_first_name)
    TextInputLayout fragmentSignUpTilFirstName;
    @BindView(R.id.fragment_sign_up_til_last_name)
    TextInputLayout fragmentSignUpTilLastName;
    @BindView(R.id.fragment_sign_up_til_phone)
    TextInputLayout fragmentSignUpTilPhone;
    @BindView(R.id.fragment_sign_up_til_email)
    TextInputLayout fragmentSignUpTilEmail;
    @BindView(R.id.fragment_sign_up_til_password)
    TextInputLayout fragmentSignUpTilPassword;
    @BindView(R.id.fragment_sign_up_til_confirm_password)
    TextInputLayout fragmentSignUpTilConfirmPassword;
    @BindView(R.id.fragment_sign_up_cb_remember)
    CheckBox fragmentSignUpCbRemember;
    private ViewModelUser viewModelUser;
//    private AdapterView.OnItemSelectedListener listener;
    public SignUpFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_sign_up, container, false);

        ButterKnife.bind(this, root);
        disappearKeypad(getActivity(), getView());
        initListener();
        fragmentSignUpTilPassword.getEditText().setTypeface(Typeface.DEFAULT);
        fragmentSignUpTilPassword.getEditText().setTransformationMethod(new PasswordTransformationMethod());
        fragmentSignUpTilConfirmPassword.getEditText().setTypeface(Typeface.DEFAULT);
        fragmentSignUpTilConfirmPassword.getEditText().setTransformationMethod(new PasswordTransformationMethod());


        return root;
    }

    @SuppressLint("FragmentLiveDataObserve")
    private void initListener() {
        viewModelUser = ViewModelProviders.of(this).get(ViewModelUser.class);
        viewModelUser.makeResetAndNewPasswordResponseAndSignUpAndBooking().observe(this, new Observer<UserLoginGeneralResponce>() {
            @Override
            public void onChanged(@Nullable UserLoginGeneralResponce response) {
                if (response != null) {
                    if (response.getStatus().equals("success")||response.getMessage().equals("The mobile has already been taken.")) {
//                        showToast(getActivity(), "success");
                        replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.user_activity_fram, new LoginFragment(),"b");

                    }

                }
            }
        });
    }

    @Override
    public void onBack() {
        replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.user_activity_fram, new LoginFragment(),"b");
    }

    @OnClick({R.id.fragment_sign_up_tv, R.id.fragment_sign_up_cb_remember, R.id.fragment_sign_up_btn_begin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_sign_up_tv:
                replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.user_activity_fram, new LoginFragment(),"b");
                break;
            case R.id.fragment_sign_up_cb_remember:
                break;
            case R.id.fragment_sign_up_btn_begin:
                onValidation();
                break;
        }
    }


private void onValidation() {

    List<EditText> editTexts = new ArrayList<>();
    List<TextInputLayout> textInputLayouts = new ArrayList<>();
    List<Spinner> spinners = new ArrayList<>();

    textInputLayouts.add(fragmentSignUpTilFirstName);
    textInputLayouts.add(fragmentSignUpTilLastName);
    textInputLayouts.add(fragmentSignUpTilEmail);
    textInputLayouts.add(fragmentSignUpTilPassword);
    textInputLayouts.add(fragmentSignUpTilConfirmPassword);


        textInputLayouts.add(fragmentSignUpTilPhone);




    cleanError(textInputLayouts);

    if (!validationAllEmpty(editTexts, textInputLayouts, spinners, getString(R.string.empty))) {

        ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.empty));
        return;
    }

    if (!validationLength(fragmentSignUpTilFirstName, getString(R.string.invalid_first_name), 3)) {
        ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_first_name));

        return;
    }

    if (!validationLength(fragmentSignUpTilLastName, getString(R.string.invalid_last_name), 3)) {
        ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_last_name));
        return;
    }

    if (!validationPhone(getActivity(), fragmentSignUpTilPhone)) {
        ToastCreator.onCreateErrorToast(getActivity(), "Enter Phone");
        return;
    }

    if (!validationEmail(getActivity(), fragmentSignUpTilEmail)) {
        ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_email_required_field));
        return;
    }

    if (!validationPassword(fragmentSignUpTilPassword, 8, getString(R.string.invalid_password))) {
        ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_password));
        return;
    }

    if (!validationConfirmPassword(getActivity(), fragmentSignUpTilPassword, fragmentSignUpTilConfirmPassword)) {
        ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_password));
        return;
    }



    onCall();
}

    private void onCall() {
        String firstName = fragmentSignUpTilFirstName.getEditText().getText().toString();
        String lastName = fragmentSignUpTilLastName.getEditText().getText().toString();
        String email = fragmentSignUpTilEmail.getEditText().getText().toString();
        String phone = fragmentSignUpTilPhone.getEditText().getText().toString();
        String password = fragmentSignUpTilPassword.getEditText().getText().toString();
//        String passwordSave = fragmentSignUpTilConfirmPassword.getEditText().getText().toString();
        Call<UserLoginGeneralResponce> clientCall;


            clientCall = getApiClient().onSignUp(firstName,lastName,phone , email, password);
            viewModelUser.setAndMakeResetAndNewPasswordResponseAndSignUpAndBooking(getActivity(), clientCall, "Succes Register", true);





    }
}