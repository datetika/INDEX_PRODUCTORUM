package com.dev.mrvazguen.indexproductorum.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.dev.mrvazguen.indexproductorum.R;

import java.util.ArrayList;
import java.util.List;

public class PurchaseConfirmationDialogFragment extends DialogFragment {
    public static String TAG = "PurchaseConfirmationDialog";
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setMessage(getString(R.string.order_confirmation))
                .setPositiveButton(getString(R.string.ok_text), (dialog, which) -> {

                    if(dialog.equals("ok")){

                    }
                    else {

                    }
                } )
                .create();
    }


}
