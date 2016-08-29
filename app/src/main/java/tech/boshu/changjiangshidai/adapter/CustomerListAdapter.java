package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.mode.Customer;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;
import tech.boshu.changjiangshidai.ui.activity.common.search.CustomerListEntity;

/**
 * Created by Administrator on 2016/1/15.
 */
public class CustomerListAdapter extends CommonAdapter<CustomerListEntity> {
    public CustomerListAdapter(Context context, List<CustomerListEntity> datas) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
        this.layoutId = R.layout.layout_dilog_listview;
    }

    @Override
    public void convert(ViewHolder holder, CustomerListEntity customerListEntity) {
        TextView textView = holder.getView(R.id.tv);
        if (customerListEntity != null) {
            textView.setText(customerListEntity.name);
        }

    }
}
