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
import com.example.barakatravelapp.adapter.ShowDayByDayVrRvAdapter;
import com.example.barakatravelapp.data.model.getUmrahAndHujjResponce.GetTopUmarAndTophajjPackage;

public class ShowDayByDayDialog extends DialogFragment {

    private LinearLayoutManager lLayout;
    private ImageView cancelBtn ,backBtn,titleName;
//    List<UmarhDay> dialogDayByDayListItem = new ArrayList<UmarhDay>();
    private GetTopUmarAndTophajjPackage getTopUmarAndTophajjPackage;
    private ShowDayByDayVrRvAdapter rcAdapter;

    public ShowDayByDayDialog(GetTopUmarAndTophajjPackage getTopUmarAndTophajjPackage) {
        this.getTopUmarAndTophajjPackage=getTopUmarAndTophajjPackage;
    }

//    public static DialogFragment newInstance(View.OnClickListener listener) {
//        DialogFragment fragment = new DialogFragment();
//        return fragment;
//    }

//    String[] players={"Lionel Messi","Christiano Ronaldo","Neymar","Gareth Bale"};

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // TODO Auto-generated method stub
        View rootView= getActivity().getLayoutInflater().inflate(R.layout.dialog_day_by_day_and_evisa_more_rv, null);

        //SET TITLE DIALOG TITLE
//        getDialog().setTitle("Best Players In The World");
//        this.getDialog().setTitle("أختيار القسم العام");
//        this.getDialog().setCancelable(true);

        cancelBtn=(ImageView) rootView.findViewById(R.id.dialog_day_by_day_rv_and_evisa_more_cancel_btn_tv);
//        backBtn=(TextView) rootView.findViewById(R.id.dialog_categories_rv_back_btn_tv);
//        titleName=(TextView) rootView.findViewById(R.id.dialog_categories_rv_category_name_tv);
//        showToast(getActivity(), String.valueOf(getTopUmarAndTophajjPackage.getUmarImages().get(0)));
        RecyclerView dialogCategoriesRvRecyclerView = (RecyclerView) rootView.findViewById(R.id.dialog_day_by_day_and_evisa_more_rv_recycler_view);

        lLayout = new LinearLayoutManager(getActivity());

        dialogCategoriesRvRecyclerView.setLayoutManager(lLayout);

         rcAdapter = new ShowDayByDayVrRvAdapter(getContext(), getActivity(), getTopUmarAndTophajjPackage.getUmarhDays());
        dialogCategoriesRvRecyclerView.setAdapter(rcAdapter);
        rcAdapter.notifyDataSetChanged();

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


//    @Override
//    public void onMethodCallback(String title) {
//        backBtn.setVisibility(View.VISIBLE);
//        titleName.setText(title);
//    }


}


//    private void showChooseCategoriesDialog() {
//        try {
//            Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT)
//                    .show();
//            List<ItemGeneralObjectModel> dialogCategoryListItem = getAllItemList();
//
////            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//            final View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_categories_rv, null);
////            alertDialog = new AlertDialog.Builder(HomeContainerFragment.this).create();
//            AlertDialog.Builder builder = new AlertDialog
//                    .Builder(new ContextThemeWrapper(getActivity(), R.style.Theme_AppCompat_Dialog));
//            AlertDialog alertDialog;
//            builder.setView(view);
//            builder.setTitle("أختيار القسم العام");
//            builder.setCancelable(false);
////            alertDialog.setMessage("أختار القسم العام");
//
//
//            RecyclerView dialogCategoriesRvRecyclerView = (RecyclerView) root.findViewById(R.id.dialog_categories_rv_recycler_view);
//
//            lLayout = new LinearLayoutManager(getActivity());
//
//            dialogCategoriesRvRecyclerView.setLayoutManager(lLayout);
//
//            DialogCategoryAdapter rcAdapter = new DialogCategoryAdapter(getContext(), getActivity(), dialogCategoryListItem);
//            dialogCategoriesRvRecyclerView.setAdapter(rcAdapter);
//            rcAdapter.notifyDataSetChanged();
//            alertDialog = builder.create();
//
//
////            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "تراجع", new DialogInterface.OnClickListener() {
////                @Override
////                public void onClick(DialogInterface dialog, int which) {
//////                        Call<RestaurantCategoryResponse> deletItemCal = getApiClient().restaurantDeleteCategory(clientData.getApiToken(), restaurantDataList.get(position).getId());
//////                        deleteAndUpdateItemCallBack(activity, deletItemCal);
//////                    costsListDataList.remove(position);
//////                    notifyItemRemoved(position);
//////                    notifyItemRangeChanged(position, costsListDataList.size());
////
////                }
////            });
//
//
//            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "الغاء", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    alertDialog.dismiss();
//                }
//            });
//
//            alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
//                @SuppressLint("ResourceAsColor")
//                @Override
//                public void onShow(DialogInterface arg0) {
//
////                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.blue);
//                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(R.color.blue);
//
//                }
//            });
////            builder.show();
//
//            alertDialog.show();
//
//
////            dialogCategoriesRvRecyclerView.setHasFixedSize(true);
//
//        } catch (Exception e) {
//
//        }
//    }