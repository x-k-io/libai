package com.kite.libai.core.function;



import java.io.Serializable;

@FunctionalInterface
public interface CheckedConsumer<T> extends Serializable {

    /**
     * Run the Consumer
     *
     * @param t T
     * @throws Throwable UncheckedException
     */
    
    void accept( T t) throws Throwable;

}
