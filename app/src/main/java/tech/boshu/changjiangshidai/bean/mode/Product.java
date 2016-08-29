package tech.boshu.changjiangshidai.bean.mode;

import java.util.List;

/**
 * Created by zoulinlin on 16/1/8.
 */
public class Product {
    /*商品名称*/
    public String name;
    /*商品名称*/
    public String productName;
    /*采购价*/
    public double purchasePrice;
    /*批发价*/
    public double wholesalePrice;
    /*零售价*/
    public double retailPrice;
    /*品牌*/
    public String brand;
    //规格
    public String spec;
    //产地
    public String address;
    //库存
    public String storeNum;
    /*分类*/
    public String catalog;
    /*商品货号*/
    public String goodsNo;
    /*商品的主键*/
    public int id;
    //选择数量
    public int selected_number;
    //图片
    public String picture;

    //是否选择
    public boolean isselected;

    public Double discount = 100.00;
    //总库存
    public int mergerNum;



    public List<ProudctIcon> picList;
    public List<ProductStore> stroeList;
}
