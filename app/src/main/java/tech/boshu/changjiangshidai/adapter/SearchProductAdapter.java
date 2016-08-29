

package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.mode.Product;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;
import tech.boshu.changjiangshidai.libs.net.HttpLoad;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.searchresult.ModelStockProduct;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)Adapter.java
 */

public class SearchProductAdapter extends CommonAdapter<ModelStockProduct> {

    public SearchProductAdapter(Context context, List<ModelStockProduct> datas) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
        this.layoutId = R.layout.adapter_search_product;
    }


    public void convert(final ViewHolder holder, final ModelStockProduct product) {
        ImageView iconImageView = holder.getView(R.id.icon);
        TextView nameTextView = holder.getView(R.id.name);
        TextView sizeTextView = holder.getView(R.id.goods_no);
        TextView stockTextView = holder.getView(R.id.stock_no);

        HttpLoad.getImage(product.picture, iconImageView);
        nameTextView.setText(product.pdName);
        sizeTextView.setText(product.goodsNo);
        stockTextView.setText(String.valueOf(product.mergerNum));
    }
}