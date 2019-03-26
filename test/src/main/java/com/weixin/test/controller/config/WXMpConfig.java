package com.weixin.test.controller.config;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WXMpConfig {

    @Bean
    public WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage(){
        WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage=new WxMpInMemoryConfigStorage();
        wxMpInMemoryConfigStorage.setAppId("wx966fab9e16bef646");
        wxMpInMemoryConfigStorage.setSecret("1d98180d2dfde14c2285056f00c48a4b");


        wxMpInMemoryConfigStorage.setToken("rocky");
        return wxMpInMemoryConfigStorage;
    }

    @Bean
    public WxMpService wxMpService(WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage){

        WxMpService wxMpService=new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpInMemoryConfigStorage);
        return wxMpService;
    }




}
