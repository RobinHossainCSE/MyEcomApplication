package com.pickers;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this,
                year, month, day);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        final Calendar calendar = Calendar.getInstance();
        calendar.set(i, i1, i2);
        final String selectedDate = sdf.format(calendar.getTime());
        final Bundle bundle = new Bundle();
        bundle.putString(Constants.DATE_KEY, selectedDate);
        bundle.putInt(Constants.YEAR, i);
        bundle.putInt(Constants.MONTH, i1);
        bundle.putInt(Constants.DAY, i2);
        getParentFragmentManager()
                .setFragmentResult(Constants.REQUEST_KEY, bundle);

    }
}
