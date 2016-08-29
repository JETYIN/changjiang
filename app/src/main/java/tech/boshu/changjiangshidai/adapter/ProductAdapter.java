package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.mode.OrderDetailDto;
import tech.boshu.changjiangshidai.bean.mode.Product;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;

/**
 * Created by Administrator on 2016/1/7.
 */
public class ProductAdapter extends CommonAdapter<OrderDetailDto> {
    private ProductAdapterLisListener listener;

    public ProductAdapter(Context context, List<OrderDetailDto> datas, ProductAdapterLisListener
            listener) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
        this.listener = listener;
        this.layoutId = R.layout.adapter_product_list;
    }

    @Override
    public void convert(final ViewHolder holder, OrderDetailDto orderDetailDto) {
        TextView product_name = holder.getView(R.id.product_name);
        TextView place = holder.getView(R.id.place);
        TextView size = holder.getView(R.id.size);
        TextView price = holder.getView(R.id.price);
        TextView number = holder.getView(R.id.number);
        Button amend_price = holder.getView(R.id.amend_price);
        ImageButton delete = holder.getView(R.id.delete);
        if (orderDetailDto != null) {
            product_name.setText(orderDetailDto.product);
            place.setText("产地：" + orderDetailDto.place);
            size.setText("规格：" + orderDetailDto.spec);
            price.setText("单价：￥" + orderDetailDto.price);
            number.setText("数量：" + orderDetailDto.num);
        }
        amend_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.amendProduct(holder.position);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.deleteProduct(holder.position);
            }
        });

    }

    public interface ProductAdapterLisListener {
        void amendProduct(int position);

        void deleteProduct(int position);
    }
}
