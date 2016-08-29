package tech.boshu.changjiangshidai.ui.activity.basic.product.productmanage;

import tech.boshu.changjiangshidai.libs.bean.ResponseBase;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;

/**
 * Created by allipper on 16/1/18.
 */
public interface CommonResponseCallback<T extends ResponseBase> {

    void onRequestSuccess(T result);

    void onFailed(ErrorBase error);
}
