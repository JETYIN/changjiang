package tech.boshu.changjiangshidai.ui.activity.basic.setting.about;

import tech.boshu.changjiangshidai.libs.activity.BasePresenter;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)AboutPresenter.java
 */


public class AboutPresenter extends BasePresenter implements IAboutPresenter {

    private IAboutView iaboutView;
    private IAboutInteractor iaboutInteractor;

    public AboutPresenter(IAboutView iaboutView) {
        super(iaboutView);
        this.iaboutView = iaboutView;
        this.iaboutInteractor = new AboutInteractorImpl();
    }


    public void setView() {
        iaboutView.setView();
    }

    public void initView() {
        iaboutView.initView();
    }

    public void openQQ() {
    }

    public void openQQs() {
    }

    public void openWxs() {
    }

    public void openEmail() {
    }

    public void openPhone() {
    }

}