package tech.boshu.changjiangshidai.ui.activity.business.saleschart;

import com.github.mikephil.charting.data.LineData;

import tech.boshu.changjiangshidai.libs.activity.IBaseInteractor;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)ISalesChartInteractor.java
 */


public interface ISalesChartInteractor extends IBaseInteractor {


    void more();

    void chooseDate();

    LineData getLineDate(int count, float range);

}