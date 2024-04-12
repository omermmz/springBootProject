package com.example.demo.config;

import lombok.AllArgsConstructor;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.web.servlet.HttpSecurityDsl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.servlet.config.annotation.CorsRegistry;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity webSecurity) throws Exception{
        webSecurity.ignoring().antMatchers(HttpMethod.POST,"/api/companyuser");
        webSecurity.ignoring().antMatchers(HttpMethod.POST,"/api/city");
        webSecurity.ignoring().antMatchers(HttpMethod.GET,"/api/city/**");
        webSecurity.ignoring().antMatchers(HttpMethod.POST,"/api/place/**");
        webSecurity.ignoring().antMatchers(HttpMethod.GET,"/api/place");
        webSecurity.ignoring().antMatchers(HttpMethod.GET,"/api/place/**");
        webSecurity.ignoring().antMatchers(HttpMethod.GET,"/api/place/getByCity/**");
        webSecurity.ignoring().antMatchers(HttpMethod.POST,"/api/reservation/getemptytime");
        webSecurity.ignoring().antMatchers(HttpMethod.GET,"/api/reservation/timeicindeneme");
        webSecurity.ignoring().antMatchers(HttpMethod.POST,"/api/place");
        webSecurity.ignoring().antMatchers(HttpMethod.POST,"/api/reservation");
        webSecurity.ignoring().antMatchers(HttpMethod.GET,"/api/reservation/myReservations/**");
        webSecurity.ignoring().antMatchers(HttpMethod.DELETE,"/api/reservation/**");
        webSecurity.ignoring().antMatchers(HttpMethod.PUT,"/api/reservation/update");
        webSecurity.ignoring().antMatchers(HttpMethod.GET,"/api/companyuser/getAllPlace");
    }
    @Autowired
    @Lazy
    private CustomAuthenticationProvider customAuthenticationProvider;



    @Override
    public void configure(HttpSecurity http) throws Exception {


        http
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .csrf().disable()
                .authorizeRequests()

                //.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                //     .antMatchers("/api/city/**").hasAnyAuthority("USER")
                //      .antMatchers("/api/user/**").permitAll()
                //     .antMatchers("/api/v1/reservationuser").permitAll()
                //     .antMatchers("/api/reservation").hasAnyAuthority("USER")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(new NoPopupBasicAuthenticationEntryPoint());
                //.and()
                //.httpBasic()
                //.and()
            //    .logout()
             //   .clearAuthentication(true)
             //   .logoutRequestMatcher(new AntPathRequestMatcher("/api/user/logout"))
           //   .deleteCookies("JSESSIONID")
           //     .and()
            //    .exceptionHandling()
            //    .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.OK))
             //   .and()
           //     .sessionManagement()
          //      .invalidSessionUrl("/")
           //     .sessionAuthenticationErrorUrl("/")
          //      .and()
               // .cors().disable();


             //  .clearAuthentication(true)
             //  //.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
             //  .logoutSuccessUrl("/").deleteCookies("JSESSIONID", "SESSION", "SESSIONID")
             //  .invalidateHttpSession(true)
             //  .and()
             //  .exceptionHandling()
             //  .and()
             //  .sessionManagement()
             //  .invalidSessionUrl("/")
             //  .sessionAuthenticationErrorUrl("/")
             //  .maximumSessions(2);
                //.sessionRegistry(sessionRegistry());



        //  http
      //          .cors().disable()
      //          //.and()
      //          .csrf().disable()
//
//
      //          .authorizeRequests()
      //          //.antMatchers("/api/companyuser/**").permitAll()
      //          .antMatchers("/api/city/**").hasAnyAuthority("COMPANYUSER")
      //        //  .antMatchers("/api/reservationuser").hasAnyAuthority("ADMIN")
      //         // .antMatchers("/api/userdeneme")
      //          .antMatchers("/api/v1/reservationuser/**").hasAnyAuthority("COMPANYUSER")
      //          .antMatchers("/api/v1/adminpage").hasAnyAuthority("ADMIN")
      //          .anyRequest()
      //           .authenticated()
      //          .and().httpBasic().authenticationEntryPoint(new NoPopupBasicAuthenticationEntryPoint())
      //          .and()
      //          .formLogin().disable();

        //.httpBasic()
        //                .and()
        //                .authorizeRequests()
        //                    .antMatchers(HttpMethod.GET,"/v1/customer/**").hasRole("USER")
        //                    .antMatchers(HttpMethod.POST,"/v1/account/**").hasRole("ADMIN")
        //                .and()
        //                .csrf().disable()
        //                .formLogin().disable();
    }




  @Override
  protected void configure(AuthenticationManagerBuilder auth)  {
      auth.authenticationProvider(customAuthenticationProvider);
  }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }





    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3001"));
        //configuration.setAllowedOriginPatterns(Collections.singletonList("http://localhost:3000"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization","DNT","X-CustomHeader","Keep-Alive","User-Agent","X-Requested-With","If-Modified-Since","Cache-Control","Content-Type","Content-Range","Range","Access-Control-Allow-Origin","Access-Control-Allow-Methods","Access-Control-Allow-Headers","Access-Control-Allow-Credentials"));
        configuration.setAllowedMethods(Arrays.asList("DELETE", "GET", "POST", "PATCH", "PUT","OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;

    }




}
