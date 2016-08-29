package tech.boshu.changjiangshidai.bean.mode;

import java.util.List;

import tech.boshu.changjiangshidai.libs.bean.ResponseBase;
import tech.boshu.changjiangshidai.ui.activity.common.search.CustomerListEntity;

/**
 * Created by Administrator on 2016/1/15.
 */
public class ResponseSaleOrderInfor extends ResponseBase {
    public Data data;

    public class Data {
        public List<CustomerListEntity> customerList;
        public PrOrder order;
        public List<Account> setAccountList;
        public List<Store> stroeList;
        public List<DeliveryMode> sendTypeList;
        public List<OrderDetailDto> orderDetailDtoList;
    }

}
