package tech.boshu.changjiangshidai.bean.mode;

import java.util.List;

import tech.boshu.changjiangshidai.libs.bean.ResponseBase;
import tech.boshu.changjiangshidai.ui.activity.common.search.CustomerListEntity;

/**
 * Created by Administrator on 2016/1/28.
 */
public class ResponseLossInfor extends ResponseBase {
    public Data data;
    public class Data{
        public List<Store> stroeList;
        public List<CustomerListEntity> customerList;
        public List<Account> setAccountList;
        public PrOrder profitLoss;
        public List<OrderDetailDto> plDetailList;
    }
}
