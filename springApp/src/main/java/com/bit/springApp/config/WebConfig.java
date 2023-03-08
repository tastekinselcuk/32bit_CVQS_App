package com.bit.springApp.config;

import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	
	@Bean
	public LocaleResolver localeResolver() {
	    SessionLocaleResolver localeResolver = new SessionLocaleResolver();
	    localeResolver.setDefaultLocale(Locale.ENGLISH);
	    return localeResolver;
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
	    LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
	    interceptor.setParamName("lang");
	    return interceptor;
	}


	
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("forward:/index.html");
//    }

}
