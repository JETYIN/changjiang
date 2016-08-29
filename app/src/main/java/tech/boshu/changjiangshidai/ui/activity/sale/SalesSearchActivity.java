package tech.boshu.changjiangshidai.ui.activity.sale;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.CustomerListAdapter;
import tech.boshu.changjiangshidai.bean.mode.Customer;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;
import tech.boshu.changjiangshidai.libs.utils.ToastUtils;
import tech.boshu.changjiangshidai.ui.activity.common.search.CommonSearchRequest;
import tech.boshu.changjiangshidai.ui.activity.common.search.CustomerListEntity;
import tech.boshu.changjiangshidai.ui.activity.common.search.ResponseCommonSearch;

public class SalesSearchActivity extends BaseActivity {

    private Button btnChooseStock;
    private EditText etOrderId;
    private Button btnChooseStart;
    private Button btnChooseEnd;
    private Button btnSearch;
    private StringBuilder time;
    private String customeriId;
    private String orderId;
    private String startTime;
    private String endTime;
    private CustomerListAdapter customerListAdapter;
    private List<CustomerListEntity> datas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.setText(R.id.right, "清空");
        initTitle("查询");
        presenter.setOnclickListener(R.id.right, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.back(view);
            }
        });
        initView();
        getData();
    }

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_sales_search;
    }


    public void initView() {
        btnChooseStock = (Button) findViewById(R.id.chooseStock);
        etOrderId = (EditText) findViewById(R.id.orderId);
        btnChooseEnd = (Button) findViewById(R.id.chooseEnd);
        btnChooseStart = (Button) findViewById(R.id.chooseStart);
        btnSearch = (Button) findViewById(R.id.search);
        btnChooseEnd.setOnClickListener(this);
        btnChooseStart.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        btnChooseStock.setOnClickListener(this);

    }


    private void datePick(final Button button) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                button.setText(new StringBuilder()
                        .append(year)
                        .append("-")
                        .append((month + 1) < 10 ? "0" + (month + 1)
                                : (month + 1)).append("-")
                        .append((day < 10) ? "0" + day : day));
            }
        }, year, monthOfYear, dayOfMonth).show();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.chooseStart) {
            datePick(btnChooseStart);

        } else if (id == R.id.chooseEnd) {
            datePick(btnChooseEnd);
        } else if (id == R.id.search) {
            orderId = etOrderId.getText().toString();
            startTime = btnChooseStart.getText().toString();
            endTime = btnChooseEnd.getText().toString();
            Intent intent = new Intent();
            if (TextUtils.isEmpty(customeriId)) {
                ToastUtils.show(mContext, "请输入客户ID");
            } else {
                intent.putExtra("customeriId", customeriId);
                intent.putExtra("orderId", orderId);
                intent.putExtra("startTime", startTime);
                intent.putExtra("endTime", endTime);
                setResult(RESULT_OK, intent);
                onBackPressed();
            }
        } else if (id == R.id.chooseStock) {
            View v = LayoutInflater.from(this).inflate(R.layout
                    .dialog_listview, null);
            final AlertDialog dialog = new AlertDialog.Builder(this).setView(v)
                    .show();
            ListView listView = (ListView) v.findViewById(R.id.delivery_detail_list);
            customerListAdapter = new CustomerListAdapter(this, datas);
            listView.setAdapter(customerListAdapter);
            customerListAdapter.setData(datas);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    dialog.dismiss();
                    btnChooseStock.setText(datas.get(i).name);
                    customeriId = datas.get(i).id;
                }
            });
        }
    }

    private void getData() {
        CommonSearchRequest.getSearchFilters(new ResponseCallback<ResponseCommonSearch>() {
            @Override
            public void onRequestSuccess(ResponseCommonSearch result) {
                if (result != null) {
                    datas = result.data.customerList;
                }
            }

            @Override
            public void onReuquestFailed(ErrorBase error) {
                ToastUtils.show(mContext, error.message);
            }
        });
    }
}

