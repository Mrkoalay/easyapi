package cn.easyapi.rest.exception;

import cn.easyapi.common.enums.I18nEnum;

import java.text.MessageFormat;

public class RollbackException extends RuntimeException {

    private Integer code;

    private Object[] params;

    public RollbackException() {
        super();
    }

    public RollbackException(String message) {
        super(message);
        this.code = I18nEnum.SERVER_ERROR.getValue();
    }

    public RollbackException(I18nEnum i18nEnum) {
        super(i18nEnum.getDesc());
        this.code = i18nEnum.getValue();
    }

    public RollbackException(I18nEnum i18nEnum, Object ...params) {
        super(MessageFormat.format(i18nEnum.getDesc(), params));
        this.code = i18nEnum.getValue();
        this.params = params;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
