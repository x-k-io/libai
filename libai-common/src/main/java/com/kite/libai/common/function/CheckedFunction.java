package com.kite.libai.common.function;



import java.io.Serializable;

@FunctionalInterface
public interface CheckedFunction<T, R> extends Serializable {

    /**
     * Run the Function
     *
     * @param t T
     * @return R R
     * @throws Throwable CheckedException
     */
    
    R apply( T t) throws Throwable;

}
