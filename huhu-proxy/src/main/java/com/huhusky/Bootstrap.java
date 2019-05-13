/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2019 TenxCloud. All Rights Reserved.
 */

package com.huhusky;

import com.huhusky.proxy.RequestReceive;

import java.io.IOException;

/**
 * Bootstrap
 *
 * @author huhu
 * @version v1.0
 * @date 2019-05-10 18:30
 */
public class Bootstrap {
    public static void main(String[] args) {
        try {
            new RequestReceive().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
