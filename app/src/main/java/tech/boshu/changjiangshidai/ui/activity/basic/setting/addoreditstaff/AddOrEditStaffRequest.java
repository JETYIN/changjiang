package tech.boshu.changjiangshidai.ui.activity.basic.setting.addoreditstaff;

import com.android.volley.Request;

import tech.boshu.changjiangshidai.bean.api.ServerRequest;
import tech.boshu.changjiangshidai.constants.ParameterConstants;
import tech.boshu.changjiangshidai.libs.net.GsonRequest;
import tech.boshu.changjiangshidai.libs.net.HttpUtils;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.ui.activity.basic.setting.starfmanage.StaffManageParam;

/**
 * Created by allipper on 16/1/26.
 */
public class AddOrEditStaffRequest extends ServerRequest {

    //3.准备数据
    public static void prepareDatas(
            StaffManageParam param,
            ResponseCallback<ResponseAddOrEditStaff> callback) {
        String url = apiHost + "/ac_editAccount.action ";
        GsonRequest<ResponseAddOrEditStaff> request = new GsonRequest<>(Request.Method.POST,
                url, ResponseAddOrEditStaff.class, null, param.toPreparedParam(), callback, callback);
        HttpUtils.getInstance().request(AddOrEditStaffActivity.class.getSimpleName(), request);
    }

    public static void addOrEdit(
            StaffManageParam param,
            ResponseCallback<ResponseAddOrEditStaff> callback) {
        String url = apiHost + "/ac_savepersonInfor.action";
        GsonRequest<ResponseAddOrEditStaff> request = new GsonRequest<>(Request.Method.POST,
                url, ResponseAddOrEditStaff.class, null, param.toAddParam(), callback, callback);
        HttpUtils.getInstance().request(AddOrEditStaffActivity.class.getSimpleName(), request);
    }

}
