package com.cmcc.iot.config;

import com.cmcc.iot.filter.Loginfilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(1024);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }


    @Bean(name = "shiroRealm")
    @DependsOn("lifecycleBeanPostProcessor")
    public ShiroRealm shiroRealm() {
        ShiroRealm realm = new ShiroRealm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher());

//        realm.setAuthenticationCacheName("authenticationCache");
//        realm.setAuthorizationCacheName("authorizationCache");
        EhCacheManager ehCacheManager=ehCacheManager();

        realm.setAuthorizationCache(ehCacheManager.getCache("authorizationCache"));
        realm.setAuthenticationCache(ehCacheManager.getCache("authenticationCache"));
        realm.setCachingEnabled(true);
        realm.setAuthenticationCachingEnabled(true);
        realm.setAuthorizationCachingEnabled(true);
        return realm;
    }

    @Bean(name = "ehCacheManager")
    @DependsOn("lifecycleBeanPostProcessor")
    public EhCacheManager ehCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return ehCacheManager;
    }

    /**
     * 多realm可以用这个bean，单realm直接在securityManager中设置
     *
     * @return
     */

    @Bean(name = "authenticator")
    public ModularRealmAuthenticator authenticator() {
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        Collection<Realm> real_list = new ArrayList<>();
        real_list.add(shiroRealm());
        authenticator.setRealms(real_list);
        authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return authenticator;
    }

    @Bean(name = "authorizer")
    public ModularRealmAuthorizer authorizer() {
        ModularRealmAuthorizer realmAuthorizer = new ModularRealmAuthorizer();
        Collection<Realm> realm_list = new ArrayList<>();
        realm_list.add(shiroRealm());
        realmAuthorizer.setRealms(realm_list);
        return realmAuthorizer;
    }




    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setAuthenticator(authenticator());//设置realm的三种方法
        securityManager.setAuthorizer(authorizer());
        securityManager.setCacheManager(ehCacheManager());//用户授权/认证信息Cache, 采用EhCache 缓存
        securityManager.setSessionManager(defaultWebSessionManager());
        return securityManager;
    }




    @Bean(name = "sessionManager")
    public DefaultWebSessionManager defaultWebSessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setGlobalSessionTimeout(1800000);
        defaultWebSessionManager.setDeleteInvalidSessions(true);
        defaultWebSessionManager.setSessionDAO(sessionDAO());
        defaultWebSessionManager.setSessionIdCookieEnabled(true);
        defaultWebSessionManager.setSessionIdCookie(sessionIdCookie());
        defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
        defaultWebSessionManager.setSessionValidationScheduler(executorServiceSessionValidationScheduler());
        defaultWebSessionManager.setCacheManager(ehCacheManager());

        return defaultWebSessionManager;
    }


    @Bean(name = "sessionDAO")
    public SessionDAO sessionDAO(){
        EhCacheManager ehCacheManager=ehCacheManager();
        EnterpriseCacheSessionDAO enterpriseCacheSessionDAO = new EnterpriseCacheSessionDAO();
        enterpriseCacheSessionDAO.setCacheManager(ehCacheManager);
        enterpriseCacheSessionDAO.setSessionIdGenerator(SessionIdGenerator());
        enterpriseCacheSessionDAO.setActiveSessionsCache(ehCacheManager.getCache("shiro-activeSessionCache"));
//        enterpriseCacheSessionDAO.setActiveSessionsCacheName();
        return enterpriseCacheSessionDAO;
    }

    @Bean(name = "sessionIdGenerator")
    SessionIdGenerator SessionIdGenerator (){
        return new JavaUuidSessionIdGenerator();
    }

    @Bean(name = "sessionIdCookie")
    SimpleCookie sessionIdCookie(){
        SimpleCookie simpleCookie = new SimpleCookie("mysid");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(-1);
        return simpleCookie;
    }

    @Bean(name = "sessionValidationScheduler")
    ExecutorServiceSessionValidationScheduler executorServiceSessionValidationScheduler(){
        ExecutorServiceSessionValidationScheduler executorServiceSessionValidationScheduler = new ExecutorServiceSessionValidationScheduler();
        executorServiceSessionValidationScheduler.setInterval(1800000);
//       executorServiceSessionValidationScheduler.setSessionManager(defaultWebSessionManager());
        return executorServiceSessionValidationScheduler;
    }


    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        LogoutFilter logoutFilter = new LogoutFilter();
        //logoutFilter.setRedirectUrl("/toLogin");
        Loginfilter loginfilter = new Loginfilter();
        loginfilter.setCacheManager(ehCacheManager());
        loginfilter.setKickoutAfter(false);
        loginfilter.setMaxSession(1);
        loginfilter.setSessionManager(defaultWebSessionManager());

        filters.put("logout", logoutFilter);
        filters.put("loginf", loginfilter);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        Map<String, String> filterChainDefinitionManager = shiroFilterFactoryBean.getFilterChainDefinitionMap();
      //  filterChainDefinitionManager.put("/user/**", "anon");
        filterChainDefinitionManager.put("/file/**", "anon");
        filterChainDefinitionManager.put("/fileupload/**", "anon");
        filterChainDefinitionManager.put("/register/**", "anon");
        filterChainDefinitionManager.put("/login", "anon");
        filterChainDefinitionManager.put("/logout", "anon");
        filterChainDefinitionManager.put("/receive/**", "anon");
        filterChainDefinitionManager.put("/islogin", "anon");

        filterChainDefinitionManager.put("/**", "loginf");
//        filterChainDefinitionManager.put("/**", "anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);
        shiroFilterFactoryBean.setFilters(filters);


        return shiroFilterFactoryBean;
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }

}
