package tech.boshu.changjiangshidai.ui.activity.stockinfo.addpurchaseorder;

import java.util.List;

import tech.boshu.changjiangshidai.bean.result.PurchaseResult;
import tech.boshu.changjiangshidai.constants.ParameterConstants;
import tech.boshu.changjiangshidai.libs.activity.BaseInteractorImpl;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)AddPurchaseOrderInteractorImpl.java
 */


public class AddPurchaseOrderInteractorImpl extends BaseInteractorImpl implements IAddPurchaseOrderInteractor {

    private AddPurchaseOrderParam addPurchaseOrderParam;

    public List<ResponseProviderListEntity> providerList;

    public List<ReponseSetAccountListEntity> setAccountList;

    public List<ReponseStroeListEntity> stroeList;

    public AddPurchaseOrderInteractorImpl() {
    }

    public void setPrepareData(List<ResponseProviderListEntity> list1,List<ReponseStroeListEntity> list2,
                               List<ReponseSetAccountListEntity> list3){
        providerList = list1;
        stroeList = list2;
        setAccountList = list3;
    }


    @Override
    public List<ResponseProviderListEntity> getSupplierList() {
        return providerList;
    }

    @Override
    public List<ReponseStroeListEntity> getStockList() {
        return stroeList;
    }

    @Override
    public List<ReponseSetAccountListEntity> getSetAccountList() {
        return setAccountList;
    }

    @Override
    public void setParam(AddPurchaseOrderParam param) {
        this.addPurchaseOrderParam = param;
    }

    @Override
    public AddPurchaseOrderParam getParam() {
        return addPurchaseOrderParam;
    }

    @Override
    public void queryPurchaseOrderIfor(final AddPurchaseOrderParam param, final AddPurchaseOrderInterface addPurchaseOrderInterface) {
        AddPurchaseOrderRequest.queryPurchaseOrderInfor(param, new ResponseCallback<ResponseAddPurchaseQureyBean>() {
            @Override
            public void onRequestSuccess(ResponseAddPurchaseQureyBean result) {
                providerList = result.data.providerList;
                stroeList = result.data.stroeList;
                setAccountList = result.data.setAccountList;
                if(addPurchaseOrderParam.action.equals(ParameterConstants.ACTION_TYPE_EDIT)){
                    addPurchaseOrderParam.order = result.data.order;
                    addPurchaseOrderParam.products = result.data.orderDetailDtoList;
                }
                addPurchaseOrderInterface.onQuerySuccess();
            }

            @Override
            public void onReuquestFailed(ErrorBase error) {
                addPurchaseOrderInterface.onFail(error.message);
            }
        });
    }

    @Override
    public void add(final AddPurchaseOrderInterface addPurchaseOrderInterface) {
        AddPurchaseOrderRequest.createOrEditPurchase(addPurchaseOrderParam, new ResponseCallback<PurchaseResult>() {
            @Override
            public void onRequestSuccess(PurchaseResult result) {
                addPurchaseOrderInterface.onAddSuccess();
            }

            @Override
            public void onReuquestFailed(ErrorBase error) {
                addPurchaseOrderInterface.onFail(error.message);
            }
        });
    }

    @Override
    public void purchase(final AddPurchaseOrderInterface addPurchaseOrderInterface) {
        AddPurchaseOrderRequest.purchase(addPurchaseOrderParam, new ResponseCallback<PurchaseResult>() {
            @Override
            public void onRequestSuccess(PurchaseResult result) {
                addPurchaseOrderInterface.onPurchaseSuccess();
            }

            @Override
            public void onReuquestFailed(ErrorBase error) {
                addPurchaseOrderInterface.onFail(error.message);
            }
        });
    }

    @Override
    public void cancel(final AddPurchaseOrderInterface addPurchaseOrderInterface) {
        AddPurchaseOrderRequest.cancel(addPurchaseOrderParam, new ResponseCallback<PurchaseResult>() {
            @Override
            public void onRequestSuccess(PurchaseResult result) {
                addPurchaseOrderInterface.onCancelSuccess();
            }

            @Override
            public void onReuquestFailed(ErrorBase error) {
                addPurchaseOrderInterface.onFail(error.message);
            }
        });
    }

}