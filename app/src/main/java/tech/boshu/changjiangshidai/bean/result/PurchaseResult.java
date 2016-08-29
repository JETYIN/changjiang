package tech.boshu.changjiangshidai.bean.result;

import java.util.List;

import tech.boshu.changjiangshidai.bean.mode.OrderDetail;
import tech.boshu.changjiangshidai.bean.mode.PrOrder;
import tech.boshu.changjiangshidai.bean.mode.ProDetail;
import tech.boshu.changjiangshidai.bean.mode.Provider;
import tech.boshu.changjiangshidai.bean.mode.SettleAccount;
import tech.boshu.changjiangshidai.bean.mode.Store;

/**
 * Created by zoulinlin on 16/1/8.
 */
public class PurchaseResult {
    /*供应商集合*/
    public List<Provider> providerList;
    /*仓库集合*/
    public List<Store> stroeList;
    /*结算账户集合*/
    public List<SettleAccount> setAccountList;
    /*采购单*/
    public PrOrder order;
    /*采购单明细*/
    public List<OrderDetail> orderDetailDtoList;

}
