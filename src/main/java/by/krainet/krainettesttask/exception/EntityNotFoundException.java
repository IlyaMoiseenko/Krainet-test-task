package by.krainet.krainettesttask.exception;

import java.util.Map;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(Class clazz, Map<String, String> searchParamMap) {
        super(EntityNotFoundException.generateMessage(clazz.getSimpleName(), searchParamMap));
    }
    private static String generateMessage(String entity, Map<String, String> searchParam) {
        return entity + " was not found for parameters " + searchParam;
    }
}
