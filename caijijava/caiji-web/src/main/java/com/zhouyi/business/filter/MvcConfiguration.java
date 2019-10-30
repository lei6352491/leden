package com.zhouyi.business.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;

@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {
	
	@Autowired
	private UserTokenInterceptor userTokenInterceptor;
	
	@Autowired
	private VisitorTokenInterceptor visitorTokenInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
				
		//登录拦截
		String[] usersUrl=new String[]{"/api/order/*","/api/member/*","/api/user/getUserInfoById"};
		registry.addInterceptor(userTokenInterceptor).addPathPatterns(usersUrl).excludePathPatterns(
				"/api/article/queryArticlePageList",
				"/api/user/login",
				"/api/user/appLogin",
//				"/api/user/selfLogin",
				"/api/user/reloadConfig",
				"/api/order/payNotice",
				"/api/member/getNameExplain",
				"/api/member/transferCallback",
				"/api/wxplatform/autoReply",
				"/api/member/testNameExplain",
				"/api/test/test",
				"/api/order/cardPayNotice",
				"/api/order/personPayNotice",
				"/api/user/getVersion"
//				"/api/order/getOrderDetail",
//				"/api/member/intelFornameRegister"
		);
		
		//游客拦截
		String[] visitorUrl=new String[]{"/api/member/testNameExplain"};
		registry.addInterceptor(visitorTokenInterceptor).addPathPatterns(visitorUrl);
		super.addInterceptors(registry);
	}


	    @Value("file.upload-location")
    private String path;


    @Bean
	MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation(path);
        return factory.createMultipartConfig();
    }
}
