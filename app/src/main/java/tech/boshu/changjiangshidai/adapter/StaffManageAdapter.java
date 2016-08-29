package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.Staff;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;
import tech.boshu.changjiangshidai.ui.activity.basic.setting.starfmanage.StaffBean;


public class StaffManageAdapter extends CommonAdapter<StaffBean> {
    private StaffManageListener listener;

    public StaffManageAdapter(Context context, List<StaffBean> datas, StaffManageListener listener) {
        super(context, datas);
        this.layoutId = R.layout.adapter_staff_manage;
        this.context = context;
        this.datas = datas;
        this.listener = listener;
    }

    public void convert(final ViewHolder holder, final StaffBean item) {
        holder.setText(R.id.name, item.name != null ? item.name : "");
        holder.setText(R.id.role, item.roleName != null ? item.roleName : "");
        holder.setText(R.id.create_date, item. addTime!= null ? item.addTime : "");

        RelativeLayout layout = holder.getView(R.id.layout);
        ImageButton ibPhone = holder.getView(R.id.call_phone);
        ImageButton ibMessage = holder.getView(R.id.send_message);

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

    public interface StaffManageListener {
        void itemClick(int position);

        void callPhone(int position);

        void senMessage(int position);
    }
}