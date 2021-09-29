package com.yimi.oysc.configutation;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.yimi.oysc.security.CustomUserService;
import com.yimi.oysc.security.LoginLogoutHandler;
import com.yimi.oysc.security.UrlRoleAuthHandler;
import com.yimi.oysc.security.UrlRolesFilterHandler;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomUserService customUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LoginLogoutHandler loginLogoutHandler;

    @Autowired
    private UrlRolesFilterHandler urlRolesFilterHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.authorizeRequests().anyRequest().permitAll();

//        http
//                .authenticationProvider(authenticationProvider())
//                .httpBasic()
//                //未登录时，进行json格式的提示，很喜欢这种写法，不用单独写一个又一个的类
//                .authenticationEntryPoint((request,response,authException) -> {
//                    response.setContentType("application/json;charset=utf-8");
//                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//                    PrintWriter out = response.getWriter();
//                    Map<String,Object> map = new HashMap<String,Object>();
//                    map.put("code",403);
//                    map.put("message","未登录");
//                    out.write(objectMapper.writeValueAsString(map));
//                    out.flush();
//                    out.close();
//                })
//                .and()
//                .authorizeRequests()
//                .anyRequest().authenticated() //必须授权才能范围
//
//                .and()
//                .formLogin() //使用自带的登录
//                .permitAll()
//                //登录失败，返回json
//                .failureHandler(new LoginLogoutHandler())
//                //登录成功，返回json
//                .successHandler(new LoginLogoutHandler())
//                .and()
//                .exceptionHandling()
//                //没有权限，返回json
//                .accessDeniedHandler((request,response,ex) -> {
//                    response.setContentType("application/json;charset=utf-8");
//                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//                    PrintWriter out = response.getWriter();
//                    Map<String,Object> map = new HashMap<String,Object>();
//                    map.put("code",403);
//                    map.put("message", "权限不足");
//                    out.write(objectMapper.writeValueAsString(map));
//                    out.flush();
//                    out.close();
//                })
//                .and()
//                .logout()
//                //退出成功，返回json
//                .logoutSuccessHandler(new LoginLogoutHandler())
//                .permitAll();

        //开启跨域访问
        http.cors().disable();
        //开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误
        http.csrf().disable();

        // 配置登录页面
        http.formLogin().permitAll().failureHandler(loginLogoutHandler).successHandler(loginLogoutHandler);

        // 用户权限不足处理器
        http.exceptionHandling().accessDeniedHandler((request,response,ex) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    PrintWriter out = response.getWriter();
                    Map<String,Object> map = new HashMap<String,Object>();
                    map.put("code",403);
                    map.put("message", "权限不足");
                    out.write(objectMapper.writeValueAsString(map));
                    out.flush();
                    out.close();
                });

        // 登录授权
        http.logout().permitAll().logoutSuccessHandler(loginLogoutHandler);

        // 授权配置
        http.authenticationProvider(authenticationProvider())
                .authorizeRequests()
                // 动态url权限
                .withObjectPostProcessor(new DefinedObjectPostProcessor())
                // url决策、投票器
                .accessDecisionManager(accessDecisionManager())
                .anyRequest().authenticated();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        // 忽略的静态地址链接在此配置，才可以经过URL动态权限校验
        web.ignoring().antMatchers("/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs/**","/test/**");
    }

    /**
     * AffirmativeBased – 任何一个AccessDecisionVoter返回同意则允许访问
     * ConsensusBased – 同意投票多于拒绝投票（忽略弃权回答）则允许访问
     * UnanimousBased – 每个投票者选择弃权或同意则允许访问
     * 决策管理、投票器
     *
     * @Author: Davy
     * @Date: 2021/9/28 17:08
    **/
    private AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<?>> decisionVoters = Lists.newArrayList(new WebExpressionVoter(), new AuthenticatedVoter(), new RoleVoter());

        // 路由权限管理
        decisionVoters.add(new UrlRoleAuthHandler());

        return new UnanimousBased(decisionVoters);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        //对默认的UserDetailsService进行覆盖
        authenticationProvider.setUserDetailsService(customUserService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }


    @Bean
    public RedissonClient redissonClient() {

        Config config = new Config();

        config.useSingleServer().setAddress("redis://127.0.0.1:6379");

        return Redisson.create(config);
    }


    /**
     * 数据库使用Mysql必须要添加这个分页才能生效
     * @Author: Davy
     * @Date: 2021/9/24 15:34
    **/
    @Bean
    public MybatisPlusInterceptor innerInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }


    class DefinedObjectPostProcessor implements ObjectPostProcessor<FilterSecurityInterceptor> {

        @Override
        public <O extends FilterSecurityInterceptor> O postProcess(O object) {
            object.setSecurityMetadataSource(urlRolesFilterHandler);
            return object;
        }
    }


}
