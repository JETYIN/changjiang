package tech.boshu.changjiangshidai.ui.activity.basic.warehouse.addoredit;

import java.util.List;

import tech.boshu.changjiangshidai.libs.bean.ResponseBase;
import tech.boshu.changjiangshidai.ui.activity.basic.account.manage.AccountBean;
import tech.boshu.changjiangshidai.ui.activity.basic.warehouse.manage.CustomerBean;

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

        public List<AccountBean> accountList;

        public CustomerBean walHouse;

    }
}
