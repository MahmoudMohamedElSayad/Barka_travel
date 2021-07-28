package com.example.barakatravelapp.view.fragment.HomeCycle2.accounts;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.adapter.EVisaPhotoGallaryHzRvAdapter;
import com.example.barakatravelapp.adapter.SpinnerAdapter;
import com.example.barakatravelapp.data.model.GeneralResposeData;
import com.example.barakatravelapp.data.model.bookEvisaResponce.BookEvisaResponce;
import com.example.barakatravelapp.data.model.userLoginResponce.UserData;
import com.example.barakatravelapp.utils.EVisaBookingDialog;
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
import static com.example.barakatravelapp.utils.HelperMethod.openGalleryِAlpom;
import static com.example.barakatravelapp.utils.HelperMethod.showToast;
import static com.example.barakatravelapp.utils.validation.Validation.cleanError;
import static com.example.barakatravelapp.utils.validation.Validation.validationAllEmpty;
import static com.example.barakatravelapp.utils.validation.Validation.validationEmail;
import static com.example.barakatravelapp.utils.validation.Validation.validationLength;
import static com.example.barakatravelapp.utils.validation.Validation.validationPhone;


public class GetEVisaFragment extends BaSeFragment {

    @BindView(R.id.fragment_get_e_visa_til_first_name)
    TextInputLayout fragmentGetEVisaTilFirstName;
    @BindView(R.id.fragment_get_e_visa_til_last_name)
    TextInputLayout fragmentGetEVisaTilLastName;
    @BindView(R.id.fragment_get_e_visa_til_phone)
    TextInputLayout fragmentGetEVisaTilPhone;
    @BindView(R.id.fragment_get_e_visa_til_email)
    TextInputLayout fragmentGetEVisaTilEmail;
    @BindView(R.id.fragment_get_e_visa_sp_passporn_num)
    Spinner fragmentGetEVisaSpPassportNum;
    @BindView(R.id.fragment_get_e_visa_get_passport_photos_btn)
    LinearLayout fragmentGetEVisaGetPassportPhotosBtn;
    @BindView(R.id.fragment_get_e_visa_get_personal_photos_btn)
    LinearLayout fragmentGetEVisaGetPersonalPhotosBtn;
    @BindView(R.id.home_discover_fragment_sub_home_rv_item_hz_sr_vw)
    HorizontalScrollView homeDiscoverFragmentSubHomeRvItemHzSrVw;
    @BindView(R.id.hfragment_get_e_visa_srv_item_hz_sr_vw)
    HorizontalScrollView hfragmentGetEVisaSrvItemHzSrVw;
    @BindView(R.id.fragment_get_e_visa_passport_photo_rv_item_hz_rv)
    RecyclerView fragmentGetEVisaPassportPhotoRvItemHzRv;
    @BindView(R.id.fragment_get_e_visa_personal_photo_rv_item_hz_rv)
    RecyclerView fragmentGetEVisaPersonalPhotoRvItemHzRv;
    @BindView(R.id.fragment_get_e_visa_hiden_part_ly)
    LinearLayout fragmentGetEVisaHidenPartLy;
    private NavController navController;
    private UserData userData;
    private String countryType;
    private SpinnerAdapter choosePassportNumAdapter;
    private List<GeneralResposeData> choosePassportNum = new ArrayList<GeneralResposeData>();
    private int choosePassportNumSelectedId1 = 0;
    private AdapterView.OnItemSelectedListener listener;
    public List<String> mPassportPath = new ArrayList<String>();
    public List<String> mPersonalPath = new ArrayList<String>();
    //    public static ArrayList<String> phoneList = new ArrayList<>();
    private static ArrayList<AlbumFile> alpom = new ArrayList<>();
    private ViewModelUser viewModelUser;
    private BookEvisaResponce bookEvisaResponce;
    private LinearLayoutManager linearLayoutHorizental;
    private EVisaPhotoGallaryHzRvAdapter eVisaPhotoGallaryHzRvAdapter, eVisaPhotoGallaryHzRvAdapter2;

    public GetEVisaFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (this.getArguments() != null) {

            countryType = this.getArguments().getString("CountryType");

//            showToast(getActivity(), pricing.getName());
        }
        View root = inflater.inflate(R.layout.fragment_get_e_visa, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        userData = LoadUserData(getActivity());
//        showToast(getActivity(), countryType);
        initListener();
        setData();
        return root;
    }

    @SuppressLint("FragmentLiveDataObserve")
    private void initListener() {
        viewModelUser = ViewModelProviders.of(this).get(ViewModelUser.class);
        viewModelUser.makeeVisaBooking().observe(this, new Observer<BookEvisaResponce>() {
            @Override
            public void onChanged(@Nullable BookEvisaResponce response) {
                if (response != null) {
                    if (response.getStatus().equals("success")) {
//                        showToast(getActivity(), "success");
                        bookEvisaResponce = response;
                        new EVisaBookingDialog().showDialog(getActivity(), bookEvisaResponce);

                    }
                }
            }
        });
    }


    private void setData() {
        choosePassportNum.add(new GeneralResposeData(0, "" + 1));
        choosePassportNum.add(new GeneralResposeData(1, "" + 2));
        choosePassportNum.add(new GeneralResposeData(2, "" + 3));
        choosePassportNum.add(new GeneralResposeData(3, "" + 4));
        choosePassportNum.add(new GeneralResposeData(4, "" + 5));
        choosePassportNum.add(new GeneralResposeData(5, "" + 6));
        choosePassportNum.add(new GeneralResposeData(6, "" + 7));
        choosePassportNum.add(new GeneralResposeData(7, "" + 8));
        choosePassportNum.add(new GeneralResposeData(8, "" + 9));
        choosePassportNum.add(new GeneralResposeData(9, "" + 10));

        choosePassportNumAdapter = new SpinnerAdapter(getActivity());
        choosePassportNumAdapter.setData(choosePassportNum, getString(R.string.How_many_Passport));
        fragmentGetEVisaSpPassportNum.setAdapter(choosePassportNumAdapter);
        fragmentGetEVisaSpPassportNum.setSelection(choosePassportNumSelectedId1);
        listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    choosePassportNumSelectedId1 = i;
                    fragmentGetEVisaHidenPartLy.setVisibility(View.VISIBLE);
                    ToastCreator.onCreateSuccessToast(getActivity(), "Please Must Select " + choosePassportNumSelectedId1 + " Passport photos and " + choosePassportNumSelectedId1 + " Personal photos");

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
        fragmentGetEVisaSpPassportNum.setOnItemSelectedListener(listener);

        initHozental(mPassportPath, fragmentGetEVisaPassportPhotoRvItemHzRv, 1);

        initHozental(mPersonalPath, fragmentGetEVisaPersonalPhotoRvItemHzRv, 2);

    }

    private void initHozental(List<String> mListPath, RecyclerView fragmentEVisaGeneralRvItemHzRv, int itemNum) {
        linearLayoutHorizental = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        fragmentEVisaGeneralRvItemHzRv.setLayoutManager(linearLayoutHorizental);
        fragmentEVisaGeneralRvItemHzRv.setHasFixedSize(true);
//        clientGetRestaurantsFiltterList(0);
        fragmentEVisaGeneralRvItemHzRv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        if (itemNum == 1) {
            eVisaPhotoGallaryHzRvAdapter = new EVisaPhotoGallaryHzRvAdapter(getContext(), getActivity(), "passport", mListPath);
            fragmentEVisaGeneralRvItemHzRv.setAdapter(eVisaPhotoGallaryHzRvAdapter);
//                    showToast(getActivity(), String.valueOf(getTopUmarAndTophajjPackage.getUmarImages().get(0)));

        }
        if (itemNum == 2) {
            eVisaPhotoGallaryHzRvAdapter2 = new EVisaPhotoGallaryHzRvAdapter(getContext(), getActivity(), "personal", mListPath);
            fragmentEVisaGeneralRvItemHzRv.setAdapter(eVisaPhotoGallaryHzRvAdapter2);
//                    showToast(getActivity(), String.valueOf(getTopUmarAndTophajjPackage.getUmarImages().get(0)));

        }
    }

    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment, new AccountFragment());
        navController.navigate(R.id.action_getEVisaFragment_to_fragment_choose_static_evisa);
        homeCycleActivity.setNavigation("v");
//        homeCycleActivity.bottomNavView.getMenu().getItem(4).setChecked(true);
    }

    @OnClick({R.id.fragment_get_e_visa_back_btn, R.id.fragment_get_e_visa_get_passport_photos_btn, R.id.fragment_get_e_visa_get_personal_photos_btn, R.id.fragment_get_e_visa_sav_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_get_e_visa_back_btn:
                onBack();
                break;
            case R.id.fragment_get_e_visa_get_passport_photos_btn:
                mPassportPath.clear();
                openGalleryِAlpom(getActivity(), alpom, new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        for (int i = 0; i < result.size(); i++) {

                            mPassportPath.add(result.get(i).getPath());
//                            showToast(getActivity(), mPassportPath.get(i));

//                        if(mPassportPath!=null){
//                            onLoadImageFromUrl(fragmentHajjAndUmrahBookingGetPassportImageImg,mPassportPath, getContext());}
                        }
//                        fragmentGetEVisaGetPassportPhotosTv.setText("Success photo Load");
                        if(result.size()!=choosePassportNumSelectedId1){
                            showToast(getActivity(),"You must Choose "+choosePassportNumSelectedId1+" Images");
                            return;
                        }else {

                        eVisaPhotoGallaryHzRvAdapter.notifyDataSetChanged();}

                    }

                }, choosePassportNumSelectedId1);

                break;
            case R.id.fragment_get_e_visa_get_personal_photos_btn:
                mPersonalPath.clear();
                openGalleryِAlpom(getActivity(), alpom, new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        for (int i = 0; i < result.size(); i++) {

                            mPersonalPath.add(result.get(i).getPath());
//                        if(mPassportPath!=null){
//                            onLoadImageFromUrl(fragmentHajjAndUmrahBookingGetPassportImageImg,mPassportPath, getContext());}
                        }
                        if(result.size()!=choosePassportNumSelectedId1){
                            showToast(getActivity(),"You must Choose "+choosePassportNumSelectedId1+" Images");
                            return;
                        }else {
                            eVisaPhotoGallaryHzRvAdapter2.notifyDataSetChanged();
                        }
                    }
                }, choosePassportNumSelectedId1);
                break;
            case R.id.fragment_get_e_visa_sav_btn:
                onValidation();
                break;
        }
    }

    private void onValidation() {

        List<EditText> editTexts = new ArrayList<>();
        List<TextInputLayout> textInputLayouts = new ArrayList<>();
        List<Spinner> spinners = new ArrayList<>();

        textInputLayouts.add(fragmentGetEVisaTilFirstName);
        textInputLayouts.add(fragmentGetEVisaTilLastName);
        textInputLayouts.add(fragmentGetEVisaTilPhone);
        textInputLayouts.add(fragmentGetEVisaTilEmail);

        cleanError(textInputLayouts);

        if (!validationAllEmpty(editTexts, textInputLayouts, spinners, getString(R.string.empty))) {

            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.empty));
            return;
        }

        if (!validationLength(fragmentGetEVisaTilFirstName, getString(R.string.invalid_first_name), 3)) {
            ToastCreator.onCreateErrorToast(getActivity(),  getString(R.string.invalid_first_name));
            return;
        }

        if (!validationLength(fragmentGetEVisaTilLastName, getString(R.string.invalid_last_name), 3)) {
            ToastCreator.onCreateErrorToast(getActivity(),  getString(R.string.invalid_last_name));
            return;
        }

        if (!validationEmail(getActivity(), fragmentGetEVisaTilEmail)) {
            ToastCreator.onCreateErrorToast(getActivity(),  getString(R.string.invalid_email_required_field));

            return;
        }


        if (!validationPhone(getActivity(), fragmentGetEVisaTilPhone)) {
            ToastCreator.onCreateErrorToast(getActivity(), "Enter Phone");
            return;
        }

        if (fragmentGetEVisaSpPassportNum.getSelectedItemPosition() == 0) {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.select_pasport_num));
            return;
        }

        if (mPassportPath.size() != 0 && mPersonalPath.size() != 0) {
            if (mPassportPath.size() != mPersonalPath.size()) {
                ToastCreator.onCreateErrorToast(getActivity(), "Please Must Select " + choosePassportNumSelectedId1 + " Passport photos and " + choosePassportNumSelectedId1 + " Personal photos");
            } else {
                onCall();

            }

        } else {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.select_image));
//                showToast(getActivity(), "Please Select Image First");
            return;
        }
    }

    private void onCall() {
        RequestBody userId = convertToRequestBody(String.valueOf(userData.getId()));
        RequestBody countryType2 = convertToRequestBody(countryType);
        RequestBody firstName = convertToRequestBody(fragmentGetEVisaTilFirstName.getEditText().getText().toString());
        RequestBody lastName = convertToRequestBody(fragmentGetEVisaTilLastName.getEditText().getText().toString());
        RequestBody email = convertToRequestBody(fragmentGetEVisaTilEmail.getEditText().getText().toString());
        RequestBody contactNum = convertToRequestBody(fragmentGetEVisaTilPhone.getEditText().getText().toString());
        List<MultipartBody.Part> passportFiles = new ArrayList<MultipartBody.Part>();
//        if (mPassportPath != null) {

        for (int i = 0; i < mPassportPath.size(); i++) {
            passportFiles.add(convertFileToMultipart(mPassportPath.get(i), "passport_photo[" + i + "]", getActivity()));
            //Where selectedFiles is selected file URI list

        }
//        }
        List<MultipartBody.Part> personalFiles = new ArrayList<MultipartBody.Part>();

        for (int i = 0; i < mPersonalPath.size(); i++) {

            personalFiles.add(convertFileToMultipart(mPersonalPath.get(i), "photo[" + i + "]", getActivity()));
//            showToast(getActivity(), "photo[" + (i)+"]");

            //Where selectedFiles is selected file URI list
        }
//        showToast(getActivity(), String.valueOf(passportFiles.size()+" "+personalFiles.size()));

        Call<BookEvisaResponce> clientCall;


        clientCall = getApiClient().evisaBooking(userId, firstName, lastName, contactNum, email, countryType2, passportFiles, personalFiles);

        viewModelUser.setVisaBooking(getActivity(), clientCall, true);


    }

}