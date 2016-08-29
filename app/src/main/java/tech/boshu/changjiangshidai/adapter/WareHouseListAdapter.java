package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.Warehourse;
import tech.boshu.changjiangshidai.bean.mode.Store;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;

/**
 * Created by Administrator on 2016/1/15.
 */
public class WareHouseListAdapter extends CommonAdapter<Store> {
    public WareHouseListAdapter(Context context,List<Store> datas){
        super(context,datas);
        this.context =context;
        this.datas = datas;
        this.layoutId = R.layout.layout_dilog_listview;
    }
    @Override
    public void convert(ViewHolder holder, Store store) {
        TextView textView = holder.getView(R.id.tv);
        if (store!=null){
            textView.setText(store.name);
        }

    }
}
