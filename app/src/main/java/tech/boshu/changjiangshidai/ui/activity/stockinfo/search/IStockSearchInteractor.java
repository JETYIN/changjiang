package tech.boshu.changjiangshidai.ui.activity.stockinfo.search;

import tech.boshu.changjiangshidai.bean.result.ResponseSearchCondition;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)IStockSearchInteractor.java
 */


public interface IStockSearchInteractor {

    void querySearchCondition(ResponseCallback<ResponseSearchCondition> callback);
}