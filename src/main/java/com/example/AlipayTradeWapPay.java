package com.example;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.example.config.AlipayConfig;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AlipayTradeWapPay {
    public static void main(String[] args) {
        try {
            AlipayConfig config = AlipayConfig.fromEnv();
            AlipayClient alipayClient = new DefaultAlipayClient(
                    config.getServerUrl(),
                    config.getAppId(),
                    config.getPrivateKey(),
                    config.getFormat(),
                    config.getCharset(),
                    config.getPublicKey(),
                    config.getSignType()
            );

            // 创建支付请求
            AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
            AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
            model.setOutTradeNo("70501111111S001111119");
            model.setTotalAmount("9.00");
            model.setSubject("大乐透");
            model.setProductCode("QUICK_WAP_WAY");
            model.setSellerId("2088102147948060");
            request.setBizModel(model);

//            AlipayTradeWapPayResponse response = alipayClient.pageExecute(request, "POST");
            // 如果需要返回GET请求，请使用
            AlipayTradeWapPayResponse response = alipayClient.pageExecute(request, "GET");
            String pageRedirectionData = response.getBody();
            System.out.println(pageRedirectionData);

            if (response.isSuccess()) {
                System.out.println("调用成功");
            } else {
                System.out.println("调用失败");
            }

        } catch (Exception e) {
            log.error("调用支付宝接口失败", e);
        }
    }
}
