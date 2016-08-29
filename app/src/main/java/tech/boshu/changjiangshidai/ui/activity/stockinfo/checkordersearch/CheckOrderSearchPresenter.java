package tech.boshu.changjiangshidai.ui.activity.stockinfo.checkordersearch;

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
 * @(#)CheckOrderSearchPresenter.java
 */


public class CheckOrderSearchPresenter extends BasePresenter implements ICheckOrderSearchPresenter {
    public static int TIME_START = 0;
    public static int TIME_END = 1;

    private ICheckOrderSearchView icheckOrderSearchView;
    private ICheckOrderSearchInteractor icheckOrderSearchInteractor;

    private List<Store> stores;
    private int storeId = -1;
    private String orderId = null;
    private Calendar startCalendar = Calendar.getInstance();
    private Calendar endCalendar = Calendar.getInstance();
    private String startDate = null;
    private String endDate = null;

    public CheckOrderSearchPresenter(ICheckOrderSearchView icheckOrderSearchView) {
        super(icheckOrderSearchView);
        this.icheckOrderSearchView = icheckOrderSearchView;
        this.icheckOrderSearchInteractor = new CheckOrderSearchInteractorImpl();
    }

    @Override
    public void setSearchData() {
        icheckOrderSearchView.showDialog();
        icheckOrderSearchInteractor.querySearchCondition(
                new ResponseCallback<ResponseSearchCondition>() {

                    @Override
                    public void onRequestSuccess(ResponseSearchCondition result) {
                        icheckOrderSearchView.hideDialog();
                        stores = result.data.stroeList;
                    }

                    @Override
                    public void onReuquestFailed(ErrorBase error) {
                        icheckOrderSearchView.hideDialog();
                        icheckOrderSearchView.showToast(error.message);
                    }
                });
    }

    @Override
    public void clearOption() {
        storeId = -1;
        orderId = null;
        startDate = null;
        endDate = null;
        icheckOrderSearchView.clearOption();
    }

    @Override
    public void createStoreDialog() {
        if (stores == null || stores.size() == 0) {
            return;
        }
        icheckOrderSearchView.createStoreDialog(stores);
    }

    @Override
    public void choiceDialog(int position) {
        storeId = stores.get(position).id;
        icheckOrderSearchView.setStoreName(stores.get(position).name);
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
        icheckOrderSearchView.createTimeDialog(type, year, month, day);
    }

    @Override
    public void choiceTimeDialog(int type, int year, int month, int day) {
        if (type == TIME_START) {
            startCalendar.set(year, month, day);
            startDate = DateUtils.getServerDate(startCalendar.getTime());
            icheckOrderSearchView.setStartDate(startDate);
        } else if (type == TIME_END) {
            endCalendar.set(year, month, day);
            endDate = DateUtils.getServerDate(endCalendar.getTime());
            icheckOrderSearchView.setEndDate(endDate);
        }
    }

    @Override
    public void search() {
        if (startCalendar != null &&
                endDate != null &&
                startCalendar.after(endCalendar)) {
            icheckOrderSearchView.showToast("开始日期不能小于结束日期");
            return;
        }
        icheckOrderSearchView.naviToCheckHistory(
                storeId,
                orderId,
                startDate,
                endDate);
    }

}