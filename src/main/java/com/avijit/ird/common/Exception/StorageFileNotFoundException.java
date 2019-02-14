package com.avijit.ird.common.Exception;

/**
 * @author Avijit Barua
 * @created_on 12/24/18 at 12:07 PM
 * @project InternalResourcesDivision
 */
public class StorageFileNotFoundException extends StorageException {

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
