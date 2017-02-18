package dang.ugi.com.model.Utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import dang.ugi.com.R;

;

public  class FragmentDialogDateTimePicker extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {
    private Context context;
    private Calendar cal = Calendar.getInstance();


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR) - 15;
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        TextView textView = (TextView) getActivity().findViewById(R.id.textview_dangki_ngaysinh);
       textView.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
    }

}