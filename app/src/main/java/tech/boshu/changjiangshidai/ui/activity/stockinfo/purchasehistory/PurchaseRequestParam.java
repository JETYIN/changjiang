package tech.boshu.changjiangshidai.ui.activity.stockinfo.purchasehistory;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.HashMap;

import tech.boshu.changjiangshidai.bean.BaseRequestParam;
import tech.boshu.changjiangshidai.libs.bean.Pagination;

/**
 * 查询采购请求的参数
 * Created by allipper on 16/1/18.
 */
public class PurchaseRequestParam extends BaseRequestParam{

    /*
    * @param companyId 商户主键
    * @param status 订单状态
    * @param providerId 供应商主键
    * @param orderId 采购单号
    * @param beginTime 开始时间 2015-12-28 17:35:00
            * @param endTime 结束时间
            * */
    public String companyId = "2222";
    public String status;
    public String providerId;
    public String orderId = "";
    public String beginTime = "";
    public String endTime = "";

    public String toString(){
        return  new Gson().toJson(this);
    }
}
