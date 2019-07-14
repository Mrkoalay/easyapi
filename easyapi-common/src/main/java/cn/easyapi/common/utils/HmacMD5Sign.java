package cn.easyapi.common.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @ClassName: HmacMD5SignImpl
 * @Description:HmacMD5Sign签名
 * @Date 2018/8/10 17:27
 */
public class HmacMD5Sign {

    public static final String KEY_MAC = "HmacMD5";

    /**
     * HMAC加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {

        SecretKey secretKey = new SecretKeySpec(key.getBytes(), KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return mac.doFinal(data);
    }

    static String encodeBase64(byte[] source) throws Exception {
        return new String(Base64.encodeBase64(source), "UTF-8");
    }

    /*byte数组转换为HexString*/
    public static String byteArrayToHexString(byte[] b) {
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString();
    }

    public static String encryptHMAC2String(String data, String key) throws Exception {
        byte[] b = encryptHMAC(data.getBytes("UTF-8"), key);
        return byteArrayToHexString(b);
    }

    // 异或
    public static String getEncryption(String para, int key) {
        char[] charArray = para.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = (char) (charArray[i] ^ key);
        }
        return String.valueOf(charArray);

    }

    public static String encryption(String key, String data) throws Exception {
        String str = key;
        // 补0
        while (str.length() < 64) {
            str += "0";
        }
        // 异或
        String istr = getEncryption(str, 0x36);
        istr += data;
        String md5 = MD5.EncoderByMd5(istr);
        String ostr = getEncryption(str, 0x5c);

        String t = ostr + md5;
        return MD5.EncoderByMd5(t);
    }

    public static void main(String[] args) throws Exception {

        String key = "1234567890abcdef";
        String data = "il7BOBSEjFdzpyKzfOFpvg/SelCP802RItKYFPfSLRxJ3jfObV19hvYOEktPAYW2nd7S8MBcyHYyacHK bISq5iTmDzG+ivnR+SZJv3USNTYVMz9rCQVSxdOcLlqsJauko79NnwQJbzDTyLooYbIwz75qBOH2/xOMir peEqRJrF/EQjWekJmGk9RtboXePu2rka+Xm51syBPhiXJAq0GfbfaFu9tNqs/e2Vjja/ltElM01qvxfXQ6da6HrT hsm5id4ClZFIiOacRfrsPLRixS/IQYtksxghvJwbqOsbIsITail9Ayy4tKcogeEZiOO+4Ed264NSKmk713wKwJLAFjCFogBx8GE3OBz4pqcAn/ydA=";
        System.out.println(encryption(key,data));
    }
}