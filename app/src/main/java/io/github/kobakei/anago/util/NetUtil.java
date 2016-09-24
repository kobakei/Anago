package io.github.kobakei.anago.util;

import android.util.Base64;

import com.google.gson.Gson;

import java.io.IOException;

import io.github.kobakei.anago.entity.Error;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava.HttpException;

/**
 * ネットワーク関連ユーティリティ
 * Created by keisuke on 2016/09/18.
 */

public class NetUtil {

    /**
     * Basic認証のヘッダーを返す
     * @param name
     * @param password
     * @return
     */
    public static String getBasicHeader(String name, String password) {
        String str = Base64.encodeToString((name + ":" + password).getBytes(), Base64.NO_WRAP);
        return "Basic " + str;
    }

    /**
     * RxJavaの返すExceptionをGitHub APIのエラーに変換する
     * それ以外のエラーの場合、nullを返す
     * @param throwable
     * @return
     */
    public static Error convertError(Throwable throwable) {
        try {
            if (throwable instanceof HttpException) {
                HttpException exception = (HttpException) throwable;
                ResponseBody responseBody = exception.response().errorBody();
                Error error = new Gson().fromJson(responseBody.string(), Error.class);
                return error;
            }
        } catch (IOException e) {
            // do nothing
        }
        return null;
    }
}
