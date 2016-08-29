package tech.boshu.changjiangshidai.libs.net;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import tech.boshu.changjiangshidai.libs.bean.ResponseBase;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;
import tech.boshu.changjiangshidai.libs.utils.Constant;
import tech.boshu.changjiangshidai.libs.utils.Logger;


/**
 * Created by zoulinlin123 on 15/7/15.
 */
public abstract class ResponseCallback<E> implements Response.ErrorListener, Response.Listener<E> {
    private static final String TAG = ResponseCallback.class.getSimpleName();

    public ResponseCallback() {
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        onReuquestFailed(new ErrorBase("-1", ErrorHelper.getMessage(volleyError)));
        if (Constant.IS_DEBUG_MODE) {
            Logger.d(TAG + " ERROR", ErrorHelper.getMessage(volleyError));
        }
    }

    @Override
    public void onResponse(E result) {
        if (result == null) {
            onReuquestFailed(new ErrorBase("-1", "没有数据返回"));
            return;
        }
        if (result instanceof ResponseBase) {
            ResponseBase responseBase = (ResponseBase) result;
            if ("SUCCESS".equals(responseBase.status)) {
                onRequestSuccess(result);
            } else {
                onReuquestFailed(new ErrorBase(responseBase.status, responseBase.msg));
                if (Constant.IS_DEBUG_MODE) {
                    Logger.d(TAG + " ERROR", responseBase.msg == null ? "NULL" :
                            responseBase.msg);
                }
            }
        } else {
            onRequestSuccess(result);
        }
    }

    public abstract void onRequestSuccess(E result);

    public abstract void onReuquestFailed(ErrorBase error);
}
