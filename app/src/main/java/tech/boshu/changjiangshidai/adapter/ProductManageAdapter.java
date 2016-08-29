package tech.boshu.changjiangshidai.adapter;

import android.content.Context;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.mode.Product;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;


public class ProductManageAdapter extends CommonAdapter<Product> {

    public ProductManageAdapter(Context context, List<Product> datas) {
        super(context, datas);
        this.layoutId = R.layout.adapter_product_manage;
        this.context = context;
        this.datas = datas;
    }

    public void convert(final ViewHolder holder, final Product item) {
        holder.setText(R.id.name, item.name);
        holder.setText(R.id.price, "¥" + item.retailPrice);
        holder.setText(R.id.code, "" + item.goodsNo);
        holder.setImageByUrl(item.picture, R.id.icon);
    }

}