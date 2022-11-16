package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
     @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);

        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/","/user/**","/image/**","/subscribe/**","/comment/**")
                .authenticated()
                .anyRequest()
                .permitAll()
                .and()  //그리고
                .formLogin() //로그인 인증이 필요한 요청이 들어오면
                .loginPage("/auth/signin") //로그인 페이지로 이동하고
                .loginProcessingUrl("/auth/signin") //로그인페이지 auth/signin이라는 Post요청을 실행시킨다.
                .defaultSuccessUrl("/"); //인증이 정상적으로 완료되면 / 로 이동한다.

    }
}
