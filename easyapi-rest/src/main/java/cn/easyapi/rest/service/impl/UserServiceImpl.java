package cn.easyapi.rest.service.impl;

import cn.easyapi.common.constants.ColumConstants;
import cn.easyapi.common.entity.User;
import cn.easyapi.common.utils.ToolUtil;
import cn.easyapi.rest.dao.UserDao;
import cn.easyapi.rest.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author yangtc
 * @Description 用户 服务实现类
 * @Date 20:24 2018/12/17
 * @Param
 * @return
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public User checkLogin(String username, String password) {
        QueryWrapper condition = new QueryWrapper();
        condition.eq(ColumConstants.USERNAME, username);
        if (StringUtils.isNotBlank(password)) {
            String md5Pwd = ToolUtil.encryptPassword(password);
            condition.eq(ColumConstants.PASSWORD, md5Pwd);
        }
        return getOne(condition);
    }


}
