package tech.boshu.changjiangshidai.ui.activity.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.ShortcutAdapter;
import tech.boshu.changjiangshidai.bean.HomepageItem;
import tech.boshu.changjiangshidai.database.DBUtils;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;

/**
 * Created by allipper on 2016-01-04.
 */
public class ShortcutActivity extends BaseActivity {

    private ShortcutAdapter adapter;
    private List<HomepageItem> datas = new ArrayList<>();
    private ListView lv;

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_shortcut;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTitle("快捷菜单");
        lv = (ListView) findViewById(R.id.list);
        presenter.setText(R.id.right, "保存");
        presenter.setOnclickListener(R.id.right, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBUtils.initShortcuts(datas);
                presenter.back(view);
            }
        });
        datas = DBUtils.queryAllShorcuts();
        //去掉最后一个添加
        datas.remove(datas.size() - 1);
        Collections.sort(datas, new Comparator<HomepageItem>() {
            @Override
            public int compare(HomepageItem item, HomepageItem t1) {
                return t1.status - item.status;
            }
        });
        adapter = new ShortcutAdapter(mContext, datas);
        lv.setAdapter(adapter);
    }

}
