package cn.easyapi.rest.controller;

import cn.easyapi.common.utils.Response;
import cn.easyapi.rest.param.UserParam;
import cn.easyapi.rest.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 用户控制层
 * @Author yangtc
 * @Date 2018/12/17 20:06
 */
@Api(value = "用户信息处理相关接口", description = "用户信息处理相关接口")
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户信息", notes = "用户信息")
    @PostMapping("/test")
    public Response shopUser(@RequestBody @ApiParam(name="用户信息",value="传入json格式",required=true)UserParam userParam) {
        return Response.success().put(userService.list());
    }
}
