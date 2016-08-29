package tech.boshu.changjiangshidai.ui.activity.common.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.ChooseDialogAdapter;
import tech.boshu.changjiangshidai.constants.ParameterConstants;
import tech.boshu.changjiangshidai.libs.net.HttpUtils;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;
import tech.boshu.changjiangshidai.libs.utils.ToastUtils;

/**
 * Created by allipper on 16/1/23.
 */
public class ChooseDialogActivity extends Activity {

    private static final String TAG = ChooseDialogActivity.class.getSimpleName();

    public static final int TYPE_BRAND = 1;
    public static final int TYPE_CATALOG = TYPE_BRAND + 1;
    public static final int TYPE_CUSTOMER = TYPE_CATALOG + 1;
    public static final int TYPE_PROVIDER = TYPE_CUSTOMER + 1;
    public static final int TYPE_STORE = TYPE_PROVIDER + 1;
    private Context mContext;
    private ListView listListView;
    private ProgressBar progressBar;
    private List<String> list = new ArrayList<>();
    private ChooseDialogAdapter adapter;

    /**
     * id : 1
     * name : 李维斯
     * status : 1
     */

    public static List<BrandTypeEntity> brandType;
    /**
     * id : 1
     * name : 222
     * type : 111
     */

    public static List<CatalogTypeEntity> catalogType;
    /**
     * companyId : 2222
     * id : 1
     * name : demaxiya
     * phone : 123123231312321
     * province : 北京市
     * status : 1
     * totalArrears : 4567
     * type : 1
     */

    public static List<CustomerListEntity> customerList;
    /**
     * companyId : 2222
     * id : 1
     * leadingPerson : 是
     * name : XX供应商
     * status : 1
     * totalArrears : 8245.2
     */

    public static List<ProviderListEntity> providerList;
    /**
     * companyId : 2222
     * id : 1
     * leadingAccount : 1
     * name : 4号仓库
     * orders : 11
     * status : 1
     */

    public static List<StroeListEntity> stroeList;
    private int type = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        setContentView(R.layout.activity_choose_dialog);
        listListView = (ListView) findViewById(R.id.list);
        progressBar = (ProgressBar) findViewById(R.id.loading);

        listListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                switch (type) {
                    case TYPE_BRAND:
                        intent.putExtra(ParameterConstants.PARAM_COMMON_SEARCH_RETURN_VALUE, brandType.get(i).id);
                        intent.putExtra(ParameterConstants.PARAM_COMMON_SEARCH_RETURN_NAME, brandType.get(i).name);
                        break;
                    case TYPE_CATALOG:
                        intent.putExtra(ParameterConstants.PARAM_COMMON_SEARCH_RETURN_VALUE, catalogType.get(i).id);
                        intent.putExtra(ParameterConstants.PARAM_COMMON_SEARCH_RETURN_NAME, catalogType.get(i).name);
                        break;
                    case TYPE_CUSTOMER:
                        intent.putExtra(ParameterConstants.PARAM_COMMON_SEARCH_RETURN_VALUE, customerList.get(i).id);
                        intent.putExtra(ParameterConstants.PARAM_COMMON_SEARCH_RETURN_NAME, customerList.get(i).name);
                        break;
                    case TYPE_PROVIDER:
                        intent.putExtra(ParameterConstants.PARAM_COMMON_SEARCH_RETURN_VALUE, providerList.get(i).id);
                        intent.putExtra(ParameterConstants.PARAM_COMMON_SEARCH_RETURN_NAME, providerList.get(i).name);
                        break;
                    case TYPE_STORE:
                        intent.putExtra(ParameterConstants.PARAM_COMMON_SEARCH_RETURN_VALUE, stroeList.get(i).id);
                        intent.putExtra(ParameterConstants.PARAM_COMMON_SEARCH_RETURN_NAME, stroeList.get(i).name);
                        break;
                    default:
                        finish();
                        break;
                }
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        prepareData();
    }

    private void prepareData() {
        type = getIntent().getIntExtra(ParameterConstants.PARAM_COMMON_SEARCH_TYPE, -1);
        switch (type) {
            case TYPE_BRAND:
                if (brandType == null || brandType.size() < 1) {
                    getDatas();
                } else {
                    setDataToAdapter();
                }
                break;
            case TYPE_CATALOG:
                if (catalogType == null || catalogType.size() < 1) {
                    getDatas();
                } else {
                    setDataToAdapter();
                }
                break;
            case TYPE_CUSTOMER:
                if (customerList == null || customerList.size() < 1) {
                    getDatas();
                } else {
                    setDataToAdapter();
                }
                break;
            case TYPE_PROVIDER:
                if (providerList == null || providerList.size() < 1) {
                    getDatas();
                } else {
                    setDataToAdapter();
                }
                break;
            case TYPE_STORE:
                if (stroeList == null || stroeList.size() < 1) {
                    getDatas();
                } else {
                    setDataToAdapter();
                }
                break;
            default:
                finish();
                break;
        }
    }

    private void setDataToAdapter() {
        list.clear();
        switch (type) {
            case TYPE_BRAND:
                for (BrandTypeEntity item : brandType) {
                    list.add(item.name);
                }
                break;
            case TYPE_CATALOG:
                for (CatalogTypeEntity item : catalogType) {
                    list.add(item.name);
                }
                break;
            case TYPE_CUSTOMER:
                for (CustomerListEntity item : customerList) {
                    list.add(item.name);
                }
                break;
            case TYPE_PROVIDER:
                for (ProviderListEntity item : providerList) {
                    list.add(item.name);
                }
                break;
            case TYPE_STORE:
                for (StroeListEntity item : stroeList) {
                    list.add(item.name);
                }
                break;
            default:
                finish();
                break;
        }
        adapter = new ChooseDialogAdapter(mContext, list);
        progressBar.setVisibility(View.GONE);
        listListView.setAdapter(adapter);
        listListView.setVisibility(View.VISIBLE);
    }

    private void getDatas() {
        CommonSearchRequest.getSearchFilters(new ResponseCallback<ResponseCommonSearch>() {
            @Override
            public void onRequestSuccess(ResponseCommonSearch result) {
                brandType = result.data.brandType;
                catalogType = result.data.catalogType;
                customerList = result.data.customerList;
                providerList = result.data.providerList;
                stroeList = result.data.stroeList;
                setDataToAdapter();
            }

            @Override
            public void onReuquestFailed(ErrorBase error) {
                ToastUtils.show(mContext, error.message);
            }
        });
    }

    @Override
    protected void onStop() {
        HttpUtils.cancelAll(TAG);
        super.onStop();
    }
}
