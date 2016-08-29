package tech.boshu.changjiangshidai.bean.mode;

import java.util.List;

import tech.boshu.changjiangshidai.libs.bean.ResponseBase;

/**
 * Created by lx02 on 2016/1/25.
 */
public class CreatPurchaseReturnedBean extends ResponseBase {

    public Data data;

    public class Data {
        /*供应商集合*/
        public List<Provider> providerList;
        /*仓库集合*/
        public List<Store> stroeList;
        /*结算账户集合*/
        public List<SettleAccount> setAccountList;
        /*采购单*/
        public PrOrder order;
        /*采购单明细*/
       // public List<OrderDetail> orderDetailDtoList;
        /*prDetailList采购单明细主键*/


    }
}
