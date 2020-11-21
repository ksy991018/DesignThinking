package com.icemelon.scheduler.filter;


import com.icemelon.scheduler.dto.NickName;
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


    public boolean canFiltered(String url) {

        url = url.trim();

        if (url.equals("/schedule") || url.equals("/schedule/")) return false;

        return true;
    }

    private boolean certificateCode(String url, SessionToken token) {

        String[] spStr = url.split("/");

        String code = spStr[2];

        UniqueCode uniqueCode = UniqueCode.fromString(code);

        return uniqueCode.equals(token.getCode());

    }

    public boolean certificateUser(String url, SessionToken token) {

        if (!url.matches("/schedule/.+/availability/.+$")) return true;

        NickName name = token.getId().getName();

        String urlName = url.split("/")[4].trim();

        return name.toString().equals(urlName);
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request =  (HttpServletRequest) servletRequest;

        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURI();


        if (!canFiltered(url)) {

            filterChain.doFilter(servletRequest,servletResponse);

            return;
        }


        HttpSession session =  request.getSession();

        SessionToken token = SessionToken.fromSession(session);

        if(!certificateCode(url, token) || !certificateUser(url, token)) {

            response.setStatus(HttpStatus.UNAUTHORIZED.value());

            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }
}
