package tech.boshu.changjiangshidai.ui.activity.stockinfo.flowsearch;

import tech.boshu.changjiangshidai.bean.result.ResponseSearchCondition;
import tech.boshu.changjiangshidai.libs.activity.IBaseInteractor;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)IStockFlowSearchInteractor.java
 */


public interface IStockFlowSearchInteractor extends IBaseInteractor {

    void querySearchCondition(ResponseCallback<ResponseSearchCondition> callback);

}