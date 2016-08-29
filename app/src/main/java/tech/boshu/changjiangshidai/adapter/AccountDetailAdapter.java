package tech.boshu.changjiangshidai.adapter;

import android.content.Context;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.AccountDetail;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;


public class AccountDetailAdapter extends CommonAdapter<AccountDetail> {

    public AccountDetailAdapter(Context context, List<AccountDetail> datas) {
        super(context, datas);
        this.layoutId = R.layout.adapter_account_detail;
        this.context = context;
        this.datas = datas;
    }

    public void convert(final ViewHolder holder, final AccountDetail item) {
    }

}