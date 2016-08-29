package tech.boshu.changjiangshidai.bean.mode;

/**
 * Created by zoulinlin on 16/1/8.
 */
public class PrOrder {
    /**
     * checkStatus : 0
     * companyId : 2222
     * createAccount : 0
     * createTime : 2015-12-28 17:35:20
     * discount : 0
     * id : PR1512280001
     * memo :
     * otherFee : 0
     * payEndTime : null
     * payMoney : 0
     * provider : XX供应商
     * providerId : 1
     * sendType : 0
     * setAccount :
     * settlement : 1
     * status : 0
     * store :
     * storeId : 1
     * totalMoney : 0
     * totalNum : 0
     * type : 1
     * updateAccount : 0
     * updateTime : null
     */

    public int checkStatus;
    public String companyId;
    public int createAccount;
    public String createTime;
    public double discount;
    public String id;
    public String memo;
    public int otherFee;
    public String payEndTime;
    public double payMoney;
    public double getMoney;
    public String provider;
    public String providerId;
    public int sendType;
    public String setAccount;
    public int settlement;
    public int status;
    public int storeId;
    public double totalMoney;
    public int totalNum;
    public int type;
    public int updateAccount;
    public String updateTime;
    public String account;
    public String sendtypeName;
    public String payEndTimeStr;



    public String customer;
    public String customerId;
//损益单信息

    public String billNo;    //损益单单号
    public String store;//仓库
    public String reportDate;//业务时间
    private String createTimeStr;

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public void setPayEndTimeStr(String payEndTimeStr) {
        this.payEndTimeStr = payEndTimeStr;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public String getPayEndTimeStr() {
        return payEndTimeStr;
    }
}
