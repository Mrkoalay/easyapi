package cn.easyapi.rest.advice;

import cn.easyapi.common.entity.User;
import cn.easyapi.rest.utils.Session;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Object createdAt = getFieldValByName("createdAt", metaObject);
        Object updatedAt = getFieldValByName("updatedAt", metaObject);
        Object creator = getFieldValByName("creator", metaObject);
        if (createdAt == null) {
            setFieldValByName("createdAt", new Date(), metaObject);
        }
        if (updatedAt == null) {
            setFieldValByName("updatedAt", new Date(), metaObject);
        }
        if (creator == null) {
            User user = Session.getUserFromSession();
            String creatorStr = user == null ?"mapper":user.getUsername();
            setFieldValByName("creator",creatorStr, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updatedAt", new Date(), metaObject);
    }
}
