package tech.boshu.changjiangshidai.ui.activity.stockinfo.finishpurchasereturnorderdetail;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.bean.mode.OrderDetailDto;
import tech.boshu.changjiangshidai.bean.mode.PrOrder;
import tech.boshu.changjiangshidai.libs.bean.ResponseBase;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.addpurchasereturnorder.ReponseSetAccountListEntity;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.addpurchasereturnorder.ReponseStroeListEntity;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.addpurchasereturnorder.ResponseProviderListEntity;

/**
 * Created by allipper on 16/1/27.
 */
public class ResponseFinishPurchaseOrderDetail extends ResponseBase {


    public DataEntity data;


    public static class DataEntity {
        public String action;
        public PrOrder order;

        public ArrayList<OrderDetailDto> orderDetailList;

        public List<ResponseProviderListEntity> providerList;

        public List<ReponseSetAccountListEntity> setAccountList;

        public List<ReponseStroeListEntity> stroeList;
    }
}
