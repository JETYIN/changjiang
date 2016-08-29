package tech.boshu.changjiangshidai.ui.activity.stockinfo.checkorderhistory;

import android.text.TextUtils;

import java.util.HashMap;

/**
 * Created by Stone on 2016/1/28.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class RequestCheckOrderHistoryParam {

    public String companyId;
    public int storeId;
    public String orderId;
    public String beginTime;
    public String endTime;

    public HashMap<String, String> toParam() {
        HashMap<String, String> params = new HashMap<>();
        if (!TextUtils.isEmpty(companyId)) {
            params.put("companyId", companyId);
        }
        if (storeId != -1) {
            params.put("storeId", String.valueOf(storeId));
        }
        if (!TextUtils.isEmpty(orderId)) {
            params.put("orderId", orderId);
        }
        if (beginTime != null) {
            params.put("beginTime", beginTime);
        }
        if (endTime != null) {
            params.put("endTime", endTime);
        }
        return params;
    }
}
