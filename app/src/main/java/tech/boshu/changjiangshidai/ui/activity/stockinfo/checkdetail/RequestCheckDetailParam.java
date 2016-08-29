package tech.boshu.changjiangshidai.ui.activity.stockinfo.checkdetail;

import java.util.HashMap;

/**
 * Created by Stone on 2016/1/20.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */

public class RequestCheckDetailParam {

    public String orderId;

    public HashMap<String, String> toParam() {
        HashMap<String, String> params = new HashMap<>();
        params.put("action", "edit");
        if (orderId != null) {
            params.put("orderId", orderId);
        }
        return params;
    }
}
