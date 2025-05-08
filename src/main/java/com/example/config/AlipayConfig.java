package com.example.config;

import lombok.Data;

@Data
public class AlipayConfig {
    private String serverUrl;
    private String appId;
    private String privateKey;
    private String publicKey;
    private String format = "json";
    private String charset = "UTF-8";
    private String signType = "RSA2";
    private String aesKey;
    private String aesType = "AES";

    public static AlipayConfig fromEnv() {
        AlipayConfig config = new AlipayConfig();
        config.setServerUrl(System.getenv("ALIPAY_SERVER_URL"));
        config.setAppId(System.getenv("ALIPAY_APP_ID"));
        config.setPrivateKey(System.getenv("ALIPAY_PRIVATE_KEY"));
        config.setPublicKey(System.getenv("ALIPAY_PUBLIC_KEY"));
        config.setAesKey(System.getenv("ALIPAY_AES_KEY"));

        if (config.getServerUrl() == null || config.getAppId() == null ||
                config.getPrivateKey() == null || config.getPublicKey() == null) {
            throw new IllegalStateException("Missing required Alipay configuration. Please check your .envrc file.");
        }

        return config;
    }
}
