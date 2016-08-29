package tech.boshu.changjiangshidai.ui.activity.basic.supplier.addoredit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.constants.ParameterConstants;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;
import tech.boshu.changjiangshidai.ui.activity.basic.supplier.manage.CustomerManageParam;
import tech.boshu.changjiangshidai.utils.ValidateUtils;

/**
 * Created by Stone on 2016/1/7.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class AddOrEditSupplierActivity extends BaseActivity implements
        CompoundButton.OnCheckedChangeListener {

    private EditText et_nameEditText;
    private EditText et_managerEditText;
    private CheckBox cb_is_availableCheckBox;
    private EditText et_discountEditText;
    private EditText et_mobileEditText;
    private EditText et_phoneEditText;
    private EditText et_emailEditText;
    private EditText et_faxEditText;
    private EditText et_bank_addressEditText;
    private EditText et_bank_accountEditText;
    private EditText et_bank_numberEditText;
    private Button btn_provinceButton;
    private Button btn_cityButton;
    private Button btn_districtButton;
    private EditText et_address_detailEditText;
    private EditText et_sortEditText;
    private EditText et_remarkEditText;

    private TextView tvTitle;
    private TextView tvSave;
    private String type;
    public List<String> customerType = new ArrayList<>(4);
    private CustomerManageParam customerManageParam = new CustomerManageParam();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    public void setViews() {
        setText(R.id.et_name, customerManageParam.accountDto.name);
        setText(R.id.et_manager, customerManageParam.accountDto.leadingPerson);
        cb_is_availableCheckBox.setChecked(customerManageParam.accountDto.status != null && customerManageParam.accountDto.status.equals("1"));
        setText(R.id.et_discount, customerManageParam.accountDto.discount);
        setText(R.id.et_mobile, customerManageParam.accountDto.mobile);
        setText(R.id.et_phone, customerManageParam.accountDto.phone);
        setText(R.id.et_email, customerManageParam.accountDto.email);
        setText(R.id.et_fax, customerManageParam.accountDto.fix);
        setText(R.id.et_bank_address, customerManageParam.accountDto.bank);
        setText(R.id.et_bank_account, customerManageParam.accountDto.bankAccount);
        setText(R.id.et_bank_number, customerManageParam.accountDto.bankNo);
        setText(R.id.btn_province, customerManageParam.accountDto.province);
        setText(R.id.btn_city, customerManageParam.accountDto.city);
        setText(R.id.btn_district, customerManageParam.accountDto.area);
        setText(R.id.et_address_detail, customerManageParam.accountDto.address);
        setText(R.id.et_sort, customerManageParam.accountDto.orders);
        setText(R.id.et_remark, customerManageParam.accountDto.memo);
    }

    public void initView() {
        et_nameEditText = (EditText) findViewById(R.id.et_name);
        et_managerEditText = (EditText) findViewById(R.id.et_manager);
        cb_is_availableCheckBox = (CheckBox) findViewById(R.id.cb_is_available);
        et_discountEditText = (EditText) findViewById(R.id.et_discount);
        et_mobileEditText = (EditText) findViewById(R.id.et_mobile);
        et_phoneEditText = (EditText) findViewById(R.id.et_phone);
        et_emailEditText = (EditText) findViewById(R.id.et_email);
        et_faxEditText = (EditText) findViewById(R.id.et_fax);
        et_bank_addressEditText = (EditText) findViewById(R.id.et_bank_address);
        et_bank_accountEditText = (EditText) findViewById(R.id.et_bank_account);
        et_bank_numberEditText = (EditText) findViewById(R.id.et_bank_number);
        btn_provinceButton = (Button) findViewById(R.id.btn_province);
        btn_cityButton = (Button) findViewById(R.id.btn_city);
        btn_districtButton = (Button) findViewById(R.id.btn_district);
        et_address_detailEditText = (EditText) findViewById(R.id.et_address_detail);
        et_sortEditText = (EditText) findViewById(R.id.et_sort);
        et_remarkEditText = (EditText) findViewById(R.id.et_remark);
        tvTitle = (TextView) findViewById(R.id.title);
        tvSave = (TextView) findViewById(R.id.right);

        tvSave.setText("保存");
        tvSave.setOnClickListener(this);
        setOnclickListener(R.id.btn_province, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectProvince();
            }
        });
        setOnclickListener(R.id.btn_city, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectCity();
            }
        });
        setOnclickListener(R.id.btn_district, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectDistict();
            }
        });
        type = getIntent().getStringExtra(ParameterConstants.PARAM_ACTION_TYPE);
        if (type.equals(ParameterConstants.ACTION_TYPE_EDIT)) {
            tvTitle.setText("供应商编辑");
            customerManageParam.action = ParameterConstants.ACTION_TYPE_EDIT;
            customerManageParam.accountDto.id = getIntent().getStringExtra(ParameterConstants.PRAMA_COMMON_VALUE);
        } else if (type.equals(ParameterConstants.ACTION_TYPE_ADD)) {
            tvTitle.setText("新增供应商");
            customerManageParam.action = ParameterConstants.ACTION_TYPE_ADD;
            customerManageParam.accountDto.status = "1";
            cb_is_availableCheckBox.setChecked(true);
        }
        prepareDatas();
        customerType.add("上海");
        customerType.add("广州");
    }

    @Override
    protected void setLayoutResId() {
        this.layoutResId = R.layout.activity_add_or_edite_supplier;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.right) {
            saveCustomer();
        }
    }


    public void prepareDatas() {
        showDialog();
        AddOrEditCustomerRequest.prepareDatas(customerManageParam, new ResponseCallback<ResponseAddOrEditCustomer>() {
            @Override
            public void onRequestSuccess(ResponseAddOrEditCustomer result) {
                if (customerManageParam.action.equals(ParameterConstants.ACTION_TYPE_EDIT)) {
                    customerManageParam.accountDto = result.data.provider;
                    setViews();
                }
//                if (result.data.customerType != null && result.data.customerType.size() > 0) {
//                    customerType = result.data.customerType;
//                }
                hideDialog();
            }

            @Override
            public void onReuquestFailed(ErrorBase error) {
                hideDialog();
                showToast(error.message);
            }
        });
    }


    public void selectProvince() {
        String[] suppliers = new String[]{"上海","广州"};
//        int i = 0;
//        for (ResponseAddOrEditCustomer.DataEntity.CustomerTypeEntity ppl : customerType) {
//            suppliers[i++] = ppl.name;
//        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(suppliers, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                customerManageParam.accountDto.province = customerType.get(which);
                btn_provinceButton.setText(customerType.get(which));
                dialog.dismiss();
            }
        }).show();
    }

    public void selectCity() {
        final String[] suppliers = new String[]{"上海","广州"};
//        int i = 0;
//        for (ResponseAddOrEditCustomer.DataEntity.CustomerTypeEntity ppl : customerType) {
//            suppliers[i++] = ppl.name;
//        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(suppliers, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                customerManageParam.accountDto.city = suppliers[which];
                btn_cityButton.setText(suppliers[which]);
                dialog.dismiss();
            }
        }).show();
    }

    public void selectDistict() {
        String[] suppliers = new String[]{"上海","广州"};
//        int i = 0;
//        for (ResponseAddOrEditCustomer.DataEntity.CustomerTypeEntity ppl : customerType) {
//            suppliers[i++] = ppl.name;
//        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(suppliers, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                customerManageParam.accountDto.area = customerType.get(which);
                btn_districtButton.setText(customerType.get(which));
                dialog.dismiss();
            }
        }).show();
    }

    //保存员工信息
    private void saveCustomer() {
        if (new ValidateUtils().validat(new ValidateUtils.ValidateInterface() {
                                            @Override
                                            public void onFailed(String message) {
                                                showToast(message);
                                            }
                                        }, et_nameEditText,
                et_managerEditText,
                et_discountEditText,
                et_mobileEditText,
                et_phoneEditText,
                et_emailEditText,
                et_faxEditText,
                et_bank_addressEditText,
                et_bank_accountEditText,
                et_bank_numberEditText,
                btn_provinceButton,
                btn_cityButton,
                btn_districtButton,
                et_address_detailEditText,
                et_sortEditText,
                et_remarkEditText)) {
            showDialog();
            customerManageParam.accountDto.name = et_nameEditText.getText().toString();
            customerManageParam.accountDto.status = cb_is_availableCheckBox.isChecked() ? "1" : "0";
            customerManageParam.accountDto.discount = et_discountEditText.getText().toString();
            customerManageParam.accountDto.mobile = et_mobileEditText.getText().toString();
            customerManageParam.accountDto.email = et_emailEditText.getText().toString();
            customerManageParam.accountDto.fix = et_faxEditText.getText().toString();
            customerManageParam.accountDto.bank = et_bank_addressEditText.getText().toString();
            customerManageParam.accountDto.bankAccount = et_bank_accountEditText.getText().toString();
            customerManageParam.accountDto.bankNo = et_bank_numberEditText.getText().toString();
            customerManageParam.accountDto.province = btn_provinceButton.getText().toString();
            customerManageParam.accountDto.city = btn_cityButton.getText().toString();
            customerManageParam.accountDto.area = btn_districtButton.getText().toString();
            customerManageParam.accountDto.address = et_address_detailEditText.getText().toString();
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
