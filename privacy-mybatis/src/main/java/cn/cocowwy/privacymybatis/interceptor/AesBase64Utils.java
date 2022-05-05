package cn.cocowwy.privacymybatis.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @author cocowwy.cn
 * @since 2022/4/21
 */
@Slf4j
public class AesBase64Utils {

    private static final String SECRET = "AmQ4LhQqztdv25B5";
    private static final byte[] SECRET_BYTES = SECRET.getBytes(StandardCharsets.UTF_8);

    public static String encrypt(String message) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(SECRET_BYTES, "AES");
            IvParameterSpec iv = new IvParameterSpec(SECRET_BYTES);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
            byte[] encryptedBytes = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
            return new String(Base64.encodeBase64(encryptedBytes));
        } catch (Exception e) {
            log.error("加密字符串[{}]时发生异常", message, e);
        }

        return null;
    }

    public static String decrypt(String message) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(SECRET_BYTES, "AES");
            IvParameterSpec iv = new IvParameterSpec(SECRET_BYTES);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
            byte[] bytesAfterBase64Decryption = Base64.decodeBase64(message);
            return new String(cipher.doFinal(bytesAfterBase64Decryption));
        } catch (Exception e) {
            log.error("解密字符串[{}]时发生异常", message, e);
        }
        return null;
    }

}
