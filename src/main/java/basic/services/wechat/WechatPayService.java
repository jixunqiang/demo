package basic.services.wechat;

import java.util.Map;

public class WechatPayService {
    WeChatPayClient client;

    public WechatPayService(String appId, String appSecret, String mchId, String apiKey) {
        WeChatPayConfig config = new WeChatPayConfig(appId, appSecret, mchId, apiKey);
        client = new WeChatPayClient(config);
    }

    /*
     * 微信支付下单接口
     * @param params 下单参数
     * @return 下单结果
     */
    public String unifiedOrder(Map<String, String> params) {
        // Next, create a WeChatPayUnifiedOrderRequest object with the necessary parameters.
        WeChatPayUnifiedOrderRequest request = new WeChatPayUnifiedOrderRequest();
        request.setBody(params.get("body"));
        request.setOutTradeNo(params.get("out_trade_no"));
        request.setTotalFee(Integer.valueOf(params.get("total_fee")));
        request.setSpbillCreateIp(params.get("spbill_create_ip"));
        request.setNotifyUrl(params.get("notify_url"));
        request.setTradeType(params.get("trade_type"));
        request.setOpenid(params.get("openid"));

        // Finally, call the unifiedOrder method of the client with the request object to place the order.
        WeChatPayOrderResponse response = client.unifiedOrder(request);

        // Return the result as a string.
        return response.toString();
    }

    /*
     * 微信小程序订单查询接口
     * @param params 查询参数
     * @return 查询结果
     */
    public String queryOrder(Map<String, String> params) {
        // Next, create a WeChatPayOrderQueryRequest object with the necessary parameters.
        WeChatPayUnifiedOrderRequest request = new WeChatPayUnifiedOrderRequest();
        request.setOutTradeNo(params.get("out_trade_no"));

        // Finally, call the orderQuery method of the client with the request object to query the order.
        WeChatPayOrderResponse response = client.orderQuery(request);

        // Return the result as a string.
        return response.toString();
    }

    /*
     * 微信小程序关闭订单接口
     * @param params 关闭订单参数
     * @return 关闭订单结果
     */
    public String closeOrder(Map<String, String> params) {
        // Next, create a WeChatPayCloseOrderRequest object with the necessary parameters.
        WeChatPayUnifiedOrderRequest request = new WeChatPayUnifiedOrderRequest();
        request.setOutTradeNo(params.get("out_trade_no"));

        // Finally, call the closeOrder method of the client with the request object to close the order.
        WeChatPayOrderResponse response = client.closeOrder(request);

        // Return the result as a string.
        return response.toString();
    }

    /*
     * 微信支付退款接口
     * @param params 退款参数
     * @return 退款结果
     */
    public String refund(Map<String, String> params) {
        // Next, create a WeChatPayRefundRequest object with the necessary parameters.
        WeChatPayUnifiedOrderRequest request = new WeChatPayUnifiedOrderRequest();
        request.setOutTradeNo(params.get("out_trade_no"));
        request.setOutRefundNo(params.get("out_refund_no"));
        request.setTotalFee(Integer.valueOf(params.get("total_fee")));
        request.setRefundFee(Integer.valueOf(params.get("refund_fee")));

        // Finally, call the refund method of the client with the request object to initiate the refund.
        WeChatPayOrderResponse response = client.refund(request);

        // Return the result as a string.
        return response.toString();
    }
}
