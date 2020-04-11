package study.mengduo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import study.mengduo.util.AuthorityInterceptor;

/**
 * @aothor mengDuo
 * @date 2020/3/29 22:53
 */

//不能加@EnableWebMvc注解，如果加上的话意味着全面接管SpringMvc配置，springboot关于mvc的自动配置将会失效
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurationSupport {
    /**
     * 在springboot2.0.0之前继承WebMvcConfigurerAdapter类，重写addInterceptors方法
     *
     * @param registry
     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        /**
//         * 拦截器按照顺序执行,如果不同拦截器拦截存在相同的URL，前面的拦截器会执行，后面的拦截器将不执行
//         */
//        registry.addInterceptor(new AuthorityInterceptor())
//                .addPathPatterns("/user/**");
//        super.addInterceptors(registry);
//    }

    /**
     * 在springboot2.0.0之后实现WebMvcConfigurer接口，重写addInterceptors方法
     *
     * @param registry
     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        /**
//         * 拦截器按照顺序执行,如果不同拦截器拦截存在相同的URL，前面的拦截器会执行，后面的拦截器将不执行
//         */
//        registry.addInterceptor(new AuthorityInterceptor())
//                .addPathPatterns("/user/**");
//    }

    /**
     * 在springboot2.0.0之后继承WebMvcConfigurationSupport类，重写addInterceptors方法
     *
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        /**
         * 拦截器按照顺序执行,如果不同拦截器拦截存在相同的URL，前面的拦截器会执行，后面的拦截器将不执行
         */
        registry.addInterceptor(new AuthorityInterceptor())
                .addPathPatterns("/manage/**");
        super.addInterceptors(registry);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决静态资源无法访问
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        // 解决swagger无法访问
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        // 解决swagger的js文件无法访问
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }


}

