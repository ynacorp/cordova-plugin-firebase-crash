package by.chemerisuk.cordova.firebase;

import by.chemerisuk.cordova.support.CordovaMethod;
import by.chemerisuk.cordova.support.ReflectiveCordovaPlugin;

import com.crashlytics.android.Crashlytics;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import io.fabric.sdk.android.Fabric;

public class FirebaseCrashPlugin extends ReflectiveCordovaPlugin {
    private final String TAG = "FirebaseCrashPlugin";

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);

        Fabric.with(cordova.getActivity(), new Crashlytics());
    }

    @CordovaMethod(ExecutionThread.WORKER)
    private void crash() {
        Crashlytics.getInstance().crash();
    }

    @CordovaMethod(ExecutionThread.WORKER)
    private void log(String message, CallbackContext callbackContext) {
        Crashlytics.log(message);
        callbackContext.success();
    }

    @CordovaMethod(ExecutionThread.UI)
    private void logError(String message, CallbackContext callbackContext) {
        Crashlytics.logException(new Exception(message));
        callbackContext.success();
    }

    @CordovaMethod(ExecutionThread.UI)
    private void setUserId(String userId, CallbackContext callbackContext) {
        Crashlytics.setUserIdentifier(userId);
        callbackContext.success();
    }

}
