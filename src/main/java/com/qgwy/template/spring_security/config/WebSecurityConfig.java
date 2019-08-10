package com.qgwy.template.spring_security.config;

import com.qgwy.template.spring_security.service.CustomUserDetailsService;
import com.qgwy.template.util.DynamicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.http.HttpServletRequest;

/**
 * ========================
 * Created with IntelliJ IDEA.
 * Date：2019/7/23 11:10
 * Version: v1.0
 * ========================
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //系统默认提供的密码校验-只含用户名和密码
        //auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
        //自定义认证信息校验器-包括用户名、密码、验证码
        auth.authenticationProvider(customAuthenticationProvider);
    }


    //自定义认证信息处理
    @Autowired
    private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;

    @Value("${loginCode.prefix}")
    private String prefix;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 如果有允许匿名的url，填在下面
                .antMatchers("/security-test/vCode","/static/**","/redis-test/**").permitAll()
                .anyRequest().authenticated()
                // 拒绝访问错误处理
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .and()
                // 设置登陆页
                .formLogin().loginPage("/security-test/login")
                // 设置登陆成功页
                .defaultSuccessUrl("/security-test/").permitAll()
                // 自定义登陆用户名和密码参数，默认为username和password
//                .usernameParameter("username")
//                .passwordParameter("password")
                //添加登录失败跳转页
                .failureForwardUrl("/security-test/login/error")
                .authenticationDetailsSource(authenticationDetailsSource)
                .and()
                // 添加图片验证码过滤器
                //.addFilterBefore(new VerifyFilter(prefix), UsernamePasswordAuthenticationFilter.class)
                .logout().permitAll()
                //自动登录
                .and().rememberMe()
                .tokenRepository(persistentTokenRepository())
                //有效时间，单位: s
                .tokenValiditySeconds(60)
                .userDetailsService(userDetailsService);



        // 关闭CSRF跨域
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().antMatchers("/**/*.js", "/lang/*.json", "/**/*.css", "/**/*.map", "/**/*.html",
                "/**/*.png");

    }

    @Autowired
    private DynamicDataSource dataSource;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // 如果token表不存在，使用下面可以自动初始化表结构，如果已经存在，请注释掉，否则会报错
        // tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        // Remove the ROLE_ prefix
        return new GrantedAuthorityDefaults("");
    }

    // 拒绝访问处理器
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }
}

