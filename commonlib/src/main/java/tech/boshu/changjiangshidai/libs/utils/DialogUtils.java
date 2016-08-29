package tech.boshu.changjiangshidai.libs.utils;

import android.app.Dialog;
import android.content.Context;

import tech.boshu.changjiangshidai.libs.R;

/**
 * Created by Stone on 2016/1/20.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class DialogUtils {
    public static Dialog createLoadingDialog(Context context) {
        Dialog dialog = new Dialog(context, R.style.CommonLibDialogStyle);
        dialog.setContentView(R.layout.common_lib_layout_loading);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}
