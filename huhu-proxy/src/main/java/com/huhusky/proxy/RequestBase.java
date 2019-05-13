/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2019 TenxCloud. All Rights Reserved.
 */

package com.huhusky.proxy;

import java.util.Map;

/**
 * RequestBase
 *
 * @author huhu
 * @version v1.0
 * @date 2019-05-10 18:40
 */
public interface RequestBase {

    String getMethod();

    String getHost();

    int getPort();

    String getProtocol();

    String getContentType();

    String getFullUrl();

    Map<String, String> getHeaders();

    String getPath();
}
