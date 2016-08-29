package tech.boshu.changjiangshidai.ui.activity.stockinfo.productstock;

import tech.boshu.changjiangshidai.libs.activity.BasePresenter;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)ProductStockPresenter.java
 */


public class ProductStockPresenter extends BasePresenter implements IProductStockPresenter {
    private IProductStockView iproductStockView;
    private IProductStockInteractor iproductStockInteractor;

    private int productId = -1;
    private int zeroNumStatus = 1;
    private ResponseProductStockInfo.ProductEntity productEntity;

    public ProductStockPresenter(IProductStockView iproductStockView) {
        super(iproductStockView);
        this.iproductStockView = iproductStockView;
        this.iproductStockInteractor = new ProductStockInteractorImpl();
    }

    @Override
    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public void setZeroNumStatus(int zeroNumStatus) {
        this.zeroNumStatus = zeroNumStatus;
    }

    @Override
    public void setProductStock() {
        iproductStockView.showDialog();
        iproductStockInteractor.queryProductStock(
                productId,
                zeroNumStatus, new ResponseCallback<ResponseProductStockInfo>() {
                    @Override
                    public void onRequestSuccess(ResponseProductStockInfo result) {
                        iproductStockView.hideDialog();
                        productEntity = result.data.product;
                        iproductStockView.setProductStockInfo(
                                productEntity.name,
                                productEntity.purchasePrice,
                                productEntity.wholesalePrice,
                                productEntity.retailPrice,
                                productEntity.brand,
                                productEntity.catalog,
                                productEntity.mergerNum);
                    }

                    @Override
                    public void onReuquestFailed(ErrorBase error) {
                        iproductStockView.hideDialog();
                        iproductStockView.showToast(error.message);
                    }
                });
    }
}