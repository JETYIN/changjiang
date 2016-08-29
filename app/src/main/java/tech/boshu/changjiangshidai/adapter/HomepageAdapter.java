package tech.boshu.changjiangshidai.adapter;

import android.content.Context;

import java.util.List;

import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;
import tech.boshu.changjiangshidai.bean.HomepageItem;

/**
 * Created by zoulinlin on 16/1/2.
 */
public class HomepageAdapter extends CommonAdapter<HomepageItem> {
    public HomepageAdapter(Context context, List<HomepageItem> datas) {
        super(context, datas);
    }

    public HomepageAdapter(Context context, List<HomepageItem> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, HomepageItem homepageItem) {

    }
}
