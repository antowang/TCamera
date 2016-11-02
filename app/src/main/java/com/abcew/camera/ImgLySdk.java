package com.abcew.camera;

import android.content.Context;
import android.content.res.Resources;
import android.renderscript.RenderScript;
import android.support.annotation.NonNull;
import android.util.Log;

import com.abcew.camera.ui.utilities.OrientationSensor;

/**
 * Created by laputan on 16/10/31.
 */
public class ImgLySdk {
    private static Context instance;

    /**
     * Must be called in your application onCreate()!
     * @param application your application
     */
    public static void init(Context application) {
        //Workaround Bugfix!!!, it prevents from crashing on Android 4.0.x
        try{ Class.forName("android.os.AsyncTask"); } catch (Throwable ignored){}

        instance = application;

        OrientationSensor.initSensor(application);

    }

    /**
     * Get the Application Context
     * @return The application context
     */
    @NonNull
    public static Context getAppContext() {
        if (instance == null) {
            throw new RuntimeException("Please Call ImgLySdk init() in Application onCreate");
        }
        return instance;
    }

    public static RenderScript getAppRsContext() {
        RenderScript rs = RenderScript.create(ImgLySdk.getAppContext());
        rs.setPriority(RenderScript.Priority.LOW);
        rs.setErrorHandler(new RsError());
        rs.setMessageHandler(new RsMassage());
        return rs;
    }

    private static class RsError extends RenderScript.RSErrorHandler {
        public void run() {
            Log.e("ImgLyRs", "RenderscriptError:" + mErrorNum + " - " + mErrorMessage);
        }
    }

    private static class RsMassage extends RenderScript.RSMessageHandler {
        public void run() {}
    }

    /**
     * Get a System Service
     * @return The System Service Object
     */
    public static Object getAppSystemService(@NonNull String name) {
        return getAppContext().getSystemService(name);
    }

    /**
     * Get the resources object.
     * @return Application resource reference
     */
    @NonNull
    public static Resources getAppResource() {
        if (instance != null) {
            return instance.getResources();
        } else {
            Log.e("ImgLySdk", "Please Call ImgLySdk init() in Application onCreate");
            //throw new RuntimeException("Please Call ImgLySdk init() in Application onCreate");
            return Resources.getSystem();
        }
    }



}
