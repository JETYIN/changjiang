package tech.boshu.changjiangshidai.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.allocatehistory
        .StockAllocateHistoryActivity;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.checkorderhistory
        .CheckOrderHistoryActivity;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.flowsearch.StockFlowSearchActivity;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.purchasehistory.PurchaseHistoryActivity;

import tech.boshu.changjiangshidai.ui.activity.stockinfo.searchresult.StockSearchResultActivity;

/**
 * Created by zoulinlin on 16/1/1.
 */
public class WarehoursePageFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stock_page, null);
        view.findViewById(R.id.back).setVisibility(View.GONE);
        view.findViewById(R.id.stockSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StockSearchResultActivity.class);
                getActivity().startActivity(intent);
            }
        });
        view.findViewById(R.id.stockFlowSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StockFlowSearchActivity.class);
                getActivity().startActivity(intent);
            }
        });
        view.findViewById(R.id.check_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CheckOrderHistoryActivity.class);
                getActivity().startActivity(intent);
            }
        });
        view.findViewById(R.id.check_allocate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StockAllocateHistoryActivity.class);
                getActivity().startActivity(intent);
            }
        });
        view.findViewById(R.id.purchase_lr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PurchaseHistoryActivity.class);
                getActivity().startActivity(intent);
            }
        });
        view.findViewById(R.id.purchase_returned).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), tech.boshu.changjiangshidai.ui.activity.stockinfo.purchasereturnhistory.PurchaseHistoryActivity.class);
                getActivity().startActivity(intent);
            }
        });
        ((TextView) view.findViewById(R.id.title)).setText("库存");
        return view;
    }
}
