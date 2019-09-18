package com.example.demomybatis.config;
import com.example.demomybatis.system.shiro.MyUserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.ServletContainerSessionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @liuhongchun
 * @Date 2019/9/16  11:48
 * @des
 */
@Configuration
public class ShiroConfig {
    /**
     * 单机环境，session交给shiro管理
     */
    @Bean
    @ConditionalOnProperty(prefix = "demo-admin", name = "cluster", havingValue = "false")
    public DefaultWebSessionManager sessionManager(@Value("${demo-admin.globalSessionTimeout:3600}") long globalSessionTimeout) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setSessionValidationInterval(globalSessionTimeout * 1000);
        sessionManager.setGlobalSessionTimeout(globalSessionTimeout * 1000);

        return sessionManager;
    }

    /**
     * 集群环境，session交给spring-session管理
     */
    @Bean
    @ConditionalOnProperty(prefix = "demo-admin", name = "cluster", havingValue = "true")
    public ServletContainerSessionManager servletContainerSessionManager() {
        return new ServletContainerSessionManager();
    }

    @Bean("securityManager")
    public SecurityManager securityManager(MyUserRealm myUserRealm, SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myUserRealm);
        securityManager.setSessionManager(sessionManager);
        securityManager.setRememberMeManager(null);

        return securityManager;
    }


    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setLoginUrl("/login.html");
        shiroFilter.setUnauthorizedUrl("/");

        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/statics/**", "anon");
        filterMap.put("/login.html", "anon");
        filterMap.put("/system/**", "anon");
        filterMap.put("/favicon.ico", "anon");
        filterMap.put("/captcha.jpg", "anon");

        ///swagger2接口权限 开放
        filterMap.put("/swagger-ui.html","anon");
        filterMap.put("/static/**", "anon");
        filterMap.put("/swagger/**","anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/swagger-resources/**","anon");
        filterMap.put("/v2/**","anon");

        filterMap.put("/configuration/security", "anon");
        filterMap.put("/configuration/ui", "anon");
        filterMap.put("/**", "authc");

        // 添加自己的过滤器并且取名为jwt
        //        Map<String, Filter> filterHashMap = new HashMap<String, Filter>();
        //        filterHashMap.put("jwt", new JwtFilter());
        //        shiroFilter.setFilters(filterHashMap);
        //
        //        filterMap.put("/**", "jwt");

        shiroFilter.setFilterChainDefinitionMap(filterMap);

        return shiroFilter;
    }

    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
