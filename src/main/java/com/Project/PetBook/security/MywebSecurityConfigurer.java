
package com.Project.PetBook.security;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import com.Project.PetBook.Services.MyUserService;

@EnableWebSecurity
public class MywebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserService myUserServiceInterface;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authorivider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/oauth2/**").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER")
                .antMatchers("/vet").hasAnyRole("VET")
                .antMatchers("/").permitAll()
                .antMatchers("/home").permitAll()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .successForwardUrl("/home")
                    .failureUrl("/login-error")
                    .permitAll()
                .and()
                .logout()
                .permitAll();

    }

    @Bean
    public DaoAuthenticationProvider authorivider() {
        DaoAuthenticationProvider daoauth = new DaoAuthenticationProvider();
        daoauth.setUserDetailsService(myUserServiceInterface);
        daoauth.setPasswordEncoder(myencoder());
        return daoauth;
    }

    @Bean
    public PasswordEncoder myencoder() {

        return new BCryptPasswordEncoder();
    }
    
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(""));
        configuration.setAllowedMethods(Arrays.asList(""));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
