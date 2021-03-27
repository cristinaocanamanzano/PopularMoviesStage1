package android.example.popularmoviesstage1.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParsingUtils {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String formatDateString(String initialDateString) {
        String formattedDateString = "";
        try {
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormatter.parse(initialDateString);
            dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            formattedDateString = dateFormatter.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDateString;
    }
}
