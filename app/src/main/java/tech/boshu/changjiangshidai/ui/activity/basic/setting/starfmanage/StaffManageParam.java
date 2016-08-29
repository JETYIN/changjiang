package tech.boshu.changjiangshidai.ui.activity.basic.setting.starfmanage;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

import tech.boshu.changjiangshidai.bean.BaseRequestParam;
import tech.boshu.changjiangshidai.constants.ParameterConstants;

/**
 * Created by allipper on 16/1/26.
 */
public class StaffManageParam extends BaseRequestParam{

    public StaffBean accountDto;
    public String loginId;
    public String name;
    public String action;

    public StaffManageParam() {
        accountDto = new StaffBean();
        accountDto.accountId = "1";
        accountDto.companyId = "2222";
    }

    public Map<String, String> toQueryParam() {
        final HashMap<String, String> params = new HashMap<>();
        params.put("accountDto.accountId", "" + accountDto.accountId);
        params.put("accountDto.companyId", accountDto.companyId);
        if (!TextUtils.isEmpty(loginId)) {
            params.put("loginId", loginId);
        }
        if (!TextUtils.isEmpty(accountDto.phone)) {
            params.put("accountDto.phone", accountDto.phone);
        }
        if (accountDto.status != -1) {
            params.put("accountDto.status", ""+ accountDto.status);
        }
        if (!TextUtils.isEmpty(name)) {
            params.put("name", name);
        }
        return params;
    }

    public Map<String, String> toPreparedParam() {
        final HashMap<String, String> params = new HashMap<>();
        if(action.equals(ParameterConstants.ACTION_TYPE_EDIT)){
            params.put("id", accountDto.accountId);
        }
        params.put("action",action);
        return params;
    }

    public Map<String, String> toAddParam() {
        final HashMap<String, String> params = new HashMap<>();
        params.put("accountDto.accountId", accountDto.accountId);
        params.put("accountDto.loginId", loginId);
        params.put("accountDto.phone", accountDto.phone);
        params.put("accountDto.email", accountDto.email);
        params.put("accountDto.memo", accountDto.memo);
        if(!TextUtils.isEmpty(accountDto.roleIds)){
            params.put("ids", accountDto.roleIds);
        }
        return params;
    }
}
