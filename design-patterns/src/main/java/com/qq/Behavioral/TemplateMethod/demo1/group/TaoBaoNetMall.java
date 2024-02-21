package com.qq.Behavioral.TemplateMethod.demo1.group;

import com.qq.Behavioral.TemplateMethod.demo1.HttpClient;
import com.qq.Behavioral.TemplateMethod.demo1.NetMall;
import sun.misc.BASE64Encoder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 模拟爬虫淘宝
 */
public class TaoBaoNetMall extends NetMall {

    public TaoBaoNetMall(String uId, String uPwd) {
        super(uId, uPwd);
    }

    @Override
    public Boolean login(String uId, String uPwd) {
        System.out.println("模拟淘宝用户登录 uId：" + uId + " uPwd：" + uPwd);
        return true;
    }

    @Override
    public Map<String, String> reptile(String skuUrl) {
        String str = HttpClient.doGet(skuUrl);
        Pattern p9 = Pattern.compile("(?<=title\\>).*(?=</title)");
        Matcher m9 = p9.matcher(str);
        Map<String, String> map = new ConcurrentHashMap<String, String>();
        if (m9.find()) {
            map.put("name", m9.group());
        }
        map.put("price", "4799.00");
        System.out.println("模拟淘宝商品爬虫解析：" + map.get("name") + " | " + map.get("price") + " 元 " + skuUrl);
        return map;
    }

    @Override
    public String createBase64(Map<String, String> goodsInfo) {
        System.out.println("模拟生成京东商品base64海报");
        return goodsInfo.toString();
    }

}
