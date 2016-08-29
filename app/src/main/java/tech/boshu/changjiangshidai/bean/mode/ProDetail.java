package tech.boshu.changjiangshidai.bean.mode;

import tech.boshu.changjiangshidai.libs.bean.ResponseBase;

/**
 * Created by lx02 on 2016/1/17.
 */
public class ProDetail extends ResponseBase {

    public Data data;

    public class Data {
        /*采购单明细主键*/
        int id;
        /*采购单单号*/
        String prId;
        /*商品主键*/
        int productId;
        /*商品数量*/
        int num;
        public Product product;

    }
}
