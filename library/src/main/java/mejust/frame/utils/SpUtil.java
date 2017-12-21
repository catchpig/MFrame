package mejust.frame.utils;

import android.content.Context;
import android.content.SharedPreferences;
import mejust.frame.app.BaseApplication;

/**
 * 创建时间:2017-12-21 17:50<br/>
 * 创建人: 王培峰<br/>
 * 修改人: 王培峰<br/>
 * 修改时间: 2017-12-21 17:50<br/>
 * 描述: SharedPreference操作工具类
 */

public class SpUtil {

    private static final String BASE_SP_NAME =
            BaseApplication.getApplication().getPackageName().replace(".", "_");
    private static volatile SpUtil spUtil;

    public static SpUtil getInstance() {
        return getInstance(BASE_SP_NAME);
    }

    public static SpUtil getInstance(String name) {
        if (spUtil == null) {
            synchronized (SpUtil.class) {
                if (spUtil == null) {
                    spUtil = new SpUtil(name);
                }
            }
        }
        return spUtil;
    }

    private SharedPreferences preferences;

    private SpUtil(String name) {
        preferences =
                BaseApplication.getApplication().getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public void putStringApply(String key, String value) {
        preferences.edit().putString(key, value).apply();
    }

    public boolean putStringCommit(String key, String value) {
        return preferences.edit().putString(key, value).commit();
    }

    public void putIntApply(String key, int value) {
        preferences.edit().putInt(key, value).apply();
    }

    public boolean putIntCommit(String key, int value) {
        return preferences.edit().putInt(key, value).commit();
    }

    public void putBooleanApply(String key, boolean value) {
        preferences.edit().putBoolean(key, value).apply();
    }

    public boolean putBooleanCommit(String key, boolean value) {
        return preferences.edit().putBoolean(key, value).commit();
    }

    public String getString(String key) {
        return getString(key, "");
    }

    public String getString(String key, String defaultValue) {
        return preferences.getString(key, defaultValue);
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        return preferences.getInt(key, defaultValue);
    }

    public boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return preferences.getBoolean(key, defaultValue);
    }

    public void removeApply(String key) {
        preferences.edit().remove(key).apply();
    }

    public boolean removeCommit(String key) {
        return preferences.edit().remove(key).commit();
    }

    public void clearApply() {
        preferences.edit().clear().apply();
    }

    public boolean clearCommit() {
        return preferences.edit().clear().commit();
    }
}
