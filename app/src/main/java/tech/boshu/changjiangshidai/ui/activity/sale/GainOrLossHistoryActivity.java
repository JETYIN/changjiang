package tech.boshu.changjiangshidai.ui.activity.sale;

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

/**
 * Created by Administrator on 2016/1/8.
 */
public class GainOrLossHistoryActivity extends BaseActivity {
    public static final int GAIN_OR_LOSS = 0;
    private ListView lvAllGainLoss;
    private AllSalesHistoryAdapter adapter;
    private List<PrOrder> datas = new ArrayList<>();
    private ImageButton ibAdd;
    private ImageButton ibSearch;
    private String status = "";
    private String companyId = "2222";
    private String orderId = "";
    private String customerId;
    private String beginTime;
    private String endTime;
    private RadioGroup rgTopLine;

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_gain_or_loss_history;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
        getData();

    }

    public void setView() {
        lvAllGainLoss = (ListView) findViewById(R.id.all_gain_loss);
        ibAdd = (ImageButton) findViewById(R.id.add);
        rgTopLine = (RadioGroup) findViewById(R.id.top_line);
        ibSearch = (ImageButton) findViewById(R.id.search);
        ((RadioButton) rgTopLine.getChildAt(0)).setChecked(true);
        adapter = new AllSalesHistoryAdapter(mContext, datas, GAIN_OR_LOSS);
        lvAllGainLoss.setAdapter(adapter);
        lvAllGainLoss.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (datas.get(position).status == 0) {
                } else {
                    Intent intent = new Intent(GainOrLossHistoryActivity.this,
                            GainLossStoreroomActivity.class);
                    intent.putExtra("action","edit");
                    intent.putExtra("orderId",datas.get(position).billNo);
                    startActivity(intent);
                }
            }
        });
        ibAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GainOrLossHistoryActivity.this, AddGainLossOrderActivity
                        .class);
                intent.putExtra("action","add");
                startActivity(intent);
            }
        });
        ibSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GainOrLossHistoryActivity.this, SalesSearchActivity
                        .class);
                startActivityForResult(intent, 1000);
            }
        });
        rgTopLine.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (R.id.all == i) {
                    status = "";
                } else if (R.id.gain_loss == i) {
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

    }

    private void getData() {
        SalesRequest.queryGainOrLossHistory(status, companyId, customerId, orderId, beginTime,
                endTime, new ResponseCallback<ResponseSaleHistory>() {

                    @Override
                    public void onRequestSuccess(ResponseSaleHistory result) {
                        if (result != null) {
                            datas = result.data.orderList;
                            adapter.setData(datas);
                        }
                        ToastUtils.show(mContext, "成功");

                    }

                    @Override
                    public void onReuquestFailed(ErrorBase error) {
                        ToastUtils.show(mContext, error.message);
                    }
                });
    }

    private void changeTopLine() {
        if ("0".equals(status)) {
            findViewById(R.id.all).setSelected(false);
            findViewById(R.id.gain_loss).setSelected(false);
            findViewById(R.id.draft).setSelected(true);
            findViewById(R.id.cancel).setSelected(false);
        } else if ("1".equals(status)) {
            findViewById(R.id.all).setSelected(false);
            findViewById(R.id.gain_loss).setSelected(true);
            findViewById(R.id.draft).setSelected(false);
            findViewById(R.id.cancel).setSelected(false);
        } else if ("2".equals(status)) {
            findViewById(R.id.all).setSelected(false);
            findViewById(R.id.gain_loss).setSelected(false);
            findViewById(R.id.draft).setSelected(false);
            findViewById(R.id.cancel).setSelected(true);
        } else {
            findViewById(R.id.all).setSelected(true);
            findViewById(R.id.gain_loss).setSelected(false);
            findViewById(R.id.draft).setSelected(false);
            findViewById(R.id.cancel).setSelected(false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1000) {
                customerId = data.getStringExtra("customerId");
                endTime = data.getStringExtra("endTime");
                beginTime = data.getStringExtra("startTime");
                orderId = data.getStringExtra("orderId");
                getData();

            }
        }
    }
}
