package id.ac.dutabangsa.rumahfilm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Triyono on 9/9/2018.
 */
public class DateFormat {

    private static String format(String date, String format) {
        String result = "";

        java.text.DateFormat old = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date oldDate = old.parse(date);
            java.text.DateFormat newFormat = new SimpleDateFormat("EEEE, MMM dd, yyyy");
            result = newFormat.format(oldDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getDate(String date) {
        return format(date, Utils.DATE_FORMAT);
    }

    public static String getDateDay(String date) {
        return format(date, Utils.DATE_FORMAT_DAY);
    }
}
