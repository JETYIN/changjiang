

package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.mode.StoreBill;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)Adapter.java
 */

public class StockFlowResultAdapter extends CommonAdapter<StoreBill> {

    public StockFlowResultAdapter(Context context, List<StoreBill> datas) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
        this.layoutId = R.layout.adapter_stock_flow_result;
    }


    public void convert(final ViewHolder holder, final StoreBill storeBill) {
        TextView orderTypeTextView = holder.getView(R.id.orderType);
        TextView subcountTextView = holder.getView(R.id.subcount);
        TextView addcountTextView = holder.getView(R.id.addcount);
        TextView productcodeTextView = holder.getView(R.id.productcode);
        TextView areaTextView = holder.getView(R.id.area);
        TextView billcodeTextView = holder.getView(R.id.billcode);
        TextView standarTextView = holder.getView(R.id.standar);
        TextView operatetimeTextView = holder.getView(R.id.operatetime);

        orderTypeTextView.setText(storeBill.getType(storeBill.type));
        subcountTextView.setText("发生数量：" + storeBill.differenceNum);
        addcountTextView.setText("剩余数量：" + storeBill.residueNum);
        productcodeTextView.setText("货号：" + storeBill.goodsNo);
        areaTextView.setText("产地：" + storeBill.place);
        billcodeTextView.setText("单据编号：" + storeBill.originalNo);
        standarTextView.setText("规格：" + storeBill.spec);
        operatetimeTextView.setText("操作时间：" + storeBill.addTime);
    }
}