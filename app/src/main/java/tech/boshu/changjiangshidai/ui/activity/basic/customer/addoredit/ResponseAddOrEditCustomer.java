package tech.boshu.changjiangshidai.ui.activity.basic.customer.addoredit;

import java.util.List;

import tech.boshu.changjiangshidai.libs.bean.ResponseBase;
import tech.boshu.changjiangshidai.ui.activity.basic.customer.manage.CustomerBean;

/**
 * Created by allipper on 16/1/26.
 */
public class ResponseAddOrEditCustomer extends ResponseBase {

 
    public DataEntity data;

    public static class DataEntity {
        /**
         * catalogStatus : 1
         * companyId : 2222
         * id : 1
         * name : 重点客户
         * pid : 0
         */

        public List<CustomerTypeEntity> customerType;

        public CustomerBean customer;

        public static class CustomerTypeEntity {
            public String catalogStatus;
            public String companyId;
            public String id;
            public String name;
            public String pid;

        }
    }
}
