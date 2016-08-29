package tech.boshu.changjiangshidai.ui.activity.stockinfo.addpurchaseorder;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.bean.mode.OrderDetailDto;
import tech.boshu.changjiangshidai.bean.mode.PrOrder;
import tech.boshu.changjiangshidai.libs.bean.ResponseBase;

/**
 * Created by allipper on 16/1/24.
 */
public class ResponseAddPurchaseQureyBean extends ResponseBase {

    /**
     * order : {}
     * providerList : [{"companyId":"2222","id":1,"leadingPerson":"是","name":"XX供应商","status":1,"totalArrears":8245.2},{"companyId":"2222","id":2,"leadingPerson":"SSS","name":"AAAA","status":1,"totalArrears":333333}]
     * setAccountList : [{"id":1,"name":"平安卡","status":1}]
     * stroeList : [{"companyId":"2222","id":1,"leadingAccount":1,"name":"4号仓库","orders":11,"status":1},{"companyId":"2222","id":2,"leadingAccount":1,"name":"8号仓库","orders":12,"status":1}]
     */

    public DataEntity data;

    public static class DataEntity {

        public PrOrder order;
        /**
         * companyId : 2222
         * id : 1
         * leadingPerson : 是
         * name : XX供应商
         * status : 1
         * totalArrears : 8245.2
         */

        public List<ResponseProviderListEntity> providerList;
        /**
         * id : 1
         * name : 平安卡
         * status : 1
         */

        public List<ReponseSetAccountListEntity> setAccountList;
        /**
         * companyId : 2222
         * id : 1
         * leadingAccount : 1
         * name : 4号仓库
         * orders : 11
         * status : 1
         */

        public List<ReponseStroeListEntity> stroeList;

        public ArrayList<OrderDetailDto> orderDetailDtoList;

    }
}
