package tech.boshu.changjiangshidai.ui.activity.basic.setting.companyinfo;

import java.util.HashMap;
import java.util.Map;

import tech.boshu.changjiangshidai.libs.bean.ResponseBase;

/**
 * Created by allipper on 16/1/26.
 */
public class CompanyParams extends ResponseBase {

    public String companyId = "2222";
    /**
     * company : {"address":"规划局 ","code":"3333","id":"2222","name":"1号商户","phone":"133456","wainMax":100,"wainMin":0}
     */

    public DataEntity data;

    public Map<String, String> toQueryParam() {
        final HashMap<String, String> params = new HashMap<>();
        params.put("companyId", companyId);
        return params;
    }


    public static class DataEntity {
        /**
         * address : 规划局
         * code : 3333
         * id : 2222
         * name : 1号商户
         * phone : 133456
         * wainMax : 100
         * wainMin : 0
         */

        public CompanyEntity company;


        public static class CompanyEntity {
            public String address;
            public String code;
            public String id;
            public String name;
            public String phone;
            public int wainMax;
            public int wainMin;

        }
    }
}
