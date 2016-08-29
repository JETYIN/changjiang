package tech.boshu.changjiangshidai.ui.activity.stockinfo.flowsearchresult;

import java.util.HashMap;

/**
 * Created by Stone on 2016/1/26.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class RequestStockFlowParam {
    public int storeId;
    public int productId;
    public String beginTime;
    public String endTime;

    public HashMap<String, String> toParam() {
        HashMap<String, String> params = new HashMap<>();
        if (storeId != -1) {
            params.put("storeId", String.valueOf(storeId));
        }
        if (productId != -1) {
            params.put("productId", String.valueOf(productId));
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
