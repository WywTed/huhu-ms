/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2019 TenxCloud. All Rights Reserved.
 */

package com.huhusky.proxy;

import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * AbstractRequest
 *
 * @author huhu
 * @version v1.0
 * @date 2019-05-10 18:42
 */
public abstract class AbstractRequest implements RequestBase{

    private String protocol;
    private String host;
    private Integer port = 80;

    public AbstractRequest(String host, int port) {
        this.host = host;
        this.port = port;
        this.protocol = "http";
    }

    public AbstractRequest(){}

    @Override
    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @Override
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public int getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }


    @Override
    public String getPath() {
        return null;
    }

    @Override
    public String getMethod(){
        return null;
    }

    @Override
    public String getContentType(){
        return null;
    }

    public static String URL_PATH_SPLIT = "/";

    @Override
    public String getFullUrl(){
        String prefix = getProtocol() + "://" + getHost() + ":" + getPort();
        if (StringUtils.isBlank(getPath())) {
            return prefix;
        }
        if (getPath().startsWith(URL_PATH_SPLIT)) {
            return prefix + getPath();
        }
        return prefix + URL_PATH_SPLIT + getPath();
    }

    @Override
    public Map<String, String> getHeaders(){
        return null;
    }

}
