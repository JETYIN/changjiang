package tech.boshu.changjiangshidai.ui.activity.business;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.InventoryDetailAdapter;
import tech.boshu.changjiangshidai.bean.InventoryDetail;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.utils.ToastUtils;

/**
 * 盘点筛选 P7-8-1查询
 */
public class InventoryDetailSearchActivity extends BaseActivity {

    private TextView tv_StartTime;// 开始时间
    private TextView tv_EndTime;// 结束时间
    private EditText et_BillNo;//单号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.setText(R.id.right, "清空");
        initTitle("筛选");
        setOnclickListener(R.id.right, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clean();
            }
        });
        findViews();
        initData();
    }

    /**
     * 清空
     */
    private void clean() {
        ToastUtils.show(this,"clean");
    }

    private void initData() {
    }

    private void findViews() {
    }

    /**
     * 清空
     */
    private void search() {
        ToastUtils.show(this,"search");
    }

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_inventory_search;
    }

    @Override
    public void onClick(View view) {
    }


}

