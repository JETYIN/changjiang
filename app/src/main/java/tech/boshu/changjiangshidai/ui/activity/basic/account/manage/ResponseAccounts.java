package tech.boshu.changjiangshidai.ui.activity.basic.account.manage;

import java.util.ArrayList;

import tech.boshu.changjiangshidai.libs.bean.ResponseBase;

/**
 * Created by allipper on 16/1/26.
 */
public class ResponseAccounts extends ResponseBase {
    public EntityData data;

    public static class EntityData {
        public String pageCount;
        public ArrayList<AccountBean> accountList;
    }
}
