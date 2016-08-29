package tech.boshu.changjiangshidai.adapter;

import android.content.Context;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.SupplierArrearsBill;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;


public class SupplierArrearsBillsAdapter extends CommonAdapter<SupplierArrearsBill> {

	public SupplierArrearsBillsAdapter(Context context, List<SupplierArrearsBill> datas) {
		super(context, datas);
		this.layoutId = R.layout.adapter_supplier_arrares;
	}


	public void convert(final ViewHolder holder, SupplierArrearsBill supplierArrearsBill) {

	}
}