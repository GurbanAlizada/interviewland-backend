package com.example.interviewlandbackend.config;



import com.example.interviewlandbackend.security.JWTAccessDeniedHandler;
import com.example.interviewlandbackend.security.JwtAuthenticationEntryPoint;
import com.example.interviewlandbackend.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {



    private final JwtFilter jwtFilter;
    private final JWTAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public SecurityConfig(JwtFilter jwtFilter,
                          JWTAccessDeniedHandler jwtAccessDeniedHandler,
                          JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.jwtFilter = jwtFilter;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }



    // hansiki permitAll ve admin huquqlari
    // varsa qeyd et zaten qalanlari isAuthenticated olur

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .cors()
                .and()
                .authorizeRequests((auth)->{   // will refactor
                    auth.antMatchers( HttpMethod.POST ,"/v1/auth/**" , "/v1/subscribe").permitAll();
                    auth.antMatchers( HttpMethod.POST ,"/v1/users/admin-register").hasAuthority("SUPER_ADMIN");
                    auth.antMatchers( HttpMethod.GET , "/v1/contents").permitAll();
                    auth.antMatchers(  "/v1/contents" , "/v1/contents/delete/{id}" ).hasAnyAuthority("ADMIN" , "SUPER_ADMIN");
                    auth.antMatchers( HttpMethod.GET , "/v1/sections").permitAll();
                    auth.antMatchers(  "/v1/sections" ,"/v1/sections/delete/{id}" ).hasAnyAuthority("ADMIN","SUPER_ADMIN");
                    auth.antMatchers( HttpMethod.GET , "/v1/questions").permitAll();
                    auth.antMatchers( "/v1/questions" , "/v1/questions/delete/{id}").hasAnyAuthority("ADMIN" , "SUPER_ADMIN");
                    auth.antMatchers( HttpMethod.POST,"/v1/subscribe/sendEmail/{id}" ).hasAnyAuthority("ADMIN" , "SUPER_ADMIN");
                    auth.anyRequest().authenticated();
                })
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling()
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(jwtFilter , UsernamePasswordAuthenticationFilter.class)
                .build();
    }



    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers(
                "/api/auth/login",
                "/api/user/register" ,
                "/api/auth/resetPassword",
                "/api/auth/logout",
                "/api/auth/forgotPassword",
                "/v2/api-docs/**" ,
                "/swagger.json" ,
                "/swagger-ui.html",
                "/swagger-resources/**" ,
                "/webjars/**");
    }



    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*");
            }
        };
    }




    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }



}
