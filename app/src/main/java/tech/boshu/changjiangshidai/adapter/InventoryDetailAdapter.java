package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.InventoryDetail;
import tech.boshu.changjiangshidai.bean.SaleDetail;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;


public class InventoryDetailAdapter extends CommonAdapter<InventoryDetail> {

	public InventoryDetailAdapter(Context context, List<InventoryDetail> datas) {
		super(context, datas);
		this.layoutId = R.layout.adapter_inventory_detail;
	}

	public void convert(final ViewHolder holder, InventoryDetail inventoryDetail) {
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