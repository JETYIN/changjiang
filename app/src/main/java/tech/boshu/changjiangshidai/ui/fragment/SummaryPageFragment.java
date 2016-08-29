package tech.boshu.changjiangshidai.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.utils.UIHelper;

/**
 * Created by zoulinlin on 16/1/1.
 */
public class SummaryPageFragment extends Fragment implements View.OnClickListener {
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.logistics_page, null);
        view.findViewById(R.id.back).setVisibility(View.GONE);
        ((TextView) view.findViewById(R.id.title)).setText("统计");
        view.findViewById(R.id.client_check).setOnClickListener(this);
        view.findViewById(R.id.supplier_check).setOnClickListener(this);
        view.findViewById(R.id.manager_survey).setOnClickListener(this);
        view.findViewById(R.id.purchase).setOnClickListener(this);
        view.findViewById(R.id.sale).setOnClickListener(this);
        view.findViewById(R.id.sale_purchase).setOnClickListener(this);
        view.findViewById(R.id.inventory).setOnClickListener(this);
        view.findViewById(R.id.count_chart).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.client_check:
                UIHelper.showClientCheckActivity(getActivity());
                break;
            case R.id.supplier_check:
                UIHelper.showSupplierCheckActivity(getActivity());
                break;
            case R.id.manager_survey:
                UIHelper.showManagerSurveyActivity(getActivity());
                break;
            case R.id.purchase:
                UIHelper.showPurchaseDetailListActivity(getActivity());
                break;
            case R.id.sale:
                UIHelper.showSaleDetailListActivity(getActivity());
                break;
            case R.id.sale_purchase:
                UIHelper.showSalePurchaseCompareActivity(getActivity());
                break;
            case R.id.inventory:
                UIHelper.showInventoryDetailListActivity(getActivity());
                break;
            case R.id.count_chart:
                UIHelper.showSaleChartActivity(getActivity());
                break;
        }
    }
}
