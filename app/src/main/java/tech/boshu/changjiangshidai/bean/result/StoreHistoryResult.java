package tech.boshu.changjiangshidai.bean.result;

import java.util.List;

import tech.boshu.changjiangshidai.bean.mode.PrOrder;
import tech.boshu.changjiangshidai.bean.mode.PrOrderReturn;

/**
 * Created by zoulinlin on 16/1/8.
 */
public class StoreHistoryResult {
    /*总页数*/
    int pageCount;
    /*总记录数*/
    int totalCount;
    /*第几页*/
    int pageNo;
    /*每页记录数*/
    int pageSize;
    List<PrOrder> prOrderList;

    List<PrOrderReturn> prOrderReturnList;

}
