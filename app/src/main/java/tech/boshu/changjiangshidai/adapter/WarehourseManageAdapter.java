package tech.boshu.changjiangshidai.adapter;

import android.content.Context;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;
import tech.boshu.changjiangshidai.ui.activity.basic.warehouse.manage.CustomerBean;


public class WarehourseManageAdapter extends CommonAdapter<CustomerBean> {

    public WarehourseManageAdapter(Context context, List<CustomerBean> datas) {
        super(context, datas);
        this.layoutId = R.layout.adapter_warehourse_manage;
        this.context = context;
        this.datas = datas;
    }

    public void convert(final ViewHolder holder, final CustomerBean item) {
        holder.setText(R.id.name, item.name);
    }

}