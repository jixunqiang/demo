package basic.services.lakala;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * 拉卡拉商户入网
 */
public class MerchantNetService {

    /**
     * 字符集固定 utf-8
     */
    private static final String ENCODING = "UTF-8";

    /**
     * API schema ,固定 LKLAPI-SHA256withRSA
     */
    public static final String SCHEMA = "LKLAPI-SHA256withRSA";

    /**
     * 公钥
     */
    private static final String publicKey = "F:/lakala/v3/OP00000003_cert.pem";

    /**
     * 私钥
     */
    private static final String privateKey = "F:/lakala/v3/OP00000003_private_key.pem";

    /**
     * 请求入口
     */
    public static final String apiUrl = "https://test.wsmsd.cn/sit";

    /**
     * appId
     */
    public static final String appId = "OP00000003";

    /**
     * 商户证书序列号，和商户私钥对应
     */
    public static final String mchSerialNo = "00dfba8194c41b84cf";

    public static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 随机数
     */
    private static final SecureRandom RANDOM = new SecureRandom();

    public void addMerchant(String body) {

    }

    /**
     * 处理 authorization
     */
    private String getAuthorization(String body) {
        try {
            String nonceStr = generateNonceStr();
            long timestamp = generateTimestamp();

            String message = appId + "\n" + mchSerialNo + "\n" + timestamp + "\n" + nonceStr + "\n" + body + "\n";
            System.out.println("getToken message :  " + message);

            PrivateKey merchantPrivateKey = loadPrivateKeyFile(new FileInputStream(new File(privateKey)));
            String signature = this.sign(message.getBytes(ENCODING), merchantPrivateKey);
            System.out.println("signature message :" + signature);

            String authorization = "appid=\"" + appId + "\"," + "serial_no=\"" + mchSerialNo + "\"," + "timestamp=\""
                    + timestamp + "\"," + "nonce_str=\"" + nonceStr + "\"," + "signature=\"" + signature + "\"";
            System.out.println("authorization message :" + authorization);
            return authorization;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("文件路径有误", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("转码出错", e);
        }
    }

    /**
     * 签名
     */
    protected String sign(byte[] message, PrivateKey merchantPrivateKey) {
        try {
            Signature sign = Signature.getInstance("SHA256withRSA");
            sign.initSign(merchantPrivateKey);
            sign.update(message);
            return new String(Base64.encodeBase64(sign.sign()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("当前Java环境不支持SHA256withRSA", e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException("无效的私钥", e);
        } catch (SignatureException e) {
            throw new RuntimeException("签名计算失败", e);
        }

    }

    /**
     * 读取私钥
     * @param inputStream inputStream
     * @return PrivateKey
     */
    protected static PrivateKey loadPrivateKeyFile(InputStream inputStream) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }

            String privateKey = outputStream.toString("utf-8").replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "").replaceAll("\\s+", "");

            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("当前Java环境不支持RSA", e);
        } catch (IOException e) {
            throw new RuntimeException("无效的密钥");
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("无效的密钥格式");
        }
    }

    protected String generateNonceStr() {
        char[] nonceStr = new char[32];
        for (int index = 0; index < nonceStr.length; ++index) {
            nonceStr[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(nonceStr);
    }

    /**
     * 获取 秒级时间戳
     * @return long
     */
    protected long generateTimestamp() {
        return System.currentTimeMillis() / 1000;
        //return 1658991498;
    }
}
