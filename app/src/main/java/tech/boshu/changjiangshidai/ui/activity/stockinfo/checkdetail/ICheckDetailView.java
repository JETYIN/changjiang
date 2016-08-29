package tech.boshu.changjiangshidai.ui.activity.stockinfo.checkdetail;


import java.util.List;

import tech.boshu.changjiangshidai.bean.result.CheckDetailDto;
import tech.boshu.changjiangshidai.bean.result.CheckOrder;
import tech.boshu.changjiangshidai.libs.activity.IBaseView;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)ICheckDetailView.java
 */
public interface ICheckDetailView extends IBaseView {
    void setCheckDetailInfo(CheckOrder checkOrder);

    void setCheckDetailList(List<CheckDetailDto> items);
}