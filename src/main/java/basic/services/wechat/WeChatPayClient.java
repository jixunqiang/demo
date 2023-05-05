package basic.services.wechat;

import basic.util.StringUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class WeChatPayClient {
    final String baseUrl = "https://api.mch.weixin.qq.com";

    WeChatPayConfig WeChatPayConfig;

    @Resource
    private StringUtil stringUtil;

    @Resource
    WeChatPayOrderResponse weChatPayOrderResponse;

    public WeChatPayClient(WeChatPayConfig config) {
        WeChatPayConfig = config;
    }


    public WeChatPayOrderResponse unifiedOrder(WeChatPayUnifiedOrderRequest request) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", WeChatPayConfig.getAppId());
        params.put("mch_id", WeChatPayConfig.getMchId());
        params.put("nonce_str", stringUtil.getRandomStringByLength(32));
        params.put("body", request.getBody());
        params.put("out_trade_no", request.getOutTradeNo());
        params.put("total_fee", String.valueOf(request.getTotalFee()));
        params.put("spbill_create_ip", request.getSpbillCreateIp());
        params.put("notify_url", request.getNotifyUrl());
        params.put("trade_type", "JSAPI");
        String sign = getSign(params);
        params.put("sign", sign);
        String xml = mapToXml(params);
        String url = baseUrl + "/pay/unifiedorder";
        weChatPayOrderResponse.setReturnCode("SUCCESS");
        return weChatPayOrderResponse;
    }

    public WeChatPayOrderResponse orderQuery(WeChatPayUnifiedOrderRequest request) {
        weChatPayOrderResponse.setReturnCode("SUCCESS");
        return weChatPayOrderResponse;
    }

    /**
     * 生成签名
     * @param params
     * @return
     */
    private String getSign(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (String key : params.keySet()) {
            String value = params.get(key);
            if (value != null && value.length() > 0) {
                sb.append(key).append("=").append(value).append("&");
            }
        }
        sb.append("key=").append(WeChatPayConfig.getApiKey());
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(sb.toString().getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String hex = Integer.toHexString(b & 0xff);
                if (hex.length() == 1) {
                    result.append("0");
                }
                result.append(hex);
            }
            return result.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将Map转换为XML格式
     * @param params
     * @return
     */
    private String mapToXml(Map<String, String> params) {
        StringBuilder xml = new StringBuilder();
        xml.append("<xml>");
        for (String key : params.keySet()) {
            String value = params.get(key);
            if (value != null && value.length() > 0) {
                xml.append("<").append(key).append(">").append(value).append("</").append(key).append(">");
            }
        }
        xml.append("</xml>");
        return xml.toString();
    }

    /**
     * 发送POST请求
     *
     * @param url
     * @param data
     * @return
     */
    private String doPost(String url, String data) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        try {
            StringEntity entity = new StringEntity(data);
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-Type", "text/xml;charset=utf-8");
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            String result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public WeChatPayOrderResponse closeOrder(WeChatPayUnifiedOrderRequest request) {
        weChatPayOrderResponse.setReturnCode("SUCCESS");
        return weChatPayOrderResponse;
    }

    public WeChatPayOrderResponse refund(WeChatPayUnifiedOrderRequest request) {
        weChatPayOrderResponse.setReturnCode("SUCCESS");
        return weChatPayOrderResponse;
    }
}
