package com.avijit.ird.common.Exception;

/**
 * @author Avijit Barua
 * @created_on 12/24/18 at 12:08 PM
 * @project InternalResourcesDivision
 */
public class UserNotFoundException extends NotFoundException {

    private String message;
    private String page;

    public UserNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public UserNotFoundException(String message,String page) {
        super(message);
        this.message = message;
        this.page = page;
    }

    public UserNotFoundException() {
        super();
    }

    public String getErrorMessage() {
        return message;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
