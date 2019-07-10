package cn.easyapi.rest.generator;

import cn.easyapi.rest.utils.HttpRequestUtil;

/**
 * @Description TODO
 * @Author yangtc
 * @Date 2019/2/22 20:39
 */
public class TestMain {
    public static void main(String[] args) {
        try {
            String url = "http://[fd15:4ba5:5a2b:1008:20c:29ff:fe34:26e3]:2379/health";
            System.out.println(HttpRequestUtil.httpGet(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
