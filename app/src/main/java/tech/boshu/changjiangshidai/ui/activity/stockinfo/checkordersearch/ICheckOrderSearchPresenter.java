package tech.boshu.changjiangshidai.ui.activity.stockinfo.checkordersearch;

import tech.boshu.changjiangshidai.libs.activity.IBasePresenter;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)ICheckOrderSearchPresenter.java
 */


public interface ICheckOrderSearchPresenter extends IBasePresenter {
    void setSearchData();

    void clearOption();

    void createStoreDialog();

    void choiceDialog(int position);

    void createTimeDialog(int type);

    void choiceTimeDialog(int type, int year, int month, int day);

    void search();
}