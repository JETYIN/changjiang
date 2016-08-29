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
import tech.boshu.changjiangshidai.ui.activity.sale.GainOrLossHistoryActivity;
import tech.boshu.changjiangshidai.ui.activity.sale.ReturnHistoryActivity;
import tech.boshu.changjiangshidai.ui.activity.sale.SalesHistoryActivity;

/**
 * Created by zoulinlin on 16/1/1.
 */
public class SaledPageFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sales_page, null);
        view.findViewById(R.id.back).setVisibility(View.GONE);
        ((TextView) view.findViewById(R.id.title)).setText("销售");
        view.findViewById(R.id.sales).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SalesHistoryActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.return_sales).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ReturnHistoryActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.gain_loss_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GainOrLossHistoryActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
