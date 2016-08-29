package tech.boshu.changjiangshidai.ui.activity.basic.account;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;
import tech.boshu.changjiangshidai.libs.utils.ToastUtils;

/**
 * Created by Stone on 2016/1/7.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class AddAccountActivity extends BaseActivity {

    private TextView tvTitle;
    private TextView tvSave;
    private EditText etPhone;
    private EditText etName;
    private EditText etEmail;
    private EditText etRemark;

    private AccountParams accountParams = new AccountParams();

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

        tvTitle.setText("新增账户");
        tvSave.setText("保存");
        tvSave.setOnClickListener(this);
    }

    @Override
    protected void setLayoutResId() {
        this.layoutResId = R.layout.activity_add_account;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.right) {
            saveAccount();
        }
    }

    //保存账户信息
    private void saveAccount() {
        accountParams.phone = etPhone.getEditableText().toString();
        accountParams.loginId = etName.getEditableText().toString();
        accountParams.email = etEmail.getEditableText().toString();
        accountParams.memo = etRemark.getEditableText().toString();
        if (TextUtils.isEmpty(accountParams.phone)) {
            ToastUtils.show(mContext, "请输入员工手机号码");
            return;
        }
        if (TextUtils.isEmpty(accountParams.loginId)) {
            ToastUtils.show(mContext, "请输入员工真实姓名");
            return;
        }
        if (TextUtils.isEmpty(accountParams.email)) {
            ToastUtils.show(mContext, "请输入员工邮箱");
            return;
        }
        AccountRequest.add(accountParams, new ResponseCallback<AccountParams>() {
            @Override
            public void onRequestSuccess(AccountParams result) {
                showToast("员工新增成功");
                finish();
            }

            @Override
            public void onReuquestFailed(ErrorBase error) {
                showToast(error.message);
            }
        });
    }

}
