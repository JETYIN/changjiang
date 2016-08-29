package tech.boshu.changjiangshidai.ui.activity.stockinfo.addpurchaseorder;

import java.util.List;

import tech.boshu.changjiangshidai.bean.mode.Account;
import tech.boshu.changjiangshidai.bean.mode.Customer;
import tech.boshu.changjiangshidai.bean.mode.DeliveryMode;
import tech.boshu.changjiangshidai.bean.mode.OrderDetailDto;
import tech.boshu.changjiangshidai.bean.mode.PrOrder;
import tech.boshu.changjiangshidai.bean.mode.Store;
import tech.boshu.changjiangshidai.libs.bean.ResponseBase;

/**
 * Created by Administrator on 2016/1/15.
 */
public class ResponsePurchaseOrderInfor extends ResponseBase {
    public Data data;

    public class Data {
        public List<Customer> customerList;
        public PrOrder order;
        public List<Account> setAccountList;
        public List<Store> stroeList;
        public List<DeliveryMode> sendTypeList;
        public List<OrderDetailDto> orderDetailDtoList;
    }

}
