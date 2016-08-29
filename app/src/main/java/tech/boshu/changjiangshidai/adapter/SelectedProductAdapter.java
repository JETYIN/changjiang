package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.mode.Product;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;


public class SelectedProductAdapter extends CommonAdapter<Product> {
    private SelectedProductListener listener;

    public SelectedProductAdapter(Context context, List<Product> datas, SelectedProductListener
            listener) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
        this.listener = listener;
        this.layoutId = R.layout.adapter_selected_product;
    }


    public void convert(final ViewHolder holder, Product product) {
        TextView name = holder.getView(R.id.name);
        TextView number = holder.getView(R.id.number);
        TextView total_number = holder.getView(R.id.total_number);
        TextView place = holder.getView(R.id.place);
        TextView size = holder.getView(R.id.size);
        EditText commodity_number = holder.getView(R.id.commodity_number);
        ImageButton delete = holder.getView(R.id.delete);
        ImageButton commodity_add = holder.getView(R.id.commodity_add);
        ImageButton commodity_sub = holder.getView(R.id.commodity_sub);
        if (product != null) {
            name.setText(product.name);
            number.setText(product.goodsNo);
            total_number.setText("小计：" + String.valueOf(product.selected_number));
            place.setText(product.address);
            size.setText(product.spec);
            commodity_number.setText(String.valueOf(product.selected_number));

        }
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.deleteProduct(holder.position);
            }
        });
        commodity_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.addProductNum(holder.position);
            }
        });
        commodity_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.subProductNum(holder.position);
            }
        });

    }

    public interface SelectedProductListener {
        void deleteProduct(int position);

        void addProductNum(int position);

        void subProductNum(int position);
    }
}
