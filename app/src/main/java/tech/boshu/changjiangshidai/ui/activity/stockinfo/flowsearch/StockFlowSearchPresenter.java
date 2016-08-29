package tech.boshu.changjiangshidai.ui.activity.stockinfo.flowsearch;

import java.util.Calendar;
import java.util.List;

import tech.boshu.changjiangshidai.bean.mode.Store;
import tech.boshu.changjiangshidai.bean.result.ResponseSearchCondition;
import tech.boshu.changjiangshidai.libs.activity.BasePresenter;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;
import tech.boshu.changjiangshidai.utils.DateUtils;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)StockFlowSearchPresenter.java
 */


public class StockFlowSearchPresenter extends BasePresenter implements IStockFlowSearchPresenter {
    public static int TIME_START = 0;
    public static int TIME_END = 1;

    private IStockFlowSearchView istockFlowSearchView;
    private IStockFlowSearchInteractor istockFlowSearchInteractor;

    private List<Store> stores;
    private String storeName = null;
    private int storeId = -1;
    private String productName = "测试商品";
    private int productId = 1;
    private Calendar startCalendar = Calendar.getInstance();
    private Calendar endCalendar = Calendar.getInstance();
    private String startDate = null;
    private String endDate = null;

    public StockFlowSearchPresenter(IStockFlowSearchView istockFlowSearchView) {
        super(istockFlowSearchView);
        this.istockFlowSearchView = istockFlowSearchView;
        this.istockFlowSearchInteractor = new StockFlowSearchInteractorImpl();
    }

    @Override
    public void createStoreDialog() {
        if (stores == null || stores.size() == 0) {
            return;
        }
        istockFlowSearchView.createStoreDialog(stores);
    }

    @Override
    public void setSearchData() {
        istockFlowSearchView.showDialog();
        istockFlowSearchInteractor.querySearchCondition(
                new ResponseCallback<ResponseSearchCondition>() {

                    @Override
                    public void onRequestSuccess(ResponseSearchCondition result) {
                        istockFlowSearchView.hideDialog();
                        stores = result.data.stroeList;
                    }

                    @Override
                    public void onReuquestFailed(ErrorBase error) {
                        istockFlowSearchView.hideDialog();
                        istockFlowSearchView.showToast(error.message);
                    }
                });
    }

    @Override
    public void choiceDialog(int position) {
        storeName = stores.get(position).name;
        storeId = stores.get(position).id;
        istockFlowSearchView.setStoreName(stores.get(position).name);
    }

    @Override
    public void createTimeDialog(int type) {
        int year = 0;
        int month = 0;
        int day = 0;
        if (type == TIME_START) {
            year = startCalendar.get(Calendar.YEAR);
            month = startCalendar.get(Calendar.MONTH);
            day = startCalendar.get(Calendar.DAY_OF_MONTH);
        } else if (type == TIME_END) {
            year = endCalendar.get(Calendar.YEAR);
            month = endCalendar.get(Calendar.MONTH);
            day = endCalendar.get(Calendar.DAY_OF_MONTH);
        }
        istockFlowSearchView.createTimeDialog(type, year, month, day);
    }

    @Override
    public void choiceTimeDialog(int type, int year, int month, int day) {
        if (type == TIME_START) {
            startCalendar.set(year, month, day);
            startDate = DateUtils.getServerDate(startCalendar.getTime());
            istockFlowSearchView.setStartDate(startDate);
        } else if (type == TIME_END) {
            endCalendar.set(year, month, day);
            endDate = DateUtils.getServerDate(endCalendar.getTime());
            istockFlowSearchView.setEndDate(endDate);
        }
    }

    @Override
    public void search() {
        if (storeId == -1) {
            istockFlowSearchView.showToast("请选择仓库");
            return;
        }
        if (productId == -1) {
            istockFlowSearchView.showToast("请选择商品");
            return;
        }
        if (startDate == null) {
            istockFlowSearchView.showToast("请选择开始日期");
            return;
        }
        if (endDate == null) {
            istockFlowSearchView.showToast("请选择结束日期");
            return;
        }
        if (startCalendar.after(endCalendar)) {
            istockFlowSearchView.showToast("开始日期不能小于结束日期");
            return;
        }
        istockFlowSearchView.naviToStockFlowResult(
                storeName,
                storeId,
                productName,
                productId,
                startDate,
                endDate);
    }

}