package tech.boshu.changjiangshidai.ui.activity.basic.setting.addoreditstaff;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.AddOrEditStaffAdapter;
import tech.boshu.changjiangshidai.constants.ParameterConstants;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;
import tech.boshu.changjiangshidai.libs.utils.ToastUtils;
import tech.boshu.changjiangshidai.ui.activity.basic.setting.starfmanage.RolesEntity;
import tech.boshu.changjiangshidai.ui.activity.basic.setting.starfmanage.StaffBean;
import tech.boshu.changjiangshidai.ui.activity.basic.setting.starfmanage.StaffManageParam;
import tech.boshu.changjiangshidai.utils.ValidateUtils;

/**
 * Created by Stone on 2016/1/7.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class AddOrEditStaffActivity extends BaseActivity implements
        CompoundButton.OnCheckedChangeListener {

    private TextView tvTitle;
    private TextView tvSave;
    private EditText etPhone;
    private EditText etName;
    private EditText etEmail;
    private EditText etRemark;
    private CheckBox cbIsAvailable;
    private ListView list;

    private String type = ParameterConstants.ACTION_TYPE_ADD;
    private StaffBean staff = new StaffBean();
    private StaffManageParam staffManageParam = new StaffManageParam();
    private AddOrEditStaffAdapter adapter;
    private List<RolesEntity> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    public void initView() {
        tvTitle = (TextView) findViewById(R.id.title);
        tvSave = (TextView) findViewById(R.id.right);
        etPhone = (EditText) findViewById(R.id.et_phone);
        etName = (EditText) findViewById(R.id.et_name);
        etEmail = (EditText) findViewById(R.id.et_email);
        etRemark = (EditText) findViewById(R.id.et_remark);
        cbIsAvailable = (CheckBox) findViewById(R.id.cb_is_available);
        list = (ListView) findViewById(R.id.list);
        adapter = new AddOrEditStaffAdapter(mContext, datas);
        list.setAdapter(adapter);

        tvSave.setText("保存");
        tvSave.setOnClickListener(this);
        cbIsAvailable.setOnCheckedChangeListener(this);

        type = getIntent().getStringExtra(ParameterConstants.PARAM_ACTION_TYPE);
        if (type.equals(ParameterConstants.ACTION_TYPE_EDIT)) {
            tvTitle.setText("员工编辑");
            staffManageParam.action = ParameterConstants.ACTION_TYPE_EDIT;
            staff.accountId = getIntent().getStringExtra(ParameterConstants.PRAMA_COMMON_VALUE);
        } else if (type.equals(ParameterConstants.ACTION_TYPE_ADD)) {
            tvTitle.setText("新增员工");
            staffManageParam.action = ParameterConstants.ACTION_TYPE_ADD;
            staff.status = 1;
            cbIsAvailable.setChecked(true);
        }
        prepareDatas();
    }

    @Override
    protected void setLayoutResId() {
        this.layoutResId = R.layout.activity_add_or_edite_staff;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.right) {
            saveStaff();
        }
    }

    public void prepareDatas() {
        showDialog();
        AddOrEditStaffRequest.prepareDatas(staffManageParam, new ResponseCallback<ResponseAddOrEditStaff>() {
            @Override
            public void onRequestSuccess(ResponseAddOrEditStaff result) {
                if (staffManageParam.action.equals(ParameterConstants.ACTION_TYPE_EDIT)) {
                    String roles = result.data.account.roleIds;
                    staff = result.data.account;
                    if (!TextUtils.isEmpty(roles)) {
                        String[] rolesStr = roles.split(",");
                        for (String str : rolesStr) {
                            adapter.selectedRols.add(str);
                        }
                    }
                    etPhone.setText(staff.phone != null ? staff.phone : "");
                    etName.setText(staff.name != null ? staff.name : "");
                    etEmail.setText(staff.email != null ? staff.email : "");
                    etRemark.setText(staff.memo != null ? staff.memo : "");
                    cbIsAvailable.setChecked(staff.status == 1);
                }
                if (result.data.roleList != null && result.data.roleList.size() > 0) {
                    datas = result.data.roleList;
                    adapter.setData(datas);

                }
                hideDialog();
            }

            @Override
            public void onReuquestFailed(ErrorBase error) {
                hideDialog();
                showToast(error.message);
            }
        });
    }

    //保存员工信息
    private void saveStaff() {
        if (!new ValidateUtils().validat(new ValidateUtils.ValidateInterface() {
            @Override
            public void onFailed(String message) {
                showToast(message);
            }
        }, etPhone, etName, etEmail, etRemark)) {
            return;
        }
        showDialog();
        staff.phone = etPhone.getEditableText().toString();
        staff.name = etName.getEditableText().toString();
        staff.email = etEmail.getEditableText().toString();
        staff.memo = etRemark.getEditableText().toString();
        staff.accountId = staff.accountId == null ? "1": staff.accountId;
        if (adapter.selectedRols.size() > 0) {
            StringBuffer stringBuffer = new StringBuffer();
            for (String str : adapter.selectedRols) {
                stringBuffer.append(str).append(",");
            }
            staff.roleIds = stringBuffer.deleteCharAt(stringBuffer.length() - 1).toString();
        }
        staffManageParam.accountDto = staff;
        staffManageParam.loginId =staff.loginId == null ? staff.phone: staff.loginId;
        AddOrEditStaffRequest.addOrEdit(staffManageParam, new ResponseCallback<ResponseAddOrEditStaff>() {
            @Override
            public void onRequestSuccess(ResponseAddOrEditStaff result) {
                hideDialog();
                ToastUtils.show(mContext, "保存成功");
                setResult(RESULT_OK);
                finish();
            }

            @Override
            public void onReuquestFailed(ErrorBase error) {
                hideDialog();
                showToast(error.message);
            }
        });

    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        int id = compoundButton.getId();
        if (id == R.id.cb_is_available) {//是否启用账号
            staff.status = isChecked ? 1 : 0;
        }
    }
}
