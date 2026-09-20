package com.kite.libai.common.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Objects;

@UtilityClass
public class AesUtils {

    public static final Charset DEFAULT_CHARSET = Charsets.UTF_8;

    public static String genAesKey() {
        return RandomUtils.random(32, RandomType.ALL);
    }

    public static String encryptToHex(String content, String aesTextKey) {
        return HexUtils.encodeToString(encrypt(content, aesTextKey));
    }

    public static String encryptToHex(byte[] content, String aesTextKey) {
        return HexUtils.encodeToString(encrypt(content, aesTextKey));
    }

    public static String encryptToBase64(String content, String aesTextKey) {
        return Base64Utils.encodeToString(encrypt(content, aesTextKey));
    }

    public static String encryptToBase64(byte[] content, String aesTextKey) {
        return Base64Utils.encodeToString(encrypt(content, aesTextKey));
    }

    public static byte[] encrypt(String content, String aesTextKey) {
        return encrypt(content.getBytes(DEFAULT_CHARSET), aesTextKey);
    }

    public static byte[] encrypt(String content, Charset charset, String aesTextKey) {
        return encrypt(content.getBytes(charset), aesTextKey);
    }

    public static byte[] encrypt(byte[] content, String aesTextKey) {
        return encrypt(content, Objects.requireNonNull(aesTextKey).getBytes(DEFAULT_CHARSET));
    }


    public static String decryptFormHexToString(String content, String aesTextKey) {
        byte[] hexBytes = decryptFormHex(content, aesTextKey);
        if (hexBytes == null) {
            return null;
        }
        return new String(hexBytes, DEFAULT_CHARSET);
    }


    public static byte[] decryptFormHex(String content, String aesTextKey) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        return decryptFormHex(content.getBytes(DEFAULT_CHARSET), aesTextKey);
    }

    public static byte[] decryptFormHex(byte[] content, String aesTextKey) {
        return decrypt(HexUtils.decode(content), aesTextKey);
    }


    public static String decryptFormBase64ToString(String content, String aesTextKey) {
        byte[] hexBytes = decryptFormBase64(content, aesTextKey);
        if (hexBytes == null) {
            return null;
        }
        return new String(hexBytes, DEFAULT_CHARSET);
    }


    public static byte[] decryptFormBase64(String content, String aesTextKey) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        return decryptFormBase64(content.getBytes(DEFAULT_CHARSET), aesTextKey);
    }

    public static byte[] decryptFormBase64(byte[] content, String aesTextKey) {
        return decrypt(Base64Utils.decode(content), aesTextKey);
    }

    public static String decryptToString(byte[] content, String aesTextKey) {
        return new String(decrypt(content, aesTextKey), DEFAULT_CHARSET);
    }

    public static byte[] decrypt(byte[] content, String aesTextKey) {
        return decrypt(content, Objects.requireNonNull(aesTextKey).getBytes(DEFAULT_CHARSET));
    }

    public static byte[] encrypt(byte[] content, byte[] aesKey) {
        return aes(Pkcs7Encoder.encode(content), aesKey, Cipher.ENCRYPT_MODE);
    }

    public static byte[] decrypt(byte[] encrypted, byte[] aesKey) {
        return Pkcs7Encoder.decode(aes(encrypted, aesKey, Cipher.DECRYPT_MODE));
    }

    private static byte[] aes(byte[] encrypted, byte[] aesKey, int mode) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(aesKey, "AES");
            IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(aesKey, 0, 16));
            cipher.init(mode, keySpec, iv);
            return cipher.doFinal(encrypted);
        } catch (Exception e) {
            throw Exceptions.unchecked(e);
        }
    }
}
