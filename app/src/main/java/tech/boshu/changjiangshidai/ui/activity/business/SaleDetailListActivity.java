package tech.boshu.changjiangshidai.ui.activity.business;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.ClientsAdapter;
import tech.boshu.changjiangshidai.adapter.SaleDetailAdapter;
import tech.boshu.changjiangshidai.bean.Client;
import tech.boshu.changjiangshidai.bean.SaleDetail;
import tech.boshu.changjiangshidai.interf.PopupMenuClickListener;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.utils.ToastUtils;
import tech.boshu.changjiangshidai.ui.widget.MyPopupSaleDetail;
import tech.boshu.changjiangshidai.utils.UIHelper;

/**
 * 销售明细 P7-3销售明细
 */
public class SaleDetailListActivity extends BaseActivity implements PopupMenuClickListener{

    private ImageView show;
    private TextView tv_Today;
    private TextView tv_Yesterday;
    private TextView tv_Week;
    private TextView tv_Month;
    private TextView tv_SingleCount;
    private TextView tv_SaleAmount;
    private TextView tv_Margin;
    private TextView tv_MarginPercent;
    private ListView lv_sale;
    private SaleDetailAdapter adapter;
    private List<SaleDetail> saleDetails = new ArrayList<>();
    private MyPopupSaleDetail popup;
    private int type = SaleDetailAdapter.NORMAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTitle("销售明细");
        setOnclickListener(R.id.iv_search, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show(SaleDetailListActivity.this,"search");
            }
        });
        setOnclickListener(R.id.iv_more, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup = new MyPopupSaleDetail(SaleDetailListActivity.this);
                popup.setListener(SaleDetailListActivity.this);
                popup.showAsDropDown(show);
            }
        });
        findViews();
        initData();
    }

    private void initData() {
        registerForContextMenu(show);
        tv_Today.setSelected(true);
        for(int i = 0;i<3;i++){
            saleDetails.add(new SaleDetail());
        }
        adapter = new SaleDetailAdapter(this,saleDetails,type);
        lv_sale.setAdapter(adapter);
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
        lv_sale = (ListView) findViewById(R.id.lv_sale);

        tv_Today.setOnClickListener(this);
        tv_Yesterday.setOnClickListener(this);
        tv_Week.setOnClickListener(this);
        tv_Month.setOnClickListener(this);
        findViewById(R.id.iv_search).setOnClickListener(this);
    }

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_sale_detail_list;
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
            UIHelper.showSaleDetailSearchActivity(this);
        }
    }

    @Override
    public void onPopueMenuItemClick(int viewID) {
        switch(viewID){
            case R.id.single:
                type = SaleDetailAdapter.SINGLE;
                break;
            case R.id.sale:
                type = SaleDetailAdapter.SALE;
                break;
            case R.id.bill:
                type = SaleDetailAdapter.BILL;
                break;
            case R.id.client:
                type = SaleDetailAdapter.CLIENT;
                break;
            case R.id.saler:
                type = SaleDetailAdapter.SALER;
                break;
            default:
                break;
        }
        popup.dismiss();
        adapter = new SaleDetailAdapter(this,saleDetails,type);
        lv_sale.setAdapter(adapter);
    }
}

