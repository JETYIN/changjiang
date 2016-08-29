package tech.boshu.changjiangshidai.ui.activity.start;

/**
 * Created by allipper on 16/1/20.
 */
public class ResponseUserInfo {

    /**
     * result : 0
     * data : {"resObj":{"userid":"cjtc0000000296@trial0030","username":"安沛","icon":"http://120.27.142.50/resources/headimg/trial0030/cjtc0000000296.jpg?rd=1453254096000","sex":"1","usertype":" "}}
     */

    public int result;
    /**
     * resObj : {"userid":"cjtc0000000296@trial0030","username":"安沛","icon":"http://120.27.142.50/resources/headimg/trial0030/cjtc0000000296.jpg?rd=1453254096000","sex":"1","usertype":" "}
     */

    public DataEntity data;


    public static class DataEntity {
        /**
         * userid : cjtc0000000296@trial0030
         * username : 安沛
         * icon : http://120.27.142.50/resources/headimg/trial0030/cjtc0000000296.jpg?rd=1453254096000
         * sex : 1
         * usertype :
         */

        public ResObjEntity resObj;


        public static class ResObjEntity {
            public String userid;
            public String username;
            public String icon;
            public String sex;
            public String usertype;
        }
    }
}
