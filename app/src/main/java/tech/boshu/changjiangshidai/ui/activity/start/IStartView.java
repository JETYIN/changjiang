package tech.boshu.changjiangshidai.ui.activity.start;

import tech.boshu.changjiangshidai.libs.activity.IBaseView;

/**
 * Created by allipper on 2016-01-01.
 */
public interface IStartView extends IBaseView{
    void setLoadingView(String url);
    void hideLoadingView();
}
