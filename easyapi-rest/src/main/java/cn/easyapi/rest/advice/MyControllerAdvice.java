package cn.easyapi.rest.advice;

import cn.easyapi.common.enums.I18nEnum;
import cn.easyapi.common.utils.I18N;
import cn.easyapi.common.utils.Response;
import cn.easyapi.rest.exception.ParamException;
import cn.easyapi.rest.exception.RollbackException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Map;

/**
 * @Author yangtc
 * @Description // 控制层增强器
 * @Date 11:29 2018/12/23
 * @Param
 * @return
 **/
@ControllerAdvice("cn.easyapi.rest.controller")
public class MyControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(MyControllerAdvice.class);

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     *
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        // model.addAttribute("test", "test");
    }

    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        logger.error("控制层异常!!!!!", ex);
        try {
            if (ex instanceof ParamException) {
                ParamException e = (ParamException) ex;
                return Response.customI18N(e.getCode(), e.getMessage(), e.getData());
            } else if (ex instanceof ConstraintViolationException) {
                String lastMessage = "";
                for (ConstraintViolation v : ((ConstraintViolationException) ex).getConstraintViolations()) {
                    try {
                        lastMessage += I18N.getMessage(v.getMessage());
                    } catch (Exception e) {
                        lastMessage += v.getMessage();
                    }
                }
                return Response.error(I18nEnum.PARAM_ERROR.getValue(), lastMessage);
            } else if (ex instanceof RollbackException) {
                return Response.error(((RollbackException) ex).getCode(), ex.getMessage());
            } else if (ex instanceof MissingServletRequestParameterException) {
                return Response.error(I18nEnum.PARAM_ERROR.getValue());
            }
            return Response.error();
        } catch (Exception e) {
            logger.error("二次异常", e);
            return Response.error();
        }
    }

}