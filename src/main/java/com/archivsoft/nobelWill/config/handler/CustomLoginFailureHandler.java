package com.archivsoft.nobelWill.config.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLoginFailureHandler implements AuthenticationFailureHandler {
//
//    @Autowired
//    private LoginService loginService;
//
//    @Autowired
//    private ComCodeProperty comCode;

    public CustomLoginFailureHandler() {
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String errorMsg = exception.getMessage();
//        if(errorMsg.equals(comCode.getProperty("login.MemberNotFound"))){
//            request.setAttribute("errorMsg", comCode.getProperty("login.MemberNotFoundRes"));
//        } else if(errorMsg.equals(comCode.getProperty("login.MemberNotApproved"))){
//            request.setAttribute("errorMsg", comCode.getProperty("login.MemberNotApprovedRes"));
//        } else if(errorMsg.equals(comCode.getProperty("login.PasswordCountOver"))){
//            request.setAttribute("errorMsg", comCode.getProperty("login.PasswordCountOverRes"));
//        } else if(errorMsg.equals(comCode.getProperty("login.BadCredentials"))){
//            request.setAttribute("errorMsg", comCode.getProperty("login.BadCredentialsRes"));
//            loginService.saveLoginFail(request.getParameterValues("id")[0]);
//        } else {
//            request.setAttribute("errorMsg", errorMsg);
//        }
        request.getRequestDispatcher("/login").forward(request, response);
    }
}
