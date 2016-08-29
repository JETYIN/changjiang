package tech.boshu.changjiangshidai.ui.activity.common.search;

import java.util.List;

import tech.boshu.changjiangshidai.libs.bean.ResponseBase;

/**
 * Created by allipper on 16/1/23.
 */
public class ResponseCommonSearch extends ResponseBase {

    public DataEntity data;


    public static class DataEntity {
        /**
         * id : 1
         * name : 李维斯
         * status : 1
         */

        public List<BrandTypeEntity> brandType;
        /**
         * id : 1
         * name : 222
         * type : 111
         */

        public List<CatalogTypeEntity> catalogType;
        /**
         * companyId : 2222
         * id : 1
         * name : demaxiya
         * phone : 123123231312321
         * province : 北京市
         * status : 1
         * totalArrears : 4567
         * type : 1
         */

        public List<CustomerListEntity> customerList;
        /**
         * companyId : 2222
         * id : 1
         * leadingPerson : 是
         * name : XX供应商
         * status : 1
         * totalArrears : 8245.2
         */

        public List<ProviderListEntity> providerList;
        /**
         * companyId : 2222
         * id : 1
         * leadingAccount : 1
         * name : 4号仓库
         * orders : 11
         * status : 1
         */

        public List<StroeListEntity> stroeList;

    }
}
