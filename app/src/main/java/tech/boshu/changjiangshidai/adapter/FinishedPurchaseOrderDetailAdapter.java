

package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.FinishedPurchaseOrderDetailItem;
import tech.boshu.changjiangshidai.bean.mode.OrderDetailDto;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;
import tech.boshu.changjiangshidai.libs.view.AutoHeightListView;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.checkdetail.CheckDetailActivity;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)Adapter.java
 */

public class FinishedPurchaseOrderDetailAdapter extends CommonAdapter<OrderDetailDto> {

    private FinishedPurchaseOrderDetailItemAdapter adapter;
    private List<FinishedPurchaseOrderDetailItem> datas = new ArrayList<>();

    public FinishedPurchaseOrderDetailAdapter(Context context, List<OrderDetailDto> datas) {
        super(context, datas);
        this.context = context;
        this.layoutId = R.layout.adapter_finished_purchase_order_detail;
    }


    public void convert(final ViewHolder holder, final OrderDetailDto data) {
        TextView nameTextView = holder.getView(R.id.name);
        AutoHeightListView listAutoHeightListView = holder.getView(R.id.list);
        holder.setText(R.id.name, data.product);
        datas.clear();
        for (int i = 0; i < 6; i++) {
            datas.add(new FinishedPurchaseOrderDetailItem());
        }
        adapter = new FinishedPurchaseOrderDetailItemAdapter(context, datas);
        listAutoHeightListView.setAdapter(adapter);
        listAutoHeightListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(context, CheckDetailActivity.class);
                context.startActivity(intent);
            }
        });
    }
}