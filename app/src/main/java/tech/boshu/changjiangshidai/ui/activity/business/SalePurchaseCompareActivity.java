package tech.boshu.changjiangshidai.ui.activity.business;


import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.PurchaseDetailAdapter;
import tech.boshu.changjiangshidai.adapter.SalePurchaseCompareAdapter;
import tech.boshu.changjiangshidai.bean.PurchaseDetail;
import tech.boshu.changjiangshidai.bean.SalePurchaseCompare;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.ui.widget.MyPopupPurchaseDetail;
import tech.boshu.changjiangshidai.utils.UIHelper;

/**
 * 进销对比 P7-7进销对比
 */
public class SalePurchaseCompareActivity extends BaseActivity {

    private TextView tv_Today;
    private TextView tv_Yesterday;
    private TextView tv_Week;
    private TextView tv_Month;
    private ListView lv_compare;
    private SalePurchaseCompareAdapter adapter;
    private List<SalePurchaseCompare> salePurchaseCompares = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTitle("进销对比");
        presenter.setOnclickListener(R.id.right, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
            }
        });
        findViews();
        initData();
    }

    private void search() {
    }

    private void initData() {
        tv_Today.setSelected(true);
        for(int i = 0;i<5;i++){
            salePurchaseCompares.add(new SalePurchaseCompare());
        }
        adapter = new SalePurchaseCompareAdapter(this,salePurchaseCompares);
        lv_compare.setAdapter(adapter);
    }

    private void findViews() {
        tv_Today = (TextView) findViewById(R.id.tv_today);
        tv_Yesterday = (TextView) findViewById(R.id.tv_yesterday);
        tv_Week = (TextView) findViewById(R.id.tv_week);
        tv_Month = (TextView) findViewById(R.id.tv_month);
        lv_compare = (ListView) findViewById(R.id.lv_compare);

        tv_Today.setOnClickListener(this);
        tv_Yesterday.setOnClickListener(this);
        tv_Week.setOnClickListener(this);
        tv_Month.setOnClickListener(this);
    }


    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_sale_purchase_compare;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.tv_today){
            if(!tv_Today.isSelected()){
                tv_Today.setSelected(true);
                tv_Yesterday.setSelected(false);
                tv_Week.setSelected(false);
                tv_Month.setSelected(false);
            }
        }else  if(id == R.id.tv_yesterday){
            if(!tv_Yesterday.isSelected()){
                tv_Today.setSelected(false);
                tv_Yesterday.setSelected(true);
                tv_Week.setSelected(false);
                tv_Month.setSelected(false);
            }
        }else  if(id == R.id.tv_week){
            if(!tv_Week.isSelected()){
                tv_Today.setSelected(false);
                tv_Yesterday.setSelected(false);
                tv_Week.setSelected(true);
                tv_Month.setSelected(false);
            }
        }else  if(id == R.id.tv_month){
            if(!tv_Month.isSelected()){
                tv_Today.setSelected(false);
                tv_Yesterday.setSelected(false);
                tv_Week.setSelected(false);
                tv_Month.setSelected(true);
            }
        }else  if(id == R.id.iv_search){
            UIHelper.showSalePurchaseCompareSearchActivity(this);
        }
    }

}

