package tech.boshu.changjiangshidai.ui.activity.basic.supplier.manage;

import java.util.List;

import tech.boshu.changjiangshidai.libs.bean.ResponseBase;

/**
 * Created by allipper on 16/1/26.
 */
public class ResponseCustomer extends ResponseBase {

    /**
     * customerList : [{"arrears":2222,"catalogName":"重点客户","companyId":"2222","id":1,"name":"demaxiya","phone":"123123231312321","province":"北京市","status":1,"totalArrears":6781,"type":1},{"arrears":0,"catalogName":"重点客户","companyId":"2222","createAccount":1,"createTime":1453368487000,"id":2,"name":"11号客户","totalArrears":0,"type":1,"updateAccount":1,"updateTime":1453369234000},{"address":"wwwwwww","area":"sssssss","arrears":0,"bank":"wwww","bankAccount":"rryuii","bankNo":"www","catalogName":"重点客户","city":"ssss","companyId":"2222","createAccount":1,"createTime":1453734411000,"discount":1,"email":"35345756@qqq.com","fix":"3367889900","id":3,"memo":"wwwwwww","mobile":"4689-","name":"222","orders":23,"phone":"124567","province":"sssss","status":1,"totalArrears":0,"type":1},{"address":"wwwwwww","area":"sssssss","arrears":0,"bank":"wwww","bankAccount":"rryuii","bankNo":"www","catalogName":"重点客户","city":"ssss","companyId":"2222","createAccount":1,"createTime":1453734693000,"discount":1,"email":"35345756@qqq.com","fix":"3367889900","id":4,"memo":"wwwwwww","mobile":"4689-","name":"222","orders":23,"phone":"124567","province":"sssss","status":1,"totalArrears":0,"type":1},{"address":"d街道","area":"c区","arrears":0,"bank":"银行","bankAccount":"银行a","bankNo":"22557788","catalogName":"普通客户","city":"b市","companyId":"2222","createAccount":2222,"createTime":1454040716000,"discount":0.55,"email":"123@33.com","fix":"285573","id":5,"memo":"here","mobile":"1888888","name":"name223","orders":33,"phone":"010222","province":"a省","status":1,"totalArrears":0,"type":2,"updateAccount":2222,"updateTime":1454049079000},{"address":"d街道","area":"c区","arrears":0,"bank":"银行","bankAccount":"银行a","bankNo":"22557788","catalogName":"普通客户","city":"b市","companyId":"2222","createAccount":2222,"createTime":1454045836000,"discount":0.55,"email":"123@33.com","fix":"","id":6,"memo":"here","mobile":"1888888","name":"name228","orders":33,"phone":"010222","province":"a省","status":0,"totalArrears":0,"type":2,"updateAccount":2222,"updateTime":1454052620000}]
     * pageCount : 1
     * pageNo : 1
     * pageSize : 200
     * totalCount : 6
     */

    public DataEntity data;

    public static class DataEntity {
        public int pageCount;
        public int pageNo;
        public int pageSize;
        public int totalCount;

        public List<CustomerBean> providerList;

    }
}
