package tech.boshu.changjiangshidai.ui.activity.stockinfo.finishpurchaseorderdetail;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.bean.mode.OrderDetailDto;
import tech.boshu.changjiangshidai.bean.mode.PrOrder;
import tech.boshu.changjiangshidai.libs.bean.ResponseBase;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.addpurchaseorder.ReponseSetAccountListEntity;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.addpurchaseorder.ReponseStroeListEntity;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.addpurchaseorder.ResponseProviderListEntity;

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
