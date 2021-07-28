package com.example.barakatravelapp.view.fragment.userCycle;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.data.model.userLoginResponce.UserLoginGeneralResponce;
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
import static com.example.barakatravelapp.utils.HelperMethod.replaceFragmentWithAnimation;
import static com.example.barakatravelapp.utils.validation.Validation.cleanError;
import static com.example.barakatravelapp.utils.validation.Validation.validationEmail;
import static com.example.barakatravelapp.utils.validation.Validation.validationTextInputLayoutListEmpty;


public class SetNewPasswordFragment extends BaSeFragment {

    @BindView(R.id.fragment_set_new_password_til_email)
    TextInputLayout fragmentSetNewPasswordTilEmail;
    List<TextInputLayout> textInputLayoutList = new ArrayList<>();
    private String email;
    private ViewModelUser viewModelUser;
    public SetNewPasswordFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_set_new_password, container, false);

        ButterKnife.bind(this, root);
        initListener();
        textInputLayoutList.add(fragmentSetNewPasswordTilEmail);
        return root;
    }

    @SuppressLint("FragmentLiveDataObserve")
    private void initListener() {
        viewModelUser = ViewModelProviders.of(this).get(ViewModelUser.class);
        viewModelUser.makeResetAndNewPasswordResponseAndSignUpAndBooking().observe(this, new Observer<UserLoginGeneralResponce>() {
            @Override
            public void onChanged(@Nullable UserLoginGeneralResponce response) {
                if(response!=null) {
                    if (response.getStatus().equals("success")) {
                        replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.user_activity_fram, new LoginFragment(),"b");
//                        showToast(getActivity(), "success");

                    }
                }
            }
        });
    }
    @Override
    public void onBack() {
        replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.user_activity_fram, new LoginFragment(),"b");
    }

    @OnClick(R.id.fragment_set_new_password_btn_reset_pass)
    public void onViewClicked() {
//        disappearKeypad(getActivity(), getView());
        onValidData();
    }

    private void onValidData() {

        cleanError(textInputLayoutList);

        if (!validationTextInputLayoutListEmpty(textInputLayoutList, getString(R.string.empty))) {
            return;
        }

        if (!validationEmail(getActivity(), fragmentSetNewPasswordTilEmail)) {

            return;
        }


        onCall();
    }

    private void onCall() {
        email = fragmentSetNewPasswordTilEmail.getEditText().getText().toString();


        Call<UserLoginGeneralResponce> resetPasswordCall = null;

            resetPasswordCall = getApiClient().userResetPassword(email);

        viewModelUser.setAndMakeResetAndNewPasswordResponseAndSignUpAndBooking(getActivity(),resetPasswordCall, "Succes Set New Password", true);


    }
}