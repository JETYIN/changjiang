package tech.boshu.changjiangshidai.ui.activity.stockinfo.purchasereturnhistory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.PurchaseReturnHistoryAdapter;
import tech.boshu.changjiangshidai.constants.ParameterConstants;
import tech.boshu.changjiangshidai.libs.activity.SwipeRefreshBaseActivity;
import tech.boshu.changjiangshidai.libs.bean.Pagination;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.addpurchasereturnorder.AddPurchaseOrderActivity;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.finishpurchasereturnorderdetail.FinishPurchaseOrderDetailActivity;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.purchasehistorysearch.PurchaseHistorySearchActivity;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)PurchaseHistoryActivity.java
 */


public class PurchaseHistoryActivity extends SwipeRefreshBaseActivity implements IPurchaseHistoryView {

    public final static int ACTIVITY_SEARCH = 100;

    private ImageButton backImageButton;
    private ImageButton addImageButton;
    private ImageButton searchImageButton;
    private RadioButton filter_defaultRadioButton;
    private RadioButton filter_countRadioButton;
    private RadioButton filter_draftRadioButton;
    private RadioButton filter_cancelRadioButton;
    private RadioGroup filter_group;

    private PurchaseReturnHistoryAdapter adapter;
    private List<PurchaseHistoryPrOrder> datas = new ArrayList<>();


    public void setView() {

        setOnclickListener(R.id.add, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AddPurchaseOrderActivity.class);
                startActivity(intent);
            }
        });
        setOnclickListener(R.id.search, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PurchaseHistorySearchActivity.class);
                startActivityForResult(intent, ACTIVITY_SEARCH);
            }
        });
        setOnItemClickListener(R.id.list, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= datas.size()) {
                    return;
                } else {
                    PurchaseHistoryPrOrder prOrder = datas.get(i);
                    Intent intent = null;
                    if (prOrder.status == 0) {
                        intent = new Intent(mContext, AddPurchaseOrderActivity.class);
                        intent.putExtra(ParameterConstants.PARAM_ACTION_TYPE, ParameterConstants.ACTION_TYPE_EDIT);
                        intent.putExtra(ParameterConstants.PARAM_ORDER_ID, prOrder.id);
                        startActivityForResult(intent, ACTIVITY_SEARCH);
                    } else if (prOrder.status == 1 || prOrder.status == 2) {
                        intent = new Intent(mContext, FinishPurchaseOrderDetailActivity.class);
                        intent.putExtra(ParameterConstants.PARAM_ORDER_ID, prOrder.id);
                        startActivityForResult(intent, ACTIVITY_SEARCH);
                    }
                }
            }
        });
    }

    public void initView() {
        backImageButton = (ImageButton) findViewById(R.id.back);
        addImageButton = (ImageButton) findViewById(R.id.add);
        searchImageButton = (ImageButton) findViewById(R.id.search);
        filter_defaultRadioButton = (RadioButton) findViewById(R.id.filter_default);
        filter_countRadioButton = (RadioButton) findViewById(R.id.filter_count);
        filter_draftRadioButton = (RadioButton) findViewById(R.id.filter_draft);
        filter_cancelRadioButton = (RadioButton) findViewById(R.id.filter_cancel);
        filter_group = (RadioGroup) findViewById(R.id.purchase_hostroy_group);
    }

    @Override
    public void bindDatas(List<PurchaseHistoryPrOrder> datas) {
        swipeLayout.setRefreshing(false);
        if (isRefresh) {
            initPageInfo();
            this.datas.clear();
        }
        this.datas.addAll(datas);
        if (adapter == null) {
            adapter = new PurchaseReturnHistoryAdapter(mContext, datas);
            listView.setAdapter(adapter);
        } else {
            adapter.setData(this.datas);
        }
    }

    @Override
    public void showNoDatas() {
        swipeLayout.setRefreshing(false);
        this.datas.clear();
        adapter.setData(this.datas);
    }

    @Override
    public Pagination getPagination() {
        return pagination;
    }

    @Override
    public void setPagination(Pagination param) {
        pagination = param;
    }

    @Override
    public void stopRefresh() {
        swipeLayout.setRefreshing(false);
    }

    private PurchaseHistoryPresenter purchaseHistoryPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setView();
        filter_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.filter_default:
                        purchaseHistoryPresenter.filterByDefault();
                        break;
                    case R.id.filter_count:
                        purchaseHistoryPresenter.filterByCount();
                        break;
                    case R.id.filter_draft:
                        purchaseHistoryPresenter.filterByDraft();
                        break;
                    case R.id.filter_cancel:
                        purchaseHistoryPresenter.filterByCancel();
                        break;
                }
                isRefresh = true;
                purchaseHistoryPresenter.getDatas();
            }
        });

        initTitle("采购退货历史");
        purchaseHistoryPresenter.setText(R.id.right, "");
        //第一个按钮为选中状态
        ((RadioButton) filter_group.getChildAt(0)).setChecked(true);
    }

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_purchase_history;
    }

    @Override
    protected void setPresenter() {
        purchaseHistoryPresenter = (PurchaseHistoryPresenter) (presenter =
                new PurchaseHistoryPresenter(
                        PurchaseHistoryActivity.this));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_SEARCH && resultCode == RESULT_OK) {
            PurchaseRequestParam purchaseRequestParam = new Gson().fromJson(data.getStringExtra(
                            ParameterConstants.PARAM_COMMON_SEARCH_RETURN_VALUE),
                    new TypeToken<PurchaseRequestParam>() {
                    }.getType());
            initPageInfo();
            purchaseRequestParam.pageSize = pagination.pageSize;
            purchaseRequestParam.pageNo = pagination.pageNo;
            purchaseHistoryPresenter.setParams(purchaseRequestParam);
            isRefresh = true;
            if (((RadioButton) filter_group.getChildAt(0)).isChecked()) {
                purchaseHistoryPresenter.filterByDefault();
                purchaseHistoryPresenter.getDatas();
            } else {
                ((RadioButton) filter_group.getChildAt(0)).setChecked(true);
            }

        }
    }
}