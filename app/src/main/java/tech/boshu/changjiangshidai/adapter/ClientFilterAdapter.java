package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;


public class ClientFilterAdapter extends CommonAdapter<String> {

    public ClientFilterAdapter(Context context, List<String> datas) {
        super(context, datas);
        this.layoutId = R.layout.adapter_client_filter;
    }


    public void convert(final ViewHolder holder, String str) {
        TextView name = holder.getView(R.id.name);

        name.setText(str);
    }
}