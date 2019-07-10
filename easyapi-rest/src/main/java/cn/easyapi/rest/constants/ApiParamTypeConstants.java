package cn.easyapi.rest.constants;

/**
 * @Description swagger 中 apiparamtype 常量
 * @Author yangtc
 * @Date 2018/12/23 12:42
 */
public class ApiParamTypeConstants {
    // 请求参数放置于Request Header，使用@RequestHeader获取
    public static String HEADER = "header";
    // 请求参数放置于请求地址，使用@RequestParam获取
    public static String QUERY = "query";
    //（用于restful接口）-->请求参数的获取：@PathVariable
    public static String PATH = "path";
    public static String BODY = "body";
    public static String FORM = "form";

}
