package tech.boshu.changjiangshidai.bean.mode;

/**
 * Created by zoulinlin on 16/1/8.
 */
public class StoreBill {
    /*	变动数量*/
    public int differenceNum;
    /*剩余数量*/
    public int residueNum;
    /*	操作时间*/
    public String addTime;
    /*货号*/
    public String goodsNo;
    /*产地*/
    public String place;
    /*规格*/
    public String spec;
    /*单据编号*/
    public String originalNo;
    /*	1.采购 2采购退货 3销售 4销售退货*/
    public int type;

    public String getType(int type) {
        if (type == 1) {
            return "采购单";
        }
        if (type == 2) {
            return "采购退货单";
        }
        if (type == 3) {
            return "销售单";
        }
        if (type == 4) {
            return "销售退货单";
        }
        return "其他";
    }

}
