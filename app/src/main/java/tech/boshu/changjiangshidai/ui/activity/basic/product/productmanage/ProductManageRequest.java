package tech.boshu.changjiangshidai.ui.activity.basic.product.productmanage;

import com.android.volley.Request;

import tech.boshu.changjiangshidai.bean.api.ServerRequest;
import tech.boshu.changjiangshidai.libs.net.GsonRequest;
import tech.boshu.changjiangshidai.libs.net.HttpUtils;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.utils.SubUtils;

/**
 * Created by allipper on 16/1/28.
 */
public class ProductManageRequest extends ServerRequest {

    /**
     * @param param    请求参数封装类
     * @param callback
     */
    //1.商品历史列表
    public static void query(
            ProductRequestParam param,
            ResponseCallback<ResponseProductManage> callback) {

        GsonRequest<ResponseProductManage> request = new GsonRequest<>(Request.Method.POST,
                apiHost + "/pd_productPage.action", ResponseProductManage.class, null, SubUtils.toParam(param), callback, callback);
        HttpUtils.getInstance().request(ProductManageActivity.class.getSimpleName(), request);
    }
}
