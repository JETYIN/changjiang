package tech.boshu.changjiangshidai.ui.activity.basic.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.ui.activity.basic.setting.about.AboutActivity;
import tech.boshu.changjiangshidai.ui.activity.basic.setting.companyinfo.CompanyInfoActivity;
import tech.boshu.changjiangshidai.ui.activity.basic.setting.personinfo.PersonInfoActivity;
import tech.boshu.changjiangshidai.ui.activity.basic.setting.starfmanage.StaffManageActivity;

/**
 * Created by Stone on 2016/1/6.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class SettingHelpActivity extends BaseActivity {

    private Button btnPerson;
    private Button btnCompany;
    private Button btnStaff;
    private Button btnHelp;
    private Button btnAbout;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnPerson = (Button) findViewById(R.id.btn_setting_person);
        btnCompany = (Button) findViewById(R.id.btn_setting_company);
        btnStaff = (Button) findViewById(R.id.btn_setting_staff);
        btnHelp = (Button) findViewById(R.id.btn_setting_help);
        btnAbout = (Button) findViewById(R.id.btn_setting_about);
        btnLogout = (Button) findViewById(R.id.btn_logout);
        ((TextView) findViewById(R.id.title)).setText("设置与帮助");
        btnPerson.setOnClickListener(this);
        btnCompany.setOnClickListener(this);
        btnStaff.setOnClickListener(this);
        btnHelp.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
    }

    @Override
    protected void setLayoutResId() {
        this.layoutResId = R.layout.activity_setting_help;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.btn_setting_person) {
            startActivity(new Intent(mContext, PersonInfoActivity.class));
        } else if (id == R.id.btn_setting_company) {
            startActivity(new Intent(mContext, CompanyInfoActivity.class));
        } else if (id == R.id.btn_setting_staff) {
            startActivity(new Intent(mContext, StaffManageActivity.class));
        } else if (id == R.id.btn_setting_help) {

        } else if (id == R.id.btn_setting_about) {
            startActivity(new Intent(mContext, AboutActivity.class));
        } else if (id == R.id.btn_logout) {

        }
    }

}
