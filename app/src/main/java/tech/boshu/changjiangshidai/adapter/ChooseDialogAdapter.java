package tech.boshu.changjiangshidai.adapter;

import android.content.Context;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;

/**
 * Created by allipper on 16/1/23.
 */
public class ChooseDialogAdapter extends CommonAdapter<String> {

    public ChooseDialogAdapter(Context context, List<String> datas) {
        super(context, datas);
        layoutId = R.layout.adapter_choose_dialog;
    }

    @Override
    public void convert(ViewHolder holder, String s) {
        holder.setText(R.id.name, s);
    }
}
