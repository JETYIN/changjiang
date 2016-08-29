package tech.boshu.changjiangshidai.libs.activity;

/**
 * 主要处理数据请求和业务逻辑
 * Created by allipper on 2015-12-31.
 */
public interface IBaseInteractor {

    /**
     * 处理back键 逻辑
     * @return
     */
    boolean processExitBusiness();

    void getDatas();
}
