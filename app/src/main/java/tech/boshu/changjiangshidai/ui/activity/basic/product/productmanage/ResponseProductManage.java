package tech.boshu.changjiangshidai.ui.activity.basic.product.productmanage;

import java.util.List;

import tech.boshu.changjiangshidai.libs.bean.ResponseBase;

/**
 * Created by allipper on 16/1/29.
 */
public class ResponseProductManage extends ResponseBase {

    /**
     * pageCount : 1
     * totalCount : 9
     * pdList : [{"address":"suzhou","barCode":"9787111213826","brandId":1,"catalogId":1,"companyId":"2222","createAccount":1,"createTime":"2015-12-30 10:32:00","description":"围巾","goodsNo":"1342","hit":444,"id":1,"name":"围巾","pdCatalogPrices":[{"csCatalogId":2,"id":1,"money":1,"productId":2,"status":1}],"picture":"http://www.kukeshangju.com/UserFile/ke/createImage/2_1452705018781_854.jpg","purchasePrice":15,"retailPrice":25,"sellCount":35,"sex":1,"spec":"1","status":1,"stock":25,"stockMax":999,"stockMin":0,"unitId":2,"updateAccount":1,"updateTime":"2016-01-18 20:24:35","warningStatus":0,"wholesalePrice":18},{"address":"suzhou","barCode":"9787111213827","brandId":1,"catalogId":1,"companyId":"2222","createAccount":1,"createTime":"2015-12-30 10:32:00","description":"帽子","goodsNo":"1343","hit":358,"id":3,"name":"帽子","pdCatalogPrices":[],"picture":"http://www.kukeshangju.com/UserFile/ke/createImage/2_1452705018781_854.jpg","purchasePrice":18,"retailPrice":32,"sellCount":32,"sex":1,"spec":"1","status":1,"stock":150,"stockMax":999,"stockMin":0,"unitId":2,"updateAccount":1,"updateTime":"2016-01-18 20:24:35","warningStatus":0,"wholesalePrice":20},{"address":"shanghai","barCode":"9787111213828","brandId":1,"catalogId":1,"companyId":"2222","createAccount":1,"createTime":"2015-12-30 10:32:00","description":"夹克","goodsNo":"1344","hit":687,"id":4,"name":"夹克","pdCatalogPrices":[],"picture":"http://www.kukeshangju.com/UserFile/ke/createImage/2_1452705018781_854.jpg","purchasePrice":69,"retailPrice":159,"sellCount":49,"sex":2,"spec":"1","status":1,"stock":500,"stockMax":999,"stockMin":0,"unitId":2,"updateAccount":1,"updateTime":"2016-01-18 20:24:35","warningStatus":0,"wholesalePrice":80},{"address":"shanghai","barCode":"9787111213829","brandId":1,"catalogId":1,"companyId":"2222","createAccount":1,"createTime":"2015-12-30 10:32:00","description":"睡衣","goodsNo":"1345","hit":546,"id":5,"name":"睡衣","pdCatalogPrices":[],"picture":"http://www.kukeshangju.com/UserFile/ke/createImage/2_1452705018781_854.jpg","purchasePrice":90,"retailPrice":189,"sellCount":56,"sex":1,"spec":"1","status":1,"stock":500,"stockMax":999,"stockMin":0,"unitId":2,"updateAccount":1,"updateTime":"2016-01-18 20:24:35","warningStatus":0,"wholesalePrice":96},{"address":"shanghai","barCode":"9787111213830","brandId":1,"catalogId":1,"companyId":"2222","createAccount":1,"createTime":"2015-12-30 10:32:00","description":"女装","goodsNo":"1346","hit":679,"id":6,"name":"女装","pdCatalogPrices":[],"picture":"http://www.kukeshangju.com/UserFile/ke/createImage/2_1452705018781_854.jpg","purchasePrice":100,"retailPrice":269,"sellCount":69,"sex":3,"spec":"1","status":1,"stock":500,"stockMax":999,"stockMin":0,"unitId":2,"updateAccount":1,"updateTime":"2016-01-18 20:24:35","warningStatus":0,"wholesalePrice":120},{"address":"shanghai","barCode":"9787111213830","brandId":1,"catalogId":1,"companyId":"2222","createAccount":1,"createTime":"2015-12-30 10:32:00","description":"男装","goodsNo":"1347","hit":388,"id":7,"name":"男装","pdCatalogPrices":[],"picture":"http://www.kukeshangju.com/UserFile/ke/createImage/2_1452705018781_854.jpg","purchasePrice":80,"retailPrice":209,"sellCount":58,"sex":2,"spec":"1","status":1,"stock":500,"stockMax":999,"stockMin":0,"unitId":2,"updateAccount":1,"updateTime":"2016-01-18 20:24:35","warningStatus":0,"wholesalePrice":90},{"address":"hangzhou","barCode":"9787111213831","brandId":1,"catalogId":1,"companyId":"2222","createAccount":1,"createTime":"2015-12-30 10:32:00","description":"鞋子","goodsNo":"1348","hit":469,"id":8,"name":"鞋子","pdCatalogPrices":[],"picture":"http://www.kukeshangju.com/UserFile/ke/createImage/2_1452705018781_854.jpg","purchasePrice":65,"retailPrice":129,"sellCount":38,"sex":1,"spec":"1","status":1,"stock":300,"stockMax":999,"stockMin":0,"unitId":2,"updateAccount":1,"updateTime":"2016-01-18 20:24:35","warningStatus":0,"wholesalePrice":69},{"address":"shenzhen","barCode":"9787111213832","brandId":1,"catalogId":1,"companyId":"2222","createAccount":1,"createTime":"2015-12-30 10:32:00","description":"皮包","goodsNo":"1349","hit":555,"id":9,"name":"皮包","pdCatalogPrices":[],"picture":"http://www.kukeshangju.com/UserFile/ke/createImage/2_1452705018781_854.jpg","purchasePrice":165,"retailPrice":266,"sellCount":29,"sex":3,"spec":"1","status":1,"stock":150,"stockMax":999,"stockMin":0,"unitId":2,"updateAccount":1,"updateTime":"2016-01-18 20:24:35","warningStatus":0,"wholesalePrice":180},{"address":"suzhou","barCode":"9787111213833","brandId":1,"catalogId":1,"companyId":"2222","createAccount":1,"createTime":"2015-12-30 10:32:00","description":"手套","goodsNo":"1350","hit":228,"id":10,"name":"手套","pdCatalogPrices":[],"picture":"http://www.kukeshangju.com/UserFile/ke/createImage/2_1452705018781_854.jpg","purchasePrice":18,"retailPrice":39,"sellCount":86,"sex":1,"spec":"1","status":1,"stock":250,"stockMax":999,"stockMin":0,"unitId":2,"updateAccount":1,"updateTime":"2016-01-18 20:24:35","warningStatus":0,"wholesalePrice":20}]
     * pageNo : 1
     * pageSize : 200
     */

    public DataEntity data;

    public static class DataEntity {
        public String pageCount;
        public String totalCount;
        public String pageNo;
        public String pageSize;
        /**
         * address : suzhou
         * barCode : 9787111213826
         * brandId : 1
         * catalogId : 1
         * companyId : 2222
         * createAccount : 1
         * createTime : 2015-12-30 10:32:00
         * description : 围巾
         * goodsNo : 1342
         * hit : 444
         * id : 1
         * name : 围巾
         * pdCatalogPrices : [{"csCatalogId":2,"id":1,"money":1,"productId":2,"status":1}]
         * picture : http://www.kukeshangju.com/UserFile/ke/createImage/2_1452705018781_854.jpg
         * purchasePrice : 15
         * retailPrice : 25
         * sellCount : 35
         * sex : 1
         * spec : 1
         * status : 1
         * stock : 25
         * stockMax : 999
         * stockMin : 0
         * unitId : 2
         * updateAccount : 1
         * updateTime : 2016-01-18 20:24:35
         * warningStatus : 0
         * wholesalePrice : 18
         */

        public List<PdListEntity> pdList;


        public static class PdListEntity {
            public String address;
            public String barCode;
            public int brandId;
            public int catalogId;
            public String companyId;
            public int createAccount;
            public String createTime;
            public String description;
            public String goodsNo;
            public int hit;
            public int id;
            public String name;
            public String picture;
            public int purchasePrice;
            public int retailPrice;
            public int sellCount;
            public int sex;
            public String spec;
            public int status;
            public int stock;
            public int stockMax;
            public int stockMin;
            public int unitId;
            public int updateAccount;
            public String updateTime;
            public int warningStatus;
            public int wholesalePrice;
            /**
             * csCatalogId : 2
             * id : 1
             * money : 1
             * productId : 2
             * status : 1
             */

            public List<PdCatalogPricesEntity> pdCatalogPrices;


            public static class PdCatalogPricesEntity {
                public int csCatalogId;
                public int id;
                public int money;
                public int productId;
                public int status;

            }
        }
    }
}
