package tech.boshu.changjiangshidai.bean.mode;

import java.util.List;

import tech.boshu.changjiangshidai.libs.bean.ResponseBase;

/**
 * Created by Administrator on 2016/1/14.
 */
public class ResponseSaleHistory extends ResponseBase {
    public Data data;

    public class Data {
        public int pageCount;
        public int totalCount;
        public int pageNo;
        public int pageSize;
        public List<PrOrder> orderList;
    }
}