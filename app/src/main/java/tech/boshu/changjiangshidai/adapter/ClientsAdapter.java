package tech.boshu.changjiangshidai.adapter;

import android.content.Context;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.Client;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;


public class ClientsAdapter extends CommonAdapter<Client> {

	public ClientsAdapter(Context context, List<Client> datas) {
		super(context, datas);
		this.layoutId = R.layout.adapter_client;
	}


	public void convert(final ViewHolder holder, Client client) {

	}
}