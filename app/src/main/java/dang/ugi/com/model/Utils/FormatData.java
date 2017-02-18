package dang.ugi.com.model.Utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by DANG on 11/21/2016.
 */

public  class FormatData {
    public static String formatMoneyVietNam(float money){
        NumberFormat numberFormat = new DecimalFormat("###,###");
        return numberFormat.format(money);
    }
    public static String formatDateTimeVietNam(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return dateFormat.format(date);
    }
    public static String formatDateVietNam(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }
    public static String formatMonthVietNam(Date date){
        DateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
        return dateFormat.format(date);
    }
    public static String formatYearVietNam(Date date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        return dateFormat.format(date);
    }
    public static java.sql.Date formatDateTimeVietNam(String strDate) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        java.sql.Date dateSQL = null;
        try {
         Date date = dateFormat.parse(strDate);
            dateSQL = new java.sql.Date(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateSQL;
    }
    public static Date formatDateVietNam(String strDate) {

        SimpleDateFormat  dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date dateSQL = null;
        try {
            Date date = dateFormat.parse(strDate);
            dateSQL = new java.sql.Date(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateSQL;
    }
    public static Integer formartDatetimeToInteger(Date date){
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return Integer.valueOf(dateFormat.format(date));
    }
}
