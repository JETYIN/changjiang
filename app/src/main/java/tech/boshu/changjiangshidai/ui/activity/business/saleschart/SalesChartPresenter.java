package tech.boshu.changjiangshidai.ui.activity.business.saleschart;

import tech.boshu.changjiangshidai.libs.activity.BasePresenter;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)SalesChartPresenter.java
 */


public class SalesChartPresenter extends BasePresenter implements ISalesChartPresenter {

    private ISalesChartView isalesChartView;
    private ISalesChartInteractor isalesChartInteractor;


    public SalesChartPresenter(ISalesChartView isalesChartView) {
        super(isalesChartView);
        this.isalesChartView = isalesChartView;
        this.isalesChartInteractor = new SalesChartInteractorImpl();
    }




    public void more() {
        isalesChartInteractor.more();
    }

    public void chooseDate() {
        isalesChartInteractor.chooseDate();
    }
}