package cn.easyapi.rest;

import cn.easyapi.rest.service.UserService;
import com.alibaba.fastjson.JSON;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author yangtc
 * @Date 2019/2/23 14:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RestTest {
    final String REST_API_URL = "http://localhost:8081";

    @Autowired
    private UserService userService;

    @Before
    public void init() {
        System.out.println("开始测试-----------------");
    }

    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }


    /**
     * @return
     * @Author yangtc
     * @Description // 构造名字
     * @Date 20:32 2019/3/9
     * @Param a001 - a030 输入 （a,1,30,3） 即可
     **/
    private List<String> buildNames(String startStr, Integer start, Integer end) {
        List<String> usernames = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            usernames.add(startStr + String.format("%03d", i));
        }
        System.out.println(JSON.toJSONString(usernames));
        return usernames;
    }


}
