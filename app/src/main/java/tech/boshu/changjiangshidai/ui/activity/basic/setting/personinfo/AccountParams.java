package tech.boshu.changjiangshidai.ui.activity.basic.setting.personinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tech.boshu.changjiangshidai.libs.bean.ResponseBase;

/**
 * Created by allipper on 16/1/26.
 */
public class AccountParams extends ResponseBase {

    /**
     * accountId : 123
     * email : 1128@qq.com
     * loginId : 123
     * memo : 老大
     * phone : 14828282828
     * privilegeList : []
     * privilegeRights : {}
     * privileges : []
     * roles : []
     */

    public String accountId;
    public String email;
    public String loginId;
    public String memo;
    public String phone;
    public String ids;
    public List<?> privilegeList;
    public List<?> privileges;
    public List<?> roles;
    public PrivilegeRights privilegeRights;


    public Map<String, String> toAddParam() {
        final HashMap<String, String> params = new HashMap<>();
        params.put("accountDto.accountId", accountId);
        params.put("accountDto.loginId", loginId);
        params.put("accountDto.phone", phone);
        params.put("accountDto.email", email);
        params.put("accountDto.memo", memo);
        return params;
    }
}
