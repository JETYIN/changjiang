package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.mode.Product;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;

/**
 * Created by Administrator on 2016/1/19.
 */
public class AddProductAdapter extends CommonAdapter<Product> {
    public AddProductAdapter(Context context, List<Product> datas) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
        this.layoutId = R.layout.adapter_goods_list;
    }

    @Override
    public void convert(ViewHolder holder, Product product) {
        TextView product_name = holder.getView(R.id.product_name);
        TextView goods_number = holder.getView(R.id.goods_number);
        TextView selected_number = holder.getView(R.id.selected_number);
        if (product != null) {
            product_name.setText(product.name);
            goods_number.setText(product.goodsNo);
            selected_number.setText("已选择：" + String.valueOf(product.selected_number));
        }

    }
}
