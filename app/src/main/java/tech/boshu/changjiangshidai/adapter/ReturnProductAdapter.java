package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.mode.OrderDetailDto;
import tech.boshu.changjiangshidai.bean.mode.Product;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;

/**
 * Created by Administrator on 2016/1/8.
 */
public class ReturnProductAdapter extends CommonAdapter<OrderDetailDto> {
    public ReturnProductAdapter(Context context, List<OrderDetailDto> datas) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
        this.layoutId = R.layout.adapter_return_product;
    }

    @Override
    public void convert(ViewHolder holder, OrderDetailDto orderDetailDto) {
        TextView product_name = holder.getView(R.id.product_name);
        TextView place = holder.getView(R.id.place);
        TextView size = holder.getView(R.id.size);
        TextView price = holder.getView(R.id.price);
        TextView num = holder.getView(R.id.num);
        if (orderDetailDto != null) {
            product_name.setText(orderDetailDto.product);
            place.setText("产地：" + orderDetailDto.place);
            size.setText("规格：" + orderDetailDto.spec);
            price.setText("单价：￥" + String.valueOf(orderDetailDto.price));
            num.setText("数量：" + String.valueOf(orderDetailDto.num));

        }

    }
}
