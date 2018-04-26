package td.techjam.tangoclient;

import android.util.Log;

public class Utils {
    private static final boolean DEBUG = true;

    public static void LogD(String tag, String text) {
        if (DEBUG) {
            Log.d(tag, text);
        }
    }

    public static void LogE(String tag, String text) {
        if (DEBUG) {
            Log.e(tag, text);
        }
    }

    public static void LogE(String tag, String text, Exception e) {
        if (DEBUG) {
            Log.e(tag, text, e);
        }
    }

    public static void LogE(String tag, String text, Throwable t) {
        if (DEBUG) {
            Log.e(tag, text, t);
        }
    }

    public static void LogV(String tag, String text) {
        if (DEBUG) {
            Log.v(tag, text);
        }
    }
}
