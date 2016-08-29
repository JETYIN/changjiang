package tech.boshu.changjiangshidai.ui.activity.stockinfo.addpurchaseorder;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;

import tech.boshu.changjiangshidai.bean.mode.Account;
import tech.boshu.changjiangshidai.bean.mode.OrderDetailDto;
import tech.boshu.changjiangshidai.bean.mode.PrOrder;
import tech.boshu.changjiangshidai.bean.mode.Product;

/**
 * Created by allipper on 16/1/24.
 */
public class AddPurchaseOrderParam {

    public Account accountDto = new Account();
    public String action;
    public PrOrder order = new PrOrder();
    public ArrayList<OrderDetailDto> products = new ArrayList<>();

    public AddPurchaseOrderParam() {
        accountDto = new Account();
        accountDto.companyId = "2222";
        accountDto.id = 1;
        order = new PrOrder();
        products = new ArrayList<>();
    }

    public HashMap<String, String> toQueryParam() {
        final HashMap<String, String> params = new HashMap<>();
        params.put("action", action);
        if (!TextUtils.isEmpty(order.id)) {
            params.put("orderId", order.id);
        }
        return params;
    }

    public HashMap<String, String> toParam() {
        final HashMap<String, String> params = new HashMap<>();
        params.put("accountDto.companyId", accountDto.companyId);
        params.put("accountDto.accountId", String.valueOf(accountDto.id));
        params.put("action", action);
        if (!TextUtils.isEmpty(order.id)) {
            params.put("order.id", order.id);
        }
        params.put("order.providerId", order.providerId);
        params.put("order.storeId", String.valueOf(order.storeId));
        params.put("order.settlement", String.valueOf(order.settlement));
        params.put("order.discount", String.valueOf(order.discount));
        params.put("order.payMoney", String.valueOf(order.payMoney));
        if (!TextUtils.isEmpty(String.valueOf(order.getMoney))) {
            params.put("order.totalMoney", String.valueOf(order.totalMoney));
        }
        if (!TextUtils.isEmpty(order.payEndTime)) {
            params.put("order.payEndTime", order.payEndTime);
        }
        params.put("order.createTime", String.valueOf(order.createTime));
        if (!TextUtils.isEmpty(order.memo)) {
            params.put("order.memo", order.memo);
        }
        for (int i = 0; i < products.size(); i++) {
            params.put("orderDetailList[" + String.valueOf(i) + "].productId", String.valueOf
                    (products.get(i).productId));
            params.put("orderDetailList[" + String.valueOf(i) + "].num", String.valueOf
                    (products.get(i).num));
            params.put("orderDetailList[" + String.valueOf(i) + "].discount", String.valueOf
                    (products.get(i).discount));
            params.put("orderDetailList[" + String.valueOf(i) + "].price", String.valueOf
                    (products.get(i).price));
        }
        return params;
    }

    public HashMap<String, String> toCancelParam() {
        final HashMap<String, String> params = new HashMap<>();
        if (!TextUtils.isEmpty(order.id)) {
            params.put("orderId", order.id);
        }
        return params;
    }
}
