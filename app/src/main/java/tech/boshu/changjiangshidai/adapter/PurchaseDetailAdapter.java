package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.PurchaseDetail;
import tech.boshu.changjiangshidai.bean.SaleDetail;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;


public class PurchaseDetailAdapter extends CommonAdapter<PurchaseDetail> {
	public static final int NORMAL = 0;
	public static final int SALE = 1;
	public static final int SINGLE = 2;
	public static final int BILL = 3;
	public static final int SUPPLIER = 4;
	public static final int PUCHASER = 5;

	public PurchaseDetailAdapter(Context context, List<PurchaseDetail> datas, int type) {
		super(context, datas);
		switch (type){
			case NORMAL:
			case SALE:
				this.layoutId = R.layout.adapter_purchase_detail_normal;
				break;
			case SINGLE:
				this.layoutId = R.layout.adapter_purchase_detail_single;
				break;
			case BILL:
				this.layoutId = R.layout.adapter_purchase_detail_bill;
				break;
			case SUPPLIER:
				this.layoutId = R.layout.adapter_purchase_detail_supplier;
				break;
			case PUCHASER:
				this.layoutId = R.layout.adapter_purchase_detail_purchaser;
				break;

		}

	}

	public void convert(final ViewHolder holder, PurchaseDetail purchaseDetail) {
		final ImageView showMore = holder.getView(R.id.iv_more);
		final RelativeLayout more = holder.getView(R.id.rl_more);

		showMore.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(more.isShown()){
					more.setVisibility(View.GONE);
					showMore.setImageResource(R.mipmap.list_arrow_down);
				}else{
					more.setVisibility(View.VISIBLE);
					showMore.setImageResource(R.mipmap.list_arrow_up);
				}
			}
		});
	}
}