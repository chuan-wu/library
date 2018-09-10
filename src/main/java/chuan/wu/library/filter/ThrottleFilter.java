/*
 * Copyright (c) Chuan.Wu $today.date
 */

package chuan.wu.library.filter;

import chuan.wu.library.exception.GeneralException;
import chuan.wu.library.utils.StatusCode;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@Order(1)
public class ThrottleFilter implements Filter {
    RateLimiter rateLimiter = RateLimiter.create(10);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!rateLimiter.tryAcquire(1, TimeUnit.SECONDS)) {
            log.warn("Throttling, Too many requests!");
            response = (HttpServletResponse) response;
            ((HttpServletResponse) response).setStatus(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED.value());
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
