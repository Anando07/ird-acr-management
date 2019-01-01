package com.sweetitech.ird.SecurityConfig.component;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class RefreshTokenErrorHandler extends ResponseEntityExceptionHandler {


@ExceptionHandler(value = {HttpClientErrorException.class})
public void handleConflict(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse)throws IOException
{
    
    httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/logout");
}
}