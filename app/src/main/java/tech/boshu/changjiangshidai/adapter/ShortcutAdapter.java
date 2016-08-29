package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.widget.CompoundButton;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.HomepageItem;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;


public class ShortcutAdapter extends CommonAdapter<HomepageItem> {

    public ShortcutAdapter(Context context, List<HomepageItem> datas) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
        this.layoutId = R.layout.adapter_shortcut;
    }


    public void convert(final ViewHolder holder, final HomepageItem item) {
        holder.setOnCheckedChangedListener(R.id.status, new CompoundButton
                .OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                item.status = b ? 1 : 0;
            }
        });
        holder.setChecked(R.id.status, item.status == 1);
        holder.setText(R.id.status, item.name);
    }
}