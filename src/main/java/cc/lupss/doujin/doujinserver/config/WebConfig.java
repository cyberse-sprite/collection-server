package cc.lupss.doujin.doujinserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Resource
//    private LoginInterceptor loginInterceptor;
//    @Resource
//    private CorsInterceptor corsInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        // 跨域拦截器需放在最上面
//        registry.addInterceptor(corsInterceptor).addPathPatterns("/**");
//        // 校验token的拦截器
//        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
//                .excludePathPatterns("/user/login")
//                .excludePathPatterns("/user/sign")
//                .excludePathPatterns("/task/pdf")
//                .excludePathPatterns("/user/head/**");
    }

}
