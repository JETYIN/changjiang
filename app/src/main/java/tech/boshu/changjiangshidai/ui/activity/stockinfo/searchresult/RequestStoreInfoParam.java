package tech.boshu.changjiangshidai.ui.activity.stockinfo.searchresult;

import android.text.TextUtils;

import java.util.HashMap;

/**
 * Created by Stone on 2016/1/20.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */

public class RequestStoreInfoParam {

    //商户Id
    public String companyId;
    //仓库主键
    public int storeId;
    //分类主键
    public int catalogId;
    //品牌主键
    public int brandId;
    //商品最小库存数
    public int minNum;
    //商品最大库存数
    public int maxNum;
    //是否过滤无库存
    public int noNumSatus;

    public HashMap<String, String> toParam() {
        HashMap<String, String> params = new HashMap<>();
        if (!TextUtils.isEmpty(companyId)) {
            params.put("companyId", companyId);
        }
        if (storeId != -1) {
            params.put("storeId", String.valueOf(storeId));
        }
        if (catalogId != -1) {
            params.put("catalogId", String.valueOf(catalogId));
        }
        if (brandId != -1) {
            params.put("brandId", String.valueOf(brandId));
        }
        if (minNum != -1) {
            params.put("minNum", String.valueOf(minNum));
        }
        if (maxNum != -1) {
            params.put("maxNum", String.valueOf(maxNum));
        }
        if (noNumSatus != -1) {
            params.put("noNumSatus", String.valueOf(noNumSatus));
        }
        return params;
    }
}
