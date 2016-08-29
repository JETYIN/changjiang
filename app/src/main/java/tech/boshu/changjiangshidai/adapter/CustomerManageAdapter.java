package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;
import tech.boshu.changjiangshidai.ui.activity.basic.customer.manage.CustomerBean;


public class CustomerManageAdapter extends CommonAdapter<CustomerBean> {
    private CustomerListener listener;

    public CustomerManageAdapter(Context context, List<CustomerBean> datas, CustomerListener listener) {
        super(context, datas);
        this.layoutId = R.layout.adapter_customer_manage;
        this.context = context;
        this.datas = datas;
        this.listener = listener;
    }

    public void convert(final ViewHolder holder, final CustomerBean item) {
        RelativeLayout layout = holder.getView(R.id.layout);
        ImageButton ibPhone = holder.getView(R.id.call_phone);
        ImageButton ibMessage = holder.getView(R.id.send_message);

        holder.setText(R.id.name, item.name);
        holder.setText(R.id.role, "客户分类：" + item.catalogName);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.itemClick(holder.position);
                }
            }
        });
        ibPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.callPhone(holder.position);
                }
            }
        });
        ibMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.senMessage(holder.position);
                }
            }
        });
    }

    public interface CustomerListener {
        void itemClick(int position);

        void callPhone(int position);

        void senMessage(int position);
    }
}