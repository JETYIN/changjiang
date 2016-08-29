package tech.boshu.changjiangshidai.ui.activity.basic.warehouse.addoredit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.constants.ParameterConstants;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;
import tech.boshu.changjiangshidai.ui.activity.basic.account.manage.AccountBean;
import tech.boshu.changjiangshidai.ui.activity.basic.warehouse.manage.CustomerManageParam;
import tech.boshu.changjiangshidai.utils.ValidateUtils;

/**
 * Created by Stone on 2016/1/7.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class AddOrEditWarehouseActivity extends BaseActivity implements
        CompoundButton.OnCheckedChangeListener {

    private EditText et_nameEditText;
    private Button btn_manageButton;
    private CheckBox cb_is_availableCheckBox;
    private EditText et_remarkEditText;
    private EditText et_sortEditText;

    private TextView tvTitle;
    private TextView tvSave;

    private String type;
    public List<AccountBean> accountList;
    private CustomerManageParam customerManageParam = new CustomerManageParam();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    public void setViews() {
        setText(R.id.et_name, customerManageParam.accountDto.name);
        for (AccountBean accountBean :
                accountList) {
            if (customerManageParam.accountDto.leadingAccount.equals(accountBean.accountId)) {
                setText(R.id.btn_manage, accountBean.name);
                break;
            }
        }
        cb_is_availableCheckBox.setChecked(customerManageParam.accountDto.status != null && customerManageParam.accountDto.status.equals("1"));
        setText(R.id.et_sort, customerManageParam.accountDto.orders);
        setText(R.id.et_remark, customerManageParam.accountDto.memo);
    }

    public void initView() {
        et_nameEditText = (EditText) findViewById(R.id.et_name);
        btn_manageButton = (Button) findViewById(R.id.btn_manage);
        cb_is_availableCheckBox = (CheckBox) findViewById(R.id.cb_is_available);
        et_sortEditText = (EditText) findViewById(R.id.et_sort);
        et_remarkEditText = (EditText) findViewById(R.id.et_remark);
        tvTitle = (TextView) findViewById(R.id.title);
        tvSave = (TextView) findViewById(R.id.right);

        tvSave.setText("保存");
        tvSave.setOnClickListener(this);
        setOnclickListener(R.id.btn_manage, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectxType();
            }
        });
        type = getIntent().getStringExtra(ParameterConstants.PARAM_ACTION_TYPE);
        if (type.equals(ParameterConstants.ACTION_TYPE_EDIT)) {
            tvTitle.setText("仓库编辑");
            customerManageParam.action = ParameterConstants.ACTION_TYPE_EDIT;
            customerManageParam.accountDto.id = getIntent().getStringExtra(ParameterConstants.PRAMA_COMMON_VALUE);
        } else if (type.equals(ParameterConstants.ACTION_TYPE_ADD)) {
            tvTitle.setText("新增仓库");
            customerManageParam.action = ParameterConstants.ACTION_TYPE_ADD;
            customerManageParam.accountDto.status = "1";
            cb_is_availableCheckBox.setChecked(true);
        }
        prepareDatas();
    }

    public void prepareDatas() {
        showDialog();
        AddOrEditCustomerRequest.prepareDatas(customerManageParam, new ResponseCallback<ResponseAddOrEditCustomer>() {
            @Override
            public void onRequestSuccess(ResponseAddOrEditCustomer result) {
                if (result.data.accountList != null && result.data.accountList.size() > 0) {
                    accountList = result.data.accountList;
                }
                if (customerManageParam.action.equals(ParameterConstants.ACTION_TYPE_EDIT)) {
                    customerManageParam.accountDto = result.data.walHouse;
                    setViews();
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

    public void selectxType() {
        String[] suppliers = new String[accountList.size()];
        int i = 0;
        for (AccountBean ppl : accountList) {
            suppliers[i++] = ppl.name;
        }
        AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setItems(suppliers, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                customerManageParam.accountDto.leadingAccount = accountList.get(which).accountId;
                btn_manageButton.setText(accountList.get(which).name);
                dialog.dismiss();
            }
        }).show();
    }

    @Override
    protected void setLayoutResId() {
        this.layoutResId = R.layout.activity_add_or_edite_warehouse;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.right) {
            saveStaff();
        }
    }

    //保存员工信息
    private void saveStaff() {
        if (new ValidateUtils().validat(new ValidateUtils.ValidateInterface() {
                                            @Override
                                            public void onFailed(String message) {
                                                showToast(message);
                                            }
                                        }, et_nameEditText,
                btn_manageButton,
                et_sortEditText,
                et_remarkEditText)) {
            showDialog();
            customerManageParam.accountDto.name = et_nameEditText.getText().toString();
            customerManageParam.accountDto.status = cb_is_availableCheckBox.isChecked() ? "1" : "0";
            customerManageParam.accountDto.orders = et_sortEditText.getText().toString();
            customerManageParam.accountDto.memo = et_remarkEditText.getText().toString();

            AddOrEditCustomerRequest.addOrEdit(customerManageParam, new ResponseCallback<ResponseAddOrEditCustomer>() {
                @Override
                public void onRequestSuccess(ResponseAddOrEditCustomer result) {
                    hideDialog();
                    showToast("保存成功");
                    setResult(RESULT_OK);
                    finish();
                }

                @Override
                public void onReuquestFailed(ErrorBase error) {
                    hideDialog();
                    showToast(error.message);
                }
            });

        }
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        int id = compoundButton.getId();
        if (id == R.id.cb_is_available) {//是否启用账号

        }
    }
}
