package dang.ugi.com.model.Utils;

import java.util.regex.Pattern;

/**
 * Created by DANG on 11/21/2016.
 */

public class CheckValidateRegex {
    public static boolean checkNumber(String strNumber){
        boolean matches = false;
        Pattern pattern = Pattern.compile("^[0-9]{0,10}$");
        if (pattern.matcher(strNumber).matches()){
            matches = true;
        }
        return matches;
    }
}
