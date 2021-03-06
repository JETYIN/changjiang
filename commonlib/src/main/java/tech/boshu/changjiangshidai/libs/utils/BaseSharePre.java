package tech.boshu.changjiangshidai.libs.utils;

/**
 * Created by allipper on 2016-01-01.
 */
public class BaseSharePre {

    public static abstract class App {
        public static final String VERSION = "app_version";//版本信息

        public static final String ACCESS_TOKEN = "app_access_token";
        public static final String EXPIRES_IN = "app_expires_in";//过期时间
        public static final String UPDATE_DATE = "app_update_date";//获取Token时的本地时间

        public static final String START_IMAGE = "app_start_image";//启动页
        public static final String ISGUIDE = "app_isguide";//引导页
        public static final String MESSAGE_PUSH = "app_message_push";//消息推送
    }

    public static abstract class User {
        public static final String ACCOUNT_UID = "user_account_uid";//用户帐号ID
        public static final String ALIAS = "user_alias";//用户别称
        public static final String MEMBER_ID = "user_member_id";//用户会员ID
        public static final String MEMBER_LEVEL = "user_member_level";//用户等级
        public static final String MEMBER_STATUS = "user_member_status";//用户状态
        public static final String ID_NUMBER = "user_id_number";//用户ID号
        public static final String ID_TYPE = "user_id_type";//用户ID类型
        public static final String IS_CHECKID = "user_is_checkid";//用户是否验证过
        public static final String AVATAR_URL = "user_avatar_url";//用户头像url
        public static final String MOBILE = "user_mobile";//用户手机
        public static final String IS_CHECK_MOBILE = "user_is_check_mobile";//用户手机是否验证
        public static final String EMAIL = "user_email";//用户邮箱
        public static final String IS_CHECK_MAIL = "user_is_check_mail";//用户邮箱是否验证
        public static final String NAME = "user_name";//用户名
        public static final String GENDER = "user_gender";//用户性别
        public static final String UPDATE_TIME = "user_update_time";//用户更新时间
    }

}
