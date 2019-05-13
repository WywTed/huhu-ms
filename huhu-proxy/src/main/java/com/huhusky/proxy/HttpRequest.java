/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2019 TenxCloud. All Rights Reserved.
 */

package com.huhusky.proxy;

import java.util.List;
import java.util.Map;

/**
 * HttpRequest
 *
 * @author huhu
 * @version v1.0
 * @date 2019-05-10 18:34
 */

public class HttpRequest extends AbstractRequest{


    private String method;
    private String path;
    private String queryString;
    private Map<String, String> headersMap;
    private Map<String, List<Object>> paramsMap;
    private Object data;

    public HttpRequest() {}

    public HttpRequest(String host, int port) {
        super(host, port);
    }

    @Override
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public Map<String, String> getHeaders() {
        return null;
    }

    @Override
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public Map<String, String> getHeadersMap() {
        return headersMap;
    }

    public void setHeadersMap(Map<String, String> headersMap) {
        this.headersMap = headersMap;
    }

    public Map<String, List<Object>> getParamsMap() {
        return paramsMap;
    }

    public void setParamsMap(Map<String, List<Object>> paramsMap) {
        this.paramsMap = paramsMap;
    }
}
