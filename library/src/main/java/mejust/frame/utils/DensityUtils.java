package mejust.frame.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;


/**
 * @author zhuazhu
 **/
public class DensityUtils {
    private static float sNoncompatDensity;
    private static float sNoncompatScaledDensity;
    public static void setCustomDensity(Activity activity, final Application application){
        final DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
        if(sNoncompatDensity==0){
            sNoncompatDensity = displayMetrics.density;
            sNoncompatScaledDensity = displayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig!=null&& newConfig.fontScale>0) {
                        sNoncompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
            final float targetDensity = (float) displayMetrics.widthPixels/360;
            final float targetScaledDensity = targetDensity*(sNoncompatScaledDensity/sNoncompatDensity);
            final int targetDensityDpi = (int) (160*targetDensity);
        }
    }
}
