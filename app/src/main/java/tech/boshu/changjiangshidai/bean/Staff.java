package tech.boshu.changjiangshidai.bean;

import com.google.gson.Gson;

/**
 * Created by Stone on 2016/1/7.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class Staff {
    //采购员角色
    public static final int STAFF_ROLE_BUYER = 0;
    //仓库管理员角色
    public static final int STAFF_ROLE_WAREHOUSE_KEEPER = 1;
    //销售员角色
    public static final int STAFF_ROLE_SALESMAN = 2;

    //员工姓名
    public String name;
    //员工邮箱
    public String email;
    //员工电话
    public String phone;
    //备注
    public String remark;
    //创建时间
    public String createDate;
    //是否启用该员工
    public boolean isAvailable;
    //员工所属角色
    public int role;

    public Staff() {
    }

    public Staff(String name,
                 String email,
                 String phone,
                 String remark,
                 String createDate,
                 boolean isAvailable,
                 int role) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.remark = remark;
        this.createDate = createDate;
        this.isAvailable = isAvailable;
        this.role = role;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
