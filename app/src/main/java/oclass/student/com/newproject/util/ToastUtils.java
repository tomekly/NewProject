package oclass.student.com.newproject.util;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by DongBang
 */
public class ToastUtils {
    public static boolean isToast = true;

    public static void toastShort(Context context, CharSequence content) {
        if (isToast) {
            Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
        }
    }

    public static void toastLong(Context context, CharSequence content) {
        if (isToast) {
            Toast.makeText(context, content, Toast.LENGTH_LONG).show();
        }
    }

    public static void toastDuration(Context context, CharSequence content, int duration) {
        if (isToast) {
            Toast.makeText(context, content, duration).show();
        }
    }

    public static void toastUiThread(final Context context, final CharSequence content, final int duration) {
        if (isToast) {
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, content, duration).show();
                }
            });
        }
    }


}
