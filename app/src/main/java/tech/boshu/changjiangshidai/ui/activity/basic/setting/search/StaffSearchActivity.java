package tech.boshu.changjiangshidai.ui.activity.basic.setting.search;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.constants.ParameterConstants;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.utils.ToastUtils;
import tech.boshu.changjiangshidai.ui.activity.basic.setting.starfmanage.StaffManageParam;

/**
 * Created by Stone on 2016/1/7.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class StaffSearchActivity extends BaseActivity {

    private TextView tvTitle;
    private TextView tvCancle;
    private EditText etPhone;
    private EditText etName;
    private CheckBox cbIsAvailable;
    private Button btnSearch;

    private StaffManageParam staffManageParam = new StaffManageParam();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    public void initView() {
        tvTitle = (TextView) findViewById(R.id.title);
        tvCancle = (TextView) findViewById(R.id.right);
        etPhone = (EditText) findViewById(R.id.et_phone);
        etName = (EditText) findViewById(R.id.et_name);
        cbIsAvailable = (CheckBox) findViewById(R.id.cb_is_available);
        btnSearch = (Button) findViewById(R.id.btn_search);

        tvTitle.setText("员工搜索");
        tvCancle.setText("取消");
        tvCancle.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
    }

    @Override
    protected void setLayoutResId() {
        this.layoutResId = R.layout.activity_staff_search;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.right) {
            finish();
        } else if (id == R.id.btn_search) {
            searchStaff();
        }
    }

    private void searchStaff() {
        String phone = etPhone.getEditableText().toString();
        String name = etName.getEditableText().toString();
        boolean isAvailable = cbIsAvailable.isChecked();
        staffManageParam.accountDto.phone = phone;
        staffManageParam.name = name;
        staffManageParam.accountDto.status = isAvailable ? 1 : 0;
        Intent intent = new Intent();
        intent.putExtra(ParameterConstants.PRAMA_COMMON_VALUE, new Gson().toJson(staffManageParam
        ));
        setResult(RESULT_OK, intent);
        finish();
    }

}
