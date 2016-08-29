package tech.boshu.changjiangshidai.ui.activity.stockinfo.addpurchasereturnorder;

import java.util.List;

import tech.boshu.changjiangshidai.constants.ParameterConstants;
import tech.boshu.changjiangshidai.libs.activity.BasePresenter;


/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)AddPurchaseOrderPresenter.java
 */


public class AddPurchaseOrderPresenter extends BasePresenter implements IAddPurchaseOrderPresenter, AddPurchaseOrderInterface {

    private IAddPurchaseOrderView iaddPurchaseOrderView;
    private IAddPurchaseOrderInteractor iaddPurchaseOrderInteractor;


    public AddPurchaseOrderPresenter(IAddPurchaseOrderView iaddPurchaseOrderView) {
        super(iaddPurchaseOrderView, new AddPurchaseOrderInteractorImpl());
        this.iaddPurchaseOrderView = iaddPurchaseOrderView;
        this.iaddPurchaseOrderInteractor = (AddPurchaseOrderInteractorImpl) iBaseInteractor;
    }


    public void selectSupplier() {
        iaddPurchaseOrderView.selectSupplier(iaddPurchaseOrderInteractor.getSupplierList());
    }

    public void selectStock() {
        iaddPurchaseOrderView.selectStock(iaddPurchaseOrderInteractor.getStockList());
    }

    public void selectPayWay() {
        iaddPurchaseOrderView.selectPayWay(iaddPurchaseOrderInteractor.getSetAccountList());
    }

    public void scanProduct() {
        iaddPurchaseOrderView.scanProduct();
    }

    public void addProduct() {
        iaddPurchaseOrderView.addProduct();
    }

    public void selectDate() {
        iaddPurchaseOrderView.selectDate();
    }

    @Override
    public void setParam(AddPurchaseOrderParam param) {
        iaddPurchaseOrderInteractor.setParam(param);
    }


    @Override
    public void queryPurchaseOrderInfor(AddPurchaseOrderParam param) {
        iaddPurchaseOrderView.showDialog();
        iaddPurchaseOrderInteractor.queryPurchaseOrderIfor(param, this);
    }

    @Override
    public void add() {
        iaddPurchaseOrderView.showDialog();
        iaddPurchaseOrderInteractor.add(this);
    }

    @Override
    public void cancel() {
        iaddPurchaseOrderView.showDialog();
        iaddPurchaseOrderInteractor.cancel(this);
    }

    @Override
    public void purchase() {
        iaddPurchaseOrderView.showDialog();
        iaddPurchaseOrderInteractor.purchase(this);
    }

    @Override
    public void setPrepareData(List<ResponseProviderListEntity> list1, List<ReponseStroeListEntity> list2, List<ReponseSetAccountListEntity> list3) {
        iaddPurchaseOrderInteractor.setPrepareData(list1,list2,list3);
    }

    @Override
    public void onQuerySuccess() {
        iaddPurchaseOrderView.hideDialog();
        AddPurchaseOrderParam addPurchaseOrderParam = iaddPurchaseOrderInteractor.getParam();
        if (addPurchaseOrderParam.action.equals(ParameterConstants.ACTION_TYPE_EDIT)) {
            iaddPurchaseOrderView.setParam(addPurchaseOrderParam);
            iaddPurchaseOrderView.setView();
        }
    }

    @Override
    public void onAddSuccess() {
        iaddPurchaseOrderView.hideDialog();
        if (iaddPurchaseOrderInteractor.getParam().action.equals(ParameterConstants.ACTION_TYPE_ADD)) {
            iaddPurchaseOrderView.showToast("新建采购单成功!");
        } else {
            iaddPurchaseOrderView.showToast("修改采购单成功!");
        }
        iaddPurchaseOrderView.back(null);
    }

    @Override
    public void onPurchaseSuccess() {
        iaddPurchaseOrderView.hideDialog();
        iaddPurchaseOrderView.showToast("采购成功!");
        iaddPurchaseOrderView.back(null);
    }

    @Override
    public void onCancelSuccess() {
        iaddPurchaseOrderView.hideDialog();
        iaddPurchaseOrderView.showToast("采购单已撤销!");
        iaddPurchaseOrderView.back(null);
    }

    @Override
    public void onFail(String message) {
        iaddPurchaseOrderView.hideDialog();
        iaddPurchaseOrderView.showToast(message);
    }
}