package io.github.kobakei.anago.util;

import android.util.Base64;

/**
 * Created by keisuke on 2016/09/18.
 */

public class NetUtil {
    public static String getBasicHeader(String name, String password) {
        String str = Base64.encodeToString((name + ":" + password).getBytes(), Base64.NO_WRAP);
        return "Basic " + str;
    }
}
