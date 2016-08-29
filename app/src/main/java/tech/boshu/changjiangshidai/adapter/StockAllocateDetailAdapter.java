

package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.StockAllocateDetailItem;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)Adapter.java
 */

public class StockAllocateDetailAdapter extends CommonAdapter<StockAllocateDetailItem> {

    public StockAllocateDetailAdapter(Context context, List<StockAllocateDetailItem> datas) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
        this.layoutId = R.layout.adapter_stock_allocate_detail;
    }


    public void convert(final ViewHolder holder, final StockAllocateDetailItem data) {
        TextView nameTextView = holder.getView(R.id.name);
        TextView areaTextView = holder.getView(R.id.area);
        TextView standarTextView = holder.getView(R.id.standar);
        TextView countTextView = holder.getView(R.id.count);
        TextView afterCountTextView = holder.getView(R.id.afterCount);
        TextView beforeCountTextView = holder.getView(R.id.beforeCount);


        holder.setText(R.id.name, "正品杭州丝绸（6224579）");
        holder.setText(R.id.area, "产地：浙江杭州");
        holder.setText(R.id.standar, "规格：XXX");
        holder.setText(R.id.count, "调拨数量：47");
        holder.setText(R.id.afterCount, "调出前数量： 40");
        holder.setText(R.id.beforeCount, "调入前数量：47");
    }
}