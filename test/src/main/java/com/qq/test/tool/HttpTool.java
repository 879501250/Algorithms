package com.qq.test.tool;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * http 工具，通过 HttpClient 处理 http 请求
 */
public class HttpTool {

    public static String sendGet(String url) throws IOException, URISyntaxException {
        HttpRequestBase requestBase = getHttpRequestBase("get", url, null, null);
        return getResponse(requestBase);
    }

    public static String sendGet(String url,
                                 Map<String, String> params,
                                 Map<String, String> heads) throws URISyntaxException, IOException {
        HttpRequestBase requestBase = getHttpRequestBase("get", url, params, heads);
        return getResponse(requestBase);
    }

    public static String sendPost(String url) throws IOException, URISyntaxException {
        HttpRequestBase requestBase = getHttpRequestBase("post", url, null, null);
        return getResponse(requestBase);
    }

    public static String sendPost(String url,
                                  Map<String, String> params,
                                  Map<String, String> heads,
                                  String entityStr) throws URISyntaxException, IOException {
        HttpPost httpPost = (HttpPost) getHttpRequestBase("post", url, params, heads);
        // 设置请求体参数
        if (entityStr != null) {
            StringEntity entity = new StringEntity(entityStr, "utf-8");
            httpPost.setEntity(entity);
        }
        return getResponse(httpPost);
    }

    public static String getResponse(HttpRequestBase request) throws IOException {
        // 创建 HttpClient 对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            // 执行 http 请求
            CloseableHttpResponse response = httpClient.execute(request);
            try {
                // 获取响应状态码
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    // 读取响应内容
                    BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                    StringBuilder resultBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        resultBuilder.append(line).append("\n");
                    }
                    return resultBuilder.toString();
                } else {
                    return "请求失败，错误码：" + statusCode;
                }
            } finally {
                // 关闭响应流
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭HttpClient连接池
            httpClient.close();
        }
        return null;
    }

    /**
     * 获取 HttpRequest
     *
     * @param type   类型
     * @param url    请求 url
     * @param params 请求参数
     * @param heads  请求头
     * @return
     * @throws URISyntaxException
     */
    private static HttpRequestBase getHttpRequestBase(String type,
                                                      String url,
                                                      Map<String, String> params,
                                                      Map<String, String> heads) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(url);
        // 设置 url 参数
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue());
            }
        }
        // 创建 HttpRequest 请求对象，默认为 HttpGet
        HttpRequestBase request;
        switch (type) {
            case "post":
                request = new HttpPost(uriBuilder.build());
                break;
            case "get":
            default:
                request = new HttpGet(uriBuilder.build());
        }
        // 设置请求头
        if (heads != null) {
            for (Map.Entry<String, String> entry : heads.entrySet()) {
                request.setHeader(entry.getKey(), entry.getValue());
            }
        }
        return request;
    }
}
