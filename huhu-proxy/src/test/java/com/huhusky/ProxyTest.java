/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2019 TenxCloud. All Rights Reserved.
 */

package com.huhusky;

import com.huhusky.proxy.HttpRequest;
import com.huhusky.proxy.ProxyCore;
import org.junit.Test;

/**
 * ProxyTest
 *
 * @author huhu
 * @version v1.0
 * @date 2019-05-11 11:20
 */
public class ProxyTest {
    @Test
    public void sendSocket() {
        HttpRequest request = new HttpRequest("localhost", 8081);
        ProxyCore pc = new ProxyCore();
        System.out.println(pc.connect(request));
    }
}
