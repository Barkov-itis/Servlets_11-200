package itis.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter("/*")
// @WebFilter({"/signIn", "/signUp", "/bag", "/calculator"})
// @WebFilter({"/update", "/insert",})

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        Boolean isAutenticated = false;
        Boolean sessionExist = session != null;
        Boolean isLoginPage = request.getRequestURI().equals("/signIn");


        if (sessionExist) {
            isAutenticated = (Boolean) session.getAttribute("autenticated");
            if(isAutenticated == null) {
                isAutenticated = false;
            }
        }

        // если авторизован и запрашивает не логин или если не авторизован и запрашивает логин
        if(isAutenticated && !isLoginPage || !isAutenticated && isLoginPage) {
            // отдаем то что хочет
            filterChain.doFilter(request, response);
        } else if (isAutenticated && isLoginPage) {
            // пользователь авторизован и просит страницу авторизации направляем на профиль
            response.sendRedirect("/minimal");
        } else {
            // не авторизован и просит другие страницы
            response.sendRedirect("/signIn");
        }
    }

    @Override
    public void destroy() {

    }
}
