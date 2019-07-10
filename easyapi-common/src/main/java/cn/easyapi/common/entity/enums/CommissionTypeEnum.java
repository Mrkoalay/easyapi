package cn.easyapi.common.entity.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * @Description 审核类型枚举表
 * @Author yangtc
 * @Date 2019/1/6 20:26
 */
public enum CommissionTypeEnum implements IEnum {

    RECHARGE(1),    // 充值
    WITHDRAW(2);    // 提现
    private int code;

    CommissionTypeEnum(final int code){
        this.code = code;
    }
    @Override
    public Serializable getValue() {
        return this.code;
    }

}
