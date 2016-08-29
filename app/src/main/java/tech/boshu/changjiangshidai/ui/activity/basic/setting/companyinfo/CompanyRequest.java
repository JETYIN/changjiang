package tech.boshu.changjiangshidai.ui.activity.basic.setting.companyinfo;

import com.android.volley.Request;

import tech.boshu.changjiangshidai.bean.api.ServerRequest;
import tech.boshu.changjiangshidai.libs.net.GsonRequest;
import tech.boshu.changjiangshidai.libs.net.HttpUtils;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;

/**
 * Created by allipper on 16/1/26.
 */
public class CompanyRequest extends ServerRequest {

    //3.公司信息查询新增/修改
    public static void query(
            CompanyParams param,
            ResponseCallback<CompanyParams> callback) {
        String url = apiHost + "/api/ac_companyEdit.action";
        GsonRequest<CompanyParams> request = new GsonRequest<>(Request.Method.POST,
                url, CompanyParams.class, null, param.toQueryParam(), callback, callback);
        HttpUtils.getInstance().request(CompanyInfoActivity.class.getSimpleName(), request);
    }

}
