package tech.boshu.changjiangshidai.ui.activity.basic.account.manage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.AccountManageAdapter;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;
import tech.boshu.changjiangshidai.ui.activity.basic.account.AccountDetailActivity;
import tech.boshu.changjiangshidai.ui.activity.basic.account.AddAccountActivity;

/**
 * Created by Stone on 2016/1/6.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class AccountManageActivity extends BaseActivity implements
        AdapterView.OnItemClickListener {

    private ImageButton ibAdd;
    private ListView listView;
    private AccountManageAdapter adapter;
    private List<AccountBean> datas = new ArrayList<>();

    @Override
    protected void setLayoutResId() {
        this.layoutResId = R.layout.activity_account_manage;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ibAdd = (ImageButton) findViewById(R.id.add);
        listView = (ListView) findViewById(R.id.list);
        adapter = new AccountManageAdapter(mContext, datas);
        listView.setAdapter(adapter);
        ibAdd.setOnClickListener(this);
        listView.setOnItemClickListener(this);
        getDatas();
    }

    public void getDatas() {
        showDialog();
        AccountManageRequest.query(new AccountManageParam(), new ResponseCallback<ResponseAccounts>() {
            @Override
            public void onRequestSuccess(ResponseAccounts result) {
                hideDialog();
                datas = result.data.accountList;

            }

            @Override
            public void onReuquestFailed(ErrorBase error) {
                hideDialog();
                showToast(error.message);
            }
        });
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.add) {
            startActivity(new Intent(mContext, AddAccountActivity.class));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(mContext, AccountDetailActivity.class));
    }
}
