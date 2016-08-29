package tech.boshu.changjiangshidai.ui.activity.basic.setting.personinfo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.application.ApplicationInit;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;
import tech.boshu.changjiangshidai.ui.activity.start.ResponseUserInfo;
import tech.boshu.changjiangshidai.utils.ValidateUtils;

/**
 * Created by Stone on 2016/1/6.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class PersonInfoActivity extends BaseActivity implements ValidateUtils.ValidateInterface {
    private Button btnSave;
    private EditText accountIdEt;
    private EditText loginIdEt;
    private EditText emailEt;
    private EditText memoEt;
    private EditText phoneEt;

    private AccountParams accountParams = new AccountParams();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnSave = (Button) findViewById(R.id.btn_save);
        ((TextView) findViewById(R.id.title)).setText("个人信息");
        accountIdEt = (EditText) findViewById(R.id.accountId);
        loginIdEt = (EditText) findViewById(R.id.loginId);
        emailEt = (EditText) findViewById(R.id.email);
        memoEt = (EditText) findViewById(R.id.memo);
        phoneEt = (EditText) findViewById(R.id.phone);
        btnSave.setOnClickListener(this);
        if(ApplicationInit.userInfo != null){
            ResponseUserInfo.DataEntity.ResObjEntity userInfo = ApplicationInit.userInfo;
            accountIdEt.setText(userInfo.userid);
            loginIdEt.setText(userInfo.username);
            emailEt.setText(userInfo.userid);
            memoEt.setText(userInfo.username);
//            sex.setText(userInfo.sex);
        }
    }

    @Override
    protected void setLayoutResId() {
        this.layoutResId = R.layout.activity_person_info;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.btn_save) {
            if (new ValidateUtils().validat(PersonInfoActivity.this, accountIdEt, loginIdEt, emailEt,
                    memoEt, phoneEt)) {
                accountParams.accountId = accountIdEt.getText().toString();
                accountParams.loginId = loginIdEt.getText().toString();
                accountParams.email = emailEt.getText().toString();
                accountParams.memo = memoEt.getText().toString();
                accountParams.phone = phoneEt.getText().toString();
                AccountRequest.add(accountParams, new ResponseCallback<AccountParams>() {
                    @Override
                    public void onRequestSuccess(AccountParams result) {
                        showToast("信息修改成功");
                        finish();
                    }

                    @Override
                    public void onReuquestFailed(ErrorBase error) {
                        showToast(error.message);
                    }
                });
            }
        }
    }

    @Override
    public void onFailed(String message) {
        showToast(message);
    }
}
