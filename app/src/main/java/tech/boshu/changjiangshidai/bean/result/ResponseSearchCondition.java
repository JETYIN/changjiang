package tech.boshu.changjiangshidai.bean.result;

import java.util.List;

import tech.boshu.changjiangshidai.bean.mode.Store;
import tech.boshu.changjiangshidai.libs.bean.ResponseBase;

/**
 * Created by Stone on 2016/1/25.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class ResponseSearchCondition extends ResponseBase {
    public EntryData data;

    public static class EntryData {
        public List<Brand> brandType;
        public List<Catalog> catalogType;
        public List<Customer> customerList;
        public List<Provider> providerList;
        public List<Store> stroeList;
    }
}
