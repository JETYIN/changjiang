package tech.boshu.changjiangshidai.ui.activity.basic.customer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.constants.ParameterConstants;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;
import tech.boshu.changjiangshidai.ui.activity.basic.customer.addoredit.AddOrEditCustomerRequest;
import tech.boshu.changjiangshidai.ui.activity.basic.customer.addoredit.ResponseAddOrEditCustomer;
import tech.boshu.changjiangshidai.ui.activity.basic.customer.manage.CustomerManageParam;

/**
 * Created by Stone on 2016/1/7.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class CustomerSearchActivity extends BaseActivity {

    private TextView tvTitle;
    private TextView tvCancle;
    private Button btnSearch;
    public List<ResponseAddOrEditCustomer.DataEntity.CustomerTypeEntity> customerType;
    private CustomerManageParam customerManageParam = new CustomerManageParam();
    private TextView btn_typeButton;
    private EditText areaEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    public void initView() {
        tvTitle = (TextView) findViewById(R.id.title);
        tvCancle = (TextView) findViewById(R.id.right);
        btn_typeButton = (TextView) findViewById(R.id.selectType);
        btnSearch = (Button) findViewById(R.id.btn_search);
        areaEditText = (EditText) findViewById(R.id.area);

        tvTitle.setText("查询筛选");
        tvCancle.setText("清空");
        tvCancle.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        setOnclickListener(R.id.selectType, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectxType();
            }
        });
        customerManageParam.action = ParameterConstants.ACTION_TYPE_ADD;
        prepareDatas();
    }

    public void prepareDatas() {
        showDialog();
        AddOrEditCustomerRequest.prepareDatas(customerManageParam, new ResponseCallback<ResponseAddOrEditCustomer>() {
            @Override
            public void onRequestSuccess(ResponseAddOrEditCustomer result) {
                if (result.data.customerType != null && result.data.customerType.size() > 0) {
                    customerType = result.data.customerType;
                }
                hideDialog();
            }

            @Override
            public void onReuquestFailed(ErrorBase error) {
                hideDialog();
                showToast(error.message);
            }
        });
    }

    @Override
    protected void setLayoutResId() {
        this.layoutResId = R.layout.activity_customer_search;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.right) {
            finish();
        } else if (id == R.id.btn_search) {
            searchCustomer();
        }
    }

    public void selectxType() {
        String[] suppliers = new String[customerType.size()];
        int i = 0;
        for (ResponseAddOrEditCustomer.DataEntity.CustomerTypeEntity ppl : customerType) {
            suppliers[i++] = ppl.name;
        }
        AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setItems(suppliers, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                customerManageParam.accountDto.type = customerType.get(which).id;
                btn_typeButton.setText(customerType.get(which).name);
                dialog.dismiss();
            }
        }).show();
    }

    private void searchCustomer() {
        customerManageParam.accountDto.area = areaEditText.getEditableText().toString();
        Intent intent = new Intent();
        intent.putExtra(ParameterConstants.PRAMA_COMMON_VALUE, new Gson().toJson(customerManageParam
        ));
        setResult(RESULT_OK, intent);
        finish();
    }

}
