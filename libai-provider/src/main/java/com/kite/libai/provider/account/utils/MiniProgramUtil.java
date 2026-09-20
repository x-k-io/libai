package com.kite.libai.provider.account.utils;

import java.security.Key;
import java.security.Security;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.kite.libai.provider.tripartite.feign.model.WeChatInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.kite.libai.common.utils.Charsets;
import com.kite.libai.common.utils.JsonUtils;
import com.kite.libai.provider.tripartite.feign.model.WeChatPhone;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MiniProgramUtil {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * 算法
     */
    private static final String KEY_ALGORITHM = "AES";

    /**
     * 解密微信加密开放数据
     *
     * @param encryptedData encryptedData
     * @param sessionKey    sessionKey
     * @param iv            iv
     * @return WeChatPhone
     */
    public static WeChatPhone decryptWeChatPhone(String encryptedData, String sessionKey, String iv) {
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            // 算法/模式/补码方式 转换的PKCS#7数据填充方式的Cipher对象
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            // 密钥
            Key keySpec = new SecretKeySpec(decoder.decode(sessionKey), KEY_ALGORITHM);
            byte[] ivBytes = decoder.decode(iv);
            // 用密钥初始化Cipher
            cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(ivBytes));
            String datastr = new String(cipher.doFinal(decoder.decode(encryptedData)), Charsets.UTF_8);
            log.info("[解密微信加密开放数据]，encryptedData:{},解密后数据datastr:{}", encryptedData, datastr);
            return JsonUtils.parse(datastr, WeChatPhone.class);
        } catch (Exception e) {
            log.error("[解密微信加密隐私数据]，sessionKey:{},解密数据失败error:{}", sessionKey, e);
        }
        return null;
    }

    /**
     * 解密微信加密开放数据
     *
     * @param encryptedData encryptedData
     * @param sessionKey    sessionKey
     * @param iv            iv
     * @return WeChatInfo
     */
    public static WeChatInfo decryptWeChatInfo(String encryptedData, String sessionKey, String iv) {
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            // 算法/模式/补码方式 转换的PKCS#7数据填充方式的Cipher对象
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            // 密钥
            Key keySpec = new SecretKeySpec(decoder.decode(sessionKey), KEY_ALGORITHM);
            byte[] ivBytes = decoder.decode(iv);
            // 用密钥初始化Cipher
            cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(ivBytes));
            String dataStr = new String(cipher.doFinal(decoder.decode(encryptedData)), Charsets.UTF_8);
            log.info("[解密微信加密隐私数据]，encryptedData:{},解密后数据datastr:{}", encryptedData, dataStr);
            return JsonUtils.parse(dataStr, WeChatInfo.class);
        } catch (Exception e) {
            log.error("[解密微信加密隐私数据]，sessionKey:{},解密数据失败error:{}", sessionKey, e);
        }
        return null;
    }
}
