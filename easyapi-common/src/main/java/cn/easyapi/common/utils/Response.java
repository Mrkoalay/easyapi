package cn.easyapi.common.utils;

import cn.easyapi.common.enums.I18nEnum;

import java.text.MessageFormat;
import java.util.HashMap;

/**
 * @Author yangtc
 * @Description 返回数据
 * @Date 20:11 2018/12/17
 * @Param
 * @return
 **/
public class Response extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    private static String CODE = "code";
    private static String MSG = "msg";
    private static String DATA = "data";


    public Response() {
        put(CODE, I18nEnum.SUCCESS.getValue());
    }

    public static Response error() {
        return error(I18nEnum.SERVER_ERROR.getValue(), I18nEnum.SERVER_ERROR.getDesc());
    }

    public static Response error(I18nEnum i18nEnum) {
        return error(i18nEnum.getValue());
    }

    public static Response error(String msg) {
        return error(I18nEnum.SERVER_ERROR.getValue(), msg);
    }

    public static Response error(int value) {
        return error(value, I18nEnum.getDesc(value));
    }

    public static Response error(int value, Object[] params) {
        String msg = MessageFormat.format(I18nEnum.getDesc(value), params);
        return error(value, msg);
    }

    public static Response error(int code, String msg) {
        Response response = new Response();
        response.put(CODE, code);
        response.put(MSG, msg);
        return response;
    }

    public static Response success(int code, String msg) {
        Response response = new Response();
        response.put(CODE, code);
        response.put(MSG, msg);
        return response;
    }

    public static Response success() {
        return new Response();
    }

    public static Response success(int value, Object[] params) {
        String msg = MessageFormat.format(I18nEnum.getDesc(value), params);
        return success(value, msg);
    }

    public static Response success(I18nEnum i18nEnum) {
        Object[] params = {};
        return success(i18nEnum, params);
    }

    public static Response success(I18nEnum i18nEnum, Object[] params) {
        return success(i18nEnum.getValue(), params);
    }

    public Response put(Object value) {
        super.put(DATA, value);
        return this;
    }

    public static Response customI18N(Integer code, String msg, Object data) {
        Response response = new Response();
        response.put(CODE, code);
        response.put(MSG, msg);
        response.put(DATA, data);
        return response;
    }
}
