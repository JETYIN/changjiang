package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.mode.Account;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;

/**
 * Created by Administrator on 2016/1/15.
 */
public class AccountListAdapter extends CommonAdapter<Account> {
    public AccountListAdapter(Context context, List<Account> datas) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
        this.layoutId = R.layout.layout_dilog_listview;

    }

    @Override
    public void convert(ViewHolder holder, Account account) {
        TextView textView = holder.getView(R.id.tv);
        if (account != null) {
            textView.setText(account.name);
        }

    }
}
