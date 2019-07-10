package cn.easyapi.rest.exception;


import cn.easyapi.common.enums.I18nEnum;
import lombok.Getter;

/**
 * @author efanhome
 * @since 2018/12/25
 */
@Getter
public class ParamException extends RuntimeException {

    private Object data;
    private String message;
    private Integer code;

    public ParamException() {
        super();
    }

    public ParamException(I18nEnum i18nEnum) {
        super(i18nEnum.getDesc());
        this.code = i18nEnum.getValue();
        this.message = i18nEnum.getDesc();
    }

    public ParamException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ParamException(Integer code, String message, Object data) {
        this(code, message);
        this.data = data;
    }

    public ParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamException(Throwable cause) {
        super(cause);
    }

    protected ParamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
