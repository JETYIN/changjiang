package tech.boshu.changjiangshidai.ui.activity.basic.product.productmanage;

import tech.boshu.changjiangshidai.libs.activity.BaseInteractorImpl;
import tech.boshu.changjiangshidai.libs.bean.Pagination;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)PurchaseHistoryInteractorImpl.java
 */


public class ProductInteractorImpl extends BaseInteractorImpl implements IProductInteractor<ProductRequestParam> {

    public ProductRequestParam productRequestParam = new ProductRequestParam();

    public CommonResponseCallback callBack;


    @Override
    public void getDatas() {
        ProductManageRequest.query(productRequestParam, new ResponseCallback<ResponseProductManage>() {
            @Override
            public void onRequestSuccess(ResponseProductManage result) {
                callBack.onRequestSuccess(result);
            }

            @Override
            public void onReuquestFailed(ErrorBase error) {
                callBack.onFailed(error);
            }
        });
    }

    public void filterByDefault() {
        productRequestParam.status = null;
    }

    public void filterByCount() {
        productRequestParam.status = "1";
    }

    public void filterByDraft() {
        productRequestParam.status = "0";
    }

    public void filterByCancel() {
        productRequestParam.status = "2";
    }

    @Override
    public void setPagination(Pagination param) {
        productRequestParam.pageCount = param.pageCount;
        productRequestParam.pageNo = param.pageNo;
        productRequestParam.pageSize = param.pageSize;
        productRequestParam.totalCount = param.totalCount;
    }

    @Override
    public void setOnSueccessCallback(CommonResponseCallback callback) {
        this.callBack = callback;
    }

    @Override
    public void setParams(ProductRequestParam params) {
        this.productRequestParam = params;
    }

}