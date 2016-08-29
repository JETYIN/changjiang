package tech.boshu.changjiangshidai.utils;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by allipper on 16/1/24.
 */
public class ValidateUtils {

    public interface ValidateInterface {
        void onFailed(String message);
    }

    private final static String[] MESSAGE = new String[]{"选择供应商", "选择仓库", "选择日期", "选择账户",
            "输入帐号", "输入姓名", "输入手机", "输入邮箱", "输入备注"};

    public boolean validat(ValidateInterface validateInterface, TextView... textView) {
        for (TextView tv : textView) {
            String value = tv.getText().toString();
            if (TextUtils.isEmpty(value)) {
                String tip = "必填项不能为空";
                if(tv instanceof EditText){
                    tip = tv.getHint().toString();
                }
                validateInterface.onFailed(tip);
                return false;
            } else {
                for (String string : MESSAGE) {
                    if (string.equals(value)) {
                        validateInterface.onFailed(string);
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
