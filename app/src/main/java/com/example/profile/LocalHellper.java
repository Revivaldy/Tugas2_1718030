package com.example.profile;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;

import java.util.Locale;

public class LocalHellper {
    private static final String Selected_Language = "locale.Helper.Selected.Language";

    public static Context OnAttach(Context context){
        String lang = getPersitedData(context, Locale.getDefault().getLanguage());
        return setLocale(context,lang);
    }

    public static Context OnAttach(Context context, String defaulLanguage){
        String lang = getPersitedData(context, defaulLanguage);
        return setLocale(context,lang);
    }

    public static Context setLocale(Context context, String lang) {
        persist (context, lang);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            return updateResources(context, lang);

        return updateResourceLegacy(context, lang);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private static Context updateResources(Context context, String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Configuration config = context.getResources().getConfiguration();
        config.setLocale(locale);
        config.setLayoutDirection(locale);

        return context.createConfigurationContext(config);
    }

    @SuppressWarnings("deprecation")
    private static Context updateResourceLegacy(Context context, String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Resources resources = context.getResources();

        Configuration config = context.getResources().getConfiguration();
        config.setLocale(locale);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            config.setLayoutDirection(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
        return context;
    }

    private static void persist(Context context, String lang) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString(Selected_Language, lang);
        editor.apply();
    }

    private static String getPersitedData(Context context, String language) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(Selected_Language, language);
    }
}
