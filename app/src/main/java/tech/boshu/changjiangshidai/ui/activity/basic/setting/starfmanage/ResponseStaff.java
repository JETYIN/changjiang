package tech.boshu.changjiangshidai.ui.activity.basic.setting.starfmanage;

import java.util.ArrayList;

import tech.boshu.changjiangshidai.libs.bean.ResponseBase;

/**
 * Created by allipper on 16/1/26.
 */
public class ResponseStaff extends ResponseBase {
    public EntityData data;

    public static class EntityData {
        public String pageCount;
        public ArrayList<StaffBean> accountList;

    }
}
