

package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.result.CheckDetailDto;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)Adapter.java
 */

public class CheckDetailAdapter extends CommonAdapter<CheckDetailDto> {

    public CheckDetailAdapter(Context context, List<CheckDetailDto> datas) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
        this.layoutId = R.layout.adapter_check_detail;
    }


    public void convert(final ViewHolder holder, final CheckDetailDto checkDetailDto) {
        TextView nameTextView = holder.getView(R.id.name);
        TextView areaTextView = holder.getView(R.id.area);
        TextView standarTextView = holder.getView(R.id.standar);
        TextView beforeCountTextView = holder.getView(R.id.beforeCount);
        TextView beforeMoneyTextView = holder.getView(R.id.beforeMoney);
        TextView wonlossCountTextView = holder.getView(R.id.wonlossCount);
        TextView winlossMoneyTextView = holder.getView(R.id.winlossMoney);

        nameTextView.setText(checkDetailDto.product);
        areaTextView.setText("产地：" + checkDetailDto.place);
        standarTextView.setText("规格：" + checkDetailDto.spec);
        beforeCountTextView.setText("盘点前数量：" + checkDetailDto.stockAmount);
        beforeMoneyTextView.setText("盘点前金额： ￥" + checkDetailDto.price);
        wonlossCountTextView.setText("盈亏数量：" + checkDetailDto.differAmount);
        winlossMoneyTextView.setText("盈亏金额： ￥" + checkDetailDto.profitLoss);
    }
}