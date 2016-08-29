package tech.boshu.changjiangshidai.utils;

import android.app.Activity;
import android.content.Intent;

import tech.boshu.changjiangshidai.ui.activity.business.BillFilterByTimeActivity;
import tech.boshu.changjiangshidai.ui.activity.business.ClientArrearsActivity;
import tech.boshu.changjiangshidai.ui.activity.business.ClientCheckActivity;
import tech.boshu.changjiangshidai.ui.activity.business.ClientFilterActivity;
import tech.boshu.changjiangshidai.ui.activity.business.InventoryDetailListActivity;
import tech.boshu.changjiangshidai.ui.activity.business.ManagerSurveyActivity;
import tech.boshu.changjiangshidai.ui.activity.business.ManagerSurveySearchActivity;
import tech.boshu.changjiangshidai.ui.activity.business.PurchaseDetailListActivity;
import tech.boshu.changjiangshidai.ui.activity.business.PurchaseDetailSearchActivity;
import tech.boshu.changjiangshidai.ui.activity.business.SaleDetailListActivity;
import tech.boshu.changjiangshidai.ui.activity.business.SaleDetailSearchActivity;
import tech.boshu.changjiangshidai.ui.activity.business.SalePurchaseCompareActivity;
import tech.boshu.changjiangshidai.ui.activity.business.SalePurchaseCompareSearchActivity;
import tech.boshu.changjiangshidai.ui.activity.business.saleschart.SalesChartActivity;
import tech.boshu.changjiangshidai.ui.activity.business.SupplierArrearsActivity;
import tech.boshu.changjiangshidai.ui.activity.business.SupplierCheckActivity;
import tech.boshu.changjiangshidai.ui.activity.business.SupplierFilterActivity;

/**
 * Created by apple on 16/1/7.
 */
public class UIHelper {

    public static void showClientFilterActivity(Activity activity) {
        Intent intent = new Intent(activity, ClientFilterActivity.class);
        activity.startActivity(intent);
    }

    public static void showClientArrearsActivity(Activity activity) {
        Intent intent = new Intent(activity, ClientArrearsActivity.class);
        activity.startActivity(intent);
    }

    public static void showBillFilterActivity(Activity activity) {
        Intent intent = new Intent(activity, BillFilterByTimeActivity.class);
        activity.startActivity(intent);
    }

    public static void showSupplierFilterActivity(Activity activity) {
        Intent intent = new Intent(activity, SupplierFilterActivity.class);
        activity.startActivity(intent);
    }

    public static void showSupplierArrearsActivity(Activity activity) {
        Intent intent = new Intent(activity, SupplierArrearsActivity.class);
        activity.startActivity(intent);
    }

    public static void showManagerSurveySearchActivity(Activity activity) {
        Intent intent = new Intent(activity, ManagerSurveySearchActivity.class);
        activity.startActivity(intent);
    }

    public static void showPurchaseDetailSearchActivity(Activity activity) {
        Intent intent = new Intent(activity, PurchaseDetailSearchActivity.class);
        activity.startActivity(intent);
    }

    public static void showClientCheckActivity(Activity activity) {
        Intent intent = new Intent(activity, ClientCheckActivity.class);
        activity.startActivity(intent);
    }

    public static void showSupplierCheckActivity(Activity activity) {
        Intent intent = new Intent(activity, SupplierCheckActivity.class);
        activity.startActivity(intent);
    }

    public static void showManagerSurveyActivity(Activity activity) {
        Intent intent = new Intent(activity, ManagerSurveyActivity.class);
        activity.startActivity(intent);
    }

    public static void showPurchaseDetailListActivity(Activity activity) {
        Intent intent = new Intent(activity, PurchaseDetailListActivity.class);
        activity.startActivity(intent);
    }

    public static void showSaleDetailListActivity(Activity activity) {
        Intent intent = new Intent(activity, SaleDetailListActivity.class);
        activity.startActivity(intent);
    }

    public static void showSalePurchaseCompareActivity(Activity activity) {
        Intent intent = new Intent(activity, SalePurchaseCompareActivity.class);
        activity.startActivity(intent);
    }

    public static void showInventoryDetailListActivity(Activity activity) {
        Intent intent = new Intent(activity, InventoryDetailListActivity.class);
        activity.startActivity(intent);
    }

    public static void showSaleDetailSearchActivity(Activity activity) {
        Intent intent = new Intent(activity, SaleDetailSearchActivity.class);
        activity.startActivity(intent);
    }

    public static void showSalePurchaseCompareSearchActivity(Activity activity) {
        Intent intent = new Intent(activity, SalePurchaseCompareSearchActivity.class);
        activity.startActivity(intent);
    }

    public static void showSaleChartActivity(Activity activity) {
        Intent intent = new Intent(activity, SalesChartActivity.class);
        activity.startActivity(intent);
    }
}
