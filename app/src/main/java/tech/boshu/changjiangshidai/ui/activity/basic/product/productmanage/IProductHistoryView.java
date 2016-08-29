package tech.boshu.changjiangshidai.ui.activity.basic.product.productmanage;


import java.util.List;

import tech.boshu.changjiangshidai.libs.activity.IBaseView;
import tech.boshu.changjiangshidai.libs.bean.Pagination;

/**
 * @(#)IPurchaseHistoryView.java
 *
 *
 * @author allipper
 * @version 1.00 2016/1/6
 */
public interface IProductHistoryView extends IBaseView{
    


    void bindDatas(List<ResponseProductManage.DataEntity.PdListEntity> datas);

    void showNoDatas();

    Pagination getPagination();

    void setPagination(Pagination param);

    void stopRefresh();
}