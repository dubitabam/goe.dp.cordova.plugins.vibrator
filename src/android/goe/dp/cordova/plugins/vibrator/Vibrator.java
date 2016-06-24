package goe.dp.cordova.plugins.vibrator;

import android.content.Context;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Vibrator extends CordovaPlugin {

    android.os.Vibrator vibrator;

    protected void pluginInitialize() {
        Context context = this.cordova.getActivity().getApplicationContext();
        vibrator = (android.os.Vibrator) context.getSystemService("vibrator");
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("vibrate")) {
            int repeat = args.optInt(1, -1);
            long[] pattern = new long[args.getJSONArray(0).length()];
            for (int i = 0; i < args.getJSONArray(0).length(); i++) {
                if (!args.getJSONArray(0).isNull(i)) {
                    pattern[i] = args.getJSONArray(0).getLong(i);
                }
            }

            vibrate(pattern, repeat, callbackContext);
            return true;
        }
        else if (action.equals("cancel")) {
            cancel(callbackContext);
            return true;
        }
        else if (action.equals("hasVibrator")) {
            hasVibrator(callbackContext);
            return true;
        }
        return false;
    }

    private synchronized void vibrate(final long[] pattern, final int repeat, final CallbackContext callbackContext) {
        vibrator.vibrate(pattern, repeat);
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 0));
    }

    private synchronized void cancel(final CallbackContext callbackContext) {
        vibrator.cancel();
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 0));
    }

    private synchronized void hasVibrator(final CallbackContext callbackContext) {
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, vibrator.hasVibrator()));
    }
}
