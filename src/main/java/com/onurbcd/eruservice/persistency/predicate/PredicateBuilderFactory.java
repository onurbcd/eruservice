package com.onurbcd.eruservice.persistency.predicate;

import com.onurbcd.eruservice.service.enums.Error;
import com.onurbcd.eruservice.service.exception.ApiException;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;

import java.lang.reflect.InvocationTargetException;

@UtilityClass
public class PredicateBuilderFactory {

    public static <P extends BasePredicateBuilder> BasePredicateBuilder init(Class<P> clazz) {
        try {
            return clazz.getConstructor().newInstance();
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException |
                 InstantiationException | InvocationTargetException | ExceptionInInitializerError e) {

            throw new ApiException(Error.INTERNAL_SERVER_ERROR, e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
