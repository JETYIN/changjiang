package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.view.View;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;
import tech.boshu.changjiangshidai.ui.activity.basic.setting.starfmanage.RolesEntity;


public class AddOrEditStaffAdapter extends CommonAdapter<RolesEntity> {

    public Set<String> selectedRols = new HashSet<>();

    public AddOrEditStaffAdapter(Context context, List<RolesEntity> datas) {
        super(context, datas);
        this.layoutId = R.layout.adapter_add_or_edit_staff;
    }

    public void convert(final ViewHolder holder, final RolesEntity item) {
        holder.setText(R.id.tv_buyer_title, item.name != null ? item.name : "");
        holder.setText(R.id.memo, item.memo != null ? item.memo : "");
        if (selectedRols.contains(item.roleId)) {
            holder.setImageResource(R.id.icon_buyer, R.mipmap.cho_on);
        } else {
            holder.setImageResource(R.id.icon_buyer, R.mipmap.cho_off);
        }
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedRols.contains(item.roleId)) {
                    selectedRols.remove(item.roleId);
                    holder.setImageResource(R.id.icon_buyer, R.mipmap.cho_off);
                } else {
                    selectedRols.add(item.roleId);
                    holder.setImageResource(R.id.icon_buyer, R.mipmap.cho_on);
                }
            }
        });
    }
}