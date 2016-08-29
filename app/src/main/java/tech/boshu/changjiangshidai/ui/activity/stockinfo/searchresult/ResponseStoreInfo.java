package tech.boshu.changjiangshidai.ui.activity.stockinfo.searchresult;

import java.util.List;

import tech.boshu.changjiangshidai.libs.bean.ResponseBase;

/**
 * Created by Stone on 16/1/8.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class ResponseStoreInfo extends ResponseBase {

    public DataEntity data;


    public static class DataEntity {
        /*仓库数量*/
        public int storeNum;
        /*库存总数量*/
        public long storeAllNum;
        /*库存总成本*/
        public double storeCost;
        /*库存预警数*/
        public int warningNum;

        public List<ModelStockProduct> productList;

        public int pageCount;
        public int pageNo;
        public int pageSize;
    }
}
