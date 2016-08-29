package tech.boshu.changjiangshidai.ui.activity.sale;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;

/**
 * Created by Administrator on 2016/1/9.
 */
public class TurnoverRateActivity extends BaseActivity {
    private ImageButton ibHint;

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_turnover_rate;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ibHint = (ImageButton) findViewById(R.id.hint);
        ibHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_hint, null);
                Button Affirm = (Button) view.findViewById(R.id.affirm);
                final Dialog dialog = new AlertDialog.Builder(mContext).setView(view).show();
                Affirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}
