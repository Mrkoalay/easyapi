package cn.easyapi.rest.utils.constants;

/**
 * @author: yangtc
 * @description: 通用常量类, 单个业务的常量请单开一个类, 方便常量的分类管理
 * @date: 2017/10/24 10:15
 */
public class Constants {

    public static final String SUCCESS_CODE = "100";
    public static final String SUCCESS_MSG = "请求成功";

    /**
     * session中存放用户信息的key值
     */
    public static final String SESSION_USER_INFO = "userInfo";
    public static final String SESSION_USER_PERMISSION = "userPermission";

    public static Integer NON_PARENT_ID = 0;

    public static final String PATH_SAPARATOR = "|";
    public static final String SEMICOLON = ";";

    public static final String YYYYMMDD = "yyyyMMdd";
}
