package tech.boshu.changjiangshidai.ui.activity.basic.product.productmanage;

import com.google.gson.Gson;

import tech.boshu.changjiangshidai.bean.BaseRequestParam;

/**
 * 商品采购请求的参数
 * Created by allipper on 16/1/18.
 */
public class ProductRequestParam extends BaseRequestParam {
    public String companyId = "2222"; //商户主键	Y
    public String status;//	商品状态0：禁用，1：启用
    public String strId;//货号，名称，条码
    public String brandId = "";//品牌Id
    public String catalogId = "";//分类Id

    public String toString() {
        return new Gson().toJson(this);
    }
}
