package tech.boshu.changjiangshidai.ui.activity.start;

import android.app.Activity;
import android.text.TextUtils;
import android.view.animation.Animation;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wafersystems.wfauthorize.authorize.WaferLogin;

import tech.boshu.changjiangshidai.libs.activity.BasePresenter;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;

/**
 * Created by allipper on 2016-01-01.
 */
public class StartPresenter extends BasePresenter implements IStartPresenter, Animation
        .AnimationListener, WaferLogin.OnAuthResultListener {

    private IStartView iStartView;
    private IStartInteractor iStartInteractor;


    public StartPresenter(IStartView iStartView) {
        super(iStartView);
        this.iStartView = iStartView;
        this.iStartInteractor = new StartInteractorImpl();
    }

    @Override
    public void onAnimationStart(Animation animation) {
        iStartView.setLoadingView("");
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        iStartView.hideLoadingView();
        iStartInteractor.setActivityContext((Activity) iStartView);
//        iStartInteractor.init(this);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onAuthSuccess(String s) {
        if (!TextUtils.isEmpty(s)) {
            ResponseAuth responseAuth = new Gson().fromJson(s, new TypeToken<ResponseAuth>() {
            }.getType());
            iStartInteractor.getUserInfo(responseAuth, new ResponseCallback<ResponseUserInfo>() {
                @Override
                public void onRequestSuccess(ResponseUserInfo result) {
                    iStartInteractor.saveUserInfo(result.data);
                    iStartView.showToast(new Gson().toJson(result.data));
                    iStartView.showToast("认证成功");
                }

                @Override
                public void onReuquestFailed(ErrorBase error) {

                }
            });
            iStartInteractor.enter();
        }
    }

    @Override
    public void onAuthFailed(int i) {
        iStartView.showToast("认证失败");
        iStartInteractor.enter();
    }
}
