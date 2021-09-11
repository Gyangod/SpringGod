package com.gyangod.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.gyangod.constants.SecurityConstant.FORBIDDEN_MESSAGE;

@Component
public class JWTAuthenticationEntryPoint extends Http403ForbiddenEntryPoint {

    private static final Log logger = LogFactory.getLog(JWTAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        if (logger.isDebugEnabled()) {
            logger.debug("Login Security has not been met. Rejecting access");
        }
        response.sendError(403, FORBIDDEN_MESSAGE);
    }
}
