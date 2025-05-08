package com.example;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.example.config.AlipayConfig;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 新加文件
 *
 * @author adrian.lin
 * @Date 2025/5/9
 */
@Slf4j
public class AlipayTradePagePay {
    public static void main(String[] args) {
        AlipayTradePagePay tradePagePay = new AlipayTradePagePay();
        tradePagePay.doAlipayGetRequest(tradePagePay.generateRequestByBizContent());
    }

    private void doAlipayGetRequest(AlipayTradePagePayRequest request) {
        doAlipayRequest(request, "GET");
    }

    private void doAlipayRequest(AlipayTradePagePayRequest request, String requestType) {
        try {
            AlipayConfig config = AlipayConfig.fromEnv();
            AlipayClient alipayClient = new DefaultAlipayClient(
                    config.getServerUrl(),
                    config.getAppId(),
                    config.getPrivateKey(),
                    config.getFormat(),
                    config.getCharset(),
                    config.getPublicKey(),
                    config.getSignType(),
                    config.getAesKey(),
                    config.getAesType()
            );
            AlipayTradePagePayResponse response;
            if ("GET".equalsIgnoreCase(requestType)) {
                response = alipayClient.pageExecute(request, "GET");
            } else {
                response = alipayClient.pageExecute(request, "POST");
            }
            String pageRedirectionData = response.getBody();
            System.out.println(pageRedirectionData);
            if (response.isSuccess()) {
                System.out.println("调用成功");
            } else {
                System.out.println("调用失败");
                // sdk版本是"4.38.0.ALL"及以上,可以参考下面的示例获取诊断链接
                // String diagnosisUrl = DiagnosisUtils.getDiagnosisUrl(response);
                // System.out.println(diagnosisUrl);
            }
        } catch (Exception e) {
            log.error("调用支付宝接口败", e);
        }
    }

    private AlipayTradePagePayRequest generateRequestByModel() {
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setOutTradeNo("20150320010101001");
        model.setTotalAmount("88.88");
        model.setSubject("Iphone6 16G");
        model.setProductCode("FAST_INSTANT_TRADE_PAY");
        model.setBusinessParams("{\"outTradeRiskInfo\":\"{\" mcCreateTradeTime \":\"2022-03-11 12:46:09\", \" extraAccountCertnoLastSix\":\"000011\"\", \" mobileOperatingPlatform \":\" ios \", \" sysVersion \":\" 15.4.2\", \" netWork \":\" wifi \"}\", \"mc_create_trade_ip\":\"11.110.111.43\"}");
        request.setBizModel(model);
        request.setNeedEncrypt(true);
        return request;
    }

    private AlipayTradePagePayRequest generateRequestByBizContent() {
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();

        Map<String, String> bizContent = new HashMap<>();
        bizContent.put("out_trade_no", "20150320010101001");
        bizContent.put("total_amount", "88.88");
        bizContent.put("subject", "Iphone6 16G");
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
        Map<String, String> businessParams = new HashMap<>();
        businessParams.put("mcCreateTradeTime", "2022-03-11 12:46:09");
        businessParams.put("extraAccountCertnoLastSix", "000011");
        businessParams.put("mobileOperatingPlatform", "ios");
        businessParams.put("sysVersion", "15.4.2");
        businessParams.put("netWork", "wifi");
        businessParams.put("mc_create_trade_ip", "11.110.111.43");
        bizContent.put("business_params", JSON.toJSONString(businessParams));

        request.setBizContent(JSON.toJSONString(bizContent));
        request.setNeedEncrypt(true);
        return request;
    }
}