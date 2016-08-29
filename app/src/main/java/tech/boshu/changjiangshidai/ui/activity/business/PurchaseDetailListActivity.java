package tech.boshu.changjiangshidai.ui.activity.business;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.PurchaseDetailAdapter;
import tech.boshu.changjiangshidai.adapter.SaleDetailAdapter;
import tech.boshu.changjiangshidai.bean.PurchaseDetail;
import tech.boshu.changjiangshidai.bean.SaleDetail;
import tech.boshu.changjiangshidai.interf.PopupMenuClickListener;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.utils.ToastUtils;
import tech.boshu.changjiangshidai.ui.widget.MyPopupPurchaseDetail;
import tech.boshu.changjiangshidai.ui.widget.MyPopupSaleDetail;
import tech.boshu.changjiangshidai.utils.UIHelper;

/**
 * 采购明细 P7-4采购明细
 */
public class PurchaseDetailListActivity extends BaseActivity implements PopupMenuClickListener{

    private ImageView show;
    private TextView tv_Today;
    private TextView tv_Yesterday;
    private TextView tv_Week;
    private TextView tv_Month;
    private TextView tv_SingleCount;
    private TextView tv_SaleAmount;
    private TextView tv_Margin;
    private TextView tv_MarginPercent;
    private ListView lv_purchase;
    private PurchaseDetailAdapter adapter;
    private List<PurchaseDetail> purchaseDetails = new ArrayList<>();
    private MyPopupPurchaseDetail popup;
    private int type = PurchaseDetailAdapter.NORMAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTitle("采购明细");
        setOnclickListener(R.id.iv_search, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show(PurchaseDetailListActivity.this,"search");
            }
        });
        setOnclickListener(R.id.iv_more, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup = new MyPopupPurchaseDetail(PurchaseDetailListActivity.this);
                popup.setListener(PurchaseDetailListActivity.this);
                popup.showAsDropDown(show);
            }
        });
        findViews();
        initData();
    }

    private void initData() {
        tv_Today.setSelected(true);
        for(int i = 0;i<3;i++){
            purchaseDetails.add(new PurchaseDetail());
        }
        adapter = new PurchaseDetailAdapter(this,purchaseDetails,type);
        lv_purchase.setAdapter(adapter);
    }

    private void findViews() {
        show = (ImageView) findViewById(R.id.iv_more);
        tv_Today = (TextView) findViewById(R.id.tv_today);
        tv_Yesterday = (TextView) findViewById(R.id.tv_yesterday);
        tv_Week = (TextView) findViewById(R.id.tv_week);
        tv_Month = (TextView) findViewById(R.id.tv_month);
        tv_SingleCount = (TextView) findViewById(R.id.tv_single_count);
        tv_SaleAmount = (TextView) findViewById(R.id.tv_sale_amount);
        tv_Margin = (TextView) findViewById(R.id.tv_margin);
        tv_MarginPercent = (TextView) findViewById(R.id.tv_margin_percent);
        lv_purchase = (ListView) findViewById(R.id.lv_purchase);

        tv_Today.setOnClickListener(this);
        tv_Yesterday.setOnClickListener(this);
        tv_Week.setOnClickListener(this);
        tv_Month.setOnClickListener(this);
        findViewById(R.id.iv_search).setOnClickListener(this);
    }

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_purchase_detail_list;
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
            UIHelper.showPurchaseDetailSearchActivity(this);
        }
    }

    @Override
    public void onPopueMenuItemClick(int viewID) {
        switch(viewID){
            case R.id.single:
                type = PurchaseDetailAdapter.SINGLE;
                break;
            case R.id.sale:
                type = PurchaseDetailAdapter.SALE;
                break;
            case R.id.bill:
                type = PurchaseDetailAdapter.BILL;
                break;
            case R.id.supplier:
                type = PurchaseDetailAdapter.SUPPLIER;
                break;
            case R.id.purchase:
                type = PurchaseDetailAdapter.PUCHASER;
                break;
            default:
                break;
        }
        popup.dismiss();
        adapter = new PurchaseDetailAdapter(this,purchaseDetails,type);
        lv_purchase.setAdapter(adapter);
    }
}

