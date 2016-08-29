package tech.boshu.changjiangshidai.ui.activity.sale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;

/**
 * Created by Administrator on 2016/1/9.
 */
public class RateOfPinActivity extends BaseActivity {
    private ImageButton ibMore;

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_rate_of_pin;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ibMore = (ImageButton) findViewById(R.id.more);
        ibMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RateOfPinActivity.this, TurnoverRateActivity.class);
                startActivity(intent);
            }
        });
    }
}
