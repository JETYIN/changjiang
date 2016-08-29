package tech.boshu.changjiangshidai.ui.activity.basic.warehouse.manage;

import java.util.List;

import tech.boshu.changjiangshidai.libs.bean.ResponseBase;

/**
 * Created by allipper on 16/1/26.
 */
public class ResponseCustomer extends ResponseBase {

    /**
     * pageCount : 1
     * pageNo : 1
     * pageSize : 200
     * storeList : [{"companyId":"2222","id":1,"leadingAccount":0,"memo":"无法","name":"少时诵诗书999","orders":11,"status":0,"updateAccount":2,"updateTime":1453828819000},{"companyId":"2222","id":2,"leadingAccount":1,"name":"8号仓库","orders":12,"status":1},{"companyId":"2222","createAccount":1,"createTime":1453196864000,"id":3,"name":"1号仓库","updateAccount":1,"updateTime":1453344839000},{"companyId":"2222","createAccount":1,"createTime":1453774244000,"id":4,"leadingAccount":0,"memo":"无法","name":"少时诵诗书888","orders":11,"status":0,"updateAccount":2,"updateTime":1453828830000},{"companyId":"2222","createAccount":1,"createTime":1453774380000,"id":5,"leadingAccount":0,"memo":"无法","name":"少时诵诗书r000","orders":11,"status":0,"updateAccount":2,"updateTime":1453815473000},{"companyId":"2222","createAccount":2,"createTime":1453815853000,"id":6,"leadingAccount":2,"memo":"1000","name":"1000","orders":1,"status":1},{"companyId":"2222","createAccount":2,"createTime":1453828849000,"id":7,"leadingAccount":2,"memo":"Kjkjkj","name":"jjkjkjk","orders":7,"status":1},{"companyId":"2222","createAccount":2,"createTime":1453881350000,"id":8,"leadingAccount":1,"memo":"56","name":"56","orders":56,"status":1},{"companyId":"2222","createAccount":2,"createTime":1453891488000,"id":9,"leadingAccount":1,"memo":"","name":"212","status":1}]
     * totalCount : 9
     */

    public DataEntity data;

    public static class DataEntity {
        public int pageCount;
        public int pageNo;
        public int pageSize;
        public int totalCount;
        /**
         * companyId : 2222
         * id : 1
         * leadingAccount : 0
         * memo : 无法
         * name : 少时诵诗书999
         * orders : 11
         * status : 0
         * updateAccount : 2
         * updateTime : 1453828819000
         */

        public List<CustomerBean> storeList;


    }
}
