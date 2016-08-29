package tech.boshu.changjiangshidai.ui.activity.basic.warehouse.manage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.WarehourseManageAdapter;
import tech.boshu.changjiangshidai.constants.ParameterConstants;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;
import tech.boshu.changjiangshidai.ui.activity.basic.warehouse.addoredit.AddOrEditWarehouseActivity;
import tech.boshu.changjiangshidai.ui.activity.basic.warehouse.WarehouseInitActivity;

/**
 * Created by Stone on 2016/1/6.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class WarehouseManageActivity extends BaseActivity implements
        AdapterView.OnItemClickListener {
    private static final int REQUST_ADD_OR_EDIT_WAREHOURSE = 100;

    private ImageButton ibAdd;
    private ListView listView;
    private WarehourseManageAdapter adapter;
    private List<CustomerBean> datas = new ArrayList<>();
    private Button btnInit;
    private CustomerManageParam customerManageParam = new CustomerManageParam();

    @Override
    protected void setLayoutResId() {
        this.layoutResId = R.layout.activity_warehouse_manage;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ibAdd = (ImageButton) findViewById(R.id.add);
        listView = (ListView) findViewById(R.id.list);
        btnInit = (Button) findViewById(R.id.btn_init);
        adapter = new WarehourseManageAdapter(mContext, datas);
        listView.setAdapter(adapter);
        ibAdd.setOnClickListener(this);
        btnInit.setOnClickListener(this);
        listView.setOnItemClickListener(this);
        getDatas();
    }

    public void getDatas() {
        showDialog();
        CustomerManageRequest.query(customerManageParam, new ResponseCallback<ResponseCustomer>() {
            @Override
            public void onRequestSuccess(ResponseCustomer result) {
                hideDialog();
                datas = result.data.storeList;
                adapter.setData(datas);
            }

            @Override
            public void onReuquestFailed(ErrorBase error) {
                hideDialog();
                showToast(error.message);
            }
        });
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.add) {
            Intent intent = new Intent(mContext, AddOrEditWarehouseActivity.class);
            intent.putExtra(ParameterConstants.PARAM_ACTION_TYPE, ParameterConstants.ACTION_TYPE_ADD);
            startActivityForResult(intent, ParameterConstants.ACTIVITY_ADD_OR_EDIT_RESULT);
        } else if (id == R.id.btn_init) {
            startActivity(new Intent(mContext, WarehouseInitActivity.class));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == ParameterConstants.ACTIVITY_ADD_OR_EDIT_RESULT) {//编辑或者新增员工
                getDatas();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(mContext, AddOrEditWarehouseActivity.class);
        intent.putExtra(ParameterConstants.PARAM_ACTION_TYPE, ParameterConstants.ACTION_TYPE_EDIT);
        intent.putExtra(ParameterConstants.PRAMA_COMMON_VALUE, datas.get(position).id);
        startActivityForResult(intent, REQUST_ADD_OR_EDIT_WAREHOURSE);
    }
}
