package tech.boshu.changjiangshidai.ui.activity.stockinfo.flowsearchresult;

import java.util.List;

import tech.boshu.changjiangshidai.bean.mode.StoreBill;
import tech.boshu.changjiangshidai.libs.bean.ResponseBase;

/**
 * Created by Stone on 2016/1/26.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class ResponseStockFlow extends ResponseBase {

    public StockFlowEntity data;

    public static class StockFlowEntity {
        public List<StoreBill> storeBillList;
    }
}
