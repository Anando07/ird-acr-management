package com.sweetitech.ird.common.Exception;

/**
 * @author Avijit Barua
 * @created_on 12/24/18 at 12:05 PM
 * @project InternalResourcesDivision
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
