package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.mode.ProductStore;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;

/**
 * Created by Administrator on 2016/1/30.
 */
public class ProductInventoryAdapter extends CommonAdapter<ProductStore> {
    public ProductInventoryAdapter(Context context, List<ProductStore> datas) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
        this.layoutId = R.layout.adapter_product_inventory;
    }

    @Override
    public void convert(ViewHolder holder, ProductStore productStore) {
        TextView store_name = holder.getView(R.id.store_name);
        TextView stock = holder.getView(R.id.stock);
        if (productStore != null) {
            store_name.setText(productStore.store);
            stock.setText(String.valueOf(productStore.amount));
        }
    }
}
