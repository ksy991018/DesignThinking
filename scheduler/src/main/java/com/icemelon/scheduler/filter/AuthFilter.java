package com.icemelon.scheduler.contorller;


import com.icemelon.scheduler.dto.SessionToken;
import com.icemelon.scheduler.dto.UniqueCode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Component
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request =  (HttpServletRequest) servletRequest;

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getHttpServletMapping().getMatchValue();

        String[] spStr = url.split("/");

        System.out.println(url);

        String code = spStr[1];

        UniqueCode uniqueCode = UniqueCode.fromString(code);

        HttpSession session =  request.getSession();

        SessionToken token = SessionToken.fromSession(session);

        if (!token.getCode().equals(uniqueCode)) {

            response.setStatus(HttpStatus.UNAUTHORIZED.value());

            return;

        }

        filterChain.doFilter(servletRequest, servletResponse);

    }
}