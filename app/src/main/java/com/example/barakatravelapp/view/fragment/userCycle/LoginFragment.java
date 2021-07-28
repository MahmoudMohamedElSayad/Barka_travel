package com.example.barakatravelapp.view.fragment.userCycle;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
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
import static com.example.barakatravelapp.utils.HelperMethod.replaceFragment;
import static com.example.barakatravelapp.utils.HelperMethod.replaceFragmentWithAnimation;
import static com.example.barakatravelapp.utils.validation.Validation.cleanError;
import static com.example.barakatravelapp.utils.validation.Validation.validationEmail;
import static com.example.barakatravelapp.utils.validation.Validation.validationPassword;
import static com.example.barakatravelapp.utils.validation.Validation.validationTextInputLayoutListEmpty;


public class LoginFragment extends BaSeFragment {

    @BindView(R.id.fragment_login_for_til_email)
    TextInputLayout fragmentLoginForTilEmail;
    @BindView(R.id.fragment_login_for_til_password)
    TextInputLayout fragmentLoginForTilPassword;
    List<TextInputLayout> textInputLayoutList = new ArrayList<>();
    private String email;
    private String password;
    private ViewModelUser viewModelUser;

    public LoginFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.bind(this, root);
//        addKeyboardToggleListener();
        textInputLayoutList.add(fragmentLoginForTilEmail);
        textInputLayoutList.add(fragmentLoginForTilPassword);
//        clientData = LoadUserData(getActivity());
        fragmentLoginForTilPassword.getEditText().setTypeface(Typeface.DEFAULT);
        fragmentLoginForTilPassword.getEditText().setTransformationMethod(new PasswordTransformationMethod());
        initListener();

        return root;
    }

    private void initListener() {
        viewModelUser = ViewModelProviders.of(this).get(ViewModelUser.class);
        viewModelUser.setGeneralLoginAndUpdateProfile().observe(getViewLifecycleOwner(), new Observer<UserLoginGeneralResponce>() {
            @Override
            public void onChanged(@Nullable UserLoginGeneralResponce response) {
                if(response!=null){
                    if (response.getStatus().equals("success")) {
//                        showToast(getActivity(),"success");

                    }  }
            }
        });
    }

    @Override
    public void onBack() {
        getActivity().finish();
    }


    @OnClick({R.id.fragment_login_for_sign_up_tv, R.id.fragment_login_for_btn_login,R.id.fragment_login_forget_password_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_login_for_sign_up_tv:
                replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.user_activity_fram, new SignUpFragment(),"b");
                break;
            case R.id.fragment_login_for_btn_login:
                onValidData();

//                startActivity(new Intent(getActivity(), HomeCycleActivity.class));
//                getActivity().finish();
                break;
            case R.id.fragment_login_forget_password_tv:
                replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.user_activity_fram, new SetNewPasswordFragment(),"b");
                break;
        }
    }

    private void onValidData() {

        cleanError(textInputLayoutList);

        if (!validationTextInputLayoutListEmpty(textInputLayoutList, getString(R.string.empty))) {
            return;
        }

        if (!validationEmail(getActivity(), fragmentLoginForTilEmail)) {

            return;
        }

        if (!validationPassword(fragmentLoginForTilPassword, 4, getString(R.string.invalid_password))) {
            return;
        }

        onCall();
    }

    private void onCall() {
        email = fragmentLoginForTilEmail.getEditText().getText().toString();
        password = fragmentLoginForTilPassword.getEditText().getText().toString();
//         apiToken=clientData.getApiToken();
//         token=new ClientFireBaseToken().getToken();
        boolean remember = true;

//        Call<ClientGeneralResponse> clientCall = getApiClient().clientLogin(email, password);


        Call<UserLoginGeneralResponce> loginCall = null;

            loginCall = getApiClient().userLogin(email, password);

        viewModelUser.setGeneralLoginAndUpdateProfile(getActivity(),loginCall, password, remember, true);


    }

}