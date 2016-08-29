package tech.boshu.changjiangshidai.ui.activity.basic.product.productmanage;

import tech.boshu.changjiangshidai.bean.BaseRequestParam;
import tech.boshu.changjiangshidai.libs.activity.IBaseInteractor;
import tech.boshu.changjiangshidai.libs.bean.Pagination;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)IPurchaseHistoryInteractor.java
 */


public interface IProductInteractor<T extends BaseRequestParam> extends IBaseInteractor {

    void filterByDefault();

    void filterByCount();

    void filterByDraft();

    void filterByCancel();

    void setPagination(Pagination param);

    void setOnSueccessCallback(CommonResponseCallback callback);

    void setParams(T params);

}