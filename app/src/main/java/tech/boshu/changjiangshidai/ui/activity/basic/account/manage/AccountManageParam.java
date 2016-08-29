package tech.boshu.changjiangshidai.ui.activity.basic.account.manage;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by allipper on 16/1/26.
 */
public class AccountManageParam {

    public AccountBean accountDto;
    public String loginId;
    public String name;

    public AccountManageParam() {
        accountDto = new AccountBean();
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
        if (!TextUtils.isEmpty(name)) {
            params.put("name", name);
        }
        return params;
    }
}
