package tech.boshu.changjiangshidai.bean.result;

import java.util.List;

import tech.boshu.changjiangshidai.bean.mode.StoreBill;

/**
 * Created by zoulinlin on 16/1/8.
 */
public class StoreFlowResult {
    /*商品主键*/
    String stroeName;
    /*商品名称*/
    String productName;
    /*开始时间*/
    String beginTime;
    /*结束时间*/
    String endTime;
    List<StoreBill> storeBillList;
}
