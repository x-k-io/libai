package com.kite.libai.core.function;


import java.io.Serializable;

@FunctionalInterface
public interface CheckedCallable<T> extends Serializable {

    /**
     * Run this callable.
     *
     * @return result
     * @throws Throwable CheckedException
     */

    T call() throws Throwable;
}
