package tech.boshu.changjiangshidai.libs.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import tech.boshu.changjiangshidai.libs.R;


/**
 * Created by Administrator on 2015/7/8.
 */
public class ToastUtils {
    private static Toast mToast = null;

    public static void show(final Context context, final String message) {
        if (mToast == null) {
            final LinearLayout view = new LinearLayout(context);
            LayoutInflater.from(context).inflate(R.layout.layout_toast, view);
            mToast = new Toast(context);
            mToast.setGravity(Gravity.CENTER, 0, 0);
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.setView(view);
        }
        TextView textView = (TextView) mToast.getView().findViewById(
                R.id.toast_text);
        textView.setText(message);
        mToast.show();
    }

    public static void show(final Context context, final int resId) {
        show(context, context.getString(resId));
    }

    public static void cancel() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
