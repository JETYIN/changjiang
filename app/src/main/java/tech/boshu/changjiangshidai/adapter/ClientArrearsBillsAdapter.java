package tech.boshu.changjiangshidai.adapter;

import android.content.Context;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.ClientArrearsBill;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;


public class ClientArrearsBillsAdapter extends CommonAdapter<ClientArrearsBill> {

	public ClientArrearsBillsAdapter(Context context, List<ClientArrearsBill> datas) {
		super(context, datas);
		this.layoutId = R.layout.adapter_client_arrares;
	}


	public void convert(final ViewHolder holder, ClientArrearsBill clientArrearsBill) {

	}
}