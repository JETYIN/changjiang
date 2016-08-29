package tech.boshu.changjiangshidai.ui.activity.sale;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.AllSalesHistoryAdapter;
import tech.boshu.changjiangshidai.bean.Saleshistory;
import tech.boshu.changjiangshidai.bean.api.SalesRequest;
import tech.boshu.changjiangshidai.bean.mode.PrOrder;
import tech.boshu.changjiangshidai.bean.mode.ResponseSaleHistory;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;
import tech.boshu.changjiangshidai.libs.utils.ToastUtils;
import tech.boshu.changjiangshidai.libs.utils.Utils;

/**
 * Created by Administrator on 2016/1/8.
 */
public class ReturnHistoryActivity extends BaseActivity {
    public final static  int RETURN = 1;
    private final static int SEARCH = 0;
    private ListView lvAllReturn;
    private AllSalesHistoryAdapter adapter;
    private List<PrOrder> datas = new ArrayList<>();
    private ImageButton ibSearch;
    private String status = "";
    private String companyId = "2222";
    private int customerId = 1;
    private String orderId = "";
    private String beginTime;
    private String endTime;
    private RadioGroup rgTopLine;
    private ImageButton ibAdd;

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_return_history;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
        getData();
    }

    public void setView() {
        lvAllReturn = (ListView) findViewById(R.id.allreturn);
        ibSearch = (ImageButton) findViewById(R.id.search);
        rgTopLine = (RadioGroup) findViewById(R.id.top_line);
        ibAdd= (ImageButton) findViewById(R.id.add);
        adapter = new AllSalesHistoryAdapter(mContext, datas,RETURN);
        lvAllReturn.setAdapter(adapter);
        ((RadioButton) rgTopLine.getChildAt(0)).setChecked(true);
        lvAllReturn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (datas.get(position).status == 1 || datas.get(position).status == 2) {
                    Intent intent = new Intent(ReturnHistoryActivity.this,
                            ReturnOrderDetailActivity
                                    .class);
                    intent.putExtra("orderId", datas.get(position).id);
                    intent.putExtra("type", datas.get(position).status);
                    startActivity(intent);
                } else if (datas.get(position).status == 0) {
                    Intent intent = new Intent(ReturnHistoryActivity.this, OrderDraftActivity
                            .class);
                    intent.putExtra("orderId", datas.get(position).id);
                    startActivity(intent);
                }
            }
        });
        ibSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReturnHistoryActivity.this, SalesSearchActivity.class);
                startActivityForResult(intent, SEARCH);
            }
        });
        rgTopLine.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (R.id.all == i) {
                    status = "";
                } else if (R.id.returned == i) {
                    status = "1";
                } else if (R.id.draft == i) {
                    status = "0";
                } else if (R.id.cancel == i) {
                    status = "2";
                }
                changeTopLine();
                getData();
            }
        });
        ibAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReturnHistoryActivity.this, AddReturnOrderActivity.class);
                intent.putExtra("action", "add");
                startActivityForResult(intent, 100);
            }
        });
    }

    private void getData() {
        if (Utils.isNetworkConnected(this)) {
            final Dialog mDialog = new Dialog(this, R.style.DialogStyle);
            mDialog.show();
            SalesRequest.queryReturnHistory(status, companyId, customerId, orderId, beginTime,
                    endTime, new ResponseCallback<ResponseSaleHistory>() {

                        @Override
                        public void onRequestSuccess(ResponseSaleHistory result) {
                            ToastUtils.show(mContext, "加载成功");
                            mDialog.dismiss();
                            if (result.data.orderList != null) {
                                datas = result.data.orderList;
                                adapter.setData(datas);
                            }
                        }

                        @Override
                        public void onReuquestFailed(ErrorBase error) {
                            ToastUtils.show(mContext, error.message);
                            mDialog.dismiss();
                        }
                    });
        }

    }

    private void changeTopLine() {
        if ("0".equals(status)) {
            findViewById(R.id.all).setSelected(false);
            findViewById(R.id.returned).setSelected(false);
            findViewById(R.id.draft).setSelected(true);
            findViewById(R.id.cancel).setSelected(false);
        } else if ("1".equals(status)) {
            findViewById(R.id.all).setSelected(false);
            findViewById(R.id.returned).setSelected(true);
            findViewById(R.id.draft).setSelected(false);
            findViewById(R.id.cancel).setSelected(false);
        } else if ("2".equals(status)) {
            findViewById(R.id.all).setSelected(false);
            findViewById(R.id.returned).setSelected(false);
            findViewById(R.id.draft).setSelected(false);
            findViewById(R.id.cancel).setSelected(true);
        } else {
            findViewById(R.id.all).setSelected(true);
            findViewById(R.id.returned).setSelected(false);
            findViewById(R.id.draft).setSelected(false);
            findViewById(R.id.cancel).setSelected(false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SEARCH) {
                customerId = Integer.valueOf(data.getStringExtra("customeriId"));
                orderId = data.getStringExtra("orderId");
                endTime = data.getStringExtra("endTime");
                beginTime = data.getStringExtra("startTime");
                getData();
            }
        }
    }
}