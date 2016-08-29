package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.PurchaseDetail;
import tech.boshu.changjiangshidai.bean.SalePurchaseCompare;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;


public class SalePurchaseCompareAdapter extends CommonAdapter<SalePurchaseCompare> {

	public SalePurchaseCompareAdapter(Context context, List<SalePurchaseCompare> datas) {
		super(context, datas);
		this.layoutId = R.layout.adapter_sale_purchase_compare;
	}

	public void convert(final ViewHolder holder, SalePurchaseCompare salePurchaseCompare) {
	}
}