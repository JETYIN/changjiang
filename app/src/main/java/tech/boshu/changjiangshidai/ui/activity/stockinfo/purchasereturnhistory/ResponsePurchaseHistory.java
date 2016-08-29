package tech.boshu.changjiangshidai.ui.activity.stockinfo.purchasereturnhistory;

import java.util.List;

import tech.boshu.changjiangshidai.libs.bean.ResponseBase;

/**
 * 查询返回结果
 * Created by allipper on 16/1/18.
 */
public class ResponsePurchaseHistory extends ResponseBase {

    /**
     * pageCount : 1
     * prOrderList : [{"checkStatus":0,"companyId":"2222","createAccount":0,"createTime":"2015-12-28 17:35:20","discount":0,"id":"PR1512280001","memo":"","otherFee":0,"payEndTime":null,"payMoney":0,"provider":"XX供应商","providerId":1,"sendType":0,"setAccount":"","settlement":1,"status":0,"store":"","storeId":1,"totalMoney":0,"totalNum":0,"type":1,"updateAccount":0,"updateTime":null}]
     * totalCount : 1
     * pageNo : 1
     * pageSize : 15
     */

    public DataEntity data;


    public static class DataEntity {
        public String pageCount;
        public String totalCount;
        public String pageNo;
        public String pageSize;
        public List<PurchaseHistoryPrOrder> prOrderReturnList;
    }
}
