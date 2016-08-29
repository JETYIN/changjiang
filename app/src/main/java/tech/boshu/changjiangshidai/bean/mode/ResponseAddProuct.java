package tech.boshu.changjiangshidai.bean.mode;

import java.util.List;

import tech.boshu.changjiangshidai.libs.bean.ResponseBase;

/**
 * Created by Administrator on 2016/1/19.
 */
public class ResponseAddProuct extends ResponseBase {
    public Data data;

    public class Data {
        public List<Product> pdList;
        public int pageCount;
        public int totalCount;
        public int pageNo;
        public int pageSize;

    }
}
