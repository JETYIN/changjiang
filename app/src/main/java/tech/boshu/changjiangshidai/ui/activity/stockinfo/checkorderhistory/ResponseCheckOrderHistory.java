package tech.boshu.changjiangshidai.ui.activity.stockinfo.checkorderhistory;

import java.util.List;

import tech.boshu.changjiangshidai.bean.result.CheckOrderHistory;
import tech.boshu.changjiangshidai.libs.bean.ResponseBase;

/**
 * Created by Stone on 2016/1/28.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class ResponseCheckOrderHistory extends ResponseBase {

    public CheckOrderEntity data;

    public static class CheckOrderEntity {
        public int pageCount;
        public int totalCount;
        public int pageNo;
        public int pageSize;
        public List<CheckOrderHistory> checkList;
    }
}
