package tech.boshu.changjiangshidai.ui.activity.basic.setting.addoreditstaff;

import java.util.List;

import tech.boshu.changjiangshidai.libs.bean.ResponseBase;
import tech.boshu.changjiangshidai.ui.activity.basic.setting.starfmanage.RolesEntity;
import tech.boshu.changjiangshidai.ui.activity.basic.setting.starfmanage.StaffBean;

/**
 * Created by allipper on 16/1/26.
 */
public class ResponseAddOrEditStaff extends ResponseBase {

    public DataEntity data;


    public static class DataEntity {
        /**
         * code : SYSTEM
         * memo :
         * name : 测试角色
         * roleId : 1
         * rolePrivilegeRels : [{"id":86,"privilegeId":14,"roleId":1},{"id":68,"privilegeId":24,"roleId":1},{"id":64,"privilegeId":3,"roleId":1},{"id":88,"privilegeId":18,"roleId":1},{"id":78,"privilegeId":23,"roleId":1},{"id":79,"privilegeId":20,"roleId":1},{"id":77,"privilegeId":4,"roleId":1},{"id":66,"privilegeId":21,"roleId":1},{"id":83,"privilegeId":19,"roleId":1},{"id":65,"privilegeId":13,"roleId":1},{"id":63,"privilegeId":2,"roleId":1},{"id":87,"privilegeId":10,"roleId":1},{"id":81,"privilegeId":6,"roleId":1},{"id":82,"privilegeId":5,"roleId":1},{"id":70,"privilegeId":25,"roleId":1},{"id":67,"privilegeId":26,"roleId":1},{"id":69,"privilegeId":16,"roleId":1},{"id":80,"privilegeId":22,"roleId":1},{"id":74,"privilegeId":11,"roleId":1},{"id":72,"privilegeId":1,"roleId":1},{"id":76,"privilegeId":7,"roleId":1},{"id":85,"privilegeId":12,"roleId":1},{"id":71,"privilegeId":8,"roleId":1},{"id":73,"privilegeId":17,"roleId":1},{"id":84,"privilegeId":15,"roleId":1},{"id":75,"privilegeId":27,"roleId":1}]
         */
        public List<RolesEntity> roleList;
        public StaffBean account;
    }
}
