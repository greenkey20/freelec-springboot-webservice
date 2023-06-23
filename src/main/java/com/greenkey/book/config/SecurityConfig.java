package com.greenkey.book.config;

import com.greenkey.book.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// 2023.6.4(일) 15h45
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // 2023.6.4(일) 18h20
//    @Autowired
    MemberService memberService;
    // 2023.6.23(금) 22h25 순환참조 문제의 임시 해결을 위해 @Lazy 어노테이션을 통한 지연로딩 vs 가장 이상적인건 스프링 빈들의 관계를 재설계해서 문제를 해결
    public SecurityConfig(@Lazy MemberService memberService) {
        this.memberService = memberService;
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/members/login")
                .defaultSuccessUrl("/")
                .usernameParameter("email")
                .failureUrl("/members/login/error")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                .logoutSuccessUrl("/");

        // 2023.6.4(일) 18h35
        http.authorizeHttpRequests()
                .requestMatchers("/", "/members/**", "/items/**", "/images/**", "/posts/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated();

        // 인증되지 않은 사용자가 리소스에 접근했을 때 수행되는 핸들러 등록
        http.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());
    }

    // 2023.2월 main project 설정 파일 참고
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2023.6.4(일) 18h30
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService)
                .passwordEncoder(passwordEncoder());
    }

    // 2023.6.4(일) 18h40
    // static 디렉터리의 하위 파일은 인증 무시하도록 설정
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers("/css/**", "/js/**", "/img/**");
    }
}
