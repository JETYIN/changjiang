package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.widget.CompoundButton;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.HomepageItem;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;


public class WarehourseProductAdapter extends CommonAdapter<HomepageItem> {

    public WarehourseProductAdapter(Context context, List<HomepageItem> datas) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
        this.layoutId = R.layout.adpater_warehourse_product;
    }


    public void convert(final ViewHolder holder, final HomepageItem item) {
        //入库数量
        holder.setText(R.id.cur_num, item.name);
        //入库前数量
        holder.setText(R.id.org_num, item.name);
        //规格
        holder.setText(R.id.size, item.name);
        //产地
        holder.setText(R.id.area, item.name);
        //产品名称
        holder.setText(R.id.product_name, item.name);
    }
}