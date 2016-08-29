

package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.CheckOrderDetail;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)Adapter.java
 */

public class AddCheckOrderAdapter extends CommonAdapter<CheckOrderDetail> {

    public AddCheckOrderAdapter(Context context, List<CheckOrderDetail> datas) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
        this.layoutId = R.layout.adapter_add_check_order;
    }


    public void convert(final ViewHolder holder, final CheckOrderDetail data) {
        TextView product_nameTextView = holder.getView(R.id.product_name);
        TextView areaTextView = holder.getView(R.id.area);
        TextView beforeCountTextView = holder.getView(R.id.beforeCount);
        TextView standarTextView = holder.getView(R.id.standar);
        TextView check_countTextView = holder.getView(R.id.check_count);


        holder.setText(R.id.check_count, "盘点数量：15");
        holder.setText(R.id.product_name, "正品杭州丝绸布料(6224579)");
        holder.setText(R.id.area, "产地：杭州");
        holder.setText(R.id.beforeCount, "盘点前数量：47");
        holder.setText(R.id.standar, "规格：XXL");
        holder.setText(R.id.check_count, "盘点数量：47");
    }
}