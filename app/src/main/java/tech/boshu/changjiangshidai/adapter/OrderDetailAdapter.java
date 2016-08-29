package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.mode.OrderDetailDto;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;

/**
 * Created by Administrator on 2016/1/20.
 */
public class OrderDetailAdapter extends CommonAdapter<OrderDetailDto> {
    public OrderDetailAdapter(Context context, List<OrderDetailDto> datas) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
        layoutId = R.layout.adapter_order_list;
    }

    @Override
    public void convert(ViewHolder holder, OrderDetailDto orderDetailDto) {
        TextView product = holder.getView(R.id.product);
        TextView place_value = holder.getView(R.id.place_value);
        TextView size_value = holder.getView(R.id.size_value);
        TextView price = holder.getView(R.id.price);
        TextView number = holder.getView(R.id.number);
        if (orderDetailDto != null) {
            product.setText(orderDetailDto.product);
            place_value.setText(orderDetailDto.place);
            size_value.setText(orderDetailDto.spec);
            price.setText(String.valueOf(orderDetailDto.price));
            number.setText(String.valueOf("*" + orderDetailDto.amount));
        }

    }
}
