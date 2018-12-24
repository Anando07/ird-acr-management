package com.sweetitech.ird.common.Exception;

/**
 * @author Avijit Barua
 * @created_on 12/24/18 at 12:06 PM
 * @project InternalResourcesDivision
 */
public class NullPasswordException extends NullPointerException{
    public NullPasswordException(String s) {
        super(s);
    }
}