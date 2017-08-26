package ds.imagestyling.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 *
 * Created by Admin
 * Created on 2/15/2016.
 * Modified on 26,August,2017
 */
public class Preferences {
    public static final String AUTH_TOKEN = "auth_Token";
    public static final String IMEI ="imei" ;
    public static final String CURRENT_TIMESTAMP ="current_time" ;
    public static final String LOCAL_TIMESTAMP ="local_time" ;
    public static final String DEVICE_ID ="device_id" ;
    public static final String IS_DEVICE_ID_UPDATE ="update_device_id";
    public static final String TIME_FOR_API = "time_interval";
    public static final String CLIENT_ID ="client_id" ;
    public static final String STROCKWIDTH ="strockwidth" ;
    public static final String STROCKCOLOR = "strokcolor";
    public static final String FONT = "font";
    public static final String SAVE ="save" ;
    public static final String FONTSTYLEPOS = "FONTSTYLEPOS";
    public static final String FONTSIZEPOS ="FONTSIZEPOS" ;
    public static final String PENPOS ="PENPOS" ;
    public static final String ORIENTATION ="ORIENTATION" ;
    public static final String IS_SOCIAL = "is_social";
    private static SharedPreferences sharedPref;
    private static final String PREF_NAME = "app.clickcontrol.net";
    public static final String EMAIL = "email";
    public static final String LOC = "loc";
    public static final String LOCATION = "LOCATION";
    public static final String ACCESSLOC = "access_loc";
        public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PROFILE_PIC = "profile_pic";
    public static final String PHONE = "phone";
    public static final String IS_LOGIN = "isLogin";
    public static final String BASE_URL = "base_url";
    public static final String    CSRF_TOKEN = "csrf-token";
    public static String HISTORYSAVE="historysave";
    public static String VIDEOPATH="videopath";
    public static String IMAGEPATH="Imagepath";
    public static String IMAGENAME="Imagename";
    public static String VIDEONAME="videoname";
    public static String SAVEDETAIL="savedetail";
    public static String SAVEVIDEO="SaveVideo";
    public static String SAVEIMAGE="SaveImage";
    public static String FONTSIZE="FontSize";
    public static String FONTTYPE="FontType";


    public static void init(Context context) {
        sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static void saveValue(String key, String value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }
 public static void saveValue(String key, long value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(key, value);
        editor.apply();
    }


    public static void saveValue(String key, int value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void saveValue(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static String getString(String key) {
        return sharedPref.getString(key, "");
    }

    public static int getInt(String key) {
        return sharedPref.getInt(key, 0);
    }
    public static long getLong(String key) {
        return sharedPref.getLong(key, 0);
    }

    public static boolean getBoolean(String key) {
        return sharedPref.getBoolean(key, false);
    }
public static boolean getBoolean1(String key) {
        return sharedPref.getBoolean(key, true);
    }

    public static void clearAll() {
        sharedPref.edit().clear().apply();
    }

    public static void clear(String key) {


    }


}
