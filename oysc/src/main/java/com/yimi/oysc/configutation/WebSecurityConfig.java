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
//                //?????????????????????json??????????????????????????????????????????????????????????????????????????????
//                .authenticationEntryPoint((request,response,authException) -> {
//                    response.setContentType("application/json;charset=utf-8");
//                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//                    PrintWriter out = response.getWriter();
//                    Map<String,Object> map = new HashMap<String,Object>();
//                    map.put("code",403);
//                    map.put("message","?????????");
//                    out.write(objectMapper.writeValueAsString(map));
//                    out.flush();
//                    out.close();
//                })
//                .and()
//                .authorizeRequests()
//                .anyRequest().authenticated() //????????????????????????
//
//                .and()
//                .formLogin() //?????????????????????
//                .permitAll()
//                //?????????????????????json
//                .failureHandler(new LoginLogoutHandler())
//                //?????????????????????json
//                .successHandler(new LoginLogoutHandler())
//                .and()
//                .exceptionHandling()
//                //?????????????????????json
//                .accessDeniedHandler((request,response,ex) -> {
//                    response.setContentType("application/json;charset=utf-8");
//                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//                    PrintWriter out = response.getWriter();
//                    Map<String,Object> map = new HashMap<String,Object>();
//                    map.put("code",403);
//                    map.put("message", "????????????");
//                    out.write(objectMapper.writeValueAsString(map));
//                    out.flush();
//                    out.close();
//                })
//                .and()
//                .logout()
//                //?????????????????????json
//                .logoutSuccessHandler(new LoginLogoutHandler())
//                .permitAll();

        //??????????????????
        http.cors().disable();
        //???????????????????????????API POST???????????????????????????????????????API POST??????403??????
        http.csrf().disable();

        // ??????????????????
        http.formLogin().permitAll().failureHandler(loginLogoutHandler).successHandler(loginLogoutHandler);

        // ???????????????????????????
        http.exceptionHandling().accessDeniedHandler((request,response,ex) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    PrintWriter out = response.getWriter();
                    Map<String,Object> map = new HashMap<String,Object>();
                    map.put("code",403);
                    map.put("message", "????????????");
                    out.write(objectMapper.writeValueAsString(map));
                    out.flush();
                    out.close();
                });

        // ????????????
        http.logout().permitAll().logoutSuccessHandler(loginLogoutHandler);

        // ????????????
        http.authenticationProvider(authenticationProvider())
                .authorizeRequests()
                // ??????url??????
                .withObjectPostProcessor(new DefinedObjectPostProcessor())
                // url??????????????????
                .accessDecisionManager(accessDecisionManager())
                .anyRequest().authenticated();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        // ?????????????????????????????????????????????????????????URL??????????????????
        web.ignoring().antMatchers("/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs/**","/test/**");
    }

    /**
     * AffirmativeBased ??? ????????????AccessDecisionVoter???????????????????????????
     * ConsensusBased ??? ?????????????????????????????????????????????????????????????????????
     * UnanimousBased ??? ???????????????????????????????????????????????????
     * ????????????????????????
     *
     * @Author: Davy
     * @Date: 2021/9/28 17:08
    **/
    private AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<?>> decisionVoters = Lists.newArrayList(new WebExpressionVoter(), new AuthenticatedVoter(), new RoleVoter());

        // ??????????????????
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
        //????????????UserDetailsService????????????
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
     * ???????????????Mysql???????????????????????????????????????
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
