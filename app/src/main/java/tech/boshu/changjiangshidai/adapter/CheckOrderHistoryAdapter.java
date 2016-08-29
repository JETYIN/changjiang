

package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.result.CheckOrderHistory;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)Adapter.java
 */

public class CheckOrderHistoryAdapter extends CommonAdapter<CheckOrderHistory> {

    public CheckOrderHistoryAdapter(Context context, List<CheckOrderHistory> datas) {
        super(context, datas);
        this.layoutId = R.layout.adapter_check_order_history;
    }


    public void convert(final ViewHolder holder, final CheckOrderHistory history) {
        ImageView iconImageView = holder.getView(R.id.icon);
        TextView dateTextView = holder.getView(R.id.date);
        TextView codeTextView = holder.getView(R.id.code);
        TextView contentTextView = holder.getView(R.id.content);

        if (history.status == 0) {
            iconImageView.setImageResource(R.mipmap.icon_order_draft);
        } else if (history.status == 1) {
            iconImageView.setImageResource(R.mipmap.icon_order);
        }
        dateTextView.setText(history.checkDate);
        codeTextView.setText(history.billNo);
        contentTextView.setText(history.stroe + " - " + history.operator);
    }
}