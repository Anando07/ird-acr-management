package com.sweetitech.ird.common.Exception;

/**
 * @author Avijit Barua
 * @created_on 12/24/18 at 12:07 PM
 * @project InternalResourcesDivision
 */
public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
