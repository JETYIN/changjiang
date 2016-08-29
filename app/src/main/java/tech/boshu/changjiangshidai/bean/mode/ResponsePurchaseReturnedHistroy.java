package tech.boshu.changjiangshidai.bean.mode;

import java.util.List;

import tech.boshu.changjiangshidai.libs.bean.ResponseBase;

/**
 * Created by lx02 on 2016/1/22.
 */
public class ResponsePurchaseReturnedHistroy extends ResponseBase {

    public Data data;

    public class Data {
        public int pageCount;
        public int totalCount;
        public int pageNo;
        public int pageSize;
        public List<PrOrder> prOrderReturnList;
    }
}
