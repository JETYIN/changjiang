package tech.boshu.changjiangshidai.libs.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;
import android.app.AlertDialog.Builder;
import android.widget.Toast;

import java.text.DecimalFormat;

import tech.boshu.changjiangshidai.libs.R;

/**
 * Created by Administrator on 2015/7/8.
 */
public class Utils {

    /**
     * 手机网络检测
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return true;
            }
        }
        ToastUtils.show(context, context.getString(R.string.connect_check));
        return false;
    }


    //拨打电话
    public static void callPhone(Context context, String number) {
        if (number == null)
            return;
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + number));
            context.startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(context, "您的设备不支持拨打电话！", Toast.LENGTH_SHORT).show();
        }
    }


    //发送短信
    public static void sendMessage(Context context, String number) {
        if (number == null)
            return;
        try {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("smsto:" + number));
            context.startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            ToastUtils.show(context, "您的设备不支持发送短信！");
        }
    }

    /**
     * 打开设置网络界面
     *
     * @param context
     */
    public static void setNetworkMethod(final Context context) {
        Builder builder = new Builder(context);
        builder.setTitle("网络设置提示").setMessage("网络连接不可用,是否进行设置?").setPositiveButton("设置", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                context.startActivity(intent);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        }).show();
    }


    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }


    public static String PriceFormat(double price) {
        DecimalFormat df = new DecimalFormat("#0.00");
        return "￥" + df.format(price);
    }

    public static String PriceFormat(String price) {
        if (TextUtils.isEmpty(price)) {
            return "￥0.00";
        }
        DecimalFormat df = new DecimalFormat("#0.00");
        try {
            return "￥" + df.format(Float.valueOf(price));
        } catch (Exception e) {
            return "￥0.00";
        }
    }

    public static int dipDimensionInteger(Context context, float value) {
        return (int) (dipDimensionFloat(context, value) + 0.5f);
    }

    public static float dipDimensionFloat(Context context, float value) {
        return context == null ? value : TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, value, context.getResources()
                        .getDisplayMetrics());
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 获取手机屏幕的高宽
     *
     * @param context
     * @return
     */
    public static Point getScreenPoint(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        return point;
    }

    /**
     * 获取不同尺寸的默认图片
     *
     * @param resId 资源ID
     * @return
     */
    public static int getDefaultImage(int resId) {
        if (resId == -1) {
            return 1000000;
        } else {
            return resId;
        }
    }

}
