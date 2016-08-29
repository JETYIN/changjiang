package tech.boshu.changjiangshidai.ui.activity.stockinfo.productstock;

import java.util.HashMap;

/**
 * Created by Stone on 2016/1/26.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class RequestProductParam {

    public int productId;
    public int zeroNumStatus;

    public HashMap<String, String> toParam() {
        HashMap<String, String> params = new HashMap<>();
        if (productId != -1) {
            params.put("productId", String.valueOf(productId));
        }
        params.put("zeroNumStatus", String.valueOf(zeroNumStatus));
        return params;
    }
}
