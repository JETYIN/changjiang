

package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.StockAllocateHistoryItem;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)Adapter.java
 */

public class StockAllocateHistoryAdapter extends CommonAdapter<StockAllocateHistoryItem> {

    public StockAllocateHistoryAdapter(Context context, List<StockAllocateHistoryItem> datas) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
        this.layoutId = R.layout.adapter_stock_allocate_history;
    }


    public void convert(final ViewHolder holder, final StockAllocateHistoryItem data) {
        ImageView iconImageView = holder.getView(R.id.icon);
        TextView pricesTextView = holder.getView(R.id.prices);
        TextView order_codeTextView = holder.getView(R.id.order_code);
        TextView contentTextView = holder.getView(R.id.content);


        holder.setText(R.id.prices, "2015-12-04 12:21:32");
        holder.setText(R.id.order_code, "JGHSHS111232323");
        holder.setText(R.id.content, "八楼仓库 -> 四楼仓库");
    }
}