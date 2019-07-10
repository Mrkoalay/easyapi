package cn.easyapi.common.utils;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author efanhome
 * @since 2018/12/25
 */
public class BeanValidator {

    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public static <T> Map<String, String> validate(T t, Class... groups) {
        Validator validator = validatorFactory.getValidator();
        Set validateResult = validator.validate(t, groups);
        if (validateResult.isEmpty()) {
            return Collections.emptyMap();
        } else {
            LinkedHashMap<String, String> errors = new LinkedHashMap<>();
            for (Object aValidateResult : validateResult) {
                ConstraintViolation violation = (ConstraintViolation) aValidateResult;
                String message = violation.getMessage();
                if (message.matches("\\{[0-9a-zA-Z-_]+}")) {
                    message = message.substring(1, message.length() - 1);
                    message = I18N.getMessage(message);
                }
                errors.put(violation.getPropertyPath().toString(), message);
            }
            return errors;
        }
    }

    public static Map<String, String> validateList(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException();
        }
        Iterator iterator = collection.iterator();
        Map errors;

        do {
            if (!iterator.hasNext()) {
                return Collections.emptyMap();
            }
            Object object = iterator.next();
            errors = validate(object);
        } while (errors.isEmpty());

        return errors;
    }

    public static Map<String, String> validateObject(Object... objects) {
        return validateList(Arrays.asList(objects));
    }
}
