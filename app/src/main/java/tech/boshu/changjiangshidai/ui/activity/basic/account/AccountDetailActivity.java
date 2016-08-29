package tech.boshu.changjiangshidai.ui.activity.basic.account;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.AccountDetailAdapter;
import tech.boshu.changjiangshidai.bean.AccountDetail;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;

/**
 * Created by Stone on 2016/1/7.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class AccountDetailActivity extends BaseActivity {

    private TextView tvTitle;
    private TextView tvSave;
    private EditText etPhone;
    private EditText etName;
    private EditText etEmail;
    private EditText etRemark;
    private ListView listView;
    private AccountDetailAdapter adapter;
    private List<AccountDetail> datas;

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
        listView = (ListView) findViewById(R.id.list);
        datas = getDatas();
        adapter = new AccountDetailAdapter(mContext, datas);
        listView.setAdapter(adapter);

        tvTitle.setText("账户流水");
    }

    private List<AccountDetail> getDatas() {
        List<AccountDetail> datas = new ArrayList<>();
        datas.add(new AccountDetail());
        datas.add(new AccountDetail());
        datas.add(new AccountDetail());
        datas.add(new AccountDetail());
        datas.add(new AccountDetail());
        datas.add(new AccountDetail());
        datas.add(new AccountDetail());
        datas.add(new AccountDetail());
        datas.add(new AccountDetail());
        datas.add(new AccountDetail());
        datas.add(new AccountDetail());
        datas.add(new AccountDetail());
        datas.add(new AccountDetail());
        datas.add(new AccountDetail());
        datas.add(new AccountDetail());
        datas.add(new AccountDetail());
        return datas;
    }

    @Override
    protected void setLayoutResId() {
        this.layoutResId = R.layout.activity_account_detail;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
    }

}
