package com.self.cloudserver.filter;

import com.self.cloudserver.config.RequestWrapper;
import com.self.cloudserver.constants.ApiUri;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * ChannelFilter
 * @author zp
 */
@WebFilter(urlPatterns = ApiUri.MODULE_URI_PREFIX + "/*", filterName = "channelFilter")
public class ChannelFilter implements Filter {
    FilterConfig filterConfig = null;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletRequest requestWrapper = null;
        if(servletRequest instanceof HttpServletRequest) {
            requestWrapper = new RequestWrapper((HttpServletRequest) servletRequest);
        }

        if(requestWrapper == null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(requestWrapper, servletResponse);
        }
    }

    @Override
    public void init(FilterConfig filterConfiguration) throws ServletException {
        this.filterConfig = filterConfiguration;
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

}
