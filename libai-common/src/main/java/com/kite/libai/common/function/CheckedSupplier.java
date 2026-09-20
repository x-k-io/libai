package com.kite.libai.common.function;



import java.io.Serializable;

@FunctionalInterface
public interface CheckedSupplier<T> extends Serializable {

    /**
     * Run the Supplier
     *
     * @return T
     * @throws Throwable CheckedException
     */
    
    T get() throws Throwable;

}
