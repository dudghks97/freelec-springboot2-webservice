package com.jojoldu.book.springboot.config.auth.dto;

import com.jojoldu.book.springboot.config.auth.CustomOAuth2UserService;
import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity  // SpringSecurity 설정들을 활성화 시켜줌
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // h2-console 화면 사용을 위해 해당 옵션들을 disable 함
        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                // URL 별 권한 관리를 설정하는 옵션의 시작점
                // authorizeRequests 가 선언되어야만 antMathers 옵션 사용이 가능함
                .authorizeRequests()
                // 권한 관리 대상을 지정하는 옵션.
                // URL HTTP 메소드 별로 관리가 가능함
                .antMatchers("/","/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()   // 전체 열람 권한 부여
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())    // 명시된 주소를 가진 API 는 USER 권한을 가진 사람만 권한 부여
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                .anyRequest().authenticated()   // 설정된 값들 이외 나머지 URL : 모두 인증된 사용자들에게만 허용(로그인한 사용자)
                // 로그아웃 성공시 "/" 주소로 이동함
                .and()
                .logout().logoutSuccessUrl("/")
                // OAuth2 로그인 성공 이후 사용자 정보 로드 시의 설정들을 담당함
                // 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체 등록
                // 리소스 서버(소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시 가능
                .and()
                .oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);
    }
}
