package com.andy.spring.springsecurity.config;


import com.andy.spring.springsecurity.component.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

/**
 * SpringBoot整合Security自定义配置
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService userService;
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        //校验用户
        auth.userDetailsService(userService).passwordEncoder( new PasswordEncoder() {
            //对密码进行加密
            @Override
            public String encode(CharSequence charSequence) {
                System.out.println(charSequence.toString());
                String encode = DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
                System.out.println("encode->"+ encode);
                return encode;
            }
            //对密码进行判断匹配
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                String encode = DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
                boolean res = s.equals( encode );
                return res;
            }
        } );

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //定制请求的授权规则
        http.authorizeRequests()
                .antMatchers("/","/index","/login","/login-error","/401","/css/**","/js/**").permitAll()
                .anyRequest().authenticated();

        //开启自动配置的登入功能
        http.formLogin()//  定义当需要用户登录时候，转到的登录页面
                .loginPage("/login").failureUrl( "/login-error" )
                .and()
                .exceptionHandling().accessDeniedPage( "/401" );

        //开启自动配置的注销功能,访问/logout表示用户注销，清空session，注销以后默认又返回登入页
        http.logout()
                .logoutSuccessUrl("/")//默认注销后的重定向的URL
                .invalidateHttpSession(true) // 注销时session是否同时无效 默认true是
                .deleteCookies();//允许注销成功时指定要删除的cookie的名称
        //启用rememberMe功能，将用户信息保存在cookie中
        http.rememberMe();

    }

}
