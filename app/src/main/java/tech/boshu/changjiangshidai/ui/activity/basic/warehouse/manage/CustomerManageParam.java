package tech.boshu.changjiangshidai.ui.activity.basic.warehouse.manage;

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
    public String action;

    public CustomerManageParam() {
        accountDto = new CustomerBean();
        accountDto.companyId = "2222";
    }

    public Map<String, String> toQueryParam() {
        final HashMap<String, String> params = new HashMap<>();
        params.put("companyId", "" + accountDto.companyId);
        if (!TextUtils.isEmpty(accountDto.id)) {
            params.put("id", accountDto.id);
        }
        return params;
    }

    public Map<String, String> toPreparedParam() {
        final HashMap<String, String> params = new HashMap<>();
        if(action.equals(ParameterConstants.ACTION_TYPE_EDIT)){
            params.put("id", accountDto.id);
        }
        params.put("companyId", "" + accountDto.companyId);
        params.put("action",action);
        return params;
    }

    public Map<String, String> toAddParam() {
        final HashMap<String, String> params = new HashMap<>();
        if(action.equals(ParameterConstants.ACTION_TYPE_ADD)){
            accountDto.id = "1";
            params.put("accountDto.accountId", accountDto.id);//客户名称	Y
            params.put("accountDto.companyId", accountDto.companyId);//
        }
        params.put("walHouse.leadingAccount", accountDto.leadingAccount);//客户名称	Y
        params.put("walHouse.name", accountDto.name);//客户类型	Y
        params.put("walHouse.status", accountDto.status);//客状态户类型	Y
        params.put("walHouse.memo", accountDto.memo);//电话	Y
        params.put("walHouse.orders", accountDto.orders);//邮箱	Y
        return params;
    }
}
