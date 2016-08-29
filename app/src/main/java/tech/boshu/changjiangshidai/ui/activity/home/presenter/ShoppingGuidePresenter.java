package tech.boshu.changjiangshidai.ui.activity.home.presenter;

import android.app.Activity;

import tech.boshu.changjiangshidai.libs.activity.BasePresenter;
import tech.boshu.changjiangshidai.ui.activity.home.interactor.IShoppingGuideInteractor;
import tech.boshu.changjiangshidai.ui.activity.home.view.IShoppingGuideView;

/**
 * @(#)ShoppingGuidePresenter.java
 *
 *
 * @author
 * @version 1.00 2016/1/6
 */


public class ShoppingGuidePresenter extends BasePresenter implements IShoppingGuidePresenter
{

	private IShoppingGuideView ishoppingGuideView;
	private IShoppingGuideInteractor ishoppingGuideInteractor;
	


	public ShoppingGuidePresenter(Activity context, IShoppingGuideView ishoppingGuideView, IShoppingGuideInteractor ishoppingGuideInteractor)
	{
		super(ishoppingGuideView);
		this.ishoppingGuideView = ishoppingGuideView;
		this.ishoppingGuideInteractor = ishoppingGuideInteractor;
	}
	

	public void setView(){
		ishoppingGuideView.setView();
	}

	public void initView(){
		ishoppingGuideView.initView();
	}
	
	
	
}