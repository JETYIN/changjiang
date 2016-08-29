package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.mode.DeliveryMode;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;

/**
 * Created by Administrator on 2016/1/18.
 */
public class DeliveryModeAdapter extends CommonAdapter<DeliveryMode> {
    public DeliveryModeAdapter(Context context, List<DeliveryMode> datas) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
        this.layoutId = R.layout.layout_dilog_listview;
    }

    @Override
    public void convert(ViewHolder holder, DeliveryMode deliveryMode) {
        TextView textView = holder.getView(R.id.tv);
        if (deliveryMode != null) {
            textView.setText(deliveryMode.codeName);
        }
    }
}
