package com.kite.libai.common.utils;

import java.util.Arrays;

public class Pkcs7Encoder {
    private static final int BLOCK_SIZE = 32;

    public static byte[] encode(byte[] src) {
        int count = src.length;
        // 计算需要填充的位数
        int amountToPad = BLOCK_SIZE - (count % BLOCK_SIZE);
        if (amountToPad == 0) {
            amountToPad = BLOCK_SIZE;
        }
        // 获得补位所用的字符
        byte pad = (byte) (amountToPad & 0xFF);
        byte[] pads = new byte[amountToPad];
        for (int index = 0; index < amountToPad; index++) {
            pads[index] = pad;
        }
        int length = count + amountToPad;
        byte[] dest = new byte[length];
        System.arraycopy(src, 0, dest, 0, count);
        System.arraycopy(pads, 0, dest, count, amountToPad);
        return dest;
    }

    public static byte[] decode(byte[] decrypted) {
        int pad = decrypted[decrypted.length - 1];
        if (pad < 1 || pad > BLOCK_SIZE) {
            pad = 0;
        }
        if (pad > 0) {
            return Arrays.copyOfRange(decrypted, 0, decrypted.length - pad);
        }
        return decrypted;
    }
}
