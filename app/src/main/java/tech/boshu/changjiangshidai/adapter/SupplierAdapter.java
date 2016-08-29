package tech.boshu.changjiangshidai.adapter;

import android.content.Context;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.Client;
import tech.boshu.changjiangshidai.bean.Supplier;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;


public class SupplierAdapter extends CommonAdapter<Supplier> {

	public SupplierAdapter(Context context, List<Supplier> datas) {
		super(context, datas);
		this.layoutId = R.layout.adapter_supplier;
	}


	public void convert(final ViewHolder holder, Supplier supplier) {

	}
}