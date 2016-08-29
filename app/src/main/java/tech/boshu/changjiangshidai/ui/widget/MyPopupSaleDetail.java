package tech.boshu.changjiangshidai.ui.widget;


import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.interf.PopupMenuClickListener;


public class MyPopupSaleDetail extends PopupWindow {

	private Context context;
	private PopupMenuClickListener listener;

	public MyPopupSaleDetail(Context context){
		this.context=context;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View conentView = inflater.inflate(R.layout.layout_popup_sale_detail, null);
		iniView(conentView);
		this.setContentView(conentView);
		this.setWidth(LayoutParams.WRAP_CONTENT);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setFocusable(true);
		this.setTouchable(true);
		this.setOutsideTouchable(true);
		// 刷新状态
		this.update();
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0000000000);
		// 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
		this.setBackgroundDrawable(dw);
		// mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
		conentView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				MyPopupSaleDetail.this.dismiss();
				return false;
			}
		});
	}

	private void iniView(View conentView) {
		conentView.findViewById(R.id.sale).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				listener.onPopueMenuItemClick(v.getId());
			}
		});
		conentView.findViewById(R.id.single).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				listener.onPopueMenuItemClick(v.getId());
			}
		});
		conentView.findViewById(R.id.bill).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				listener.onPopueMenuItemClick(v.getId());
			}
		});
		conentView.findViewById(R.id.client).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				listener.onPopueMenuItemClick(v.getId());
			}
		});
		conentView.findViewById(R.id.saler).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				listener.onPopueMenuItemClick(v.getId());
			}
		});
	}

	public void setListener(PopupMenuClickListener listener){
		this.listener=listener;
	}



}
