package basic.services.wechat;

public class WeChatPayConfig {
    public String appId;
    public String appSecret;
    public String mchId;
    public String apiKey;
    public String notifyUrl;
    public WeChatPayConfig(String appId, String appSecret, String mchId, String apiKey) {
        this.setAppId(appId);
        this.setAppSecret(appSecret);
        this.setMchId(mchId);
        this.setApiKey(apiKey);
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
