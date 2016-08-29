package tech.boshu.changjiangshidai.ui.activity.common.search;

import com.android.volley.Request;

import tech.boshu.changjiangshidai.bean.api.ServerRequest;
import tech.boshu.changjiangshidai.libs.net.GsonRequest;
import tech.boshu.changjiangshidai.libs.net.HttpUtils;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;

/**
 * Created by allipper on 16/1/23.
 */
public class CommonSearchRequest extends ServerRequest {

    public static void getSearchFilters(ResponseCallback<ResponseCommonSearch> callback) {
        GsonRequest<ResponseCommonSearch> request = new GsonRequest<>(Request.Method.GET,
                apiHost + "/searchPage.action", ResponseCommonSearch.class, null, null, callback, callback);
        HttpUtils.getInstance().request(ChooseDialogActivity.class.getSimpleName(), request);
    }

}
