package tech.boshu.changjiangshidai.ui.activity.business;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.ClientFilterAdapter;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.utils.ToastUtils;
import tech.boshu.changjiangshidai.libs.utils.Utils;
import tech.boshu.changjiangshidai.utils.UIHelper;

/**
 * 供应商筛选  P7-2-1筛选
 */
public class SupplierFilterActivity extends BaseActivity {

    private EditText et_ArrearsMax;//最大欠款
    private EditText et_ArrearsMin;//最小欠款
    private ToggleButton tBtn_filter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.setText(R.id.right, "清空");
        initTitle("筛选");
        presenter.setOnclickListener(R.id.right, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clean();
            }
        });
        findViews();
        initData();
    }

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_supplier_filter;
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
        et_ArrearsMax = (EditText) findViewById(R.id.et_arrears_max);
        et_ArrearsMin = (EditText) findViewById(R.id.et_arrears_min);
        tBtn_filter = (ToggleButton) findViewById(R.id.tbtn_filter);

        findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
            }
        });
    }

    /**
     * 搜索
     */
    private void search() {
        if(!isPrepareForNext()){
            return;
        }
        UIHelper.showSupplierArrearsActivity(this);
    }

    private boolean isPrepareForNext() {
        if(!Utils.isNetworkConnected(this)){
            Utils.setNetworkMethod(this);
            return false;
        }
        return true;
    }


}

