package com.nazer.saini.chatarchitecture.ui.dialogues;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.widget.DatePicker;



import java.util.Calendar;
import java.util.TimeZone;

public class DateTimePickerDialogue {

    private static DateTimePickerDialogue dateTimePickerDialogue;
    private Long minDate;
    private Long maxDate;
    Dialogues.DialogueOneButtonCallback oneButtonCallback;

    /**
     * =============================================================Date Time Alert Dialogue
     */
    private DateTimePickerDialogue() {

    }

    public static DateTimePickerDialogue instance() {
        if (dateTimePickerDialogue == null)
            dateTimePickerDialogue = new DateTimePickerDialogue();
        return dateTimePickerDialogue;
    }

    public DateTimePickerDialogue setMinDate(Long minDate) {
        this.minDate = minDate;
        return this;
    }

    public DateTimePickerDialogue setMaxDate(Long maxDate) {
        this.maxDate = maxDate;
        return this;
    }

    public void showDatePicker(Context context, Dialogues.DialogueOneButtonCallback oneButtonCallback) {
        this.oneButtonCallback = oneButtonCallback;
        DatePickerDialog dialog = getDatePicker(context);
        if (minDate!=null&&minDate > 0)
            dialog.getDatePicker().setMinDate(minDate);
        if (maxDate!=null&&maxDate > 0)
            dialog.getDatePicker().setMaxDate(maxDate);
        dialog.show();
    }


    private DatePickerDialog getDatePicker(Context context) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        return new DatePickerDialog(context, onDateSetListener,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
    }

    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Log.e("onDateSet: ", " " + year + " " + month + " " + dayOfMonth);

//            oneButtonCallback.OnOkClick(" " + year + " " + month + " " + dayOfMonth);
            oneButtonCallback.OnOkClick(getDateLong( year, month, dayOfMonth));
        }
    };

    private Long getDateLong( int year, int month, int dayOfMonth)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        return calendar.getTimeInMillis();
    }


}
