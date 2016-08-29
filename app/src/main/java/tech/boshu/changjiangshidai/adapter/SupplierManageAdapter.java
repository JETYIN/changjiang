package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.Supplier;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;
import tech.boshu.changjiangshidai.ui.activity.basic.supplier.manage.CustomerBean;


public class SupplierManageAdapter extends CommonAdapter<CustomerBean> {
    private SupplierListener listener;

    public SupplierManageAdapter(Context context, List<CustomerBean> datas, SupplierListener listener) {
        super(context, datas);
        this.layoutId = R.layout.adapter_supplier_manage;
        this.context = context;
        this.datas = datas;
        this.listener = listener;
    }

    public void convert(final ViewHolder holder, final CustomerBean item) {
        RelativeLayout layout = holder.getView(R.id.layout);
        ImageButton ibPhone = holder.getView(R.id.call_phone);
        ImageButton ibMessage = holder.getView(R.id.send_message);
        holder.setText(R.id.name, item.name);
        holder.setText(R.id.role, "负责人：：" + item.leadingPerson);

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

    public interface SupplierListener {
        void itemClick(int position);

        void callPhone(int position);

        void senMessage(int position);
    }
}