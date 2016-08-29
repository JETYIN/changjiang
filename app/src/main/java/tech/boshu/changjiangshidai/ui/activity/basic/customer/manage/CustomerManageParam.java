package tech.boshu.changjiangshidai.ui.activity.basic.customer.manage;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

import tech.boshu.changjiangshidai.bean.BaseRequestParam;
import tech.boshu.changjiangshidai.constants.ParameterConstants;

/**
 * Created by allipper on 16/1/26.
 */
public class CustomerManageParam extends BaseRequestParam{

    public CustomerBean accountDto;
    public String loginId;
    public String name;
    public String action;

    public CustomerManageParam() {
        accountDto = new CustomerBean();
        accountDto.companyId = "2222";
    }

    public Map<String, String> toQueryParam() {
        final HashMap<String, String> params = new HashMap<>();
        params.put("companyId", "" + accountDto.companyId);
        if (!TextUtils.isEmpty(accountDto.name)) {
            params.put("name", accountDto.name);
        }
        if (!TextUtils.isEmpty(accountDto.type)) {
            params.put("type", accountDto.type);
        }
        if (!TextUtils.isEmpty(accountDto.arrears)) {
            params.put("area", ""+ accountDto.arrears);
        }
        return params;
    }

    public Map<String, String> toPreparedParam() {
        final HashMap<String, String> params = new HashMap<>();
        if(action.equals(ParameterConstants.ACTION_TYPE_EDIT)){
            params.put("id", accountDto.id);
        }
        params.put("action",action);
        return params;
    }

    public Map<String, String> toAddParam() {
        final HashMap<String, String> params = new HashMap<>();
        if(action.equals(ParameterConstants.ACTION_TYPE_EDIT)){
            params.put("customer.id", accountDto.id);//客户名称	Y
        }
        params.put("customer.name", accountDto.name);//客户名称	Y
        params.put("customer.type", accountDto.type);//客户类型	Y
        params.put("customer.status", accountDto.status);//客状态户类型	Y
        params.put("customer.phone", accountDto.phone);//电话	Y
        params.put("customer.email", accountDto.email);//邮箱	Y
        params.put("customer.mobile", accountDto.mobile);//手机	Y
        params.put("customer.discount", accountDto.discount);//折扣	Y
        params.put("customer.fix", accountDto.fix);//传真	Y
        params.put("customer.website", accountDto.website);//网址	Y
        params.put("customer.bank", accountDto.bank);//开户银行	Y
        params.put("customer.bankNo", accountDto.bankNo);//银行账号	Y
        params.put("customer.bankAccount", accountDto.bankAccount);//银行账户	Y
        params.put("customer.province", accountDto.province);//省	Y
        params.put("customer.city", accountDto.city);//市	Y
        params.put("customer.area", accountDto.area);//区县	Y
        params.put("customer.address", accountDto.address);//详细地址	Y
        params.put("customer.orders", accountDto.orders);//排序	Y
        params.put("customer.memo", accountDto.memo);//备注	Y
        return params;
    }
}
