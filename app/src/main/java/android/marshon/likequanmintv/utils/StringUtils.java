package android.marshon.likequanmintv.utils;

import java.util.regex.Pattern;

/**
 * Created by Marshon.Chen on 2016/8/10.
 * DESC:
 */
public class StringUtils {

    public static boolean isPhone(String phone) {
        Pattern pattern = Pattern.compile("[1][34578]\\d{9}");
        return pattern.matcher(phone).matches();
    }
}
