package com.sweetitech.ird.configuration;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
*
 * @author Avijit Barua
 * @created_on 1/1/19 at 1:23 PM
 * @project ird
*/


public class CustomAccessDeniedHandler extends AccessDeniedHandlerImpl {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        if (accessDeniedException instanceof MissingCsrfTokenException
                || accessDeniedException instanceof InvalidCsrfTokenException) {

            if(request.getRequestURI().contains("login")){
                response.sendRedirect(request.getContextPath()+"/login");
            }
        }

        super.handle(request, response, accessDeniedException);

    }

}
