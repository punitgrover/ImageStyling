package ds.imagestyling.utils;

import android.content.Context;
import android.graphics.Bitmap;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.DisplayMetrics;


/**
 *
 * Created by sky
 * Created on 2015/7/6.
 * Modified on 26,August,2017
 */
public class App extends MultiDexApplication {

    protected static App       mInstance;
    private Bitmap imageBit;

    private DisplayMetrics     displayMetrics = null;

    public Bitmap getImageBit() {
        return imageBit;
    }



    public App(){
        mInstance = this;
    }

    public static App getApp() {
        if (mInstance != null && mInstance instanceof App) {
            return (App) mInstance;
        } else {
            mInstance = new App();
            mInstance.onCreate();
            return (App) mInstance;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Preferences.init(this);

        mInstance = this;
    }
    public int dp2px(float f)
    {
        return (int)(0.5F + f * getScreenDensity());
    }
    public float getScreenDensity() {
        if (this.displayMetrics == null) {
            setDisplayMetrics(getResources().getDisplayMetrics());
        }
        return this.displayMetrics.density;
    }


    public void setDisplayMetrics(DisplayMetrics DisplayMetrics) {
        this.displayMetrics = DisplayMetrics;
    }





    @Override
    protected void attachBaseContext(Context newBase) {
        MultiDex.install(newBase);
        super.attachBaseContext(newBase);
    }






    public void setImageBit(Bitmap imageBit) {
        this.imageBit = imageBit;
    }


}
