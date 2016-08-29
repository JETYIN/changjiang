package tech.boshu.changjiangshidai.ui.activity.stockinfo.flowsearch;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)IStockFlowSearchPresenter.java
 */


public interface IStockFlowSearchPresenter {

    void setSearchData();

    void createStoreDialog();

    void choiceDialog(int position);

    void createTimeDialog(int type);

    void choiceTimeDialog(int type, int year, int month, int day);

    void search();

}