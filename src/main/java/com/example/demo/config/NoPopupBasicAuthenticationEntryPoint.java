package com.example.demo.config;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class NoPopupBasicAuthenticationEntryPoint implements AuthenticationEntryPoint {


    String localeMessage = "bad credential";
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //Response errorResponse = apiAdviceHelper.createErrorResponse(localeMessage);
        response.resetBuffer();
        response.setStatus(HttpStatus.OK.value());
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("Content-Type", "application/json");
        //PrintWriter out = response.getWriter();
        //out.print(gson.toJson(errorResponse));
        response.flushBuffer();
    }
}
