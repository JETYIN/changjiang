package tech.boshu.changjiangshidai.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.ui.activity.basic.account.manage.AccountManageActivity;
import tech.boshu.changjiangshidai.ui.activity.basic.customer.manage.CustomerManageActivity;
import tech.boshu.changjiangshidai.ui.activity.basic.product.productmanage.ProductManageActivity;
import tech.boshu.changjiangshidai.ui.activity.basic.setting.SettingHelpActivity;
import tech.boshu.changjiangshidai.ui.activity.basic.supplier.manage.SupplierManageActivity;
import tech.boshu.changjiangshidai.ui.activity.basic.warehouse.manage.WarehouseManageActivity;

/**
 * Created by zoulinlin on 16/1/1.
 */
public class BasicPageFragment extends Fragment implements View.OnClickListener {
    private ImageButton ibSetting;
    private RelativeLayout manageProduct;
    private RelativeLayout manageStock;
    private RelativeLayout manageAccount;
    private RelativeLayout manageCustomer;
    private RelativeLayout manageSupplier;


    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.basic_page, null);
        ibSetting = (ImageButton) view.findViewById(R.id.setting);
        manageProduct = (RelativeLayout) view.findViewById(R.id.manageProduct);
        manageStock = (RelativeLayout) view.findViewById(R.id.manageStock);
        manageAccount = (RelativeLayout) view.findViewById(R.id.manageAccount);
        manageCustomer = (RelativeLayout) view.findViewById(R.id.manageCustomer);
        manageSupplier = (RelativeLayout) view.findViewById(R.id.manageSupplier);
        ibSetting.setOnClickListener(this);
        manageProduct.setOnClickListener(this);
        manageStock.setOnClickListener(this);
        manageAccount.setOnClickListener(this);
        manageCustomer.setOnClickListener(this);
        manageSupplier.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.setting) {
            startActivity(new Intent(getActivity(), SettingHelpActivity.class));
        } else if (id == R.id.manageProduct) {
            startActivity(new Intent(getActivity(), ProductManageActivity.class));
        } else if (id == R.id.manageStock) {
            startActivity(new Intent(getActivity(), WarehouseManageActivity.class));
        } else if (id == R.id.manageAccount) {
            startActivity(new Intent(getActivity(), AccountManageActivity.class));
        } else if (id == R.id.manageCustomer) {
            startActivity(new Intent(getActivity(), CustomerManageActivity.class));
        } else if (id == R.id.manageSupplier) {
            startActivity(new Intent(getActivity(), SupplierManageActivity.class));
        }
    }
}
