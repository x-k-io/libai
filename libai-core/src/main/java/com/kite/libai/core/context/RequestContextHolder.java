package com.kite.libai.core.context;

import lombok.Data;

@Data
public final class RequestContextHolder {

    private static final ThreadLocal<RequestContext> REQUEST_CONTEXT = new ThreadLocal<>();


    private RequestContextHolder() {
    }

    public static RequestContext get() {
        return REQUEST_CONTEXT.get();
    }

    public static void set(RequestContext context) {
        REQUEST_CONTEXT.set(context);
    }

    public static void remove() {
        REQUEST_CONTEXT.remove();
    }
}
