package tech.boshu.changjiangshidai.ui.activity.basic.setting.companyinfo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;

/**
 * Created by Stone on 2016/1/6.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class CompanyInfoActivity extends BaseActivity {
    private Button btnSave;

    private CompanyParams companyParams = new CompanyParams();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnSave = (Button) findViewById(R.id.btn_save);
        ((TextView) findViewById(R.id.title)).setText("公司信息");
        btnSave.setOnClickListener(this);
        getDatas();
    }

    private void getDatas() {
        showDialog();
        CompanyRequest.query(companyParams, new ResponseCallback<CompanyParams>() {
            @Override
            public void onRequestSuccess(CompanyParams result) {
                hideDialog();
                setView(result.data.company);
            }

            @Override
            public void onReuquestFailed(ErrorBase error) {
                hideDialog();
                showToast(error.message);
            }
        });
    }

    private void setView(CompanyParams.DataEntity.CompanyEntity company) {
        setText(R.id.name, company.name);
        setText(R.id.phone, company.phone);
        setText(R.id.address, company.address);
        setText(R.id.code, company.code);
    }

    @Override
    protected void setLayoutResId() {
        this.layoutResId = R.layout.activity_company_info;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.btn_save) {

        }
    }

}
