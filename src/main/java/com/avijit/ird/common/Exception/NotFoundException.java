package com.avijit.ird.common.Exception;

/**
 * @author Avijit Barua
 * @created_on 12/24/18 at 12:06 PM
 * @project InternalResourcesDivision
 */
public class NotFoundException extends Exception {
    private String message;
    private String page;

    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public NotFoundException(String message,String page) {
        super(message);
        this.message = message;
        this.page = page;
    }

    public NotFoundException() {
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