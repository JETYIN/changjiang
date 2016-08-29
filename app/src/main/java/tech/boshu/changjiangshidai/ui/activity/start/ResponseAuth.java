package tech.boshu.changjiangshidai.ui.activity.start;

/**
 * Created by allipper on 16/1/18.
 */
public class ResponseAuth {


    /**
     * access_token : 88e4c63c-7312-4a9e-8340-10a62adc3ea0
     * token_type : bearer
     * refresh_token : 64f0b301-29e0-4184-8154-9ae851dd1358
     * scope : read
     * expires_in : 38628
     */

    public TokenEntity token;
    /**
     * token : 381D2AEF1CE8055D97D195899AC9270778B213906979E55AC0683309136213A0644CA070F2F44C48690ADFB17B88F805
     * userid : zhangzhen@wafersystems.com
     * username : 张振
     */

    public UserEntity user;

    public static class TokenEntity {
        public String access_token;
        public String token_type;
        public String refresh_token;
        public String scope;
        public int expires_in;
    }

    public static class UserEntity {
        public String token;
        public String userid;
        public String username;
    }

    public String toUrl(){
        return String.format("http://120.27.142.50/helper/oauth/me?access_token=%1$s&token=%2$s",token.access_token,user.token);
    }
}
