package com.example.barakatravelapp.utils;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.adapter.EVisaMoreItemsAdapter;
import com.example.barakatravelapp.adapter.ShowDayByDayVrRvAdapter;
import com.example.barakatravelapp.data.model.getBookingEvisaResponce.EvisaDetail;
import com.example.barakatravelapp.data.model.getUmrahAndHujjResponce.GetTopUmarAndTophajjPackage;

import java.util.List;

public class EvisaMoreRVDialog extends DialogFragment {

    private PhotoGallaryAdapterCallback photoGallaryAdapterCallback;
    private LinearLayoutManager lLayout;
    private ImageView cancelBtn ,backBtn,titleName;
//    List<UmarhDay> dialogDayByDayListItem = new ArrayList<UmarhDay>();
    private List<EvisaDetail> evisaDetails;
    private EVisaMoreItemsAdapter eVisaMoreItemsAdapter;

    public EvisaMoreRVDialog(List<EvisaDetail> evisaDetails, PhotoGallaryAdapterCallback photoGallaryAdapterCallback) {
        this.evisaDetails=evisaDetails;
        this.photoGallaryAdapterCallback=photoGallaryAdapterCallback;
    }

//    public static DialogFragment newInstance(View.OnClickListener listener) {
//        DialogFragment fragment = new DialogFragment();
//        return fragment;
//    }

//    String[] players={"Lionel Messi","Christiano Ronaldo","Neymar","Gareth Bale"};

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // TODO Auto-generated method stub
        View rootView= getActivity().getLayoutInflater().inflate(R.layout.dialog_day_by_day_and_evisa_more_rv2, null);

        //SET TITLE DIALOG TITLE
//        getDialog().setTitle("Best Players In The World");
//        this.getDialog().setTitle("أختيار القسم العام");
//        this.getDialog().setCancelable(true);

        cancelBtn=(ImageView) rootView.findViewById(R.id.dialog_day_by_day_rv_and_evisa_more_cancel_btn_tv);
//        backBtn=(TextView) rootView.findViewById(R.id.dialog_categories_rv_back_btn_tv);
//        titleName=(TextView) rootView.findViewById(R.id.dialog_categories_rv_category_name_tv);
//        showToast(getActivity(), String.valueOf(getTopUmarAndTophajjPackage.getUmarImages().get(0)));
        RecyclerView dialogRvRecyclerView = (RecyclerView) rootView.findViewById(R.id.dialog_day_by_day_and_evisa_more_rv_recycler_view);

        lLayout = new LinearLayoutManager(getActivity());

        dialogRvRecyclerView.setLayoutManager(lLayout);

        eVisaMoreItemsAdapter = new EVisaMoreItemsAdapter(getContext(), getActivity(), evisaDetails,photoGallaryAdapterCallback,this);
        dialogRvRecyclerView.setAdapter(eVisaMoreItemsAdapter);
        eVisaMoreItemsAdapter.notifyDataSetChanged();

        //BUTTON
        cancelBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                dismiss();
            }
        });

//        return rootView;
        return new AlertDialog.Builder(getActivity())
//                .setTitle("أختيار القسم العام")
                .setView(rootView)
//                .setPositiveButton(android.R.string.ok,
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int whichButton) {
//                                // do something
//                            }
//                        }
//                )
                .create();
    }

    }