package com.weixin.test.controller;


import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@RestController
public class weixinController {


    @Autowired
 private    WxMpService wxMpService;

   private String code;

    @GetMapping("/weixin")
    public String accept(HttpServletRequest request){

        String signature=request.getParameter("signature");
        String timestamp=request.getParameter("timestamp");
        String nonce=request.getParameter("nonce");
        String echostr=request.getParameter("echostr");


        if (wxMpService.checkSignature(timestamp,nonce,signature)){
            System.out.println(echostr);
        }
        return echostr;



    }

    @PostMapping("/weixin")
    public void WxMessage(HttpServletRequest request, HttpServletResponse response) throws IOException, WxErrorException, InterruptedException {
        WxMpXmlMessage wxMpXmlMessage=WxMpXmlMessage.fromXml(request.getInputStream());

        System.out.println(wxMpXmlMessage.getContent());


        String url="http://28ht2t.natappfree.cc/code";
        wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);

        Thread.sleep(5000);
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);


        WxMpXmlOutTextMessage wxMpXmlOutMessage=new WxMpXmlOutTextMessage();

        wxMpXmlOutMessage.setToUserName(wxMpUser.getOpenId());
        wxMpXmlOutMessage.setContent("白痴");

        wxMpXmlOutMessage.setMsgType("text");
        wxMpXmlOutMessage.setCreateTime(new Date().getTime());

        wxMpXmlOutMessage.setFromUserName("gh_d9060a65500c");


        response.getWriter().write(   wxMpXmlOutMessage.toXml());

    }

    @RequestMapping("/code")
    public void code(HttpServletRequest request){

        String code=request.getParameter("code");
        this.code= code;

    }


}
