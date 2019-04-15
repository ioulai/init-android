package fr.eni.ecole.androkado.application;

import android.app.Application;
import android.content.Context;

public class AndroKadoApplication extends Application {
    private static Context appContext;
    public final static String CONFIGURATION_PREF = "configuration";

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();

        /* If you has other classes that need context object to initialize when application is created,
         you can use the appContext here to process. */
    }

    public static Context getAppContext() {
        return appContext;
    }
}
