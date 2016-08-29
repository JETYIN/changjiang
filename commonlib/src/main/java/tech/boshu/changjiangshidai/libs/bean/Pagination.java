package tech.boshu.changjiangshidai.libs.bean;

/**
 * Created by allipper on 2015/10/16.
 */
public class Pagination {
    //当前页
    public String pageNo = "1";
    //每页订单数量
    public String pageSize = "10";
    //总页数
    public String pageCount ="0";
    //订单总数
    public String totalCount="0";

    /**
     * 对比是否是最后一页
     *
     * @return
     */
    public boolean isLastPage() {
        return Integer.valueOf(pageNo) == (Integer.valueOf(pageCount) - 1) || (Integer.valueOf(pageNo )== 1 && Integer.valueOf(pageCount) == 1);
    }
}
