package tech.boshu.changjiangshidai.ui.activity.basic.supplier.manage;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

import tech.boshu.changjiangshidai.bean.BaseRequestParam;
import tech.boshu.changjiangshidai.constants.ParameterConstants;
import tech.boshu.changjiangshidai.ui.activity.basic.account.manage.AccountBean;

/**
 * Created by allipper on 16/1/26.
 */
public class CustomerManageParam extends BaseRequestParam {

    public CustomerBean accountDto;
    public AccountBean accountBean;
    public String loginId;
    public String name;
    public String action;

    public CustomerManageParam() {
        accountBean = new AccountBean();
        accountBean.companyId = "2222";
        accountBean.accountId = "1";
        accountDto = new CustomerBean();
    }

    public Map<String, String> toQueryParam() {
        final HashMap<String, String> params = new HashMap<>();
        params.put("companyId", "" + accountBean.companyId);
        if (!TextUtils.isEmpty(accountDto.name)) {
            params.put("name", accountDto.name);
        }
        return params;
    }

    public Map<String, String> toPreparedParam() {
        final HashMap<String, String> params = new HashMap<>();
        if (action.equals(ParameterConstants.ACTION_TYPE_EDIT)) {
            params.put("id", accountDto.id);
        }
        params.put("action", action);
        return params;
    }

    public Map<String, String> toAddParam() {
        final HashMap<String, String> params = new HashMap<>();
        if(!TextUtils.isEmpty(action) && action.equals(ParameterConstants.ACTION_TYPE_EDIT)){

        }
        params.put("accountDto.companyId", "" + accountBean.companyId);
        params.put("accountDto.accountId", "" + accountBean.accountId);
        if (action.equals(ParameterConstants.ACTION_TYPE_EDIT)) {
            params.put("provider.id", accountDto.id);//客户名称	Y
        }
        params.put("provider.name", accountDto.name);//客户名称	Y
        params.put("provider.leadingPerson", accountDto.leadingPerson);//客户类型	Y
        params.put("provider.status", accountDto.status);//客状态户类型	Y
        params.put("provider.phone", accountDto.phone);//电话	Y
        params.put("provider.email", accountDto.email);//邮箱	Y
        params.put("provider.mobile", accountDto.mobile);//手机	Y
        params.put("provider.discount", accountDto.discount);//折扣	Y
        params.put("provider.fix", accountDto.fix);//传真	Y
        params.put("provider.website", accountDto.website);//网址	Y
        params.put("provider.bank", accountDto.bank);//开户银行	Y
        params.put("provider.bankNo", accountDto.bankNo);//银行账号	Y
        params.put("provider.bankAccount", accountDto.bankAccount);//银行账户	Y
        params.put("provider.province", accountDto.province);//省	Y
        params.put("provider.city", accountDto.city);//市	Y
        params.put("provider.area", accountDto.area);//区县	Y
        params.put("provider.address", accountDto.address);//详细地址	Y
        params.put("provider.orders", accountDto.orders);//排序	Y
        params.put("provider.memo", accountDto.memo);//备注	Y
//        params.put("provider.updateAccount", accountDto.updateAccount);//备注	Y
//        params.put("provider.updateTime", accountDto.updateTime);//备注	Y
        return params;
    }
}
