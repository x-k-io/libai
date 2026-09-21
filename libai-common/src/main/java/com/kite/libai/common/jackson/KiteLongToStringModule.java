package com.kite.libai.common.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.PackageVersion;

public class KiteLongToStringModule extends SimpleModule {

    public KiteLongToStringModule() {
        super("KiteLongToStringModule", PackageVersion.VERSION);
        this.addSerializer(Long.class, ToStringSerializer.instance);
        this.addSerializer(Long.TYPE, ToStringSerializer.instance);
    }
}
