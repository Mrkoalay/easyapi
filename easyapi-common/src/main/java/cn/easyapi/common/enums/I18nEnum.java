package cn.easyapi.common.enums;


import cn.easyapi.common.utils.I18N;

/**
 * @Author yangtc
 * @Description // 返回数据枚举
 * @Date 19:39 2018/12/20
 * @Param
 * @return
 **/
public enum I18nEnum {
    LANG(1, "lang"),
    UN_LOGIN(2, "un_login"),
    USER_EXISTS(3, "user_exists"),
    UNBIND_PHONE_NUMBER(4, "unbind_phone_number"),
    PASSWORD_ERROR(5, "password_error"),
    SUCCESS(200, "success"),
    AUTH_ERROR(401, "auth_error"),
    SERVER_ERROR(500, "server_error"),
    NO_PERMISSION(500, "no_permission"),
    PARAM_ERROR(10001, "param_error");


    private Integer value;
    private String desc;

    I18nEnum(final Integer value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getDesc(){
        return I18N.getMessage(desc);
    }
    public static String getDesc(int value){
        for( I18nEnum se : I18nEnum.values()){
            if(se.value == value){
                return I18N.getMessage(se.desc);
            }
        }
        return I18N.getMessage(SERVER_ERROR.desc);
    }
}
