package tech.boshu.changjiangshidai.ui.activity.stockinfo.productstock;

import tech.boshu.changjiangshidai.libs.bean.ResponseBase;

/**
 * Created by Stone on 2016/1/26.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class ResponseProductStockInfo extends ResponseBase {

    public DataEntity data;

    public static class DataEntity {
        public ProductEntity product;
    }

    public static class ProductEntity {
        public int id;
        //商品名称
        public String name;
        //分类
        public String catalog;
        //品牌
        public String brand;
        //采购价
        public double purchasePrice;
        //批发价
        public double wholesalePrice;
        //零售价
        public double retailPrice;
        //总库存
        public int mergerNum;
    }

}
