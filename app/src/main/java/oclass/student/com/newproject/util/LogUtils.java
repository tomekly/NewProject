package oclass.student.com.newproject.util;

import android.util.Log;

/**
 * Log工具类
 *
 */
public class LogUtils {
    public static boolean isLog = true;

    public static void i(String tag, String msg) {
        if (isLog) {
            Log.i(tag, msg);
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (isLog) {
            Log.i(tag, msg, tr);
        }
    }

//    public static void d(String tag) {
//        if (isLog) {
//            Log.d(tag, msg);
//        }
//    }

    public static void d(String tag, String msg, Throwable tr) {
        if (isLog) {
            Log.d(tag, msg, tr);
        }
    }

//    public static void e(String tag) {
//        if (isLog) {
//            Log.e(tag, msg);
//        }
//    }

    public static void e(String tag, String msg, Throwable tr) {
        if (isLog) {
            Log.e(tag, msg, tr);
        }
    }

    public static void v(String tag, String msg) {
        if (isLog) {
            Log.v(tag, msg);
        }
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (isLog) {
            Log.v(tag, msg, tr);
        }
    }


}
