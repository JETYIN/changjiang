package tech.boshu.changjiangshidai.adapter;

import android.content.Context;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;
import tech.boshu.changjiangshidai.ui.activity.basic.account.manage.AccountBean;


public class AccountManageAdapter extends CommonAdapter<AccountBean> {

    public AccountManageAdapter(Context context, List<AccountBean> datas) {
        super(context, datas);
        this.layoutId = R.layout.adapter_account_manage;
        this.context = context;
        this.datas = datas;
    }

    public void convert(final ViewHolder holder, final AccountBean item) {
    }

}