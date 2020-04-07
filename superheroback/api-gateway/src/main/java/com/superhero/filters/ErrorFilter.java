package com.superhero.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ERROR_TYPE;

@Component
@CommonsLog
public class ErrorFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0 ;
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().getThrowable() != null;
    }

    @Override
    public Object run() {
        Throwable throwable = RequestContext.getCurrentContext().getThrowable();
        log.error("Exception was thrown in filters: ", throwable);
        return null;
    }
}
