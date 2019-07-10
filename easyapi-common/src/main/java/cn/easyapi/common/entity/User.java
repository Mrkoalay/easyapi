package cn.easyapi.common.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 *  用户信息
 * </p>
 *
 * @author yangtc
 * @since 2018-12-20
 */
@Data
@Accessors(chain = true)

public class User extends SuperEntity<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
	private String username;
    /**
     * 密码
     */
	private String password;

}
